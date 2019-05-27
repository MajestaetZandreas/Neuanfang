import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Waffe.
 * 
 * @author (Shium Rahman, Jupp Bruns) 
 * @version (24.05.19)
 */
public class Waffe extends Sprite
{
    /**
     * Konstruktor für Objekte der Klasse Kreaturen
     */
    public Waffe(BufferedImage[] i, double x, double y, long delay)
    {
        super(i, x, y, delay);
    }
    
    
    @Override
    public void doLogic(long delta)
    {
        super.doLogic(delta);
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
}
