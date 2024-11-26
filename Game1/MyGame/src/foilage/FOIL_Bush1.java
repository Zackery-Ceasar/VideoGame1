package foilage;

import entity.Entity;
import main.GamePanel;

public class FOIL_Bush1 extends Entity {

        @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public FOIL_Bush1(GamePanel gp) {

        super(gp);

        name = "Bush1";

        width = gp.tileSize;
        height = gp.tileSize;

        solidArea.x = 20; // 8
        solidArea.y = 20; // 24
        solidArea.width = 8;
        solidArea.height = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down1 = setup("./res/foilage/003");

        collision = false;

        obj = true;

    }
}
