package foilage;

import entity.Entity;
import main.GamePanel;

public class FOIL_Tree1 extends Entity {

    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public FOIL_Tree1(GamePanel gp) {

        super(gp);

        name = "Tree1";

        width = gp.tileSize + 50;
        height = gp.tileSize*6;

        solidArea.x = 30; // 96
        solidArea.y = gp.tileSize*4; // 24
        solidArea.height = 36;
        solidArea.width = 36;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down1 = setup("./res/foilage/001");

        collision = true;

        obj = true;

    }
}
