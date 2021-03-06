import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Vector;

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
    //Das Hauptmenü, welches vor dem Spielen geöffnet wird
    private JButton start;
    private JButton einstellungen;
    private JButton info;
    private JButton ende;
    private Game game;
    //Attribute für die Zustände der Buttons
    private boolean istSpielstartGedrueckt=false;
    private boolean istSpielanleitungGedrueckt=false;
    private boolean istBeendenGedrueckt=false;
    
    private Spielanleitung spielanleitungZiel;//Die Spielanleitung
    private Spielanleitung spielanleitungZiel2;//Die Spielanleitung
    private Spielanleitung spielanleitungTasten;//Die Spielanleitung
    public static void main(String[] arg)
    {
        new Hauptmenue();
    }
    
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
                    Game game = new Game(/*getHauptmenue()*/);
                    setVisible(false);
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
                    //istSpielanleitungGedrueckt=true;
                    öffneSpielanleitung();
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
                      System.exit(0); //und das Spiel geschlossen
                      //Attribut auf true geaendert, da der Button gedrueckt wurde
                   }
            }
        });
        
        // if(game!=null)
        // {
            // while(game.getEndscreen()!=null)
            // {
                // if(game.getEndscreen().getIstZurueckGedrueckt())
                // //wenn ende Button gedrueckt
                // {                    
                    // setVisible(true);
                // }
            // }
        // }

        
        //Buttons und Hintergrund auf Frame geadded
        add(start);
        add(einstellungen);
        add(info);
        add(ende);
        add(background);
        
        setVisible(true);//Frame wird angezeigt
        
    }
    
    public void  öffneSpielanleitung()
    {
        
        spielanleitungZiel = new Spielanleitung(1); //wird ein neues Spielanleitungsobjekt (1.Teil) erzeugt
        while(spielanleitungZiel.getIstZurueckGedrueckt()==false) //solange nicht zurück gedrückt wurde
        {
            setVisible(false); //bleibt das Hauptmenü ausgeblendet
            if(spielanleitungZiel.getIstWeiterGedrueckt()) //wenn weiter gedrueckt wird
            {
                spielanleitungZiel2 = new Spielanleitung(2); //2.Teil der Spielanleitung geoeffnet
                while(spielanleitungZiel2.getIstZurueckGedrueckt()==false) //solange nicht wieder zurueck gedrueckt wird
                {
                    spielanleitungZiel.setVisible(false); //1.Teil der Spielanleitung wird unsichtbar gemacht
                    if(spielanleitungZiel2.getIstWeiterGedrueckt()) //wenn weiter gedrueckt
                    {
                        spielanleitungTasten = new Spielanleitung(0); //3.Teil der Spielanleitung geoffnet
                        while(spielanleitungTasten.getIstZurueckGedrueckt()==false) //solange nicht wieder zurueck gedrueckt wird
                        {
                            spielanleitungZiel2.setVisible(false); //2.Teil der Spielanleitung wird unsichtbar gemacht
                        }
                        spielanleitungZiel2.setVisible(true); //2.Teil wieder eingeblendet
                        spielanleitungZiel2.setIstWeiterGedrueckt(false); //Weiter-nopf wird nicht gedrueckt
                        spielanleitungTasten.setVisible(false); //3.Teil wird nicht mehr angezeigt
                        spielanleitungTasten = null; //und geloescht
                    }
                }
                spielanleitungZiel.setVisible(true);
                spielanleitungZiel.setIstWeiterGedrueckt(false);
                spielanleitungZiel2.setVisible(false);
                spielanleitungZiel2 = null;
            }
        }
        setVisible(true); //danach wird das Hauptmenü wieder eingeblendet
        //SpielanleitungGedrueckt=false; //es wird keine neue Spielanleitung erzeugt oder eingeblendet
        spielanleitungZiel.setVisible(false); //die Spielanleitung wird ausgeblendet
        spielanleitungZiel = null; //und gelöscht
            
            
    }
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
