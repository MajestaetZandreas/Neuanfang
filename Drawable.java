import java.awt.Graphics;

/**
 * Dieses Interface ist für das zeichnen von Objekten zuständig
 * 
 * @author (Gideon Schafroth, Clemens Zander, Jupp Bruns) 
 * @version (15.05.2019)
 */

public interface Drawable
{
    /**
     * Diese abstakte Methode ist für das zeichnet von Objekte zuständig
     */
    public abstract void drawObjects(Graphics graphics);
}
