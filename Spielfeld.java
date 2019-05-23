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
 * Author(Clemens Zander, Jupp Bruns, Gideon Schafroth, Cihan Karahan)
 * Version(10.5.19)
 */
public class Spielfeld extends JPanel
{
    private JFrame frame;
    // private Canvas canvas;
    private String titel;
    private int breite;
    private int hoehe;
    private Vector<Sprite> painter;
    private Vector<Sprite> actors;
    
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    
    
    /**
    * Konstruktor der Klasse Spielfeld
    * @param title Titel der über dem Fenster angezeigt wird
    * @param width die Breite des Fensters
    * @param height die Höhe des Fensters
    */
    public Spielfeld(String title, int width, int height, Vector<Sprite> painter, Vector<Sprite> actors)
    {
        titel = title;
        breite = width;
        hoehe = height;
        this.painter=painter;
        this.actors=actors;
        

        //Spielfeld wird erstellt
        frame = new JFrame(titel);
        frame.setPreferredSize(new Dimension(breite, hoehe));
        getBildschirmmitte();
        frame.setLocation(screenWidthMiddle, screenHeightMiddle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();
        screenWidthMiddle = ((int) ((d.getWidth() / 2 - breite / 2)));
        screenHeightMiddle = ((int) ((d.getHeight() / 2 - hoehe / 2)));
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
    
    public void schliessen()
    {
        frame.setVisible(false);
    }
}

