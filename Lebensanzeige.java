import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Lebenspunkte.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Lebensanzeige extends Sprite
{ 
    
    public Lebensanzeige(BufferedImage[] i, double x, double y, long delay)
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
        return false;
    }
}