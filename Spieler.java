import java.awt.image.BufferedImage;

/**
 * Diese Klasse beschäftigt sich mit dem Spieler und seinen Funktionen
 *
 * @author (Shium Rahman, Clemens Zander, Cihan Karahan, Jupp Bruns, Gideon Schafroth)
 * @version (25.05.2019)
 */
public class Spieler extends Sprite
{
    private int pvs; //Ein Attribut, um die Falldauer zu speichern und zurückzugeben, obwohl sie in der Klasse game ist
    private KeyManager keyManager;
    private int lebenspunkte=3; //Der Spieler startet mit 3 Lebenspunkten
    private boolean beruehrtBoden=false; //Der Spieler ist am Anfang des Spiels nicht auf dem Boden
    
    public Spieler(BufferedImage[] i, double x, double y, long delay, KeyManager kManager)
    {
        super(i, x, y, delay);
        keyManager=kManager;
    }
    
    /**
     * @author (Jupp B., Gideon S.)
     * 
     *  Diese Methode verändert die Vertikale Geschwindigkeit, sowie die Dauer des Falls
     */
    public int logic(int prevVertSpeed, long delta)
    {
        pvs=prevVertSpeed;
        doLogic(delta);
        return pvs;
    }
    
    /**
     * @author(Clemens Z., Shium R., Jupp B.)
     * 
     * Diese Methode stellt sicher, dass die Spielfigur sich im Rahmen des Spielfelds bewegt, außerdem ändert sie ggf. Variablen
     */
    @Override
    public void doLogic(long delta)
    {
        super.doLogic(delta);
        
        if(getX()<0)//Spielfigur befindet sich am linken Rand
        {
            setHorizontalSpeed(0);
            setX(0);
        }
        
        if(getX()+getWidth()>1280)//Spielfigur befindet sich am rechten Rand
        {
            setX(1280-getWidth());
            setHorizontalSpeed(0);
        }
        
        if(getY()<0)//Spielfigur befindet sich am oberen Rand
        {
            setY(0);
            setVerticalSpeed(0);
        }
        
        if(getY()+getHeight()>960)//Spielfigur befindet sich am unteren Rand = "Boden"
        {
            setY(960-getHeight());
            setVerticalSpeed(0);
            pvs=0;
            beruehrtBoden=true;//die Spielfigur beruehrt den "Boden"
        }
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
    
    public void reduceHP()
    {
        lebenspunkte--;
    }
    
    public int getHP()
    {
        return lebenspunkte;
    }
    
    public boolean getBeruehrtBoden()
    {
        return beruehrtBoden;
    }
    
    public void setBeruehrtBoden(boolean zustand)
    {
        beruehrtBoden=zustand;
    }
}