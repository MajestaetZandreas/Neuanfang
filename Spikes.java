
import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Spikes.
 * 
 * @author (Shium R. und Cihan K.) 
 * @version (25.05.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor sie in diesen Code eintauchen
 */
public class Spikes extends Sprite
{
    /**
     * Konstruktor für Objekte der Klasse Kreaturen
     */
    public Spikes(BufferedImage[] i, double x, double y, long delay)
    {
        super(i, x, y, delay);
    }

    /**
     * @author(Shium R, Cihan K.)
     * 
     * Diese Methode ruft die doLogic-Methode aus der Klasse Sprite auf, und spielt damit die Animation ab
     */
    @Override
    public void doLogic(long delta)
    {
        super.doLogic(delta);
    }
    
    /**
     * @author(Cihan K., Gideon S., Jupp B. - Verschidene versionen)
     * 
     * Diese Methode überprüft ob sich zwei Objekte überschneiden und gibt das Resultat aus
     * 
     * @return true, wenn zwei Objekte sich überschneiden
     *         false, wenn nicht
     */
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
