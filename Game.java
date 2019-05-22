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
    
    
    public static void main(String[] args)
    {
        Game game = new Game();
    }
    
    public Game()
    {
        Hauptmenue frame = new Hauptmenue("Huepfburg-2D");
        camera = new Kamera(0,0);
        // copter = new Spieler();
        
        //doInitialisierung();
        Thread thread = new Thread(this);
        thread.start();
    }
    
    /**
     * Überschreibung der Methode run() aus der Klasse Runnable
     */
    @Override
    public void run()
    {
        while(spielStart)
        {
            if(hauptmenue.getIstGestartet())
            {
                spielfeld = new Spielfeld("Huepfburg-2D", 1600, 1200);
                frame = spielfeld.getFrame();
            }
            
            while(frame.isVisible())//solange das Fenster angezeigt wird
            {
                // spielfeld.computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
                
                
                
                // spielfeld.checkKeys();
                // spielfeld.doLogic();
                // spielfeld.moveObjects();
                // spielfeld.cloneVectors();
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
    }
    
    // private void setKamera()
    // {
        // int positionX = copter.getX();
        // int positionY = copter.getY();
    // }
}
