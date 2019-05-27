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
* @author (Jupp Bruns, Clemens Zander, Shium Rahman) 
* @version (27.05.2019)
* 
* Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
*/
public class Game implements Runnable
{
    private static Hauptmenue hauptmenue; //Das Hauptmenü, welches vor dem Spielen geöffnet wird
    private SpielanleitungZiel spielanleitungZiel;//Die Spielanleitung
    private SpielanleitungZiel spielanleitungZiel2;//Die Spielanleitung
    private SpielanleitungTasten spielanleitungTasten;//Die Spielanleitung
    private Endscreen endscreen; //Das Fenster, welches am Ende des Spiels erscheint
    private Spielfeld spielfeld; //Das Fenster in welchem gespielt wird
    private Level levels; //Das Level beschreibt die Anordnung der Plattformen
    
    private boolean spielStart=true; //Dieses Attribut ist solange true, wie das Spiel läuft
    private boolean victory=false; //Dieses Attribut git an, ob der Spieler gewonnen oder verloren hat
    
    private boolean reload; //Dieses Attribut ist true, nachdem der Spieler geschossen hat und verhindert Dauerfeuer
    private int reloadTime=100; //Dieses Attribut wird zur Berechnung der Dauer des Nachladens benötigt
    
    private boolean safe; //Dieses Attribut ist true, nachdem der Spieler Schaden genommen hat und verhindert den sofortigen Tod
    private int safeTime=100; //Dieses Attribut wird zur Berechnung der Dauer der sicheren Zeit des Spielers benötigt
    
    private boolean getroffen; //Dieses Attribut ist true, nachdem der Gegner Schaden genommen hat und verhindert den sofortigen Sied
    private int getroffenTime=100; //Dieses Attribut wird zur Berechnung der Dauer der sicheren Zeit des Gegners benötigt
    
    private JFrame frame;
    
    private Spieler player;
    
    private KeyManager keyManager;
    
    private boolean collided; //Dieses Attribut ist true, wenn zwei Sprites (normalerweise Spieler und Plattform) sich überschneiden oder berühren
    
    private int rndG; //Int-Wert zur Bestimmung, welche Gegner-Grafik benutzt werden soll
    private int rndP; ////Int-Wert zur Bestimmung, auf welche Plattform der Gegner nach erlittenem Schaden teleportiert werded soll
    //^noch nicht in Benutzung
    private long delta; //Dauer eines Durchlaufs
    private long last; //Die Zeit vom Anfang eines Durchlaufs
    private long fps; //Anzahl Bilder pro Sekunde
    
    private boolean canJump; //Dieses Attribut ist false, nachdem gesprungen wurde und verhindert, dass in der Luft gesprungen werden kann
    private int vSpeed = 160 /* 75*/; //Die Geschwindigkeit, mit welcher sich der Spieler im Sprung nach oben bewegt
    private int hSpeed = 100; //Die Geschwindigkeit, mit welcher sich der Spieler seitlich bewegt
    private double fallgeschwindigkeit=1.75; /*0.75 */ //Die Beschleunigung, welche den Fall jeden Loop beschleunigt
    private boolean inJump; //Dieses Attribut ist true, nachdem der Spieler gesprungen ist, und beeinflusst den Fall in der Luft
    private int prevVertSpeed=201; //Die Anzahl an Loops, in denen der Spieler bereits fällt
    
    private Vector<Sprite> actors; 
    private Vector<Sprite> painter;
    private Hintergrund hintergrund;
    
    private int plattformNr; //Dieses Attribut speichert die Nummer der Plattform im Array, mit welcher der Spieler kollidiert
    
    private Gegner ghost;
    
    private Lebensanzeige lebenspunkte; //Die Lebenspunkte des Spielers
    private Lebensanzeige lebenspunkteG; //Die Lenspunkte des Gegners
    
    private Waffe kugel;
    
