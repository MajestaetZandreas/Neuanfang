// import java.awt.Canvas;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.lang.Math;
import java.lang.String;

//...
/**
 * Die Klasse Spielfeld erstellt das Fenster, in welchem das Spiel angezeigt wird
 * @author(Clemens Zander, Jupp Bruns, Gideon Schafroth, Cihan Karahan)
 * @version(10.5.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Spielfeld extends JPanel
{
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    
    private JFrame frame;

    private int breite;
    private int hoehe;
    private Vector<Sprite> painter;
    private Vector<Sprite> actors;       
    
    /**
    * Konstruktor der Klasse Spielfeld
    * @param title Titel der über dem Fenster angezeigt wird
    * @param width die Breite des Fensters
    * @param height die Höhe des Fensters
    */
    public Spielfeld( int width, int height, Vector<Sprite> painter, Vector<Sprite> actors)
    {
        breite = width;
        hoehe = height;
        this.painter=painter;
        this.actors=actors;
        this.setPreferredSize(new Dimension(breite, hoehe));
        
        //Spielfeld wird erstellt
        frame = new JFrame("Hüpfburg-2D");
        getBildschirmmitte();
        frame.setLocation(screenWidthMiddle,screenHeightMiddle-20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }    
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//
        screenWidthMiddle = ((int) ((d.getWidth() / 2 - 640)));
        screenHeightMiddle = ((int) ((d.getHeight() / 2 - 480)));
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
    
     /**
     * 
     * @param graphics Referenz auf Zeichenfläche des JPanels 
     */
    
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
}

