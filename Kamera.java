
/**
 * Diese Klasse ist dafür zuständig, dass der Ausschnitt des Spiels angezeigtr wird,
 * in welchem sich der Spieler befindet
 * 
 * @author (Clemens Zander, Shium Rahman, Gideon Schafroth) 
 * @version (22.05.2019)
 */
public class Kamera
{
    
    private int xOffset,yOffset;
    private float x, y;

    /**
     * Konstruktor für Objekte der Klasse Kamera
     */
    public Kamera(float x, float y)
    {
        this.x=x;
        this.y=y;
    }

    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public void setX(float newX)
    {
        x=newX;
    }
    
    public void getY(float newY)
    {
        y=newY;
    }
}
