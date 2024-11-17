package object;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Health extends SuperObject {
GamePanel gp;
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Health(GamePanel gp) {

        this.gp = gp;
        name = "health";
        alt = new BufferedImage[3];

        try {

            alt[0] = ImageIO.read(new File("res/objects/healthBarEnding.png"));
            alt[0] = uTool.scaleImage(alt[0], 2, 16);
        
            alt[1] = ImageIO.read(new File("res/objects/healthBar.png"));
            alt[1] = uTool.scaleImage(alt[1], 2, 16);

            alt[2] = ImageIO.read(new File("res/objects/emptyHealthBar.png"));
            alt[2] = uTool.scaleImage(alt[2], 2, 16);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
