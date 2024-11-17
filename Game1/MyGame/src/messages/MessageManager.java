package messages;



import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;



public class MessageManager {

    

    public int screenX;
    public int screenY;
    public Message[] messages;
    public int messageWidth;
    public int messageHeight;
    public int messageTime = 500;
    public int messageSet;
    public boolean sendMessage = false;



    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MessageManager(GamePanel gp) {

        messages = new Message[10];
        messageWidth = gp.tileSize * 6;
        messageHeight = gp.tileSize * 3;
        screenX = gp.screenWidth/2 - (messageWidth)/2;
        screenY = gp.screenHeight/2 - (gp.tileSize/2) - 4 * gp.tileSize;

    }



    public void sendMessage(Graphics2D g2) {
        while (messageTime != 0 && sendMessage && messages[messageSet] != null) {
            g2.drawImage(messages[messageSet].image, screenX, screenY, messageWidth, messageHeight, null);
            messageTime--;
            
                switch(messageTime) {
                    case 400 -> messageSet++;
                    case 300 -> messageSet++;
                    case 200 -> messageSet++;
                    case 100 -> messageSet++;
                    default -> {
                    }
        
         }
            return;
        }
        messageTime = 500;
        sendMessage = false;
        
        for (int i = 0; messages[i] != null; i++) {
            messages[i] = null;
        }

    }


    @SuppressWarnings("UseSpecificCatch")
    public void bootsMessage() {

        try {

            messages[0] = new Message();
            messages[0].image = ImageIO.read(new File("./res/messages/CONGRATS.png"));

            messages[1] = new Message();
            messages[1].image = ImageIO.read(new File("./res/messages/PRESS_SHIFT_TO_SPRINT.png")); //res\messages\PRESS_SHIFT_TO_SPRINT.png

        } catch (Exception e) {
        }

        messageSet = 0;
        sendMessage = true;

        

    }





}
