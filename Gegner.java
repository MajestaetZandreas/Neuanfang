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

    @Override
    public void doLogic(long delta)
    {
        super.doLogic(delta);
    }
   
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
