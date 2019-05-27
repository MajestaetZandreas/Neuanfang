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
public class SpielanleitungZiel2 extends Menue implements ActionListener
{
    private JButton zurueck;
    private JButton weiter;
    private boolean istZurueckGedrueckt=false;
    private boolean istWeiterGedrueckt=false;
    
    
    public SpielanleitungZiel2()
    {
        JFrame frame = new JFrame("Spielanleitung");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(406,429);
        getBildschirmmitte();
        setLocation(getScreenWidthMiddle(), getScreenHeightMiddle()-20);
        setLayout(null);
        setResizable(false);
        
        ImageIcon background_image=new ImageIcon("src/pics/SpielanleitungZiel2.png");
        JLabel background = new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,400,400);
        
        zurueck = new JButton("Zurück");
        zurueck.setPreferredSize(new Dimension(10,20));
        zurueck.setBounds(30,350, 80, 20);
        zurueck.setForeground(new Color(0,0,0,255));
        zurueck.setBackground(new Color(255,255,255,255));
        zurueck.addActionListener(this);
        
        weiter = new JButton("Weiter");
        weiter.setPreferredSize(new Dimension(10,20));
        weiter.setBounds(290,350, 80, 20);
        weiter.setForeground(new Color(0,0,0,255));
        weiter.setBackground(new Color(255,255,255,255));
        weiter.addActionListener(this);
         
        add(weiter);
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
        
        if(e.getSource()==weiter)
        {
            istWeiterGedrueckt=true;
        }
    }   
    
    public boolean getIstZurueckGedrueckt()
    {
        return istZurueckGedrueckt;
    }
    
    public boolean getIstWeiterGedrueckt()
    {
        return istWeiterGedrueckt;
    }
    
    public void setIstZurueckGedrueckt(boolean zustand)
    {
        istZurueckGedrueckt=zustand;
    }
    
    public void setIstWeiterGedrueckt(boolean zustand)
    {
        istWeiterGedrueckt=zustand;
    }
}