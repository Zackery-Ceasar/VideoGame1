package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
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
        solidArea.y = 26;
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
        //System.out.println(collisionOn);

        actionLockCounter++;

        
       
        int i = 1;
        if(actionLockCounter == 120) {
            Random random = new Random();
            prevDirection = direction;
            
            i = random.nextInt(150) + 1;  // pick up a number from 1 to 100
            //System.out.println(i);



            if (i <= 25) {
                direction = "up";
                up = true;
            } else {
                up = false;
            }

            if (i > 25 && i <= 50) {
                direction = "down";
                down = true;
            } else {
                down = false;
            }

            if (i > 50 && i <= 75) {
                direction = "left";
                left = true;
            } else {
                left = false;
            }

            if (i > 75 && i <= 100) {
                direction = "right";
                right = true;
            } else {
                right = false;
            }

            if (i > 100 && i <= 150) {
                direction = prevDirection;
                if ("up".equals(prevDirection)) {
                    up = true;
                    down = false;
                    left = false;
                    right = false;
                } else if ("down".equals(prevDirection)) {
                    up = false;
                    down = true;
                    left = false;
                    right = false;
                } else if ("left".equals(prevDirection)) {
                    up = false;
                    down = false;
                    left = true;
                    right = false;
                } else if ("right".equals(prevDirection)) {
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                }
                
                idle = !idle;
                
            }

            actionLockCounter = 0;
        }
        
    }

    @Override
    public void speak() {

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 5;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];

        //dialogueIndex++;

        //if (gp.keyH.enterPressed) {


            if (gp.player.up) {
                //gp.player.worldY += 5;
                direction = "down";
                down = true;
            } else if (gp.player.down) {
                //gp.player.worldY -= 5;
                direction = "up";
                up = true;
            } else if (gp.player.left) {
                //gp.player.worldX += 5;
                direction = "right";
                right = true;
            } else if (gp.player.right) {
                //gp.player.worldX -= 5;
                direction = "left";
                left = true;
            }
        //}
        
        
    }

   

    @Override
    public void draw(Graphics2D g2) {


        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
            

        if (worldX + 2*gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - 2*gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + 2*gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - 2*gp.tileSize < gp.player.worldY + gp.player.screenY ) {

                if (!idle) {
                    image = drawMovingAnimation();
                } else {
                    image = drawIdleAnimation();
                }

                g2.drawImage(image, screenX, screenY, width, height, null);
        }

    
    }

    

        

}
