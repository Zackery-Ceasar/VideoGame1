package object;

import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;




public class OBJ_Key extends SuperObject{

    GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Key(GamePanel gp) {

        this.gp = gp;
        name = "Key";

        try {

            image = ImageIO.read(new File("./res/objects/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
