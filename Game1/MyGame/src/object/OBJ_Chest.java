package object;

import java.io.File;
import javax.imageio.ImageIO;



public class OBJ_Chest extends SuperObject{
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Chest() {
        name = "Chest";

        try {

            image = ImageIO.read(new File("./res/objects/chestClosed.png"));
            closed = ImageIO.read(new File("./res/objects/chestClosed.png"));
            open = ImageIO.read(new File("./res/objects/chestOpen.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        collision = true;

    }
}

