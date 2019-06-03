import java.awt.event.*;

/**
 * Diese Klasse ist für die Realisierung von Tastendrücken zuständig
 * 
 * @author (Clemens Zander, Shium Rahman) 
 * @version (22.02.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class KeyManager implements KeyListener
{
    private boolean[] keys;//boolean Array, welche Taste gedrueckt wurde und welche nicht
    public boolean jump,  left, right, jumpG,  leftG, rightG, fire; //Attribute für die Tasten
    
    /**
     * @author (Clemens Zander, Shium Rahman) 
     * Konstruktor der Klasse KeyManager
     */
    public KeyManager()
    {
      keys = new boolean[6];//neues Array, mit genuegen Platz fuer alle Tasten
    }
    
    /**
     * @author (Clemens Zander, Shium Rahman) 
     * Methode speichert in jedes Attribut den Aktuellen Zustand einer Taste
     * (gedrueckt, nicht gedrueckt)
     */
    public void update()
    {
        //Tasten gedrueckt/nicht gedrueckt
        jump = keys[KeyEvent.VK_W];
        jumpG = keys[KeyEvent.VK_UP];
        left = keys[KeyEvent.VK_A];
        leftG = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D];
        rightG = keys[KeyEvent.VK_RIGHT];
        fire = keys[KeyEvent.VK_SPACE];
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
