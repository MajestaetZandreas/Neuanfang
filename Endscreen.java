import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Endscreen Fenster ffuer das Spiel Huepfburg-2D
 * 
 * @author (Cihan Karahan und Shium Rahman) 
 * @version (28.05.19)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public class Endscreen extends JFrame implements ActionListener
{
    private int screenWidthMiddle;//x-Koordinate für den Punkt, an welchem das Endscreen-Fenster erzeugt werden muss, damit sich dieses mittig auf dem Bildschirm befindet
    private int screenHeightMiddle;//y-Koordinate für den Punkt, an welchem das Endscreen-Fenster erzeugt werden muss, damit sich dieses mittig auf dem Bildschirm befindet
    
    private JButton zurueck;//Button um vom Endscreen in das Hauptmenue zurueckzukehren
    private boolean istZurueckGedrueckt=false;//Zustand des zurueck-Buttons
    
    /**
     * @author(Cihan Karahan und Shium Rahman)
     * 
     * Konstruktor der Klasse Endscreen
     * Erzeugt ein Fenster mit der entsprechenden Hintergrundgrafik, abhängig von Sieg oder Niederlage, und einem Knopf zum zurueckkehren in das Hauptmenue
     * 
     * @param istGewonnen boolean Variable, ob das Spiel gewonnen oder verloren wurde
     */
    public Endscreen(boolean istGewonnen)
    {
        super("Endscreen");//neues JFrame mit entsprechenden Titel
        setDefaultCloseOperation(EXIT_ON_CLOSE);//klicken des Schliessen-Knopfs (obere rechte Ecke) führt zum beenden der Applikation
        setSize(300,129);//Grösse des Fensters gesetzt
        getBildschirmmitte();//Bildschirmmitte bestimmt
        setLocation(screenWidthMiddle, screenHeightMiddle-20);//Ausrichtung des JFrame mit den ermittelten x- und y-Werten, damit das Fenster Mittig erscheint
                                                              //Die begruendete Veränderung des y-Wetes (screenHeightMiddle-20) bitte in der README-Datei nachlesen
        setLayout(null);//kein Layout soll auf dem JFrame vorherrschen
        setResizable(false);//Die Größe des Fensters kann nicht während der Laufzeit, am Fenster selbst, vorgenommen werden
        JLabel background = new JLabel("",erstelleScreen(istGewonnen),0);//Hintergrundbild gespeichert
        background.setBounds(0,0,300,100);//Ausrichtung an die richtige Position mit Beruecksichtigung der Größe der Grafik
        
        //Button erstellt
        zurueck = new JButton("Hauptmenü");//neuer Button mit Aufschrift
        zurueck.setPreferredSize(new Dimension(10,20));//Größe des Buttons gesetzt
        zurueck.setBackground(new Color(0,0,0,0));//Hintergrundfarbe auf schwarz gesetzt
        zurueck.setForeground(new Color(255,255,255,255));//Schriftfarbe auf weiss gesetzt
        zurueck.setBorderPainted(false);//der Rand des Buttons soll nicht gezeichnet werden
        zurueck.setBounds(-20,80, 130, 20);//Position und Größe des Buttons gesetzt
        zurueck.addActionListener(this);//ActionListener für diesen Button hinzugefuegt
        zurueck.setVisible(true);//Button wird angezeigt
        
        add(zurueck);//Button auf Frame geadded
        add(background);//Hintergrund auf Frame geadded
        setVisible(true);//Fenster wird angezeigt
    }
    
    /**
     * @author (Cihan Karahan und Shium Rahman)
     * 
     * Methode berechnet die Koordinaten eines Puktes, an welchem das Spielfeld-Fenster erzeugt werden muss
     */
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//Größe des Bildschirms, auf welchem die Applikation aktuell läuft, gespeichert
        screenWidthMiddle = ((int) ((d.getWidth() / 2 - 150)));//x-Koordinate, für den Punkt, an welchem das Endscreen-Fenster erzeugt werden muss, berechnet   (300/2 = 150)
        screenHeightMiddle = ((int) ((d.getHeight() / 2 - 65)));//y-Koordinate, für den Punkt, an welchem das Endscreen-Fenster erzeugt werden muss, berechnet   (129/2 = 65 gerundet)
    }
    
    /**
     * @author(Cihan Karahan und Shium Rahman)
     * 
     * Diese Methode ueberschreibt die Methode actionPerformed() in Klasse ActionListener
     * Methode prueft ob zurueck-Button gedrueckt wurde und fuehrt, falls gedrueckt, eine entsprechende Funktion aus
     * 
     * @param e ActionEvent, welches stattgefunden hat (z.B.: "auslösen" eines Knopfes)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==zurueck)//wenn ActionEvent e hat die Quelle: zurueck-Button
        {
            istZurueckGedrueckt=true;//Zustand des Buttons auf true, da dieser gedrueckt wurde
        }
    }

    /**
     * @author(Cihan Karahan und Shium Rahman)
     * Methode gibt ein BackgroundImage zurueck, abhängig von der Eingabe
     *      true=gewonnen=Gewwonen Bild
     *      falss=verloren= Verloren Bild
     *      
     * @param istGewonnen boolean Variable, ob das Spiel gewonnen oder verloren wurde
     */
    private ImageIcon erstelleScreen(boolean istGewonnen)
    {
        ImageIcon background_image;//neue Variable, in welch BackgroundImage gespeichert wird
        if(istGewonnen==true)//wenn gewonnen wurde
        {
            background_image=new ImageIcon("src/pics/Endscreen_gewonnen.png");//in Variable wird Sieg-Image gespeichert
        }
        else//wenn verloren wurde
        {
            background_image=new ImageIcon("src/pics/Endscreen_verloren.png");//in Variable wird Niederlage-Image gespeichert
        }
        return background_image;//Zurueckgabe des Images
    }
    
    /**
     * @author(Cihan Karahan und Shium Rahman)
     * get-Methode fuer Zustand des Zurueck-Buttons
     */
    public boolean getIstZurueckGedrueckt()
    {
        return istZurueckGedrueckt;
    }
    
    /**
     * @author(Cihan Karahan und Shium Rahman)
     * set-Methode fuer Zustand des Zurueck-Buttons
     */
    public void setIstZurueckGedrueckt(boolean zustand)
    {
        istZurueckGedrueckt=zustand;
    }
}
