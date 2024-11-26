package object;

import entity.Entity;
import java.awt.image.BufferedImage;
import main.GamePanel;



public class OBJ_Chest extends Entity {



    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Chest(GamePanel gp) {

        super(gp);

        name = "Chest";

        //solidArea = new Rectangle();

        solidArea.x = 10;
        solidArea.y = 12;
        solidArea.width = 28;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        

        alt = new BufferedImage[2];

        alt[0] = setup("./res/objects/chestClosed");
        alt[1] = setup("./res/objects/chestOpen");

        down1 = alt[0];

        obj = true;
        collision = true;

    }
}

