package object;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_FurnitureC extends SuperObject {
    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_FurnitureC(GamePanel gp) {

        this.gp = gp;

        name = "FurnitureC";

        alt = new BufferedImage[5];



        try {

            


            alt[0] = ImageIO.read(new File("./res/objects/bed.png"));
            alt[1] = ImageIO.read(new File("./res/objects/bedBot.png"));
            alt[2] = ImageIO.read(new File("./res/objects/chair.png"));

            alt[0] = uTool.scaleImage(alt[0], gp.tileSize, gp.tileSize);
            alt[1] = uTool.scaleImage(alt[1], gp.tileSize, gp.tileSize);
            alt[2] = uTool.scaleImage(alt[2], gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        collision = true;

    }
}

