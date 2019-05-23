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
    private JLabel spieltitel;
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    private boolean istSpielGestartet=false;
    private boolean istHauptmenueBeended=false;
    private static final int buttonX=132;
    private static final int buttonYVerschiebung=10;
    
    public Hauptmenue(String title)
    {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        getBildschirmmitte();
        setLocation(screenWidthMiddle, screenHeightMiddle);
        setLayout(null);
        setVisible(true);
        
        
        // spieltitel = new JLabel("Hüpfburg-2D");
        // add(spieltitel);
        
            //Buttons
        //SpielStart-Button
        start = new JButton("Spiel Starten");
        start.setBounds(buttonX, 150 + buttonYVerschiebung, 120, 30);
        start.setBackground(new Color(0,0,0,255));
        start.setForeground(new Color(255,255,255,255));
        add(start);
        start.addActionListener(this);
        
        //Einstellungen-Button
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(buttonX, 200 + buttonYVerschiebung, 120, 30);
        einstellungen.setBackground(new Color(0,0,0,255));
        einstellungen.setForeground(new Color(255,255,255,255));
        add(einstellungen);
        einstellungen.addActionListener(this);
        
        //Spielanleitung-Button
        info = new JButton("Spielanleitung");
        info.setBounds(buttonX, 250 + buttonYVerschiebung, 120, 30);
        info.setBackground(new Color(0,0,0,255));
        info.setForeground(new Color(255,255,255,255));
        add(info);
        info.addActionListener(this);
        
        //Beenden-Button
        ende = new JButton("Beenden");
        ende.setPreferredSize(new Dimension(10,20));
        ende.setBounds(buttonX, 300 + buttonYVerschiebung, 120, 30);
        ende.setBackground(new Color(0,0,0,255));
        ende.setForeground(new Color(255,255,255,255));
        add(ende);
        ende.addActionListener(this);
        
        // ImageIcon icon = new ImageIcon ("src/pics/menu hintergrund.png");
        // JLabel l1 = new JLabel (icon);
        // add(l1);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)
        {
            istSpielGestartet=true;
            this.setVisible(false);
        }
        
        if(e.getSource()==einstellungen)
        {
            //nur für das Design/beinhaltet keine Funktionen
        }
        
        if(e.getSource()==info)
        {
            //nur für das Design/beinhaltet keine Funktionen
        }
        
        if(e.getSource()==ende)
        {
            istHauptmenueBeended=true;
            System.exit(0);
        }
    }
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//
        screenWidthMiddle = ((int) ((d.getWidth() - this.getWidth()) / 2));
        screenHeightMiddle = ((int) ((d.getHeight() - this.getHeight()) / 2));
    }
    
    public boolean getIstHauptmenueBeended()
    {
        return istHauptmenueBeended;
    }
    
    public boolean getIstSpielGestartet()
    {
        return istSpielGestartet;
    }
}
