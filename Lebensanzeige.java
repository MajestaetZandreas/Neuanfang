import java.awt.image.BufferedImage;

/**
 * Die Lebensanzeigeklasse beschreibt das zeichenbare Objekt Lebensanzeige, welche die Anzahl
 * der Leben bzw. die Lebenzenergie anzeigt.
 * 
 * @author (Shium Rahman, Clemens Zander) 
 * @version (24.05.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Lebensanzeige extends Sprite
{ 
    
    public Lebensanzeige(BufferedImage[] i, double x, double y, long delay)
    {
        super(i, x, y, delay);
    }    
  
    /**
     * Diese Methode überschreibt collidedWith aus Sprite
     * 
     * @return false, da die Lebenspunkte nicht kollidieren dürfen
     */
    public boolean collidedWith(Sprite s)
    {
        return false;
    }
}
