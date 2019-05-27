import java.awt.image.BufferedImage;


/**
 * Die Plattform Klasse beschäftigt sich mit den Plattformen auf welchen der Spieler stehen kann
 *
 * @author (Shium Rahman, Clemens Zander)
 * @version (15.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Plattform extends Sprite
{
    
    
    public Plattform(BufferedImage[] i, double x, double y, long delay)
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
