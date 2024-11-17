package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public String direction;

    public int width, height;





    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int spriteCounterIdle = 0;
    public int spriteNumIdle = 1;

    public BufferedImage up1, up2, up3, up4, up5;
    public BufferedImage down1, down2, down3, down4, down5, down6;
    public BufferedImage left1, left2, left3, left4, left5;
    public BufferedImage right1, right2, right3, right4, right5;
    public BufferedImage idle1, idle2, idle3, idle4, upIdle1, leftIdle1, rightIdle1;


    int doorOpen = 999;
    int timer = 30;

    public int dialogueIndex = 0;


    GamePanel gp;


    
    
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter;
    public boolean idle;
    public String dialogues[] = new String[20];

    // STATUS

    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }





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
                
                g2.drawImage(image, screenX, screenY, width, height, null);
        }

    
    }

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
                
                
                g2.drawImage(image, screenX, screenY, width, height, null);
        }

    
    }

    public void setAction() {}
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 5;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];

        //dialogueIndex++;

        if (gp.keyH.enterPressed) {
            if ("up".equals(gp.player.direction)) {
                gp.player.worldY += 5;
                direction = "down";
            } else if ("down".equals(gp.player.direction)) {
                gp.player.worldY -= 5;
                direction = "up";
            } else if ("left".equals(gp.player.direction)) {
                gp.player.worldX += 5;
                direction = "right";
            } else {
                gp.player.worldX -= 5;
                direction = "left";
            }
        }
        
    }

    public void update() {

        setAction();

        collisionOn = false;

        if (!idle) {
            collisionDetector();
            updateMovingAnimation(6);
        } else {
            updateIdleAnimation();
        }

        

        


    }
    












    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;

    }


    public BufferedImage drawMovingAnimation() {

        //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        BufferedImage image = null;

                switch(direction) {
                    case "up" -> {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        if (spriteNum == 3) {
                            image = up1;
                        }
                        if (spriteNum == 4) {
                            image = up3;
                        }
                        if (spriteNum == 5) {
                            image = up4;
                        }
                        if (spriteNum == 6) {
                            image = up5;
                        }
                        if (spriteNum == 7) {
                            image = up4;
                        }
                        if (spriteNum == 8) {
                            image = up3;
                        }
                        }
                    case "down" -> {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        if (spriteNum == 3) {
                            image = down1;
                        }
                        if (spriteNum == 4) {
                            image = down3;
                        }
                        if (spriteNum == 5) {
                            image = down4;
                        }
                        if (spriteNum == 6) {
                            image = down5;
                        }
                        if (spriteNum == 7) {
                            image = down4;
                        }
                        if (spriteNum == 8) {
                            image = down3;
                        }
                        }
                    case "left" -> {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        if (spriteNum == 3) {
                            image = left1;
                        }
                        if (spriteNum == 4) {
                            image = left3;
                        }
                        if (spriteNum == 5) {
                            image = left4;
                        }
                        if (spriteNum == 6) {
                            image = left5;
                        }
                        if (spriteNum == 7) {
                            image = left4;
                        }
                        if (spriteNum == 8) {
                            image = left3;
                        }
                        }
                    case "right" -> {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        if (spriteNum == 3) {
                            image = right1;
                        }
                        if (spriteNum == 4) {
                            image = right3;
                        }
                        if (spriteNum == 5) {
                            image = right4;
                        }
                        if (spriteNum == 6) {
                            image = right5;
                        }
                        if (spriteNum == 7) {
                            image = right4;
                        }
                        if (spriteNum == 8) {
                            image = right3;
                        }
                        }
            
                    }
              //  }

        return image;
    }

    public void updateMovingAnimation(int speed) {
            //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
                


                spriteCounter++;
                if (spriteCounter > speed) {
                    switch (spriteNum) {
                        case 1 -> spriteNum = 2;
                        case 2 -> spriteNum = 3;
                        case 3 -> spriteNum = 4;
                        case 4 -> spriteNum = 5;
                        case 5 -> spriteNum = 6;
                        case 6 -> spriteNum = 7;
                        case 7 -> spriteNum = 8;
                        case 8 -> spriteNum = 1;
                        default -> {
                        }
                    }
                    spriteCounter = 0;
                }

            //}

    }

    public int collisionDetector() {
        //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {


            gp.cChecker.checkTile(this, gp.numMap);
            int i = gp.cChecker.checkObject(this);
            gp.cChecker.checkPlayer(this);
            

            if(collisionOn == false) {
                
                switch(direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                        
                }
            }
            return 999;
        //}
        
    }

     

    public BufferedImage drawIdleAnimation() {

        BufferedImage image = null;

                switch(direction) {
                    case "up" -> {
                        if (spriteNumIdle <= 6) {
                            image = upIdle1;
                        }
                }
                    case "down" -> {
                        if (spriteNumIdle <= 6) {
                            image = idle1;
                        }
                        
               }
                    case "left" -> {
                        if (spriteNumIdle <= 6) {
                            image = leftIdle1;
                        }
               }
                    case "right" -> {
                        if (spriteNumIdle <= 6) {
                            image = rightIdle1;
                        }
               }
            


       }

       return image;




    }




    


    

    public void updateIdleAnimation() {

                
            spriteCounterIdle++;
                            
            if (spriteCounterIdle > 45) {
                switch (spriteNumIdle) {
                    case 1 -> spriteNumIdle = 2;
                    case 2 -> spriteNumIdle = 3;
                    case 3 -> spriteNumIdle = 4;
                    case 4 -> spriteNumIdle = 5;
                    case 5 -> spriteNumIdle = 6;
                    case 6 -> spriteNumIdle = 1;
                        
                }
                spriteCounterIdle = 0;
            }



    }
    
}
