package object;

import entity.Entity;
import java.awt.image.BufferedImage;
import main.GamePanel;


public class OBJ_Health extends Entity {

    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Health(GamePanel gp) {

        super(gp);
        name = "health";
        alt = new BufferedImage[3];
        alt[0] = setup("res/objects/healthBarEnding", 2, 16);
        alt[1] = setup("res/objects/healthBar", 2, 16);
        alt[2] = setup("res/objects/emptyHealthBar", 2, 16);


        
        obj = true;
        

    }

   
}
