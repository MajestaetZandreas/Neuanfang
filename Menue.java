import java.awt.*;
import javax.swing.*;

/**
 * Diese Klasse erzeugt 
 * 
 * @author (Clemens Zander, Shium  Rahman) 
 * @version (24.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public abstract class Menue extends JFrame
{
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//
        screenWidthMiddle = ((int) ((d.getWidth() / 2 - 203)));
        screenHeightMiddle = ((int) ((d.getHeight() / 2 - 215)));
    }
    
    public int getScreenWidthMiddle()
    {
        return screenWidthMiddle;
    }
    
    public int getScreenHeightMiddle()
    {
        return screenHeightMiddle;
    }
}
