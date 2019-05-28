import java.awt.image.BufferedImage;

/**
 * Die Hintergrundklasse beschreibt das zeichenbare Objekt Hintergrund.
 * 
 * @author (Clemens Zander, Shium Rahman) 
 * @version (22.05.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Hintergrund extends Sprite
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse Hintergrund
     */
    public Hintergrund(BufferedImage[] i, double x, double y, long delay)
    {
        super(i, x, y, delay);
    }

    /**
     * Diese Methode überschreibt collidedWith aus Sprite
     * 
     * @return false, da der Hintergrund nicht kollidieren darf
     */
    public boolean collidedWith(Sprite s)
    {
        return false;
    }
}
