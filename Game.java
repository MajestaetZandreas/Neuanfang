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
import java.util.Random;


/**
* Die Hauptklasse des Spiels, von der alles "gesteuert" wird.
*  
* @author (Clemens Zander, Shium Rahman) 
* @version (22.05.2019)
*/
public class Game implements Runnable
{
    private static Hauptmenue hauptmenue;
    private Spielfeld spielfeld;
    private Spielanleitung spielanleitung;
    
    private boolean spielStart=true;
    private boolean victory=false;
    
    private boolean reload;
    private int reloadTime;
    
    private boolean safe;
    private int safeTime;
    
    private JFrame frame;
    
    private Spieler copter;
    
    private KeyManager keyManager;
    
    private boolean collided;
    
    private int rndG;
    private int rndP;
    private long delta;//Dauer eines Durchlaufs
    private long last;//Die Zeit vom Anfang eines Durchlaufs
    private long fps;//Anzahl Bilder pro Sekunde
    
    private boolean canJump;
    private boolean jump;
    private int speed = 200;
    private double fallgeschwindigkeit=1;
    private boolean inJump;
    private int prevVertSpeed=201;
    
    private Vector<Sprite> actors;
    private Vector<Sprite> painter;
    private Hintergrund hintergrund;
    private Plattform plattform;
    private Plattform plattform2;
    private Plattform plattform3;
    private int plattformNr;
    
    private Kreaturen drache;
    
    private Lebensanzeige lebenspunkte;
    private Lebensanzeige lebenspunkteG;
    
    private Waffe kugel;
    
    private BufferedImage[] spieler;
    private BufferedImage[] spielerR;
    private BufferedImage[] herz1;
    private BufferedImage[] herz2;
    private BufferedImage[] herz3;
    private BufferedImage[] energieKugel;
    
    private ArrayList<Plattform> plattforms;
    private ArrayList<Kreaturen> gegner;
    
    public static void main(String[] args)
    {
        Game game = new Game();
    }
    
    public Game()
    {
        hauptmenue = new Hauptmenue();
        keyManager = new KeyManager();
        
        doInitialisierung();
        Thread thread = new Thread(this);
        thread.start();
        
        Random rand=new Random();
        rndG=rand.nextInt(1);
        rndP=rand.nextInt(3);
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
            if(hauptmenue.getIstSpielanleitungGedrueckt())
            {
                spielanleitung = new Spielanleitung();
                hauptmenue.setIstSpielanleitungGedrueckt(false);
            }
            
            if(hauptmenue.getIstSpielstartGedrueckt())
            {
                spielfeld = new Spielfeld(1280, 960, painter, actors);
                spielfeld.getFrame().addKeyListener(keyManager);
                while(spielfeld.getFrame().isVisible())//solange das Fenster angezeigt wird
                {
                    computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
                    
                    
                    checkKeys();
                    doLogic();
                    
                    moveObjects();
                    cloneVectors();
                    
                    reloadTime++;
                    if(reloadTime >= 100)
                    {
                        reload=false;
                        reloadTime=0;
                    }
                    
                    safeTime++;
                    if(safeTime >= 100)
                    {
                        safe=false;
                        safeTime=0;
                    }
                    
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
        
            if(hauptmenue.getIstBeendenGedrueckt())
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
        
        if(keyManager.fire&&reload==false)
        {
            kugel=new Waffe(energieKugel,copter.getX(),copter.getY()+10,100);
            actors.add(kugel);
            if(copter.getImage()==spielerR)
            kugel.setHorizontalSpeed(300);
            else
            kugel.setHorizontalSpeed(-300);
            if(gegner.get(rndG).collidedWith(kugel))
            {
                gegner.get(rndG).reduceHP();
                actors.remove(kugel);
            }   
            
            
            if(kugel.getX()<=0 || kugel.getX()>=1280)
            {
                actors.remove(kugel);
            }
            reload=true;
        }
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
        
        for(int i=0;i<gegner.size();i++)
        { 
            Sprite s = gegner.get(i);
            if(copter.collidedWith(s)&&!safe)
            {
                copter.reduceHP();
                safe=true;
            }
        }
        
        if(copter.getHorizontalSpeed()<0)
        {
            copter.setImage(spieler);
        }
        else if(copter.getHorizontalSpeed()>0)
        {
            copter.setImage(spielerR);
        }
        
        if(copter.getHP()==3)
        {
            lebenspunkte.setImage(herz3);
        }
        else if(copter.getHP()==2)
        {
            lebenspunkte.setImage(herz2);
        }
        else if(copter.getHP()==1)
        {
            lebenspunkte.setImage(herz1);
        }
        else
        {
            spielfeld.getFrame().setVisible(false);
            victory=false;
            spielStart=false;
        }
        
        if(gegner.get(rndG).getHP()==3)
        {
            lebenspunkteG.setImage(herz3);
        }
        else if(gegner.get(rndG).getHP()==2)
        {
            lebenspunkteG.setImage(herz2);
        }
        else if(gegner.get(rndG).getHP()==1)
        {
            lebenspunkteG.setImage(herz1);
        }
        else
        {
            spielfeld.getFrame().setVisible(false);
            victory=true;
            spielStart=false;
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
        herz3 = loadPics("src/pics/herz3voll.png",1);
        herz2 = loadPics("src/pics/herz2voll.png",1);
        herz1 = loadPics("src/pics/herz1voll.png",1);
        BufferedImage[] gegnerDrache = loadPics("src/pics/Enemy_ghost.gif",4);
        energieKugel = loadPics("src/pics/Energiekugel.png", 1);
        
        
        actors = new Vector<Sprite>();
        plattforms = new ArrayList<Plattform>();
        painter = new Vector<Sprite>();
        gegner = new ArrayList<Kreaturen>();
        copter = new Spieler(spieler,0,100,100, keyManager);
        
        hintergrund = new Hintergrund(hintergrund_image,0,0,100);
        
        plattform = new Plattform(plattform_image,0,400,100);
        plattform2 = new Plattform(plattform_image,200,400,100);
        plattform3 = new Plattform(plattform_image,600,400,100);
        
        drache=new Kreaturen(gegnerDrache,600,370,100);
        
        
        gegner.add(drache);
        
        lebenspunkte=new Lebensanzeige(herz3,0,10,100);
        lebenspunkteG=new Lebensanzeige(herz3,gegner.get(rndG).getX(),gegner.get(rndG).getY()-25,100);
        
        
        actors.add(hintergrund);
        actors.add(copter);
        actors.add(plattform3);
        actors.add(plattform);
        actors.add(plattform2);
        actors.add(gegner.get(rndG));
        actors.add(lebenspunkte);
        actors.add(lebenspunkteG);
        
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
