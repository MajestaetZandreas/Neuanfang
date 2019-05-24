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
    public boolean jump,  left, right, jumpG,  leftG, rightG;
    
    
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
