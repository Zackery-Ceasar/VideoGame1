package object;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;



public class OBJ_Door2 extends SuperObject{

    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Door2(GamePanel gp) {

        this.gp = gp;
    
        name = "Door2";

        alt = new BufferedImage[2];

        try {

            image = ImageIO.read(new File("./res/objects/doorClosed2.png"));
            alt[0] = ImageIO.read(new File("./res/objects/doorClosed2.png"));
            alt[1] = ImageIO.read(new File("./res/objects/doorOpen2.png"));

            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            alt[0] = uTool.scaleImage(alt[0], gp.tileSize, gp.tileSize);
            alt[1] = uTool.scaleImage(alt[1], gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

    }

    
}