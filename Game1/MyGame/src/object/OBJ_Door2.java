package object;

import entity.Entity;
import java.awt.image.BufferedImage;
import main.GamePanel;



public class OBJ_Door2 extends Entity {

    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Door2(GamePanel gp) {

        super(gp);

        name = "Door2";

        alt = new BufferedImage[2];

        alt[0] = setup("./res/objects/doorClosed2");
        alt[1] = setup("./res/objects/doorOpen2");

        down1 = alt[0];
        obj = true;
        collision = true;

    }

    
}