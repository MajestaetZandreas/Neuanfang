import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Beschreiben Sie hier die Klasse Endscreen.
 * 
 * @author (Cihan Karahan und Shium Rahman) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Endscreen extends JFrame implements ActionListener
{
    private int screenWidthMiddle;
    private int screenHeightMiddle;
    private JButton zurueck;
    private boolean istZurueckGedrueckt=false;
    
    
    public Endscreen(boolean istGewonnen)
    {
        super("Endscreen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,130);
        getBildschirmmitte();
        setLocation(screenWidthMiddle, screenHeightMiddle);
        setLayout(null);
        setResizable(false);
        JLabel background = new JLabel("",erstelleScreen(istGewonnen),0);
        background.setBounds(0,0,300,100);
        
        zurueck = new JButton("Hauptmenü");
        zurueck.setPreferredSize(new Dimension(10,20));
        zurueck.setBackground(new Color(0,0,0,0));
        zurueck.setForeground(new Color(255,255,255,255));
        zurueck.setBorderPainted(false);
        zurueck.setBounds(-20,80, 130, 20);
        zurueck.addActionListener(this);
        zurueck.setVisible(true);
        
        add(background);
        add(zurueck);
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
    
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//
        screenWidthMiddle = ((int) ((d.getWidth() - this.getWidth()) / 2));
        screenHeightMiddle = ((int) ((d.getHeight() - this.getHeight()) / 2));
    }
    
    public boolean getIstZurueckGedrueckt()
    {
        return istZurueckGedrueckt;
    }
    
    public void setIstZurueckGedrueckt(boolean zustand)
    {
        istZurueckGedrueckt=zustand;
    }
    
    private ImageIcon erstelleScreen(boolean istGewonnen)
    {
        ImageIcon background_image;
        if(istGewonnen==true)
        {
            background_image=new ImageIcon("src/pics/Endscreen_gewonnen.png");
        }
        else
        {
            background_image=new ImageIcon("src/pics/Endscreen_verloren.png");
        }
        return background_image;
    }
}
