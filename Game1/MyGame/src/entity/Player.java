package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import messages.MessageManager;



public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    AssetSetter aSetter;
    MessageManager mManager;

    public BufferedImage up1, up2, upIdle1, down1, down2, left1, left2, right1, right2, idle1, idle2, idle3, idle4;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public boolean chestOpen = false;
    public boolean hasBoots = false;
    public int moveSpeed;
    public boolean switchScreen = false;
    public int checkMap = 0;



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

        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 25;
        speed = 3;
        direction = "down";

    }
    


    @SuppressWarnings("CallToPrintStackTrace")
    public final void getPlayerImage() {


        up1 = setup("backward1");
        up2 = setup("backward2");
        down1 = setup("forward1");
        down2 = setup("forward2");
        left1 = setup("left1");
        left2 = setup("leftIdle1");
        right1 = setup("right1");
        right2 = setup("rightIdle1");
        idle1 = setup("idle1");
        idle2 = setup("idle2");
        idle3 = setup("idle3");
        idle4 = setup("idle4");
        upIdle1 = setup("backwardIdle1");



    }

    @SuppressWarnings("CallToPrintStackTrace")
    public final void updatePlayerImageBoots() {


        up1 = setup("backward1B");
        up2 = setup("backward2B");
        down1 = setup("forward1B");
        down2 = setup("forward2B");
        left1 = setup("left1B");
        left2 = setup("leftIdle1B");
        right1 = setup("right1B");
        right2 = setup("rightIdle1B");
        idle1 = setup("idle1B");
        idle2 = setup("idle2B");
        idle3 = setup("idle3B");
        idle4 = setup("idle4B");
        upIdle1 = setup("backwardIdle1B");




    }

    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("./res/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;

    }




    public void update() {

        int objIndex = 0;

        // animations

        sprint();

        updateMovingAnimation(moveSpeed);
        updateIdleAnimation();

        // check tile collision
        collisionOn = false;

        // check tile and object collision and get index of collided object


 // normal case:
        objIndex = collisionDetector(); 
        
        

        // Checks key and chest objects
        keyUpdate(objIndex);
        chestOpen();

        // Items

        bootsUpdate(objIndex);

        // Door functions
        doorUpdateOpen(objIndex);
        doorUpdateClose(doorOpen);


        closeAllDoorsWhenSwitched();
        
    }

    public void draw(Graphics2D g2) {


        BufferedImage image = null;

        image = drawMovingAnimation(image);
        image = drawIdleAnimation(image);


        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }


    public void keyUpdate(int i) {
        if (i != 999) {

            if (gp.obj[gp.numMap][i] != null) {
                String objectName = gp.obj[gp.numMap][i].name;

                switch(objectName) {
                    case "Key" -> {
                        hasKey++;
                        gp.obj[gp.numMap][i] = null;
                        gp.ui.showMessage("Key gained!");
                    }
                    case "Chest" -> {
                        if(hasKey > 0) {
                            gp.obj[gp.numMap][i].image = gp.obj[gp.numMap][i].open;
                            hasKey--;
                            chestOpen = true;
                        } else if (hasKey == 0 && gp.obj[gp.numMap][i].image != gp.obj[gp.numMap][i].open) {
                            gp.ui.showMessage("Maybe... Key?");
                        }
                    }
                }
            }

        }
    }

    public void bootsUpdate(int i) {
        if (i != 999) {
            //System.out.println("int: " + i);

            if (gp.obj[gp.numMap][i] != null) {
                String objectName = gp.obj[gp.numMap][i].name;

                switch(objectName) {
                    case "Boots" -> {
                        if(!aSetter.animationActive) {
                            gp.obj[gp.numMap][i] = null;
                            hasBoots = true;
                            mManager.bootsMessage();
                            updatePlayerImageBoots();
                            

                        }
                        
                    }

                }
            }

        }
    }




    public void sprint() {
        if (hasBoots && keyH.shiftPressed == true) {
            moveSpeed = 7;
            speed = 5;
        } else {
            speed = 3;
            moveSpeed = 11;
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

            if (gp.obj[gp.numMap][i] != null) {
                String objectName = gp.obj[gp.numMap][i].name;
                switch(objectName) {
                    case "Door" -> {
                        if (gp.obj[gp.numMap][i].collision) {
                            gp.obj[gp.numMap][i].collision = false;
                            gp.obj[gp.numMap][i].image = gp.obj[gp.numMap][i].open;
                            doorOpen = i;
                            checkMap = gp.numMap;
                        }
                    }
                    case "Door2" -> {
                        if (gp.obj[gp.numMap][i].collision) {
                            gp.obj[gp.numMap][i].collision = false;
                            gp.obj[gp.numMap][i].image = gp.obj[gp.numMap][i].open;
                            doorOpen = i;
                            checkMap = gp.numMap;
                        }
                    }
                }
            }


        }
    }

    public void closeAllDoorsWhenSwitched() {
        if (switchScreen) {
            for (int j = 0; j < gp.objectQTY; j++) {
                for (int e = 0; e < gp.tileM.numMaps; e++) {
                    if (gp.obj[e][j] != null) {
                        String objectName = gp.obj[e][j].name;
                        switch(objectName) {
                            case "Door" -> {
                                gp.obj[e][j].image = gp.obj[e][j].closed;
                                gp.obj[e][j].collision = true;

                            }
                            case "Door2" -> {
                                gp.obj[e][j].image = gp.obj[e][j].closed;
                                gp.obj[e][j].collision = true;
                                
                            }
                        }       

                    }
              }
            }
            switchScreen = false;
        }
    }
    

    public void doorUpdateClose(int i) {
        if (checkMap != gp.numMap) {
            return;
        }

        if (i != 999) {

            if (timer == 0) {


                if (!gp.obj[checkMap][i].collision) {
                    gp.obj[checkMap][i].collision = true;
                    gp.obj[checkMap][i].image = gp.obj[gp.numMap][i].closed;
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




    


    public void updateMovingAnimation(int speed) {
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
            if (spriteCounter > speed) {
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


            gp.cChecker.checkTile(this, gp.numMap);
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
