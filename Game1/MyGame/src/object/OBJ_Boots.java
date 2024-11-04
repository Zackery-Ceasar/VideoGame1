package object;

import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;


    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Boots(GamePanel gp) {

        this.gp = gp;

        name = "Boots";

        try {

            image = ImageIO.read(new File("./res/objects/boots.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);


        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false;

    }

}
