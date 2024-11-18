package object;



import entity.Entity;
import main.GamePanel;




public class OBJ_Key extends Entity {

    
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public OBJ_Key(GamePanel gp) {

        super(gp);
        
        obj = true;
        name = "Key";
        down1 = setup("./res/objects/key");

        
    }
}
