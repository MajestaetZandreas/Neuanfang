import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Spielanleitung Fenster fuer das Spiel Huepfburg-2D
 * 
 * @author (Clemens Zander, Shium  Rahman) 
 * @version (28.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Spielanleitung extends Menue implements ActionListener
{
    //Attribute fuer die Buttons auf dem Fenster
    private JButton zurueck;
    private JButton weiter;
    
    //Attribute fuer die Zustaende der Buttons
    private boolean istZurueckGedrueckt=false;
    private boolean istWeiterGedrueckt=false;
    
    private String pfad;//Pfadangabe fuer das Hintergrundbild, welches angezeigt werden soll
    
    /**
     * @author(Clemens Zander und Shium Rahman
     * Konstruktor der Klasse Spielanleitung
     * Erzeugt ein Spielanleitungs-Fenster, in der Mitte (leicht verschoben => Begruendung in der README-Datei) des Bildschirms, mit Buttons zum "weiterblättern" und zurueckkeheren, abhaengig von dem Spielanleitungsteil
     * 
     * @param spielanleitung Teil der dreiteiligen Spielanleitung
     *      0= Spielanleitung fuer die Steuerung ueber die Tasten auf der Tastatur
     *      1= 1. Teil der Spielanleitung ueber das Spielziel
     *      2= 2. Teil der Spielanleitung ueber das Spielziel
     */
    public Spielanleitung(int spielanleitung)
    {
        JFrame frame = new JFrame("Spielanleitung");//neues Fenster mit Titel
        setDefaultCloseOperation(EXIT_ON_CLOSE);//klicken des Schliessen-Knopfs (obere rechte Ecke) führt zum beenden der Applikation
        setSize(406,429);//Groesse des Fensters gesetzt
        getBildschirmmitte();//Bildschirmmitte bestimmt
        setLocation(getScreenWidthMiddle(), getScreenHeightMiddle()-20);//Ausrichtung des JFrame mit den ermittelten x- und y-Werten, damit das Fenster Mittig erscheint
                                                                        //Die begruendete Veränderung des y-Wetes (screenHeightMiddle-20) bitte in der README-Datei nachlesen
        setLayout(null);//kein Layout soll auf dem J
        setResizable(false);//Die Größe des Fensters kann nicht während der Laufzeit, am Fenster selbst, vorgenommen werden
        
        //Hinzufuegen des Hintergrundbildes
        spielanleitungGrafik(spielanleitung);//Pfad bestimmt, welcher zu dem geweahlten Spielanleitungsteil gehoert (siehe Parameter)
        ImageIcon background_image=new ImageIcon(pfad);//Hintergrundbild gespeichert/gesetzt
        JLabel background = new JLabel("",background_image,JLabel.CENTER);//Hintergrund bild auf JLabel geadded
        background.setBounds(0,0,400,400);//Position und Groesse des Hintergurndes Gesetzt
        
        //Hinzufuegen des Zurueck-Buttons, ueber welchen jede Spielanleitung verfuegt
        zurueck = new JButton("Zurück");//neuer Button
        zurueck.setBounds(30,350, 80, 20);//Position und Groesse des Button gesetzt
        zurueck.setForeground(new Color(0,0,0,255));//Schriftfarbe auf schwarz gesetzt
        zurueck.setBackground(new Color(255,255,255,255));//Hintergrundfarbe auf weiss gesetzt
        zurueck.addActionListener(this);//ActionListener fuer diesen Button hinzugefuegt
        if(spielanleitung>0)//falls eine der beiden Spielanleitungen erzeugt werden sollen, die das Spielziel angeben (siehe Parameter), wird ein weiterer Button hinzugefuegt,
                            //denn von diesen Spielanleitungs-Fenster, soll man weiter in die anderen Spielanleitungs-Fenster kommen
        {
            //Hinzufuegen eines Weiter-Buttons, ueber welchen man zu den weiteren Spielanleitungsteilen kommt
            weiter = new JButton("Weiter");
            weiter.setPreferredSize(new Dimension(10,20));
            weiter.setBounds(290,350, 80, 20);
            weiter.setForeground(new Color(0,0,0,255));
            weiter.setBackground(new Color(255,255,255,255));
            weiter.addActionListener(this);
            
            add(weiter);//Button dem Fenster hinzugefuegt
        }
        
        //Hintergrund und Button zu Fenster hinzugefuegt
        add(zurueck);
        add(background);
        
        setVisible(true);//Fenster wird angezeigt
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * Methode speichert in Attribut pfad (Typ=String) den Pfad zum Hintergrundbild, welches der gewaehlten Spielanleitun entspricht
     * 
     * @param spielanleitung int Wert fuer die Wahl des Spielanleitungsteils
     */
    public String spielanleitungGrafik(int spielanleitung)
    {
        if(spielanleitung==0)//wenn der Spielanleitungsteil fuer die Steuerung erzuegt werden soll
        {
            pfad="src/pics/SpielanleitungTasten.png";//in pfad Pfad auf entsprechendes Hintergrundbild gespeichert
        }
        if(spielanleitung==1)//wenn der Spielanleitungsteil fuer das Spielziel (1.Teil) erzuegt werden soll
        {
            pfad="src/pics/SpielanleitungZiel.png";//in pfad Pfad auf entsprechendes Hintergrundbild gespeichert
        }
        if(spielanleitung==2)//wenn der Spielanleitungsteil fuer das Spielziel (2.Teil) erzuegt werden soll
        {
            pfad="src/pics/SpielanleitungZiel2.png";//in pfad Pfad auf entsprechendes Hintergrundbild gespeichert   
        }
        return pfad;//Pfad wird zurueckgegeben
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * 
     * Diese Methode ueberschreibt die Methode actionPerformed() in Klasse ActionListener
     * Methode prueft ob zurueck-Button oder weiter-Button gedrueckt wurde und fuehrt, falls gedrueckt, eine entsprechende Funktion aus
     * 
     * @param e ActionEvent, welches stattgefunden hat (z.B.: "auslösen" eines Knopfes)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==zurueck)//wenn zurueck-Button gedrueckt
        {
            istZurueckGedrueckt=true;//Zustand des Knopfes geander, da diser gedrueckt wurde
        }
        
        if(e.getSource()==weiter)//wenn weiter-Button gedrueckt
        {
            istWeiterGedrueckt=true;//Zustand des Knopfes geander, da diser gedrueckt wurde
        }
    }   
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * get-Methode fuer den Zustand des zureuck-Buttons
     */
    public boolean getIstZurueckGedrueckt()
    {
        return istZurueckGedrueckt;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * get-Methode fuer den Zustand des weiter-Buttons
     */
    public boolean getIstWeiterGedrueckt()
    {
        return istWeiterGedrueckt;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * set-Methode fuer den Zustand des zureuck-Buttons
     * @param zustand boolean Wert fuer Zustand des Buttons
     */
    public void setIstZurueckGedrueckt(boolean zustand)
    {
        istZurueckGedrueckt=zustand;
    }
    
    /**
     * @author(Clemens Zander und Shium Rahman)
     * set-Methode fuer den Zustand des weiter-Buttons
     * @param zustand boolean Wert fuer Zustand des Buttons
     */
    public void setIstWeiterGedrueckt(boolean zustand)
    {
        istWeiterGedrueckt=zustand;
    }
}