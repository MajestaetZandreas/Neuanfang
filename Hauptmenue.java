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
        start.setForeground(Color.WHITE);
        start.setBackground(Color.BLACK);                
        start.addActionListener(this);
        
        //Einstellungen-Button
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(buttonX, 130 + buttonYVerschiebung, 140, 40);
        einstellungen.setForeground(Color.WHITE);
        einstellungen.setBackground(Color.BLACK);                
        einstellungen.addActionListener(this);
        
        //Spielanleitung-Button
        info = new JButton("Spielanleitung");
        info.setBounds(buttonX, 210 + buttonYVerschiebung, 140, 40);
        info.setForeground(Color.WHITE); 
        info.setBackground(Color.BLACK);       
        info.addActionListener(this);
        
        //Beenden-Button
        ende = new JButton("Beenden");
        ende.setPreferredSize(new Dimension(10,20));
        ende.setBounds(buttonX, 290 + buttonYVerschiebung, 140, 40);
        ende.setForeground(Color.WHITE);
        ende.setBackground(Color.BLACK);
        ende.addActionListener(this);
        
        // ImageIcon icon = new ImageIcon ("src/pics/menu hintergrund.png");
        // JLabel l1 = new JLabel (icon);
        // add(l1);
        add(start);
        add(einstellungen);
        add(info);
        add(ende);
        
        add(background);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)
        {
            istSpielstartGedrueckt=true;
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
