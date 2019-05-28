import java.awt.image.BufferedImage;

/**
 * Die Gegenerklasse beschreibt das zeichenbare Objekt Gegener    
 * 
 * @author (Shium R., Jupp B.) 
 * @version (25.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Gegner extends Sprite
{
    private int lebenspunkte=3; //Jeder Gegner startet mit 3 Lebenspunkten
    

    /**
     * Konstruktor für Objekte der Klasse Kreaturen
     */
    public Gegner(BufferedImage[] i, double x, double y, long delay)
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
    
    public int getHP()
    {
        return lebenspunkte;
    }
    
    public void reduceHP()
    {
        lebenspunkte--;
    }
}
