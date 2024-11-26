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
    public int animationSpeed = 6;
    public boolean up = false, down = true, left = false, right = false;
    public String direction = "down";
    public String prevDirection;

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


    public GamePanel gp;

    //OBJ TRANSFER
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public BufferedImage alt[];

    //INVINCIBILITY

    public boolean invincible = false;
    public int invincibleCounter = 0;

    public int type;
    
    
    
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    //public boolean collisionOn = false;
    public boolean collisionUp, collisionDown, collisionLeft, collisionRight;




    public int actionLockCounter;
    public boolean idle = true;
    public boolean obj = false;
    public String dialogues[] = new String[20];

    // STATUS

    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY= solidArea.y;

        alt = new BufferedImage[10];
        height = gp.tileSize;
        width = gp.tileSize;



    }





    public void draw(Graphics2D g2) {


        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
            

        if (worldX + 6*gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - 6*gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + 6*gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - 6*gp.tileSize < gp.player.worldY + gp.player.screenY) {
                 
                if (obj) { 
                    image = down1;
                } else {
                    if (!idle) {
                        image = drawMovingAnimation();
                    } else {
                        image = drawIdleAnimation();
                    }
                
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


            if (gp.player.up) {
                gp.player.worldY += 5;
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
            } else {
                //gp.player.worldX -= 5;
                direction = "left";
                left = true;
            }
        }
        
    }

    public void update() {

        setAction();

        collisionUp = false;
        collisionDown = false;
        collisionLeft = false;
        collisionRight = false;
        

        //collisionOn = false;

        if (!idle) {
            collisionDetector();
            updateMovingAnimation();
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

    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;

    }


    public BufferedImage drawMovingAnimation() {

        //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        BufferedImage image = null;

        if (up) {
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
        } else
        if (down) {
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
        } else
        if (left) {
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
        } else
        if (right) {
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


        return image;
    }

    public void updateMovingAnimation() {
            //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
                


                spriteCounter++;
                if (spriteCounter > animationSpeed) {
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
            gp.cChecker.checkObject(this, gp.obj);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkFoilage(this, gp.foilage);
            boolean contactPlayer = gp.cChecker.checkPlayer(this);

            if(this.type == 2 && contactPlayer) {
                if(!gp.player.invincible) {
                    gp.player.life--;
                    gp.player.invincible = true;
                }
            }
            

            //if(collisionOn == false) {
                if (up && !collisionUp) {
                    worldY -= speed;
                }
                if (down && !collisionDown) {
                    worldY += speed;
                }
                if (left && !collisionLeft) {
                    worldX -= speed;
                }
                if (right && !collisionRight) {
                    worldX += speed;
                }


            //}
        //}

        return 999;
        
    }

     

    public BufferedImage drawIdleAnimation() {

        BufferedImage image = null;

        if ("up".equals(direction)) {
            if (spriteNumIdle <= 6) {
                image = upIdle1;
            }
        } 
        if (down) {
            if (spriteNumIdle <= 6) {
                image = idle1;
            }
        } 
        if (left) {
            if (spriteNumIdle <= 6) {
                image = leftIdle1;
            }
        } 
        if (right) {
            if (spriteNumIdle <= 6) {
                image = rightIdle1;
            }
        }

       return image;




    }




    


    

    public void updateIdleAnimation() {

                
            spriteCounterIdle++;
                            
            if (spriteCounterIdle > animationSpeed) {
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
