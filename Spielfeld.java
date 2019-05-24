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
 * Author(Clemens Zander, Jupp Bruns, Gideon Schafroth, Cihan Karahan)
 * Version(10.5.19)
 */
public class Spielfeld extends JPanel
{
    private JFrame frame;
    private String fenstertitel;
    private JProgressBar lebenspunkte;

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
        fenstertitel = title;
        breite = width;
        hoehe = height;
        this.painter=painter;
        this.actors=actors;
        this.setPreferredSize(new Dimension(breite, hoehe));
        
        //Spielfeld wird erstellt
        frame = new JFrame(fenstertitel);
        getBildschirmmitte();
        frame.setLocation(screenWidthMiddle, screenHeightMiddle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        lebenspunkte = new JProgressBar(0, 100);
        lebenspunkte.setForeground(new Color(10,20,30,255));
        lebenspunkte.setBounds(200, 200, 60,6);
        
        frame.add(lebenspunkte);
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

