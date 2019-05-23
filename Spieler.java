import java.awt.image.BufferedImage;

/**
 * Diese Klasse beschäftigt sich mit dem Spieler und seinen Funktionen
 *
 * @author (Shium Rahman, Clemens Zander, Cihan Karahan)
 * @version (15.05.2019)
 */
public class Spieler extends Sprite
{
    private int inAir = 1;
    private KeyManager keyManager;
    
    public Spieler(BufferedImage[] i, double x, double y, long delay, KeyManager kManager)
    {
        super(i, x, y, delay);
        keyManager=kManager;
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
        
        if(getX()+getWidth()>800)
        {
            setX(800-getWidth());
            setHorizontalSpeed(0);
        }
        
        if(getY()<0)
        {
            setY(0);
            setVerticalSpeed(0);
            inAir=0;
        }
        
        if(getY()+getHeight()>600)
        {
            setY(600-getHeight());
            setVerticalSpeed(0);
        }
    }
    
    public boolean collidedWith(Sprite s)
    {
        if(this.intersects(s))
        {
            if(!(s instanceof Hintergrund))
            {
                inAir=0;
                // System.out.println("klappt");
                if(keyManager.jump||inAir==1) 
                {
                    setY(getY()-2);
                    inAir=1;
                    return false;
                }
                else return true;
            }
            else
            {
                // System.out.println("klappt nicht");
                inAir=0;
                return false;
            }
        }
        else
        {
            // System.out.println("klappt gar nicht");
            inAir=2;
            return false;
        }
    }
    
    public void setInAir(int newInt)
    {
        inAir=newInt;
    }
    
    public int getInAir()
    {
        return inAir;
    }
    
    
}