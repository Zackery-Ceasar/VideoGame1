package object;

import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;



public class OBJ_Door2 extends SuperObject{

    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Door2(GamePanel gp) {

        this.gp = gp;

        name = "Door2";

        try {

            image = ImageIO.read(new File("./res/objects/doorClosed2.png"));
            closed = ImageIO.read(new File("./res/objects/doorClosed2.png"));
            open = ImageIO.read(new File("./res/objects/doorOpen2.png"));

            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            closed = uTool.scaleImage(closed, gp.tileSize, gp.tileSize);
            open = uTool.scaleImage(open, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

    }

    
}