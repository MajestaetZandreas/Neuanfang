import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Kreaturen.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kreaturen extends Sprite
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int lebenspunkte;
    

    /**
     * Konstruktor für Objekte der Klasse Kreaturen
     */
    public Kreaturen(BufferedImage[] i, double x, double y, long delay)
    {
        super(i, x, y, delay);
    }

    @Override
    public void doLogic(long delta)
    {
        super.doLogic(delta);
    }
   
    public void collidedWith(Sprite s)
    {
        if(this.intersects(s))
        {
            
        }
        else
        {
            
        }
     }  
    
}