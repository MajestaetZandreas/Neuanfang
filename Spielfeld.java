// import java.awt.Canvas;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.util.ListIterator;
import java.lang.Math;

//...
/**
 * Die Klasse Spielfeld erstellt das Fenster, in welchem das Spiel angezeigt wird
 * Author(Clemens Zander, Jupp Bruns, Gideon Schafroth)
 * Version(10.5.19)
 */
public class Spielfeld extends JPanel implements Runnable, KeyListener
{
    private JFrame frame;
    // private Canvas canvas;
    private String titel;
    private int breite;
    private int hoehe;
    private long delta;//Dauer eines Durchlaufs
    private long last;//Die Zeit vom Anfang eines Durchlaufs
    private long fps;//Anzahl Bilder pro Sekunde
    
    private boolean canJump;
    private boolean jump;
    private boolean fall;
    private boolean left;
    private boolean right;
    private int speed = 50;
    private double fallgeschwindigkeit=1;
    
    private int prevVertSpeed;
    
    private Spieler copter;
    private Hintergrund hinterGrund;
    private Plattform plattform;
    private Vector<Sprite> actors;
    private Vector<Sprite> painter;
    
    
    /**
    * Konstruktor der Klasse Spielfeld
    * @param title Titel der über dem Fenster angezeigt wird
    * @param width die Breite des Fensters
    * @param height die Höhe des Fensters
    */
    public Spielfeld(String title, int width, int height)
    {
        titel = title;
        breite = width;
        hoehe = height;
        //Spielfeld wird erstellt
        this.setPreferredSize(new Dimension(breite, hoehe));
        frame = new JFrame(titel);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addKeyListener(this);
        
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        
        doInitialisierung();
        Thread thread = new Thread(this);
        thread.start();
    }
    /*#----------------------------------------------------------Get- und Set-Methoden-------------------------------*/
    public void setPrevVertSpeed(int newSpeed)
    {
        prevVertSpeed=newSpeed;
    }
    
    /*#----------------------------------------------------------Public-Methoden------------------------------------------------------*/
    /**
     * Überschreibung der Methode run() aus der Klasse Runnable
     */
    @Override
    public void run()
    {
        while(frame.isVisible())//solange das Fenster angezeigt wird
        {
            computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
            
            
            
            checkKeys();
            doLogic();
            moveObjects();
            cloneVectors();
            
            
            repaint();//Neuzeichnung wird ausgelöst
            try
            {
                Thread.sleep(10);//Programm wartet ("schläft")
            }
            catch(InterruptedException e)//sonst macht er nichts
            {
            }
        }
    }
    
    /**
     * Überschreibt die Methode paintComponent() aus der Klasse Runnable
     * 
     * @param graphics Referenz auf Zeichenfläche des JPanels 
     */
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponents(graphics);
        
        
        if(painter!=null)
        {
            for(ListIterator<Sprite> it=actors.listIterator();it.hasNext();)
            {
                Sprite r = it.next();
                r.drawObjects(graphics);
            }
        }
    }
    
    /**
     * Diese Methode sorgt dafür, dass wenn eine Taste gedrückt wird, eine 
     * entsprechende Reaktion erfolgt
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_W && copter.getInAir()==false)
        {
            jump=true;
            canJump=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_A)
        {
            left=true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_D)
        {
            right=true;
        }
    }
    
    /**
     * Diese Methode sorgt dafür, dass wenn eine Taste losgelassen wird, eine 
     * entsprechende Reaktion erfolgt
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        // if(e.getKeyCode()==KeyEvent.VK_W)
        // {
            // jump=false;
        // }
        
        if(e.getKeyCode()==KeyEvent.VK_A)
        {
            left=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_D)
        {
            right=false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    /*#--------------------------------------------Private-Methoden-------------------------------------------------*/
    /**
     * Diese Methode übernimmt Abfrage von Tastatureingaben und die jeweiligen Änderungen
     */
    private void checkKeys()
    {
        
        if(jump)
        {
            copter.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
            copter.setInAir(true);
            // if(prevVertSpeed>=20)
            // {
                // jump=false;
            // }
        }
        if(left) copter.setHorizontalSpeed(-speed);
        if(right) copter.setHorizontalSpeed(speed);
        
        if(!left&&!right) copter.setHorizontalSpeed(0);
        // if(!jump) copter.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
        if(copter.getInAir()==false)
        {
            prevVertSpeed=0;
            copter.setVerticalSpeed(0);
            canJump=true;
            jump=false;
        }
    }
    
    /**
     * Diese Methode übernimmt die Ausführung von Logik-Operationen
     */
    private void doLogic()
    {
        for(ListIterator<Sprite> it=actors.listIterator();it.hasNext();)
        {
            Sprite r = it.next();
            r.doLogic(delta);
        }
        
        for(int i=0;i<actors.size();i++)
        {
            for(int n=i+1;n<actors.size();n++)
            {
                Sprite s1= actors.elementAt(i);
                Sprite s2= actors.elementAt(n);
                s1.collidedWith(s2);
            }
        }
    }
    
    /**
     * Diese Methode übernimmt das bewegen von Objekten
     */
    private void moveObjects()
    {
        for(ListIterator<Sprite> it=actors.listIterator();it.hasNext();)
        {
            Sprite r = it.next();
            r.move(delta);
        }
    }
    
    /**
     * Diese Methode lädt die Bilder aus dem pics-Ordner
     * 
     * @param path - der Speicherort der Bilder
     *        pics - die Anzahl Bilder im Ordner
     */
    private BufferedImage[] loadPics(String path, int pics)
    {
        BufferedImage[] anim = new BufferedImage[pics];
        BufferedImage source=null;
        
        URL pic_url=getClass().getClassLoader().getResource(path);
        
        try
        {
            source=ImageIO.read(pic_url);
        }
        catch(IOException e)
        {
        }
        
        for(int i=0;i<pics;i++)
        {
            anim[i]=source.getSubimage(i*source.getWidth()/pics, 0, source.getWidth()/pics, source.getHeight());
        }
        
        return anim;
    }
    
    /**
     * Errechnung der Zeit für einen Schleifendurchlauf
     */
    private void computeDelta()
    {
        delta = System.nanoTime() - last;
        last = System.nanoTime();
        fps = ((long) 1e9)/delta;//10^9 dividiert durch die Dauer des Durchlaufs um nicht so viele Nachkommastellen zu haben
    }
    
    /**
     * Diese Methode übernimmt das Laden von Grafiken
     */
    private void doInitialisierung()
    {
        last=System.nanoTime();
        
        BufferedImage[] spieler = loadPics("src/pics/heli.gif",4);
        BufferedImage[] hintergrund = loadPics("src/pics/gelb3.jpg",1);
        BufferedImage[] plattForm = loadPics("src/pics/image-2019-05-17.png",1);
        
        actors = new Vector<Sprite>();
        copter = new Spieler(spieler,400,300,100);
        plattform = new Plattform(plattForm,500,400,100);
        hinterGrund = new Hintergrund(hintergrund,0,0,100);
        painter = new Vector<Sprite>();
       
        actors.add(hinterGrund);
        actors.add(copter);
        actors.add(plattform);
    }
    
    @SuppressWarnings("unchecked")
    private void cloneVectors()
    {
        for(int i=0; i<actors.size();i++)
        {
            painter.add(actors.elementAt(i));
        }
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}

