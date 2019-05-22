import javax.swing.JFrame;

/**
 * Die Hauptklasse des Spiels, von der alles "gesteuert" wird.
 * 
 * @author (Clemens Zander, Shium Rahman) 
 * @version (22.05.2019)
 */
public class Game implements Runnable 
{
    private static Hauptmenue hauptmenue;
    private Kamera camera;
    private Spielfeld spielfeld;
    public boolean spielStart=true;
    private JFrame frame;
    
    
    public static void main(String[] args)
    {
        Game game = new Game();
    }
    
    public Game()
    {
        Hauptmenue frame = new Hauptmenue("Huepfburg-2D");
        camera = new Kamera(0,0);
    }
    
    /**
     * Überschreibung der Methode run() aus der Klasse Runnable
     */
    @Override
    public void run()
    {
        while(spielStart)
        {
            if(hauptmenue.getIstGestartet())
            {
                spielfeld = new Spielfeld("Huepfburg-2D", 1600, 1200);
                frame = spielfeld.getFrame();
            }
            
            while(frame.isVisible())//solange das Fenster angezeigt wird
            {
                // computeDelta();//Errechnung der Zeit für den vorhergehenden Schleifendurchlauf
                
                
                
                // checkKeys();
                // doLogic();
                // moveObjects();
                // cloneVectors();
                // setKamera();
                
                
                // repaint();//Neuzeichnung wird ausgelöst
                try
                {
                    Thread.sleep(10);//Programm wartet ("schläft")
                }
                catch(InterruptedException e)//sonst macht er nichts
                {
                }
            }
        }
    }
}
