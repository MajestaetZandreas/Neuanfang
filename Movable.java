
/**
 * Dieses Interface ist für das Bewegen von Objekten zuständig
 * 
 * @author (Clemens Zander, Jupp Bruns, Gideon Schafroth) 
 * @version (15.05.2019)
 */

public interface Movable
{
    /**
     * Diese Methode ist für eigentliche Bewegungen von Objekten zuständig
     */
    public abstract void doLogic(long delta);
    
    /**
     * Diese Methode ist dafür zuständig, dass die Bewegung logisch abläuft (Kollision, Gravität u.ä.)
     */
    public abstract void move(long delta);
}
