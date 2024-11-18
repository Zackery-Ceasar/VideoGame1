package object;

import entity.Entity;
import java.awt.image.BufferedImage;
import main.GamePanel;



public class OBJ_Door extends Entity {


    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Door(GamePanel gp) {


        super(gp);

        name = "Door";

        alt = new BufferedImage[2];

        

        alt[0] = setup("./res/objects/doorClosed");
        alt[1] = setup("./res/objects/doorOpen");

        down1 = alt[0];

        obj = true;


        collision = true;


    }



    

    
}
