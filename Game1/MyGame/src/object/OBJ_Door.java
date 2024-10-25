package object;

import java.io.File;
import javax.imageio.ImageIO;



public class OBJ_Door extends SuperObject{
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Door() {
        name = "Door";

        try {

            image = ImageIO.read(new File("./res/objects/doorClosed.png"));
            closed = ImageIO.read(new File("./res/objects/doorClosed.png"));
            open = ImageIO.read(new File("./res/objects/doorOpen.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

    }

    
}
