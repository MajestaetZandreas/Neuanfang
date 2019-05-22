import java.awt.event.*;

/**
 * Diese Klasse ist für die Realisierung von Tastendrücken zuständig
 * 
 * @author (Clemens Zander, Shium Rahman) 
 * @version (22.02.2019)
 */
public class KeyManager implements KeyListener
{
    private boolean canJump;
    private boolean[] keys;
    public boolean jump,  left, right;
    
    
    public KeyManager()
    {
      keys = new boolean[256];
    }
    
    public void update()
    {
        jump = keys[KeyEvent.VK_W];    
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
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
