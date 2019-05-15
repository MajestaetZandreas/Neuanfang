import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Die Grafik-Klasse oder so
 * 
 * @author (Jupp Bruns, Gideon Schafroth, Clemens Zander) 
 * @version (15.05.2019)
 */
public class Sprite extends Rectangle2D.Double implements Drawable, Movable
{
    private long delay;
    private long animation=0;
    private Spielfeld parent;
    private BufferedImage[] pics;
    private int currentpic = 0;
    private double x;
    private double y;
    
    
    private double dx; //Delta x
    private double dy; //Delta y
    
    
    /**
     * Konstruktor der Klasse Sprite
     */
    public Sprite(BufferedImage[] i, double x, double y, long delay, Spielfeld p)
    {
        pics=i;
        this.x=x;
        this.y=y;
        this.delay=delay;
        this.width=pics[0].getWidth();
        this.height=pics[0].getHeight();
        parent = p;
    }
    
    /*#---------------------------------------------------------------------Public-Methoden----------------------------------------------------*/
    
    /**
     * Diese Methode zeichnet ein BufferedImage auf dem Spielfeld
     */
    public void drawObjects(Graphics graphics)
    {
        graphics.drawImage(pics[currentpic], (int) x, (int) y, null); //double wird zu int, da es keine halben Pixel gibt
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
        if(dx!=0)
        {
            x=x+dx*(delta/1e9);
        }
        
        if(dy!=0)
        {
            y=y+dy*(delta/1e9);
        }
    }
    
    /*#---------------------------------------------------------------Get-und Set-Methoden-------------------------------------------------*/
    public double getHorizontalSpeed()
    {
        return dx;
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
    
    /*#---------------------------------------------------------------Priavte-Methoden-----------------------------------------------------*/
    /**
     * 
     */
    private void computeAnimation()
    {
        currentpic++;
        
        if(currentpic>=pics.length)
        {
            currentpic=0;
        }
    }
}
