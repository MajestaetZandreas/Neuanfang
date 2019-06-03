import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.lang.Math;
import java.lang.String;

/**
 * Spielfeld Fenster fuer das Spiel Huepfburg-2D
 * 
 * @author(Clemens Zander, Jupp Bruns, Gideon Schafroth, Cihan Karahan)
 * @version(28.5.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Spielfeld extends JPanel
{
    private int screenWidthMiddle;//x-Koordinate für den Punkt, an welchem das Spielfeld-Fenster erzeugt werden muss, damit sich dieses mittig auf dem Bildschirm befindet
    private int screenHeightMiddle;//y-Koordinate für den Punkt, an welchem das Spielfeld-Fenster erzeugt werden muss, damit sich dieses mittig auf dem Bildschirm befindet
    
    private JFrame frame;//JFrame des Spielfelds

    private int breite;//breite des Spielfeld-Fensters
    private int hoehe;//hoehe des Spielfeld-Fensters
    private Vector<Sprite> painter;//Vektor für alle Objekte auf dem Spielfeld, welche gezeichnet werden sollen
    private Vector<Sprite> actors; //Vektor für alle Objekte auf dem Spielfeld    
    
    /**
    *@author(Clemens Zander Jupp Bruns) 
    *
    * Konstruktor der Klasse Spielfeld
    * Erstellt ein JFrame mit den übergebenden Werten in der Mitte (leicht nach oben verschoben => Begründung in der README-Datei) des Bildschirms
    * 
    * @param width die Breite des Fensters
    * @param height die Höhe des Fensters
    * @param painter Objekte die auf dem Spielfeld gezeichnet werden sollen in einem Vektor
    * @param actors Objekte die es auf dem Spielfeld gibt in einem Vektor
    */
    public Spielfeld( int width, int height, Vector<Sprite> painter, Vector<Sprite> actors)
    {
        //Parameter auf entsprechende Attribute gesetzt
        breite = width;
        hoehe = height;
        this.painter=painter;
        this.actors=actors;
        this.setPreferredSize(new Dimension(breite, hoehe));
        
        //Spielfeld wird erstellt
        frame = new JFrame("Hüpfburg-2D");//neues JFrame mit entsprechenden Titel
        getBildschirmmitte();//Bildschirmmitte bestimmt
        frame.setLocation(screenWidthMiddle,screenHeightMiddle-20);//Ausrichtung des JFrame mit den ermittelten x- und y-Werten, damit das Fenster Mittig erscheint
                                                                   //Die begruendete Veränderung des y-Wetes (screenHeightMiddle-20) bitte in der README-Datei nachlesen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//klicken des Schliessen-Knopfs (obere rechte Ecke) führt zum beenden der Applikation
        frame.setResizable(false);//Die Größe des Fensters kann nicht während der Laufzeit, am Fenster selbst, vorgenommen werden
        
        //Fenster sichtbar gemacht
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }    
    
    /**
     * @author (Clemens Zander und Gideon Schafroth)
     * 
     * Methode berechnet die Koordinaten eines Puktes, an welchem das Spielfeld-Fenster erzeugt werden muss
     */
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//Größe des Bildschirms, auf welchem die Applikation aktuell läuft, gespeichert
        screenWidthMiddle = ((int) ((d.getWidth() / 2 - 640)));//x-Koordinate, für den Punkt, an welchem das Spielfeld-Fenster erzeugt werden muss, berechnet   (1080/2 = 640)
        screenHeightMiddle = ((int) ((d.getHeight() / 2 - 480)));//y-Koordinate, für den Punkt, an welchem das Spielfeld-Fenster erzeugt werden muss, berechnet   (960/2 = 480)
    }
    
     /**
     * @author(Clemens Zander und Jupp Bruns)
     * 
     * @param graphics Referenz auf Zeichenfläche des JPanels 
     */
    
    public void paintComponent(Graphics graphics)
    {
        super.paintComponents(graphics);
         
        if(painter!=null)
        {
            Vector<Sprite> removeIds=new Vector<Sprite>();
            for(ListIterator<Sprite> it=actors.listIterator();it.hasNext();)
            {
                Sprite r = it.next();
                r.drawObjects(graphics);
            }
        }
    }
    
    /**
     * @author(Clemens Zander und Gideon Schafroth)
     * 
     * get-Methode für das JFrame des Spielfeldes
     */
    public JFrame getFrame()
    {
        return frame;
    }
}

