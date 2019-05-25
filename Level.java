import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Beschreiben Sie hier die Klasse Level.
 * 
 * @author (Shium M.Rahman und Clemens. Z) 
 * @version (2..0)
 */
public class Level
{
    public Level(){
        
    }
    /**
     * hier werden die Anordnung der Plattformen festgelegt und somit ein Level aufgebaut
     * 
     * dies ist durch eine Integer-Variable, die das Level bestimmt und if-Anweisungen, die den Integer-wert abfragt und dazu die jeweilige Plattformanordnung bestimmt, noch erweiterbar
     */
    public ArrayList<Plattform> erstelleLevel()
    {
        BufferedImage[] plattform_image = loadPics("src/pics/plattform2.png",1);//Bild datei der Plattformen wird ins Spiel geladen
        ArrayList<Plattform> plattforms = new ArrayList<Plattform>();
        
        //Plattformen werden erstellt
        Plattform plattform01 = new Plattform(plattform_image,0,870,100);
        Plattform plattform02 = new Plattform(plattform_image,174,870,100);
        Plattform plattform03 = new Plattform(plattform_image,294,870,100);
        Plattform plattform04 = new Plattform(plattform_image,414,870,100);
        Plattform plattform05 = new Plattform(plattform_image,618,870,100);
        Plattform plattform06 = new Plattform(plattform_image,758,870,100);
        Plattform plattform07 = new Plattform(plattform_image,985,870,100);
        Plattform plattform08 = new Plattform(plattform_image,1165,800,100);
        Plattform plattform09 = new Plattform(plattform_image,989,720,100);
        Plattform plattform10 = new Plattform(plattform_image,818,720,100);
        Plattform plattform11 = new Plattform(plattform_image,638,710,100);
        Plattform plattform12 = new Plattform(plattform_image,444,710,100);
        Plattform plattform13 = new Plattform(plattform_image,244,720,100);
        Plattform plattform14 = new Plattform(plattform_image,55,720,100);
        Plattform plattform15 = new Plattform(plattform_image,1,633,100);
        Plattform plattform16 = new Plattform(plattform_image,138,560,100);
        Plattform plattform17 = new Plattform(plattform_image,268,489,100);
        Plattform plattform18 = new Plattform(plattform_image,167,410,100);
        Plattform plattform19 = new Plattform(plattform_image,22,363,100);
        Plattform plattform20 = new Plattform(plattform_image,479,489,100);
        Plattform plattform21 = new Plattform(plattform_image,677,489,100);
        Plattform plattform22 = new Plattform(plattform_image,870,489,100);
        Plattform plattform23 = new Plattform(plattform_image,1069,497,100);
        Plattform plattform24 = new Plattform(plattform_image,1069,497,100);
        Plattform plattform25 = new Plattform(plattform_image,1160,430,100);
        Plattform plattform26 = new Plattform(plattform_image,1059,360,100);
        Plattform plattform27 = new Plattform(plattform_image,915,298,100);
        Plattform plattform28 = new Plattform(plattform_image,781,250,100);
        Plattform plattform29 = new Plattform(plattform_image,586,245,100);
        Plattform plattform30 = new Plattform(plattform_image,390,242,100);
        Plattform plattform31 = new Plattform(plattform_image,283,200,100);
        Plattform plattform32 = new Plattform(plattform_image,112,140,100);
        Plattform plattform33 = new Plattform(plattform_image,320,100,100);
        Plattform plattform34 = new Plattform(plattform_image,500,80,100);
        Plattform plattform35 = new Plattform(plattform_image,865,606,100);
        
        //Plattfotmen werden der Arraylist plattforms hinzugefügt
        plattforms.add(plattform01);plattforms.add(plattform02);plattforms.add(plattform03);
        plattforms.add(plattform04);plattforms.add(plattform05);plattforms.add(plattform06);
        plattforms.add(plattform07);plattforms.add(plattform08);plattforms.add(plattform09);
        plattforms.add(plattform10);plattforms.add(plattform11);plattforms.add(plattform12);
        plattforms.add(plattform13);plattforms.add(plattform14);plattforms.add(plattform15);
        plattforms.add(plattform16);plattforms.add(plattform17);plattforms.add(plattform18);
        plattforms.add(plattform19);plattforms.add(plattform20);plattforms.add(plattform21);
        plattforms.add(plattform22);plattforms.add(plattform23);plattforms.add(plattform24);
        plattforms.add(plattform25);plattforms.add(plattform26);plattforms.add(plattform27);
        plattforms.add(plattform28);plattforms.add(plattform29);plattforms.add(plattform30);
        plattforms.add(plattform31);plattforms.add(plattform32);plattforms.add(plattform33);
        plattforms.add(plattform34);plattforms.add(plattform35);
       
        return plattforms;
        
            
    }
    
    /**
     * Diese Methode lädt die Bilder aus dem pics-Ordner
     * 
     * @param path - der Speicherort der Bilder
     *        pics - die Anzahl Bilder im Ordner
     */
    private BufferedImage[] loadPics(String path, int pics)
    {  
        BufferedImage[] anim = new BufferedImage[pics];
        BufferedImage source=null;
        
        URL pic_url=getClass().getClassLoader().getResource(path);
        
        try
        {
            source=ImageIO.read(pic_url);
        }
        catch(IOException e)
        {
        }
        
        for(int i=0;i<pics;i++)
        {
            anim[i]=source.getSubimage(i*source.getWidth()/pics, 0, source.getWidth()/pics, source.getHeight());
        }
        
        return anim;
    } 
    
    
}
