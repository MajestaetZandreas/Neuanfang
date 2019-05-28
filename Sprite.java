import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;



/**
 * Diese Klasse beschäftigt sich mit den Grafiken der Objekte auf dem Spielfeld
 * 
 * @author (Jupp Bruns, Gideon Schafroth, Clemens Zander) 
 * @version (15.05.2019)
 * 
 * Diese Klasse ist so gut wie komplett aus dem Tutorial übernommen
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public abstract class Sprite extends Rectangle2D.Double
{
    private long delay; //dieses Attribut wird zur Regulation der Animationsdauer verwendet
    private long animation=0; //dieses Attribut wird zur Berechnung der Animationsdauer verwendet
    private BufferedImage[] pics; //Das BufferedImage, welches die Grafik für das Sprite darstellt
    private int currentpic = 0; //das Bild, welches in der Animation gerade benutzt wird
    private double x; //die x-Position des Sprites
    private double y; //die y-Position des Sprites
    
    
    private double dx; //Delta x
    private double dy; //Delta y
    
    
    /**
     * Konstruktor der Klasse Sprite
     */
    public Sprite(BufferedImage[] i, double x, double y, long delay)
    {
        pics=i;
        this.x=x;
        this.y=y;
        this.delay=delay;
        this.width=pics[0].getWidth();
        this.height=pics[0].getHeight();
    }
    
    /**
     * Diese Methode zeichnet einen Teil eines BufferedImages auf dem Spielfeld
     */
    public void drawObjects(Graphics graphics)
    {
        graphics.drawImage(pics[currentpic], (int) x, (int) y, null); //double wird zu int, da es keine halben Pixel gibt
    }
    
    /**
     * Diese Methode zeichnet einen String in ein Bild ein
     */
    public void drawString(Graphics graphics, String string, int x, int y)
    {
        graphics.drawString(string,x,y);
    }
    
    public void setImage(BufferedImage[] i)
    {
        pics=i;
    }
    
    public BufferedImage[] getImage()
    {
        return pics;
    }
    
    /**
     * Diese Methode ist für die Animation der Spielfigur zuständig
     */
    public void doLogic(long delta)
    {
        animation= animation+(delta/1000000);
        if(animation>delay)
        {
            animation=0;
            computeAnimation();
        }
    }
    
    /**
     * Diese Methode ist für die wirkliche Bewegung der Spielfigur zuständig
     */
    public void move(long delta)
    {
        x=x+dx*(delta/1e9);
        y=y+dy*(delta/1e9);
    }
    
    public abstract boolean collidedWith(Sprite s);
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getHorizontalSpeed()
    {
        return dx;
    }
    
    public void setX(double x)
    {
        this.x=x;
    }
    
    public void setY(double y)
    {
        this.y=y;
    }
    
    public void setHorizontalSpeed(double newDx)
    {
        dx=newDx;
    }
    
    public double getVerticalSpeed()
    {
        return dy;
    }
    
    public void setVerticalSpeed(double newDy)
    {
        dy=newDy;
    }
    
    /**
     * Hier werden Gifs abgespielt
     */
    private void computeAnimation()
    {
        currentpic++; //das Bild das gerade angezeigt wird, wird um eins erhöht
        
        if(currentpic>=pics.length) //wenn das letzte erreicht wird
        {
            currentpic=0; //ist das nächste Bild wieder das Erste
        }
    }
    
    
}
