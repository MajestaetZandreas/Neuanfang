
import java.awt.image.BufferedImage;

/**
 * Die Spikeklasse beschreibt das zeichenbare Objekt Spike, welche dem Spieler schaden zufügen. 
 * 
 * @author (Shium R. und Cihan K.) 
 * @version (25.05.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
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
