import java.awt.image.BufferedImage;

/**
 * Diese Klasse beschäftigt sich mit dem Spieler und seinen Funktionen
 *
 * @author (Shium Rahman, Clemens Zander, Cihan Karahan)
 * @version (15.05.2019)
 */
public class Spieler extends Sprite
{
    private int pvs;
    private KeyManager keyManager;
    private int lebenspunkte;
    
    public Spieler(BufferedImage[] i, double x, double y, long delay, KeyManager kManager)
    {
        super(i, x, y, delay);
        keyManager=kManager;
    }
    
    
    public int logic(int prevVertSpeed, long delta)
    {
        pvs=prevVertSpeed;
        doLogic(delta);
        return pvs;
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
        
        if(getX()+getWidth()>1280)
        {
            setX(1280-getWidth());
            setHorizontalSpeed(0);
        }
        
        if(getY()<0)
        {
            setY(0);
            setVerticalSpeed(0);
        }
        
        if(getY()+getHeight()>960)
        {
            setY(960-getHeight());
            setVerticalSpeed(0);
            pvs=0;
         }
    }
    
    public boolean collidedWith(Sprite s)
    {
        if(this.intersects(s))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void reduceHP()
    {
        lebenspunkte--;
    }
    
    public int getHP()
    {
        return lebenspunkte;
    }
}