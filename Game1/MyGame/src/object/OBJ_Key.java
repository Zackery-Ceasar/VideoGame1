package object;

import java.io.File;
import javax.imageio.ImageIO;



public class OBJ_Key extends SuperObject{
    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Key() {
        name = "Key";

        try {

            image = ImageIO.read(new File("./res/objects/key.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
