import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Diese Klasse erzeugt 
 * 
 * @author (Clemens Zander, Shium  Rahman) 
 * @version (24.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class SpielanleitungTasten extends Menue implements ActionListener
{
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    private JButton zurueck;
    private boolean istZurueckGedrueckt=false;
    
    
    public SpielanleitungTasten()
    {
        JFrame frame = new JFrame("Spielanleitung");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(406,429);
        getBildschirmmitte();
        setLocation(getScreenWidthMiddle(), getScreenHeightMiddle()-20);
        setLayout(null);
        setResizable(false);
        
        ImageIcon background_image=new ImageIcon("src/pics/SpielanleitungTasten.png");
        JLabel background = new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,400,400);
        
        zurueck = new JButton("Zurück");
        zurueck.setPreferredSize(new Dimension(10,20));
        zurueck.setBounds(30,350, 80, 20);
        zurueck.setForeground(new Color(0,0,0,255));
        zurueck.setBackground(new Color(255,255,255,255));
        zurueck.addActionListener(this);
        
        add(zurueck);
        add(background);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==zurueck)
        {
            istZurueckGedrueckt=true;
        }
    }
    
    public boolean getIstZurueckGedrueckt()
    {
        return istZurueckGedrueckt;
    }
    
    public void setIstZurueckGedrueckt(boolean zustand)
    {
        istZurueckGedrueckt=zustand;
    }
}