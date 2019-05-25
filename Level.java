import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Beschreiben Sie hier die Klasse Level.
 * 
 * @author (Shium M.Rahman) 
 * @version (1.0)
 */
public class Level
{
    public Level(){
        
    }
    
    public ArrayList<Plattform> erstelleLevel()
    {
        BufferedImage[] plattform_image = loadPics("src/pics/plattform2.png",1);
        ArrayList<Plattform> plattforms = new ArrayList<Plattform>();
        
        
        Plattform plattform01 = new Plattform(plattform_image,000,950,100);
        Plattform plattform02 = new Plattform(plattform_image,120,950,100);
        Plattform plattform03 = new Plattform(plattform_image,240,950,100);
        Plattform plattform04 = new Plattform(plattform_image,280,950,100);
        Plattform plattform05 = new Plattform(plattform_image,480,950,100);
        Plattform plattform06 = new Plattform(plattform_image,600,950,100);
        Plattform plattform07 = new Plattform(plattform_image,680,950,100);
        Plattform plattform08 = new Plattform(plattform_image,840,950,100);
        Plattform plattform09 = new Plattform(plattform_image,960,950,100);
        Plattform plattform10 = new Plattform(plattform_image,1080,950,100);
        Plattform plattform11 = new Plattform(plattform_image,1200,950,100);
        Plattform plattform12 = new Plattform(plattform_image,1160,950,100);
        Plattform plattform13 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform14 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform15 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform16 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform17 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform18 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform19 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform20 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform21 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform22 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform23 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform24 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform25 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform26 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform27 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform28 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform29 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform30 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform31 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform32 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform33 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform34 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform35 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform36 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform37 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform38 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform39 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform40 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform41 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform42 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform43 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform44 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform45 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform46 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform47 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform48 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform49 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform50 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform51 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform52 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform53 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform54 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform55 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform56 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform57 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform58 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform59 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform60 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform61 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform62 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform63 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform64 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform65 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform66 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform67 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform68 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform69 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform70 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform71 = new Plattform(plattform_image,(int) (1160*Math.random())+1,(int) (850*Math.random())+1,100);
        Plattform plattform72 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform73 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform74 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform75 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform76 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform77 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform78 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform79 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform80 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform81 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform82 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform83 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
        Plattform plattform84 = new Plattform(plattform_image,(int) (1160*Math.random())+800,(int) (850*Math.random())+1,100);
       
        plattforms.add(plattform01);
        plattforms.add(plattform02);
        plattforms.add(plattform03);
        plattforms.add(plattform04);
        plattforms.add(plattform05);
        plattforms.add(plattform06);
        plattforms.add(plattform07);
        plattforms.add(plattform08);
        plattforms.add(plattform09);
        plattforms.add(plattform10);
        plattforms.add(plattform11);
        plattforms.add(plattform12);
        plattforms.add(plattform13);
        plattforms.add(plattform14);
        plattforms.add(plattform15);
        plattforms.add(plattform16);
        plattforms.add(plattform17);
        plattforms.add(plattform18);
        plattforms.add(plattform19);
        plattforms.add(plattform20);
        plattforms.add(plattform21);
        plattforms.add(plattform22);
        plattforms.add(plattform23);
        plattforms.add(plattform24);
        plattforms.add(plattform25);
        plattforms.add(plattform26);
        plattforms.add(plattform27);
        plattforms.add(plattform28);
        plattforms.add(plattform29);
        plattforms.add(plattform30);
        plattforms.add(plattform31);
        plattforms.add(plattform32);
        plattforms.add(plattform33);
        plattforms.add(plattform34);
        plattforms.add(plattform35);
        plattforms.add(plattform36);
        plattforms.add(plattform37);
        plattforms.add(plattform38);
        plattforms.add(plattform39);
        plattforms.add(plattform40);
        plattforms.add(plattform41);
        plattforms.add(plattform42);
        plattforms.add(plattform43);
        plattforms.add(plattform44);
        plattforms.add(plattform45);
        plattforms.add(plattform46);
        plattforms.add(plattform47);
        plattforms.add(plattform48);
        plattforms.add(plattform49);
        plattforms.add(plattform50);
        plattforms.add(plattform51);
        plattforms.add(plattform52);
        plattforms.add(plattform53);
        plattforms.add(plattform54);
        plattforms.add(plattform55);
        plattforms.add(plattform56);
        plattforms.add(plattform57);
        plattforms.add(plattform58);
        plattforms.add(plattform59);
        plattforms.add(plattform60);
        plattforms.add(plattform61);
        plattforms.add(plattform62);
        plattforms.add(plattform63);
        plattforms.add(plattform64);
        plattforms.add(plattform65);
        plattforms.add(plattform66);
        plattforms.add(plattform67);
        plattforms.add(plattform68);
        plattforms.add(plattform69);
        plattforms.add(plattform70);
        plattforms.add(plattform71);
        plattforms.add(plattform72);
        plattforms.add(plattform73);
        plattforms.add(plattform74);
        plattforms.add(plattform75);
        plattforms.add(plattform76);
        plattforms.add(plattform77);
        plattforms.add(plattform78);
        plattforms.add(plattform79);
        plattforms.add(plattform80);
        plattforms.add(plattform81);
        plattforms.add(plattform82);
        plattforms.add(plattform83);
        plattforms.add(plattform84);
            
        
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
