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
        
        ImageIcon background_image=new ImageIcon("src/pics/menu hintergrund.png");
        JLabel background = new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,400,400);
        add(background);
        
            //Buttons
        //SpielStart-Button
        start = new JButton("Spiel Starten");
        start.setBounds(buttonX, 50 + buttonYVerschiebung, 140, 40);
        start.setBackground(new Color(0,0,0,255));
        start.setForeground(new Color(255,255,255,255));
        add(start);
        start.addActionListener(this);
        
        //Einstellungen-Button
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(buttonX, 130 + buttonYVerschiebung, 140, 40);
        einstellungen.setBackground(new Color(0,0,0,255));
        einstellungen.setForeground(new Color(255,255,255,255));
        add(einstellungen);
        einstellungen.addActionListener(this);
        
        //Spielanleitung-Button
        info = new JButton("Spielanleitung");
        info.setBounds(buttonX, 210 + buttonYVerschiebung, 140, 40);
        info.setBackground(new Color(0,0,0,255));
        info.setForeground(new Color(255,255,255,255));
        add(info);
        info.addActionListener(this);
        
        //Beenden-Button
        ende = new JButton("Beenden");
        ende.setPreferredSize(new Dimension(10,20));
        ende.setBounds(buttonX, 290 + buttonYVerschiebung, 140, 40);
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
