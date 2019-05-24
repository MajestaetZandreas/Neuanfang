import java.awt.*;
import javax.swing.*;

/**
 * Diese Klasse erzeugt 
 * 
 * @author (Clemens Zander, Shium  Rahman) 
 * @version (24.05.2019)
 */
public class Spielanleitung extends JFrame
{
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    
    public Spielanleitung(String titel)
    {
        super(titel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,420);
        getBildschirmmitte();
        setLocation(screenWidthMiddle, screenHeightMiddle);
        setLayout(null);
        setResizable(false);
        
        ImageIcon background_image=new ImageIcon("src/pics/spielanleitung.png");
        JLabel background = new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,400,400);
        
        add(background);
        setVisible(true);
    }
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//
        screenWidthMiddle = ((int) ((d.getWidth() - this.getWidth()) / 2));
        screenHeightMiddle = ((int) ((d.getHeight() - this.getHeight()) / 2));
    }
}
