import java.awt.*;
import javax.swing.*;

/**
 * Die Menue Klasse ist abstrakt und vererbt an alle Unterklassen, welche sich mit dem Hauptmenue des Spiels beschäftigen, um dort Code-Duplizierung zu vermeiden
 * 
 * @author (Clemens Zander und Gideon Schafroth) 
 * @version (24.05.2019)
 * 
 * Wir empfehlen die README Datei zu lesen, bevor Sie in diesen Code eintauchen
 */
public abstract class Menue extends JFrame
{
    private int screenWidthMiddle;//x-Koordinate, für den Punkt, an welchem ein Fenster (406*429px) erzeugt werden muss
    private int screenHeightMiddle;//y-Koordinate, für den Punkt, an welchem ein Fenster (406*429px) erzeugt werden muss
    
    /**
     * @author(Clemens Zander und Gideon Schafroth)
     * 
     * Methode berechnet die Koordinaten eines Puktes, an welchem ein Fenster mit den Massen: 406*429 erzeugt werden muss
     */
    public void getBildschirmmitte()
    {
        Dimension d = this.getToolkit().getScreenSize();//Größe des Bildschirms, auf welchem die Applikation aktuell läuft, gespeichert
        screenWidthMiddle = ((int) ((d.getWidth() / 2 - 203)));//x-Koordinate, für den Punkt, an welchem ein Fenster (406*429px) erzeugt werden muss berechnet   (406/2 = 203)
        screenHeightMiddle = ((int) ((d.getHeight() / 2 - 215)));//y-Koordinate, für den Punkt, an welchem ein Fenster (406*429px) erzeugt werden muss berechnet   (429/2 = 215 gerundet)
    }
    
    /**
     * @author(Clemens Zander und Gideon Schafroth)
     * 
     * get-Methode für screenWidthMiddle
     */
    public int getScreenWidthMiddle()
    {
        return screenWidthMiddle;
    }
    
    /**
     * @author(Clemens Zander und Gideon Schafroth)
     * 
     * get-Methode für screenHeightMiddle
     */
    public int getScreenHeightMiddle()
    {
        return screenHeightMiddle;
    }
}
