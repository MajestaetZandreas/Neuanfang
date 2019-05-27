import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;



/**
 * Die Grafik-Klasse oder so
 * 
 * @author (Jupp Bruns, Gideon Schafroth, Clemens Zander) 
 * @version (15.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor sie in diesen Code eintauchen
 */
public abstract class Sprite extends Rectangle2D.Double
{
    private long delay;
    private long animation=0;
    private BufferedImage[] pics;
    private int currentpic = 0;
    private double x;
    private double y;
    
    
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
    
    /*#---------------------------------------------------------------------Public-Methoden----------------------------------------------------*/
    
    /**
     * Diese Methode zeichnet ein BufferedImage auf dem Spielfeld
     */
    public void drawObjects(Graphics graphics)
    {
        graphics.drawImage(pics[currentpic], (int) x, (int) y, null); //double wird zu int, da es keine halben Pixel gibt
    }
    
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
    
    /*#---------------------------------------------------------------Get-und Set-Methoden-------------------------------------------------*/
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
    
    /*#---------------------------------------------------------------Private-Methoden-----------------------------------------------------*/
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
