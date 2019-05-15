// import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//...
/**
 * Die Klasse Spielfeld erstellt das Fenster, in welchem das Spiel angezeigt wird
 * Author(Clemens Zander, Jupp Bruns, Gideon Schafroth)
 * Version(10.5.19)
 */
public class Spielfeld extends JPanel implements Runnable
{
    private JFrame frame;
    // private Canvas canvas;
    private String titel;
    private int breite;
    private int hoehe;
    private long delta;//Dauer eines Durchlaufs
    private long last;//Die Zeit vom Anfang eines Durchlaufs
    private long fps;//Anzahl Bilder pro Sekunde
    
    /**
    * Die Methode, mit der man das Programm als .jar-File öffnen kann
    */
    public static void main(String[] args)
    {
       new Spielfeld("Hüpfburg-2D",800,600);
    }
    
    /**
    * Konstruktor der Klasse Spielfeld
    * @param title Titel der über dem Fenster angezeigt wird
    * @param width die Breite des Fensters
    * @param height die Höhe des Fensters
    */
    public Spielfeld(String title, int width, int height)
    {
      titel = title;
      breite = width;
      hoehe = height;
      //Spielfeld wird erstellt
      this.setPreferredSize(new Dimension(breite, hoehe));
      frame = new JFrame(titel);
      frame.setLocation(100,100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      frame.add(this);
      frame.pack();
      frame.setVisible(true);
      
      doInitialisierung();
      Thread thread = new Thread(this);
      thread.start();
    }
    
    /*#----------------------------------------------------------Public-Methoden------------------------------------------------------*/
    /**
     * Überschreibung der Methode run() aus der Klasse Runnable
     */
    @Override
    public void run()
    {
        while(frame.isVisible())//solange das Fenster angezeigt wird
        {
            computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
            
            checkKeys();
            doLogic();
            moveObjects();
            
            Sprite copter;
            Vector<Sprite> actors;
            
            repaint();//Neuzeichnung wird ausgelöst
            try
            {
                Thread.sleep(10);//Programm wartet ("schläft")
            }
            catch(InterruptedException e)//sonst macht er nichts
            {
            }
        }
    }
    
    
    
    /**
     * Überschreibt die Methode paintComponent() aus der Klasse Runnable
     * 
     * @param graphics Referenz auf Zeichenfläche des JPanels 
     */
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponents(graphics);
        graphics.setColor(Color.red);
        graphics.drawString("FPS: " + Long.toString(fps), 20, 10);
    }
    
    /*#--------------------------------------------Private-Methoden-------------------------------------------------*/
    /**
     * Diese Methode übernimmt Abfrage von Tastatureingaben
     */
    private void checkKeys()
    {
        
    }
    
    /**
     * Diese Methode übernimmt die Ausführung von Logik-Operationen
     */
    private void doLogic()
    {
        
    }
    
    /**
     * Diese Methode übernimmt das bewegen von Objekten
     */
    private void moveObjects()
    {
        
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
    
    /**
     * Diese Methode übernimmt das Laden von Grafiken
     */
    private void doInitialisierung()
    {
        last=System.nanoTime();
        
        BufferedImage[] spieler = loadPics("pics/heli.gif",4);
    }
}

