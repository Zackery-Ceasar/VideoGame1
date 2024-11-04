package object;

import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;



public class OBJ_Chest extends SuperObject{


    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Chest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";

        try {

            image = ImageIO.read(new File("./res/objects/chestClosed.png"));
            closed = ImageIO.read(new File("./res/objects/chestClosed.png"));
            open = ImageIO.read(new File("./res/objects/chestOpen.png"));

            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            closed = uTool.scaleImage(closed, gp.tileSize, gp.tileSize);
            open = uTool.scaleImage(open, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        collision = true;

    }
}

