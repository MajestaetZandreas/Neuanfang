import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Lebenspunkte.
 * 
 * @author (Shium Rahman) 
 * @version (24.05.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor sie in diesen Code eintauchen
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
