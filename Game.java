
/**
 * Die Hauptklasse des Spiels, vonm der alles "gesteuert" wird.
 * 
 * @author (Clemens Zander, Shium Rahman) 
 * @version (22.05.2019)
 */
public class Game
{
    private static Hauptmenue hauptmenue;
    private Kamera camera;
    
    public static void main(String[] args)
    {
        Game game = new Game();
        hauptmenue.erstelleHauptmenue();
    }
    
    public Game()
    {
        hauptmenue = new Hauptmenue(null);
        camera = new Kamera(0,0);
    }
}
