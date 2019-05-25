import java.awt.image.BufferedImage;

/**
 * Beschreiben Sie hier die Klasse Kreaturen.
 * 
 * @author (Shium R., Jupp B.) 
 * @version (25.05.2019)
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
     * @author(Jupp B., Gideon S.)
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
    
    public int getHP()
    {
        return lebenspunkte;
    }
    
    public void reduceHP()
    {
        lebenspunkte--;
    }
}
