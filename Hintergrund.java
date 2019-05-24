import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Hintergrund.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
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
