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


/**
 * Die Hauptklasse des Spiels, von der alles "gesteuert" wird.
 *  
 * @author (Clemens Zander, Shium Rahman) 
 * @version (22.05.2019)
 */
public class Game implements Runnable
{
    private static Hauptmenue hauptmenue;
    private Kamera camera;
    private Spielfeld spielfeld;
    public boolean spielStart=true;
    private JFrame frame;
    private Spieler copter;
    private KeyManager keyManager;
    
    private long delta;//Dauer eines Durchlaufs
    private long last;//Die Zeit vom Anfang eines Durchlaufs
    private long fps;//Anzahl Bilder pro Sekunde
    
    private boolean canJump;
    private boolean jump;
    private int speed = 50;
    private double fallgeschwindigkeit=1;
    
    private int prevVertSpeed;
    private Vector<Sprite> actors;
    private Vector<Sprite> painter;
    private Hintergrund hinterGrund;
    private Plattform plattform;
    private Plattform plattform2;

    public static void main(String[] args)
    {
        Game game = new Game();
    }
    
    public Game()
    {
        hauptmenue = new Hauptmenue("Huepfburg-2D");
        keyManager = new KeyManager();
        camera = new Kamera(0,0);
        
        
        doInitialisierung();
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void setPrevVertSpeed(int newSpeed)
    {
        prevVertSpeed=newSpeed;
    }
    
    /**
     * Überschreibung der Methode run() aus der Klasse Runnable
     */
    @Override
    public void run()
    {
        while(spielStart)
        {
            if(hauptmenue.getIstSpielGestartet())
            {
                spielfeld = new Spielfeld("Huepfburg-2D", 800, 600, painter, actors);
                spielfeld.getFrame().addKeyListener(keyManager);
                    while(spielfeld.getFrame().isVisible())//solange das Fenster angezeigt wird
                {
                    computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
                    
                    
                    checkKeys();
                    doLogic();
                    moveObjects();
                    cloneVectors();
                    // setKamera();
                    
                    
                    spielfeld.repaint();//Neuzeichnung wird ausgelöst
                    try
                    {
                        Thread.sleep(10);//Programm wartet ("schläft")
                    }
                    catch(InterruptedException e)//sonst macht er nichts
                    {
                    }
                }
            }
        
            if(hauptmenue.getIstHauptmenueBeended())
            {
                spielStart=false;
            }
        }
    }
    
    /**
     * Diese Methode übernimmt Abfrage von Tastatureingaben und die jeweiligen Änderungen
     */
    private void checkKeys()
    {
        keyManager.update();
        if((keyManager.jump || copter.getInAir()==1) /*|| (copter.getInAir()==false && copter.getX()==400 && copter.getY()==300 && keyManager.jump) || 
        (copter.getX()>=plattform.getX() && copter.getX()<=(plattform.getX()+20)) && ((copter.getY())==plattform.getY() && keyManager.jump)*/)
        {
            copter.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
            copter.setInAir(1);
            // if(prevVertSpeed>=20)
            // {
                // jump=false;
            // }
        }
        if(keyManager.left) copter.setHorizontalSpeed(-speed);
        if(keyManager.right) copter.setHorizontalSpeed(speed);
        
        if(!keyManager.left && !keyManager.right) copter.setHorizontalSpeed(0);
        //if(!jump) copter.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
        if(copter.getInAir()==0)
        {
            prevVertSpeed=0;
            copter.setVerticalSpeed(0);
            if(keyManager.jump)copter.setInAir(1);
            // jump=false;
        }
        
        if(copter.getInAir()==2)
        {
             copter.setVerticalSpeed(fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
            copter.setInAir(1);
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
    
    
    
    @SuppressWarnings("unchecked")
    private void cloneVectors()
    {
        for(int i=0; i<actors.size();i++)
        {
            painter.add(actors.elementAt(i));
        }
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
        copter = new Spieler(spieler,400,300,100, keyManager);
        plattform = new Plattform(plattForm,500,400,100);
        plattform2 = new Plattform(plattForm,480,400,100);
        hinterGrund = new Hintergrund(hintergrund,0,0,100);
        painter = new Vector<Sprite>();
       
        actors.add(hinterGrund);
        actors.add(copter);
        actors.add(plattform);
        actors.add(plattform2);
    }
    
    // private void setKamera()
    // {
        // int positionX = copter.getX();
        // int positionY = copter.getY();
    // }
}
