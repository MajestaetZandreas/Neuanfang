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
    long delay;
    Spielfeld parent;
    BufferedImage[] pics;
    int currentpic = 0;
    
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
    
    public void drawObjects(Graphics g)
    {
        
    }
    
    public void doLogic(long delta)
    {
        
    }
    
    public void move(long delta)
    {
        
    }
}
