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
    private Spielfeld spielfeld;
    
    public void erstelleHauptmenue()
    {
        Hauptmenue frame = new Hauptmenue("Hauptmenü");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public Hauptmenue(String title)
    {
        super(title);
        start = new JButton("Spiel Starten");
        start.setBounds(120, 40, 160, 40);
        add(start);
        start.addActionListener(this);
        
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(120, 120, 160, 40);
        add(einstellungen);
        einstellungen.addActionListener(this);
        
        info = new JButton("Spielanleitung");
        info.setBounds(120, 200, 160, 40);
        add(info);
        info.addActionListener(this);
        
        ende = new JButton("Beenden");
        ende.setBounds(120, 280, 160, 40);
        add(ende);
        ende.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)
        {
            spielfensterStarten();
        }
        
        if(e.getSource()==einstellungen)
        {
            //ToDo:
        }
        
        if(e.getSource()==info)
        {
            //ToDo:
        }
        
        if(e.getSource()==ende)
        {
            System.exit(0);
        }
    }
    
    public void spielfensterStarten()
    {
        Spielfeld spielfeld = new Spielfeld("Hüpfburg-2D", 800, 600);
        dispose();
    }
}
