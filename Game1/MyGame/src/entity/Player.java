package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;
import messages.MessageManager;



 public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    AssetSetter aSetter;
    MessageManager mManager;

    public BufferedImage up1, up2, upIdle1, down1, down2, left1, left2, right1, right2, idle1, idle2, idle3, idle4;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    public boolean chestOpen = false;
    public boolean hasBoots = false;



    public Player(GamePanel gp, KeyHandler keyH, AssetSetter aSetter, MessageManager mManager) {


        this.gp = gp;
        this.keyH = keyH;
        this.aSetter = aSetter;
        this.mManager = mManager;


        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();
        


    }

    public final void setDefaultValues() {

        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "down";

    }
    


    @SuppressWarnings("CallToPrintStackTrace")
    public final void getPlayerImage() {


        try {

            up1 = ImageIO.read(new File("./res/player/backward1.png"));
            up2 = ImageIO.read(new File("./res/player/backward2.png"));            
            down1 = ImageIO.read(new File("./res/player/forward1.png"));            
            down2 = ImageIO.read(new File("./res/player/forward2.png"));            
            left1 = ImageIO.read(new File("./res/player/left1.png"));
            left2 = ImageIO.read(new File("./res/player/leftIdle1.png"));
            right1 = ImageIO.read(new File("./res/player/right1.png"));
            right2 = ImageIO.read(new File("./res/player/rightIdle1.png"));
            idle1 = ImageIO.read(new File("./res/player/idle1.png"));
            idle2 = ImageIO.read(new File("./res/player/idle2.png"));
            idle3 = ImageIO.read(new File("./res/player/idle3.png"));
            idle4 = ImageIO.read(new File("./res/player/idle4.png"));
            upIdle1 = ImageIO.read(new File("./res/player/backwardIdle1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @SuppressWarnings("CallToPrintStackTrace")
    public final void updatePlayerImageBoots() {


        try {

            up1 = ImageIO.read(new File("./res/player/backward1B.png"));
            up2 = ImageIO.read(new File("./res/player/backward2B.png"));            
            down1 = ImageIO.read(new File("./res/player/forward1B.png"));            
            down2 = ImageIO.read(new File("./res/player/forward2B.png"));            
            left1 = ImageIO.read(new File("./res/player/left1B.png"));
            left2 = ImageIO.read(new File("./res/player/leftIdle1B.png"));
            right1 = ImageIO.read(new File("./res/player/right1B.png"));
            right2 = ImageIO.read(new File("./res/player/rightIdle1B.png"));
            idle1 = ImageIO.read(new File("./res/player/idle1B.png"));
            idle2 = ImageIO.read(new File("./res/player/idle2B.png"));
            idle3 = ImageIO.read(new File("./res/player/idle3B.png"));
            idle4 = ImageIO.read(new File("./res/player/idle4B.png"));
            upIdle1 = ImageIO.read(new File("./res/player/backwardIdle1B.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    public void update() {

        // animations

        updateMovingAnimation();
        updateIdleAnimation();

        // check tile collision
        collisionOn = false;

        // check tile and object collision and get index of collided object


        int objIndex = collisionDetector();

        // Checks key and chest objects
        keyUpdate(objIndex);
        chestOpen();

        // Items

        bootsUpdate(objIndex);

        // Door functions
        doorUpdateOpen(objIndex);
        doorUpdateClose(doorOpen);
        
    }

    public void draw(Graphics2D g2) {


        BufferedImage image = null;

        image = drawMovingAnimation(image);
        image = drawIdleAnimation(image);

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }


    public void keyUpdate(int i) {
        if (i != 999) {

            if (gp.obj[i] != null) {
                String objectName = gp.obj[i].name;

                switch(objectName) {
                    case "Key" -> {
                        hasKey++;
                        gp.obj[i] = null;
                        System.out.println("Key:" + hasKey);
                    }
                    case "Chest" -> {
                        if(hasKey > 0) {
                            gp.obj[i].image = gp.obj[i].open;
                            hasKey--;
                            chestOpen = true;
                            System.out.println("Key:" + hasKey);
                        }
                    }
                }
            }

        }
    }

    public void bootsUpdate(int i) {
        if (i != 999) {

            if (gp.obj[i] != null) {
                String objectName = gp.obj[i].name;

                switch(objectName) {
                    case "Boots" -> {
                        if(!aSetter.animationActive) {
                            gp.obj[i] = null;
                            hasBoots = true;
                            mManager.bootsMessage();
                            updatePlayerImageBoots();
                            if (hasBoots) {
                                speed++;
                            }

                        }
                        
                    }

                }
            }

        }
    }

    public void chestOpen() {
        if (chestOpen) {
            aSetter.dropObject();
            chestOpen = false;
        }

    }

    public void doorUpdateOpen(int i) {
        if (i != 999) {

            if (gp.obj[i] != null) {
                String objectName = gp.obj[i].name;
                switch(objectName) {
                    case "Door" -> {
                        if (gp.obj[i].collision) {
                            gp.obj[i].collision = false;
                            gp.obj[i].image = gp.obj[i].open;
                            doorOpen = i;
                        }
                    }
                }
            }


        }
    }

    public void doorUpdateClose(int i) {

        if (i != 999) {

            if (timer == 0) {


                if (!gp.obj[i].collision) {
                    gp.obj[i].collision = true;
                    gp.obj[i].image = gp.obj[i].closed;
                    doorOpen = 999;
                }

            timer = 30;

            }

        timer--;
        }
        
    }


    public BufferedImage drawMovingAnimation(BufferedImage image) {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        
                switch(direction) {
                    case "up" -> {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        }
                    case "down" -> {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        }
                    case "left" -> {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        }
                    case "right" -> {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        }
            
                    }
                }

        return image;
    }

    public BufferedImage drawIdleAnimation(BufferedImage image) {
        if (keyH.upPressed == false && keyH.downPressed == false && keyH.leftPressed == false && keyH.rightPressed == false) {
                switch(direction) {
                    case "up" -> {
                        if (spriteNumIdle <= 8) {
                            image = upIdle1;
                        }
                }
                    case "down" -> {
                        if (spriteNumIdle == 1) {
                            image = idle1;
                        }
                        if (spriteNumIdle == 2) {
                            image = idle2;
                        }
                        if (spriteNumIdle == 3) {
                            image = idle3;
                        }
                        if (spriteNumIdle == 4) {
                            image = idle4;
                        }
                        if (spriteNumIdle == 5) {
                            image = idle4;
                        }
                        if (spriteNumIdle == 6) {
                            image = idle3;
                        }
                        if (spriteNumIdle == 7) {
                            image = idle2;
                        }
                        if (spriteNumIdle == 8) {
                            image = idle1;
                        }
               }
                    case "left" -> {
                        if (spriteNumIdle <= 8) {
                            image = left2;
                        }
               }
                    case "right" -> {
                        if (spriteNumIdle <= 8) {
                            image = right2;
                        }
               }
            
                }


       }
       return image;




    }




    


    public void updateMovingAnimation() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed == true) {
                direction = "up";
                
            } else if (keyH.downPressed == true) {
                direction = "down";
                
            } else if (keyH.leftPressed == true) {
                direction = "left";
                
            } else if (keyH.rightPressed == true) {
                direction = "right";
                
            }


            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }

    public void updateIdleAnimation() {

        if (keyH.upPressed == false || keyH.downPressed == false || keyH.leftPressed == false || keyH.rightPressed == false) {
                
                spriteCounterIdle++;
                            
                if (spriteCounterIdle > 18) {
                    switch (spriteNumIdle) {
                        case 1 -> spriteNumIdle = 2;
                        case 2 -> spriteNumIdle = 3;
                        case 3 -> spriteNumIdle = 4;
                        case 4 -> spriteNumIdle = 5;
                        case 5 -> spriteNumIdle = 6;
                        case 6 -> spriteNumIdle = 7;
                        case 7 -> spriteNumIdle = 8;
                        case 8 -> spriteNumIdle = 1;
                        
                    }
                    spriteCounterIdle = 0;
                }


        }
    }

    public int collisionDetector() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {


            gp.cChecker.checkTile(this);
            int i = gp.cChecker.checkObject(this, true);
            

            if(collisionOn == false) {
                
                switch(direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                        
                }
            }
            return i;
        }
        return 999;
    }














}
