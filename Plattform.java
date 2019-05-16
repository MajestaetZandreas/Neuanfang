import java.awt.image.BufferedImage;


/**
 * Die Plattform Klasse beschäftigt sich mit den Plattformen auf welchen der Spieler stehen kann
 *
 * @author (Shium Rahman, Clemens Zander)
 * @version (15.05.2019)
 */
public class Plattform extends Sprite
{
   private final int SPEED = 20;
   
   public Plattform(BufferedImage[] i, double x, double y, long delay)
   {
       super(i, x, y, delay);
       
       // if((int) (Math.random()*2)<1)
       // {
           // setHorizontalSpeed(-SPEED);
       // }
       // else
       // {
           // setHorizontalSpeed(SPEED);
       // }
   }
   
   @Override
   public void doLogic(long delta)
   {
       super.doLogic(delta);
   }
   
}
