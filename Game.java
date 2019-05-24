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
import java.util.ArrayList;
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
    
    private boolean collided;
    
    private long delta;//Dauer eines Durchlaufs
    private long last;//Die Zeit vom Anfang eines Durchlaufs
    private long fps;//Anzahl Bilder pro Sekunde
    
    private boolean canJump;
    private boolean jump;
    private int speed = 200;
    private double fallgeschwindigkeit=1;
    
    private int prevVertSpeed=201;
    private Vector<Sprite> actors;
    private Vector<Sprite> painter;
    private Hintergrund hintergrund;
    private Plattform plattform;
    private Plattform plattform2;
    private Plattform plattform3;
    private int plattformNr;
    private boolean inJump;
    
    private Lebensanzeige lebenspunkt1;
    private Lebensanzeige lebenspunkt2;
    private Lebensanzeige lebenspunkt3;
    private Lebensanzeige lebenspunkt4;
    
    private BufferedImage[] spieler;
    private BufferedImage[] spielerR;
    
    private ArrayList<Plattform> plattforms;
    
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
                spielfeld = new Spielfeld("Huepfburg-2D", 1280, 960, painter, actors);
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
        prevVertSpeed=copter.logic(prevVertSpeed, delta);
        if((keyManager.jump||keyManager.jumpG) && (canJump==true || prevVertSpeed==0))
        {
            copter.setY(copter.getY()-3);
            inJump=true;
            copter.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed=prevVertSpeed+5;
            canJump=false;
            collided=false;
        }
        
        if(collided==true)
        {
            canJump=true;
            inJump=false;
            prevVertSpeed=0;
            copter.setVerticalSpeed(0);
            copter.setY(plattforms.get(plattformNr).getY()-30);
        }
        
        if(prevVertSpeed>4)
        {
            canJump=false;
            copter.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
        }
        
        if(collided==false&&inJump==false)
        {
            copter.setVerticalSpeed(fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
        }
        
        if(keyManager.left||keyManager.leftG) copter.setHorizontalSpeed(-speed);
        if(keyManager.right||keyManager.rightG) copter.setHorizontalSpeed(speed);
        if((!keyManager.left&&!keyManager.leftG) && (!keyManager.right&&!keyManager.rightG)) copter.setHorizontalSpeed(0);
        
        
    }
    
    /**
     * Diese Methode übernimmt die Ausführung von Logik-Operationen
     */
    private void doLogic()
    {
        collided=false;
        for(int i=0;i<plattforms.size()&&collided==false;i++)
        {
            Sprite s = plattforms.get(i);
            collided=copter.collidedWith(s);
            plattformNr = i;
        }
        
        for(ListIterator<Sprite> it=actors.listIterator();it.hasNext();)
        {
            Sprite r = it.next();
            r.doLogic(delta);
        }
        
        if(copter.getHorizontalSpeed()<0)
        {
            copter.setImage(spieler);
        }
        else if(copter.getHorizontalSpeed()>0)
        {
            copter.setImage(spielerR);
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
        
        spieler = loadPics("src/pics/sheeet4.gif",4);
        spielerR = loadPics("src/pics/sheeet4_rechts.gif",4);
        BufferedImage[] hintergrund_image = loadPics("src/pics/gelb3.png",1);
        BufferedImage[] plattform_image = loadPics("src/pics/plattform2.png",1);
        BufferedImage[] lebenspunkt_image = loadPics("src/pics/plattform2.png",1);
        
        
        actors = new Vector<Sprite>();
        plattforms = new ArrayList<Plattform>();
        painter = new Vector<Sprite>();
        
        copter = new Spieler(spieler,500,100,100, keyManager);
        
        hintergrund = new Hintergrund(hintergrund_image,0,0,100);
        plattform2 = new Plattform(plattform_image,600,400,100);
        plattform = new Plattform(plattform_image,200,400,100);
        plattform3 = new Plattform(plattform_image,0,400,100);
        lebenspunkt1=new Lebensanzeige(lebenspunkt_image,0,950,100);
        lebenspunkt2=new Lebensanzeige(lebenspunkt_image,20,950,100);
        lebenspunkt3=new Lebensanzeige(lebenspunkt_image,40,950,100);
        lebenspunkt4=new Lebensanzeige(lebenspunkt_image,60,950,100);

        actors.add(hintergrund);
        actors.add(copter);
        actors.add(plattform3);
        actors.add(plattform);
        actors.add(plattform2);
        
        plattforms.add(plattform);
        plattforms.add(plattform2);
        plattforms.add(plattform3);
    }
    
    // private void setKamera()
    // {
        // int positionX = copter.getX();
        // int positionY = copter.getY();
    // }
}
