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
    private boolean[] keys;
    public boolean jump,  left, right, jumpG,  leftG, rightG, fire; 
    
    
    public KeyManager()
    {
      keys = new boolean[256];
    }
    
    public void update()
    {
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
