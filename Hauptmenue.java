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
    
    public Hauptmenue(String title)
    {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,400);
        
        getBildschirmmitte();
        this.setLocation(screenWidthMiddle, screenHeightMiddle);
        
        this.setLayout(null);
        this.setVisible(true);
        start = new JButton("Spiel Starten");
        start.setBounds(120, 40, 160, 40);
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        add(start);
        start.addActionListener(this);
        
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(120, 120, 160, 40);
        einstellungen.setBackground(Color.black);
        einstellungen.setForeground(Color.white);
        add(einstellungen);
        einstellungen.addActionListener(this);
        
        info = new JButton("Spielanleitung");
        info.setBounds(120, 200, 160, 40);
        info.setBackground(Color.black);
        info.setForeground(Color.white);
        add(info);
        info.addActionListener(this);
        
        ende = new JButton("Beenden");
        ende.setBounds(120, 280, 160, 40);
        ende.setBackground(Color.black);
        ende.setForeground(Color.white);
        add(ende);
        ende.addActionListener(this);
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