    //Diese Bilder müssen hier angelegt werden, da sie im Laufe des Spiels geändert werden müssen
    private BufferedImage[] spieler_image;
    private BufferedImage[] spielerR_image;
    private BufferedImage[] herz1_image;
    private BufferedImage[] herz2_image;
    private BufferedImage[] herz3_image;
    private BufferedImage[] herz1_imageSpieler;
    private BufferedImage[] herz2_imageSpieler;
    private BufferedImage[] herz3_imageSpieler;
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
        rndP=rand.nextInt(35);
    }
    
    public void setPrevVertSpeed(int newSpeed)
    {
        prevVertSpeed=newSpeed;
    }
    
    /**
     * @author(Clemens Z., Shium R., Jupp B., Teilweise stark ans Tutorial angelehnt)
     * 
     * Überschreibung der Methode run() aus der Klasse Runnable
     * Diese Methode läuft in Dauerschleife und ruft die Grundfunktionen des Spiels ab
     */
    @Override
    public void run()
    {
        while(spielStart) //eigentlich immer, nur in dieser Schleife abbbrechbar
        {   
            if(hauptmenue.getIstSpielstartGedrueckt()) 
            {
                doInitialisierung(); //Alle Grafiken werden erstellt und intialisiert
                spielfeld = new Spielfeld(1280, 960, painter, actors); //Das Spielfeld wird erstellt
                spielfeld.getFrame().addKeyListener(keyManager);
                hauptmenue.setVisible(false); //Das Hauptmenü wird ausgeblendet
                while(spielfeld.getFrame().isVisible())//solange das Fenster angezeigt wird
                {
                    computeDelta(); //Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
                    
                    
                    checkKeys(); //Die Kommandos des ActionListeners werden ausgeführt(Movement + Angriff)
                    doLogic(); //Kollision aller kolliderenden Sprites wird abgefragt
                    gegner.get(rndG).doLogic(delta); //Die Animation des Gegners wird aufgerufen
                    
                    moveObjects(); //Die Objekte werden bewegt und neu gezeichnet
                    cloneVectors(); //Der actors Vector wird erneut in den painter Vektor geklont
                    if(reload) //wenn an schon geschossen hat
                    reloadTime--; //wird die Zeit in welcher nicht gefeuert werden kann  verringert
                    if(reloadTime <= 0) //wenn sie bei 0 ist
                    {
                        reload=false; //kann man wieder schießen
                        reloadTime=100; //die Nachladezeit wird wieder auf 100 gesetzt
                    }
                    
                    if(safe) //wenn der Spieler schon getroffen wurde
                    safeTime--; //wird die Zeit in der er keinen Schaden mehr erleiden kann verringert
                    if(safeTime <= 0) //wenn sie bei 0 ist
                    {
                        safe=false; //kann man wieder Schaden erleiden
                        safeTime=100; //die sichere Zeit wird wieder auf 100 gesetzt
                    }
                    
                    if(getroffen) //wenn der Gegner schon getroffen wurde
                    getroffenTime--; //wird die Zeit in welcher er keinen Schaden erleiden kann verringert
                    if(getroffenTime <= 0) //wenn sie bei 0 ist
                    {
                        getroffen=false; //kann der Gegner wieder Schaden erleiden
                        getroffenTime=100; //die Zeit in der der gegner sicher ist wird wieder auf 100 gesetzt
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
                hauptmenue.setVisible(true); //das Hauptmenü wird wieder eingeblendet
                hauptmenue.setIstSpielstartGedrueckt(false); //das Spielfeld wird nicht wieder erzeugt
                spielfeld.setVisible(false); //das Spielfeld wird wieder ausgeblendet
                spielfeld = null; //das Spielfeld wird gelöscht
                endscreen=new Endscreen(victory); //ein Ende abhängig vom Ausgang des Spiels wird geöffnet
            }
            
            if(endscreen!=null) //wenn ein Endfenster existiert
            {
                while(endscreen.getIstZurueckGedrueckt()==false) //solange nicht zurück gedrückt wurde
                {
                    hauptmenue.setVisible(false); //bleibt das Hauptmenü ausgeblendet
                }
                hauptmenue.setVisible(true); //danach wird das Hauptmenü wieder eingeblendet
                endscreen.setVisible(false); //das Endfenster wird ausgeblendet
                endscreen = null; //und gelöscht
            }
            
            if(hauptmenue.getIstSpielanleitungGedrueckt()) //wenn man auf den Knopf "Spielanleitung" drückt
            {
                spielanleitungZiel = new SpielanleitungZiel(1); //wird ein neues Spielanleitungsobjekt erzeugt
                while(spielanleitungZiel.getIstZurueckGedrueckt()==false) //solange nicht zurück gedrückt wurde
                {
                    hauptmenue.setVisible(false); //bleibt das Hauptmenü ausgeblendet
                    if(spielanleitungZiel.getIstWeiterGedrueckt())
                    {
                        spielanleitungZiel2 = new SpielanleitungZiel(2);
                        while(spielanleitungZiel2.getIstZurueckGedrueckt()==false)
                        {
                            spielanleitungZiel.setVisible(false);
                            if(spielanleitungZiel2.getIstWeiterGedrueckt())
                            {
                                spielanleitungTasten = new SpielanleitungTasten();
                                while(spielanleitungTasten.getIstZurueckGedrueckt()==false)
                                {
                                    spielanleitungZiel2.setVisible(false);
                                }
                                spielanleitungZiel2.setVisible(true);
                                spielanleitungZiel2.setIstWeiterGedrueckt(false);
                                spielanleitungTasten.setVisible(false);
                                spielanleitungTasten = null;
                            }
                        }
                        spielanleitungZiel.setVisible(true);
                        spielanleitungZiel.setIstWeiterGedrueckt(false);
                        spielanleitungZiel2.setVisible(false);
                        spielanleitungZiel2 = null;
                    }
                }
                hauptmenue.setVisible(true); //danach wird das Hauptmenü wieder eingeblendet
                hauptmenue.setIstSpielanleitungGedrueckt(false); //es wird keine neue Spielanleitung erzeugt oder eingeblendet
                spielanleitungZiel.setVisible(false); //die Spielanleitung wird ausgeblendet
                spielanleitungZiel = null; //und gelöscht
            }
            
            if(hauptmenue.getIstBeendenGedrueckt()) //wenn man beenden dückt
            {
                spielStart=false; //wird die run-Methode abgebrochen
                System.exit(0); //und das Spiel geschlossen
            }
        }
    }
    
    /**
     * @author(Shium R., Jupp B., Stark an das Tutorial angelehnt, allerdings stark erweitert)
     * 
     * Diese Methode übernimmt das erstellen von Grafiken
     */
    private void doInitialisierung()
    {
        last=System.nanoTime(); //die letzte Systemzeit wird in Nanosekunden zwischengespeichert
        
        //Alle Bilder werden zugewiesen
        spieler_image = loadPics("src/pics/sheeet4.gif",4);
        spielerR_image = loadPics("src/pics/sheeet4_rechts.gif",4);
        BufferedImage[] hintergrund_image = loadPics("src/pics/gelb3.png",1);
        herz3_image = loadPics("src/pics/herz3voll.png",1);
        herz2_image = loadPics("src/pics/herz2voll.png",1);
        herz1_image = loadPics("src/pics/herz1voll.png",1);
        herz3_imageSpieler = loadPics("src/pics/Herz3.png",1);
        herz2_imageSpieler = loadPics("src/pics/Herz2.png",1);
        herz1_imageSpieler = loadPics("src/pics/Herz1.png",1);
        
        BufferedImage[] gegnerGhost_image = loadPics("src/pics/Enemy_ghost.gif",4);
        energieKugel_image = loadPics("src/pics/Energiekugel.png",1);
        BufferedImage[] spikes_image= loadPics("src/pics/Spikes.png",1);
        
        //Die Bilder werden ihren jeweiligen Sprites zugewiesen ggf. zusätzlich ArrayLists
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
        
        player = new Spieler(spieler_image,60,740,60, keyManager);
        ghost=new Gegner(gegnerGhost_image,560,50,60);
        
        kugel=new Waffe(energieKugel_image,player.getX(),player.getY()+10,100);
        
        
        
        spikes.add(spike1);
        spikes.add(spike2);
        spikes.add(spike3);
        spikes.add(spike4);
        spikes.add(spike5);
        spikes.add(spike6);
        
        gegner.add(ghost);
        
        // lebenspunkte=new Lebensanzeige(herz3_image,0,10,100); //Muss vom Char geändert werden
        lebenspunkte=new Lebensanzeige(herz3_imageSpieler,0,10,100);
        lebenspunkteG=new Lebensanzeige(herz3_image,gegner.get(rndG).getX(),gegner.get(rndG).getY()-25,100);
        
        //alle Sprites werden der actors ArrayList hinzugefügt
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
     * @author(Jupp B., Cihan K., Gideon S., kleinste Anteile Tutorial)
     * 
     * Diese Methode übernimmt Abfrage von Tastatureingaben und die jeweiligen Änderungen
     */
    private void checkKeys()
    {
        player.doLogic(delta); //die doLogic Methode aus der Klasse Spieler wird aufgerufen, was die Randkollision und Animation veranlasst
        keyManager.update(); //die Tastatureingaben werden abgefragt
        if((keyManager.jump||keyManager.jumpG) && (canJump==true || prevVertSpeed==0)) //wenn der Sprungbefehl ausgelöst wird und der Spieler springen kann
        {
            player.setY(player.getY()-3); //wird der Spieler etwas nach oben gesetzt, um die Kollision zu deaktivieren, die den Sprung bzw. Fall verhindern würde
            inJump=true; //der Spieler ist im Sprungprozess
            player.setVerticalSpeed(-vSpeed+fallgeschwindigkeit*prevVertSpeed); //der Spieler bewegt sich mit einer bestimmten Geschwindigkeit nach oben, wird aber von der Gravitation gebremst
            prevVertSpeed=prevVertSpeed+5; //der Wert der Loops in denen gefallen wird, wird um 5 erhöht, um potetiellen Bugs vorzubeugen
            canJump=false; //der Spieler kann nicht mehr springen
            collided=false; //und kollidiert nicht mehr
        }
        
        if(collided==true) //wenn der Spieler mit einer Plattform kollidiert
        {
            canJump=true; //kann er wieder springen
            inJump=false; //und ist nicht mehr im Sprung
            prevVertSpeed=0; //die Anzahl an Loops in denen der Spieler fällt, wird wieder auf 0 gesetzt
            player.setVerticalSpeed(0); //der Spieler bewegt sich nicht mehr vertikal
            player.setY(plattforms.get(plattformNr).getY()-30); //Der Spieler wird über die Plattform gesetzt, um weiteren Bugs vorzubeugen, allerdings egal von wo er kollidiert, er wird nach oben gesetzt, auch von den Seiten oder unten
        }
        
        if(prevVertSpeed>4) //wenn der Spieler bereits 4 Loops fällt (Fehlerfänger)
        {
            canJump=false; //kann er nicht mehr springen
            player.setVerticalSpeed(-vSpeed+fallgeschwindigkeit*prevVertSpeed); //und bewegt sich mit fester, von der Gravitation verringerter Geschwingkeit auf- bzw. abwärts
            prevVertSpeed++; //den Loops in denen er fällt wird einer hinzugefügt
        }
        
        if(collided==false&&inJump==false) //wenn der Spieler nicht kollidiert oder im Sprung ist 
        {
            player.setVerticalSpeed(fallgeschwindigkeit*prevVertSpeed); //fällt er abhängig von der Gravitation (ohne feste Sprunggeschwindigkeit)
            prevVertSpeed++; //den Loops in denen er fällt wird einer hinzugefügt
        }
        
        if(keyManager.left||keyManager.leftG) //wenn der Tastatur befehl zum Laufen nach links ausgelöst wird
        {
            player.setHorizontalSpeed(-hSpeed); //bewegt sich der Spieler mit fester Geschwindigkeit nach links
        }
        
        if(keyManager.right||keyManager.rightG) //wenn der Tastatur befehl zum Laufen nach rechts ausgelöst wird
        {
           player.setHorizontalSpeed(hSpeed); //bewegt sich der Spieler mit fester Geschwindigkeit nach rechts
        }  
    
        if((!keyManager.left&&!keyManager.leftG) && (!keyManager.right&&!keyManager.rightG))  //wenn weder ein Tastaturbefehl zum Laufen nach rechts  noch nach links ausgelöst wird
        {
            player.setHorizontalSpeed(0); //gibt es keine horizontale Bewegung
        }
        
        if(keyManager.fire&&reload==false) //wenn der Tastaturbefehl zum Schuss gegeben wird und nicht bereits geschossen wurde
        {
            kugel=new Waffe(energieKugel_image,player.getX(),player.getY()+10,100); //wird eine neue Kugel an der Stelle des Spielers (seine Brust) erzeugt
            actors.add(kugel); //den actors hinzugefügt
            
            if(player.getImage()==spielerR_image)kugel.setHorizontalSpeed(300); //und mit einer festen Geschwindigkeit abgefeuert
            else kugel.setHorizontalSpeed(-300);  //je nach Bild nach rechts oder links, also immer in Blickrichtung
            
            reload=true; //der Spieler kann nicht mehr schießen, muss also nachladen
        }
        
        
    }
    
    
    /**
     * @author(Jupp B., Gideon S., Shium R., Cihan K., zu kleinsten Teilen noch vom Tutorial kopiert)
     * 
     * Diese Methode übernimmt die Ausführung von Logik-Operationen, wie Kollision oder Lebenspunkt anderungen --> Tod bzw. Sieg
     */
    private void doLogic()
    {
        collided=false; //der Spieler kollidiert zu Beginn nicht, um die Schleife zu aktivieren
        
        for(int i=0;i<plattforms.size()&&collided==false;i++) //solange es noch un geprüfte Plattformen gibt und der Spieler noch mit keiner kollidiert ist
        { 
            Sprite s = plattforms.get(i); //Plattform and der Stelle i wird zwischengespeichert
            collided=player.collidedWith(s); //Kollision wird zwischen Spieler und aktueller Plattform geprüft
            plattformNr = i; //die Nummer der aktuellen Plattform wird zwischen gespeichert
        }
        
        for(int i=0;i<gegner.size();i++)//Die ArrayList aus Gegnern wird durchgegangen, damit der richtige auch geprüft wird, außerdem könnte es in zukunft meherere Gegner geben
        { 
            Sprite s = gegner.get(i); //der aktuelle Gegner wird zwischengespeichert
            if(player.collidedWith(s)&&!safe) //wenn der Spieler mit einem Gegner kollidiert und gerade Schaden erleiden kann
            {
                player.reduceHP(); //verliert er einen Lebenspunkt
                safe=true; //und kann keinen Schaden mehr nehmen
            }
        }
        
        for(int i=0;i<spikes.size();i++)//Die ArrayList aus Spikes wird durchgegangen
        { 
            Sprite s = spikes.get(i); //der aktuelle Spike wird zwischengespeichert
            if(player.collidedWith(s)&&!safe) //wenn der Spieler mit einem Spike kollidiert und gerade Schaden erleiden kann
            {
                player.reduceHP(); //verliert er einen Lebenspunkt
                safe=true; //und kann keinen Schaden mehr nehmen
            }
        }
        
        if(player.getBeruehrtBoden()) //wenn der Spieler den Boden beruehrt 
        {
            victory=false; //hat man das Spiel verloren
            spielfeld.getFrame().setVisible(false); //und es wird ausgeblendet
        }
        
        if(player.getHorizontalSpeed()<0) //wenn der Spieler sich nach links bewegt
        {
            player.setImage(spieler_image); //wird das Bild des Spielers, wie er nach links schaut benutzt
        }
        else if(player.getHorizontalSpeed()>0) //wenn der Spieler sich nach rechts bewegt
        {
            player.setImage(spielerR_image); //wird das Bild des Spielers, wie er nach rechts schaut benutzt
        }
        
        if(player.getHP()==3) //wenn der Spieler 3 Lebenspunkte hat
        {
            lebenspunkte.setImage(herz3_imageSpieler); //wird das Bild für 3 Lebenspunkte benutzt / Änderung
        }
        else if(player.getHP()==2) //wenn der Spieler 2 Lebenspunkte hat
        {
            lebenspunkte.setImage(herz2_imageSpieler); //wird das Bild für 2 Lebenspunkte benutzt
        }
        else if(player.getHP()==1) //wenn der Spieler 1 Lebenspunkt hat
        {
            lebenspunkte.setImage(herz1_imageSpieler); //wird das Bild für 1 Lebenspunkt benutzt
        }
        else //wenn der Spieler keine Lebenspunkte mehr hat
        {
            victory=false; //hat man das Spiel verloren
            spielfeld.getFrame().setVisible(false); //und es wird ausgeblendet
        }
        
        if(gegner.get(rndG).getHP()==3) //wenn der Gegner 3 Lebenspunkte hat
        {
            lebenspunkteG.setImage(herz3_image);
        } 
        else if(gegner.get(rndG).getHP()==2) //wenn der Gegner 2 Lebenspunkte hat
        {
            lebenspunkteG.setImage(herz2_image); //wird das Bild für 2 Lebenspunkte benutzt
        }
        else if(gegner.get(rndG).getHP()==1) //wenn der Gegner 1 Lebenspunkt hat
        {
            lebenspunkteG.setImage(herz1_image); //wird das Bild für 1 Lebenspunkt benutzt
        }
        else //wenn der Gegner keine Lebenspunkte mehr hat
        {
            victory=true; //hat man das Spiel gewonnen
            spielfeld.getFrame().setVisible(false); //und es wird ausgeblendet
        }
        
        if(kugel.getX()<=0 || kugel.getX()>=1280) //wenn die Kugel aus dem Bild fliegt
        {
            actors.remove(kugel); //wird sie aus der actors ArrayList entfernt
        }
        
        if(gegner.get(rndG).collidedWith(kugel)&&getroffen==false) //wenn der Gegner mit einer Kugel kollidiert und Schaden erleiden kann
        {
            gegner.get(rndG).reduceHP(); //erleidet er Schaden
            getroffen=true; //und kann keinen Schaden mehr erleiden
        } 
    }
    
    /**
     * @author(Clemens Z., Shium R., 1zu1 aus dem Tutorial übernommen)
     * 
     * Diese Methode übernimmt das bewegen von Objekten
     */
    private void moveObjects()
    {
        for(ListIterator<Sprite> it=actors.listIterator();it.hasNext();) //der Vector actors, wird mittels eine ListIterators durchgegangen, welcher sich immer exakt zwischen 2 Objekten befindet
        {
            Sprite r = it.next(); //das nächste Objekt wird zwischengespeichert
            r.move(delta); //und bewegt
        }
    }
    
    /**
     * @author(Jupp B., Gideon S., 1zu1 aus dem Tutorial übernommen)
     * 
     * Diese Methode lädt die Bilder aus dem pics-Ordner
     * 
     * @param path - der Speicherort der Bilder
     *        pics - die Anzahl Bilder im Ordner
     */
    private BufferedImage[] loadPics(String path, int pics)
    {  
        BufferedImage[] anim = new BufferedImage[pics];
        BufferedImage source=null;
        
        URL pic_url=getClass().getClassLoader().getResource(path); //der Ort des Bildes wird gespeichert
        
        try //das Bild soll ausgelesen werden, wenn möglich
        {
            source=ImageIO.read(pic_url);
        }
        catch(IOException e)
        {
        }
        
        for(int i=0;i<pics;i++) //eine .png bzw. .gif Datei wird in ein BufferedImage Array umgewandelt
        {
            anim[i]=source.getSubimage(i*source.getWidth()/pics, 0, source.getWidth()/pics, source.getHeight());
        }
        
        return anim;
    } 
    
    /**
     * @author(Jupp B., Gideon S., 1zu1 aus dem Tutorial übernommen)
     * 
     * Errechnung der Zeit für einen Schleifendurchlauf
     */
    private void computeDelta()
    {
        delta = System.nanoTime() - last; //die Differenz der Letzten und der aktuellen Systemzeit wird gespeichert
        last = System.nanoTime(); //die aktuelle Systemzeit wird als nächste letzte gespeichert
        fps = ((long) 1e9)/delta; //10^9 dividiert durch die Dauer des Durchlaufs um nicht so viele Nachkommastellen zu haben
    }
    
    @SuppressWarnings("unchecked") //Alle Warnungen des Compilers bezüglich un überprüfter Aktionen werden unterdrückt
    /**
     * @author(Jupp B., Gideon S., 1zu1 aus dem Tutorial übernommen)
     *
     * Diese Methode klont einen Vektoren in einen anderen
     * hier: actors in painter
     */
    private void cloneVectors()
    {
        for(int i=0; i<actors.size();i++) //der ganze actors Vector wird durchgegangen
        {
            painter.add(actors.elementAt(i)); //und die Objekte werden in den painter vector gespeichert, um sie zu zeichnen
        }
    }
    
    
    

}
