package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {




    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Boots(GamePanel gp) {

        super(gp);

        name = "Boots";

        width = gp.tileSize/2;
        height = gp.tileSize/2;

        down1 = setup("./res/objects/boots");

        collision = false;

        obj = true;

    }

}
