package object;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;



public class OBJ_Chest extends SuperObject{


    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Chest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";

        alt = new BufferedImage[2];

        try {

            image = ImageIO.read(new File("./res/objects/chestClosed.png"));
            alt[0] = ImageIO.read(new File("./res/objects/chestClosed.png"));
            alt[1] = ImageIO.read(new File("./res/objects/chestOpen.png"));

            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            alt[0] = uTool.scaleImage(alt[0], gp.tileSize, gp.tileSize);
            alt[1] = uTool.scaleImage(alt[1], gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        collision = true;

    }
}

