
/**
 * Beschreiben Sie hier die Klasse Kamera.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kamera
{
    
    private int xOffset,yOffset;
    private int sizeX, sizeY;

    /**
     * Konstruktor für Objekte der Klasse Kamera
     */
    public Kamera(int sizeX, int sizeY)
    {
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void centerOnEntity(final Spieler s, int spielBreite)
    {
        xOffset= (int)s.getX() - spielBreite/2;
        if(xOffset < 0)
        {
            xOffset = 0;
        }
        else
        {
            int t = sizeX * 64 - (spielBreite);
            if(xOffset > t)
            {
                xOffset = t;
            }
            
        }
        
    }
}
