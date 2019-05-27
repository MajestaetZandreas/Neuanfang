// import java.awt.*;
// import java.awt.event.*;

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
    private Spielanleitung spielanleitung;
    private Endscreen endscreen;
    private Spielfeld spielfeld;
    private Level levels;
    
    private boolean spielStart=true;
    private boolean victory=false;
    
    private boolean reload;
    private int reloadTime;
    
    private boolean safe;
    private int safeTime;
    
    private boolean getroffen;
    private int getroffenTime;
    
    private JFrame frame;
    
    private Spieler player;
    
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
    private int plattformNr;
    
    private Gegner ghost;
    
    private Lebensanzeige lebenspunkte;
    private Lebensanzeige lebenspunkteG;
    
    private Waffe kugel;
    
    private BufferedImage[] spieler_image;
    private BufferedImage[] spielerR_image;
    private BufferedImage[] herz1_image;
    private BufferedImage[] herz2_image;
    private BufferedImage[] herz3_image;
    private BufferedImage[] energieKugel_image;
    
    private ArrayList<Plattform> plattforms;
    private ArrayList<Gegner> gegner;
    private ArrayList<Spikes> spikes;
    
    public static void main(String[] args)
    {
        Game game = new Game();
    }
    
    public Game()
    {
        hauptmenue = new Hauptmenue();
        keyManager = new KeyManager();
        levels= new Level();
        
        
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
            if(hauptmenue.getIstSpielstartGedrueckt()) 
            {
                doInitialisierung();
                spielfeld = new Spielfeld(1280, 960, painter, actors);
                spielfeld.getFrame().addKeyListener(keyManager);
                hauptmenue.setVisible(false);
                while(spielfeld.getFrame().isVisible())//solange das Fenster angezeigt wird
                {
                    computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
                    
                    
                    checkKeys();
                    doLogic();
                    gegner.get(rndG).doLogic(delta);
                    
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
                    
                    getroffenTime++;
                    if(getroffenTime >= 100)
                    {
                        getroffen=false;
                        getroffenTime=0;
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
                hauptmenue.setVisible(true);
                hauptmenue.setIstSpielstartGedrueckt(false);
                spielfeld.setVisible(false);
                spielfeld = null;
                endscreen=new Endscreen(victory);
            }
            
            if(hauptmenue.getIstSpielanleitungGedrueckt())
            {
                spielanleitung = new Spielanleitung();
                while(spielanleitung.getIstZurueckGedrueckt()==false)
                {
                    hauptmenue.setVisible(false);
                }
                hauptmenue.setVisible(true);
                hauptmenue.setIstSpielanleitungGedrueckt(false);
                spielanleitung.setVisible(false);
                spielanleitung = null;
            }
        
            if(hauptmenue.getIstBeendenGedrueckt())
            {
                spielStart=false;
                System.exit(0);
            }
        }
    }
    
    /**
     * Diese Methode übernimmt das Laden von Grafiken
     */
    private void doInitialisierung()
    {
        last=System.nanoTime();
        
        spieler_image = loadPics("src/pics/sheeet4.gif",4);
        spielerR_image = loadPics("src/pics/sheeet4_rechts.gif",4);
        BufferedImage[] hintergrund_image = loadPics("src/pics/gelb3.png",1);
        herz3_image = loadPics("src/pics/herz3voll.png",1);
        herz2_image = loadPics("src/pics/herz2voll.png",1);
        herz1_image = loadPics("src/pics/herz1voll.png",1);
        BufferedImage[] gegnerGhost_image = loadPics("src/pics/Enemy_ghost.gif",4);
        energieKugel_image = loadPics("src/pics/Energiekugel.png",1);
        BufferedImage[] spikes_image= loadPics("src/pics/Spikes.png",1);
        
        
        plattforms = levels.erstelleLevel();
        spikes= new ArrayList<Spikes>();
        
        actors = new Vector<Sprite>();
        painter = new Vector<Sprite>();

        gegner = new ArrayList<Gegner>();
        
        hintergrund = new Hintergrund(hintergrund_image,0,0,100);
        Spikes spike1=new Spikes(spikes_image,321,841,100);
        Spikes spike2=new Spikes(spikes_image,167,381,100);
        Spikes spike3=new Spikes(spikes_image,474,681,100);
        Spikes spike4=new Spikes(spikes_image,616,216,100);
        Spikes spike5=new Spikes(spikes_image,865,577,100);
        Spikes spike6=new Spikes(spikes_image,943,577,100);
        
        player = new Spieler(spieler_image,60,840,60, keyManager);
        ghost=new Gegner(gegnerGhost_image,560,50,60);
        
        kugel=new Waffe(energieKugel_image,player.getX(),player.getY()+10,100);
        
        
        
        spikes.add(spike1);
        spikes.add(spike2);
        spikes.add(spike3);
        spikes.add(spike4);
        spikes.add(spike5);
        spikes.add(spike6);
        
        gegner.add(ghost);
        
        lebenspunkte=new Lebensanzeige(herz3_image,0,10,100);
        lebenspunkteG=new Lebensanzeige(herz3_image,gegner.get(rndG).getX(),gegner.get(rndG).getY()-25,100);
        
        actors.add(0,hintergrund);
        actors.add(player);
        for(int i=0;i<plattforms.size();i++)
        actors.add(plattforms.get(i));
        for(int i=0;i<spikes.size();i++)
        actors.add(spikes.get(i));
        actors.add(gegner.get(rndG));
        actors.add(lebenspunkte);
        actors.add(lebenspunkteG);
        
       
    }
    
    /**
     * Diese Methode übernimmt Abfrage von Tastatureingaben und die jeweiligen Änderungen
     */
    private void checkKeys()
    {
        prevVertSpeed=player.logic(prevVertSpeed, delta);
        keyManager.update();
        if((keyManager.jump||keyManager.jumpG) && (canJump==true || prevVertSpeed==0))
        {
            player.setY(player.getY()-3);
            inJump=true;
            player.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed=prevVertSpeed+5;
            canJump=false;
            collided=false;
        }
        
        if(collided==true)
        {
            canJump=true;
            inJump=false;
            prevVertSpeed=0;
            player.setVerticalSpeed(0);
            player.setY(plattforms.get(plattformNr).getY()-30);
        }
        
        if(prevVertSpeed>4)
        {
            canJump=false;
            player.setVerticalSpeed(-speed+fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
        }
        
        if(collided==false&&inJump==false)
        {
            player.setVerticalSpeed(fallgeschwindigkeit*prevVertSpeed);
            prevVertSpeed++;
        }
        
        if(keyManager.left||keyManager.leftG) 
        {
            player.setHorizontalSpeed(-speed);
        }
        
        if(keyManager.right||keyManager.rightG) 
        {
           player.setHorizontalSpeed(speed);
        }  
    
        if((!keyManager.left&&!keyManager.leftG) && (!keyManager.right&&!keyManager.rightG)) 
        {
            player.setHorizontalSpeed(0);
        }
        
        if(keyManager.fire&&reload==false)
        {
            kugel=new Waffe(energieKugel_image,player.getX(),player.getY()+10,100);
            actors.add(kugel);
            
            if(player.getImage()==spielerR_image)kugel.setHorizontalSpeed(300);
            else kugel.setHorizontalSpeed(-300);  
            
            reload=true;
        }
        
        
    }
    
    
    /**
     * Diese Methode übernimmt die Ausführung von Logik-Operationen
     */
    private void doLogic()
    {
        collided=false;
        
        for(int i=0;i<plattforms.size()&&collided==false;i++)//jupp/Gideon
        { 
            Sprite s = plattforms.get(i);
            collided=player.collidedWith(s);
            plattformNr = i;
        }
        
        for(int i=0;i<gegner.size();i++)//Jupp/Gideon
        { 
            Sprite s = gegner.get(i);
            if(player.collidedWith(s)&&!safe)
            {
                player.reduceHP();
                safe=true;
            }
        }
        
        for(int i=0;i<spikes.size();i++)//Shium/Cihan
        { 
            Sprite s = spikes.get(i);
            if(player.collidedWith(s)&&!safe)
            {
                player.reduceHP();
                safe=true;
            }
        }
        
        if(player.getBeruehrtBoden())//wenn die Spielfigur den Boden beruehrt, hat man das Spiel verloren und es wird beendet
        {
            spielfeld.getFrame().setVisible(false);
            victory=false;
        }
        
        if(player.getHorizontalSpeed()<0)
        {
            player.setImage(spieler_image);
        }
        else if(player.getHorizontalSpeed()>0)
        {
            player.setImage(spielerR_image);
        }
        
        if(player.getHP()==3)
        {
            lebenspunkte.setImage(herz3_image);
        }
        else if(player.getHP()==2)
        {
            lebenspunkte.setImage(herz2_image);
        }
        else if(player.getHP()==1)
        {
            lebenspunkte.setImage(herz1_image);
        }
        else
        {
            spielfeld.getFrame().setVisible(false);
            victory=false;
        }
        
        if(gegner.get(rndG).getHP()==3)
        {
            lebenspunkteG.setImage(herz3_image);
        }
        else if(gegner.get(rndG).getHP()==2)
        {
            lebenspunkteG.setImage(herz2_image);
        }
        else if(gegner.get(rndG).getHP()==1)
        {
            lebenspunkteG.setImage(herz1_image);
        }
        else
        {
            spielfeld.getFrame().setVisible(false);
            victory=true;
        }
        
        if(kugel.getX()<=0 || kugel.getX()>=1280)
        {
            actors.remove(kugel);
        }
        
        if(gegner.get(rndG).collidedWith(kugel)&&getroffen==false)
        {
            gegner.get(rndG).reduceHP();
            getroffen=true;
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
    
    
    

}
