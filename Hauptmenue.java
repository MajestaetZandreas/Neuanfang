import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Haupt-GUI des Hüpfburg-2D Spiels
 *
 * @author (Clemens Zander, Shium Rahman)
 * @version (15.05.2019)
 */
public class Hauptmenue extends JFrame implements ActionListener
{
    private JButton start;
    private JButton einstellungen;
    private JButton info;
    private JButton ende;
    
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    private boolean istSpielstartGedrueckt=false;
    private boolean istBeendenGedrueckt=false;
    private boolean istSpielanleitungGedrueckt=false;
    // private static final int buttonColorR=0;
    // private static final int buttonColorG=0;
    // private static final int buttonColorB=0;
    // private static final int buttonTransperenz=50;
    // private static final int fontColorR=255;
    // private static final int fontColorG=255;
    // private static final int fontColorB=255;
    // private static final int fontTransperenz=255;
    private static final int buttonX=132;
    private static final int buttonYVerschiebung=10;
    
    public Hauptmenue()
    {
        super("Hauptmenü");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        getBildschirmmitte();
        setLocation(screenWidthMiddle, screenHeightMiddle);
        setLayout(null);
        setResizable(false);
        
        ImageIcon background_image=new ImageIcon("src/pics/menu_hintergrund.png");
        JLabel background = new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,400,400);
        
        
            //Buttons
        //SpielStart-Button
        start = new JButton("Spiel Starten");
        start.setBounds(buttonX, 50 + buttonYVerschiebung, 140, 40);
        start.setForeground(new Color(255,255,255,255));
        start.setBackground(new Color(0,0,0,255));                
        start.addActionListener(this);
        
        //Einstellungen-Button
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(buttonX, 130 + buttonYVerschiebung, 140, 40);
        einstellungen.setForeground(new Color(255,255,255,255));
        einstellungen.setBackground(new Color(0,0,0,255));                
        einstellungen.addActionListener(this);
        
        //Spielanleitung-Button
        info = new JButton("Spielanleitung");
        info.setBounds(buttonX, 210 + buttonYVerschiebung, 140, 40);
        info.setForeground(new Color(255,255,255,255)); 
        info.setBackground(new Color(0,0,0,255));       
        info.addActionListener(this);
        
        //Beenden-Button
        ende = new JButton("Beenden");
        ende.setPreferredSize(new Dimension(10,20));
        ende.setBounds(buttonX, 290 + buttonYVerschiebung, 140, 40);
        ende.setForeground(new Color(255,255,255,255));
        ende.setBackground(new Color(0,0,0,255));
        ende.addActionListener(this);
        
        // ImageIcon icon = new ImageIcon ("src/pics/menu hintergrund.png");
        // JLabel l1 = new JLabel (icon);
        // add(l1);
        add(background);
        add(start);
        add(einstellungen);
        add(info);
        add(ende);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)
        {
            istSpielstartGedrueckt=true;
            this.setVisible(false);
        }
        
        if(e.getSource()==einstellungen)
        {
            //nur für das Design/beinhaltet keine Funktionen
        }
        
        if(e.getSource()==info)
        {
            istSpielanleitungGedrueckt=true;
        }
        
        if(e.getSource()==ende)
        {
            istBeendenGedrueckt=true;
            System.exit(0);
        }
    }
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//
        screenWidthMiddle = ((int) ((d.getWidth() - this.getWidth()) / 2));
        screenHeightMiddle = ((int) ((d.getHeight() - this.getHeight()) / 2));
    }
    
    public boolean getIstBeendenGedrueckt()
    {
        return istBeendenGedrueckt;
    }
    
    public boolean getIstSpielstartGedrueckt()
    {
        return istSpielstartGedrueckt;
    }
    
    public boolean getIstSpielanleitungGedrueckt()
    {
        return istSpielanleitungGedrueckt;
    }
    
    public void setIstBeendenGedrueckt(boolean zustand)
    {
        istBeendenGedrueckt=zustand;
    }
    
    public void setIstSpielstartGedrueckt(boolean zustand)
    {
        istSpielstartGedrueckt=zustand;
    }
    
    public void setIstSpielanleitungGedrueckt(boolean zustand)
    {
        istSpielanleitungGedrueckt=zustand;
    }
}
