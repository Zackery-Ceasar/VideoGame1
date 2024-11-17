package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.GamePanel;


public class NPC_WiseWarrior extends Entity{

    public NPC_WiseWarrior(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = 1;
        width = gp.tileSize;
        height = gp.tileSize + gp.tileSize/4;

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 24;

        getImage();
        setDialogue();

        idle = true;

    }

    public final void getImage() {


        up1 = setup("./res/npc/NPCup1"); //   ./res/npc/NPCup1.png
        up2 = setup("./res/npc/NPCup2");
        up3 = setup("./res/npc/NPCup3");
        up4 = setup("./res/npc/NPCup4");
        up5 = setup("./res/npc/NPCup5");

        down1 = setup("./res/npc/NPCdown1");
        down2 = setup("./res/npc/NPCdown2");
        down3 = setup("./res/npc/NPCdown3");
        down4 = setup("./res/npc/NPCdown4");
        down5 = setup("./res/npc/NPCdown5");

        left1 = setup("./res/npc/NPCleft1");
        left2 = setup("./res/npc/NPCleft2");
        left3 = setup("./res/npc/NPCleft3");
        left4 = setup("./res/npc/NPCleft4");
        left5 = setup("./res/npc/NPCleft5");
        
        right1 = setup("./res/npc/NPCright1");
        right2 = setup("./res/npc/NPCright2");
        right3 = setup("./res/npc/NPCright3");
        right4 = setup("./res/npc/NPCright4");
        right5 = setup("./res/npc/NPCright5");
        
        idle1 = setup("./res/npc/NPCidle1");

        upIdle1 = setup("./res/npc/NPCidleUp1");

        leftIdle1 = setup("./res/npc/NPCidleLeft1");

        rightIdle1 = setup("./res/npc/NPCidleRight1");


    }

    public void setDialogue() {
        dialogues[0] = "...";
        dialogues[1] = "Hello, abomination.";
        dialogues[2] = "You may be wondering who I am or how I got here?";
        dialogues[3] = "Well it matters not, for I myself do not know.";
        dialogues[4] = "All that matters is that You and I are stuck here. Forever.";
        dialogues[5] = "...";
        dialogues[6] = "Where are your boots?";


    }

    @Override
    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(150) + 1;  // pick up a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            if (i > 100 && i <= 150) {
                idle = !idle;
                
            }

            actionLockCounter = 0;
        }
        
    }

    @Override
    public void speak() {
        super.speak();
        
    }

   

    @Override
    public void drawFront(Graphics2D g2) {


        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image;
        
            

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY && 
            gp.player.worldY < worldY) {

                if (!idle) {
                    image = drawMovingAnimation();
                } else {
                    image = drawIdleAnimation();
                }
                g2.setColor(new Color(0, 0, 0, 65));
                g2.fillOval(screenX + 4, screenY + gp.tileSize, gp.tileSize *4/5, gp.tileSize/3);
                g2.drawImage(image, screenX, screenY, width, height, null);
        }

    
    }

    @Override
    public void drawBehind(Graphics2D g2) {


        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image;
        
            

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY && 
            gp.player.worldY >= worldY) {

                if (!idle) {
                    image = drawMovingAnimation();
                } else {
                    image = drawIdleAnimation();
                }
                
                g2.setColor(new Color(0, 0, 0, 65));
                g2.fillOval(screenX + 4, screenY + gp.tileSize, gp.tileSize *4/5, gp.tileSize/3);
                
                g2.drawImage(image, screenX, screenY, width, height, null);
        }

    
    }

        

}
