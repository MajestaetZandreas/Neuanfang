import java.awt.image.BufferedImage;

/**
 * Diese Klasse beschäftigt sich mit dem Spieler und seinen Funktionen
 *
 * @author (Shium Rahman, Clemens Zander)
 * @version (15.05.2019)
 */
public class Spieler extends Sprite
{
    public Spieler(BufferedImage[] i, double x, double y, long delay, Spielfeld spielfeld)
    {
        super(i, x, y, delay, spielfeld);
    }
    
    @Override
    public void doLogic(long delta)
    {
        super.doLogic(delta);
        
        if(getX()<0)
        {
            setHorizontalSpeed(0);
            setX(0);
        }
        
        if(getX()+getWidth()>parent.getWidth())
        {
            setX(parent.getWidth()-getWidth());
            setHorizontalSpeed(0);
        }
        
        if(getY<0)
        {
            setY(0);
            setVerticalSpeed(0);
        }
        
        if(getY()+getHeight()>parent.getHeight())
        {
            setY(parent.getHeight()-getHeight());
            serVerticalSpeed(0);
        }
    }
}
