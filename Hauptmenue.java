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
public class Hauptmenue extends Menue 
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
     * @author(Clemens Zander und Shium Rahman)
     * Konstruktor der Klasse Hauptmenue
     * Erzeugt ein neues Fenster für das Hauptmenue in der Mitte (leicht verschoben => Begruendung dafuer: siehe README-Datei) des Bildschirms,
     * welches vier Buttons mit ActionListeners besitzt, durch welche Aktionen hervorgerufen werden koennen
     */
    public Hauptmenue()
    {
        JFrame frame = new JFrame("Hauptmenü");//neues Fenster mit Titel
        setDefaultCloseOperation(EXIT_ON_CLOSE);//klicken des Schliessen-Knopfs (obere rechte Ecke) führt zum beenden der Applikation
        setSize(406,429);//Groesse des Fensters gesetzt
        getBildschirmmitte();//Bildschirmmitte bestimmt
        setLocation(getScreenWidthMiddle(), getScreenHeightMiddle()-20);//Ausrichtung des JFrame mit den ermittelten x- und y-Werten, damit das Fenster Mittig erscheint
                                                                        //Die begruendete Veränderung des y-Wetes (screenHeightMiddle-20) bitte in der README-Datei nachlesen
        setLayout(null);//kein Layout soll auf dem JFrame vorherrschen
        setResizable(false);//Die Größe des Fensters kann nicht während der Laufzeit, am Fenster selbst, vorgenommen werden
        
        //Hintergrundbild
        ImageIcon background_image=new ImageIcon("src/pics/menu_hintergrund.png");//PNG-Datei aus src-Folder gespeichert
        JLabel background = new JLabel("",background_image,JLabel.CENTER);//auf JLabel eingefügt
        background.setBounds(0,0,400,400);//Position und Größe gesetzt
        
        //Buttons:
        //SpielStart-Button
        start = new JButton("Spiel Starten");//neuer Button mit Titel
        start.setBounds(132, 80, 140, 40);//Position und Größe gesetzt
        // start.setForeground(Color.WHITE);//Schriftfarbe gesetzt
        // start.setBackground(Color.BLACK);//Hintergrundfarbe gesetzt                
        start.addActionListener(new java.awt.event.ActionListener() {
                        // Beim Drücken des start-Buttons wird actionPerformed aufgerufen
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if(e.getSource()==start)
                            //wenn start Button gedrueckt
                            {
                                istSpielstartGedrueckt=true;
                                //Attribut auf true geaendert, da der Button gedrueckt wurde
                            }
   
                        }
                    });//neuer Actionlistener für den Button erstellt
    
        //Einstellungen-Button
        einstellungen = new JButton("Einstellungen");
        einstellungen.setBounds(132, 160, 140, 40);
        // einstellungen.setForeground(Color.WHITE);
        // einstellungen.setBackground(Color.BLACK);                
        einstellungen.addActionListener(new java.awt.event.ActionListener() {
                        // Beim Drücken des einstellungen-Button wird actionPerformed aufgerufen
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if(e.getSource()==einstellungen)
                            //wenn einstellungen Button gedrueckt
                            {
                                //nur für das Design/beinhaltet keine Funktionen
                            }
                        }
                    });
        
        //Spielanleitung-Button
        info = new JButton("Spielanleitung");
        info.setBounds(132, 240, 140, 40);
        // info.setForeground(Color.WHITE); 
        // info.setBackground(Color.BLACK);       
        info.addActionListener(new java.awt.event.ActionListener() {
                        // Beim Drücken des info-Buttons wird actionPerformed aufgerufen
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if(e.getSource()==info)
                            //wenn info Button gedrueckt
                            {
                                istSpielanleitungGedrueckt=true;
                                //Attribut auf true geaendert, da der Button gedrueckt wurde
                            }
                        }
                    });
        
        //Beenden-Button
        ende = new JButton("Beenden");
        ende.setBounds(132, 320, 140, 40);
        // ende.setForeground(Color.WHITE);
        // ende.setBackground(Color.BLACK);
        ende.addActionListener(new java.awt.event.ActionListener() {
                    // Beim Drücken des ende-Buutons wird actionPerformed aufgerufen
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                        if(e.getSource()==ende)
                        //wenn ende Button gedrueckt
                            {
                                istBeendenGedrueckt=true;
                                //Attribut auf true geaendert, da der Button gedrueckt wurde
                            }
                        }
                    });
        
        //Buttons und Hintergrund auf Frame geadded
        add(start);
        add(einstellungen);
        add(info);
        add(ende);
        add(background);
        
        setVisible(true);//Frame wird angezeigt
    }
    
    // /**
     // * @author(Clemens Zander Shium Rahman)
     // * 
     // * Überschreibung der Methode actionPerformed aus der Klasse ActionListener
     // * Diese Methode führt eine bestimmte Funktion/Zuweisung aus, je nachdem, welcher Button gedrueckt wurde
     // * 
     // * @param e ActionEvent, welches stattgefunden hat (z.B.: "auslösen" eines Knopfes)
     // */
    // @Override
    // public void actionPerformed(ActionEvent e)
    // {
        // if(e.getSource()==start)//wenn start Button gedrueckt
        // {
            // istSpielstartGedrueckt=true;//Attribut auf true geaendert, da der Button gedrueckt wurde
        // }
        
        // if(e.getSource()==einstellungen)//wenn einstellungen Button gedrueckt
        // {
            // nur für das Design/beinhaltet keine Funktionen
        // }
        
        // if(e.getSource()==info)//wenn info Button gedrueckt
        // {
            // istSpielanleitungGedrueckt=true;//Attribut auf true geaendert, da der Button gedrueckt wurde
        // }
        
        // if(e.getSource()==ende)//wenn ende Button gedrueckt
        // {
            // istBeendenGedrueckt=true;//Attribut auf true geaendert, da der Button gedrueckt wurde
        // }
    // }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * get-Methode fuer den Zustand des Beenden-Buttons
     */
    public boolean getIstBeendenGedrueckt()
    {
        return istBeendenGedrueckt;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * get-Methode fuer den Zustand des Spielstart-Buttons
     */
    public boolean getIstSpielstartGedrueckt()
    {
        return istSpielstartGedrueckt;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * get-Methode fuer den Zustand des Spielanleitung-Buttons
     */
    public boolean getIstSpielanleitungGedrueckt()
    {
        return istSpielanleitungGedrueckt;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * set-Methode fuer den Zustand des Beenden-Buttons
     * @param zustand boolean Wert fuer den Zustand des Buttons
     */
    public void setIstBeendenGedrueckt(boolean zustand)
    {
        istBeendenGedrueckt=zustand;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * set-Methode fuer den Zustand des Spielstart-Buttons
     * @param zustand boolean Wert fuer den Zustand des Buttons
     */
    public void setIstSpielstartGedrueckt(boolean zustand)
    {
        istSpielstartGedrueckt=zustand;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * set-Methode fuer den Zustand des Spielanleitung-Buttons
     * @param zustand boolean Wert fuer den Zustand des Buttons
     */
    public void setIstSpielanleitungGedrueckt(boolean zustand)
    {
        istSpielanleitungGedrueckt=zustand;
    }
}
