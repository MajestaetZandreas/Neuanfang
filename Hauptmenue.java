import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Hauptmenüfenster des Hüpfburg-2D Spiels
 *
 * @author (Clemens Zander, Shium Rahman)
 * @version (15.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Hauptmenue extends Menue implements ActionListener
{
    //Attribute für die vier Buttons, welche auf dem Fenster zu sehen sein sollen
    private JButton start;
    private JButton einstellungen;
    private JButton info;
    private JButton ende;
    
    //Attribute für die Zustände der Buttons
    private boolean istSpielstartGedrueckt=false;
    private boolean istSpielanleitungGedrueckt=false;
    private boolean istBeendenGedrueckt=false;
    
    /**
     * Konstruktor der Klasse Hauptmenue
     */
    public Hauptmenue()
    {
        JFrame frame = new JFrame("Hauptmenü");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(406,429);
        getBildschirmmitte();
        setLocation(getScreenWidthMiddle(), getScreenHeightMiddle()-20);
        setLayout(null);
        setResizable(false);
        
        //Hintergrundbild
        ImageIcon background_image=new ImageIcon("src/pics/menu_hintergrund.png");//PNG-Datei aus src-Folder gespeichert
        JLabel background = new JLabel("",background_image,JLabel.CENTER);//auf JLabel eingefügt
        background.setBounds(0,0,400,400);//Position und Größe gesetzt
        
        //Buttons:
        //SpielStart-Button
        start = new JButton("Spiel Starten");//neuer Button mit Titel
        start.setBounds(132, 80, 140, 40);//Position und Größe gesetzt
        start.setForeground(Color.WHITE);//Schriftfarbe gesetzt
        start.setBackground(Color.BLACK);//Hintergrundfarbe gesetzt                
        start.addActionListener(this);//neuer Actionlistener für den Button erstellt
        
        //Einstellungen-Button
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(132, 160, 140, 40);
        einstellungen.setForeground(Color.WHITE);
        einstellungen.setBackground(Color.BLACK);                
        einstellungen.addActionListener(this);
        
        //Spielanleitung-Button
        info = new JButton("Spielanleitung");
        info.setBounds(132, 240, 140, 40);
        info.setForeground(Color.WHITE); 
        info.setBackground(Color.BLACK);       
        info.addActionListener(this);
        
        //Beenden-Button
        ende = new JButton("Beenden");
        ende.setBounds(132, 320, 140, 40);
        ende.setForeground(Color.WHITE);
        ende.setBackground(Color.BLACK);
        ende.addActionListener(this);
        
        //Buttons und Hintergrund auf Frame geadded
        add(start);
        add(einstellungen);
        add(info);
        add(ende);
        add(background);
        
        setVisible(true);//Frame wird angezeigt
    }
    
    /**
     * Überschreibung der Methode actionPerformed aus der Klasse ActionListener
     * Diese Methode führt eine bestimmte Funktion/Zuweisung aus, je nachdem, welcher Button gedrueckt wurde
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)//wenn start Button gedrueckt
        {
            istSpielstartGedrueckt=true;//Attribut auf true geaendert, da der Button gedrueckt wurde
        }
        
        if(e.getSource()==einstellungen)//wenn einstellungen Button gedrueckt
        {
            //nur für das Design/beinhaltet keine Funktionen
        }
        
        if(e.getSource()==info)//wenn info Button gedrueckt
        {
            istSpielanleitungGedrueckt=true;//Attribut auf true geaendert, da der Button gedrueckt wurde
        }
        
        if(e.getSource()==ende)//wenn ende Button gedrueckt
        {
            istBeendenGedrueckt=true;//Attribut auf true geaendert, da der Button gedrueckt wurde
        }
    }
    
    /*#-----------------------get- und set-Methoden für die Attribute, welche die Zustände der Buttons speichern----------------------*/
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
