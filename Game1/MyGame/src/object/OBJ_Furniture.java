package object;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Furniture extends SuperObject {
    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Furniture(GamePanel gp) {

        this.gp = gp;

        name = "Furniture";

        alt = new BufferedImage[5];



        try {

            


            alt[0] = ImageIO.read(new File("./res/objects/carpet.png"));


            alt[0] = uTool.scaleImage(alt[0], gp.tileSize, gp.tileSize);


        } catch (Exception e) {
            e.printStackTrace();
        }
        
        collision = false;

    }
}