package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;
import messages.MessageManager;



public class Player extends Entity {
    KeyHandler keyH;
    AssetSetter aSetter;
    MessageManager mManager;

    

    public BufferedImage image = null;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public boolean chestOpen = false;
    public boolean hasBoots = false; // turn back to false
    public int moveSpeed;

    // MAP CHANGERS
    public boolean switchScreen = false;
    public int checkMap = 0;
    public boolean closeDoor = false;

    // NPC
    public int currentNPC;

    //dodge
    public int dodgeCooldown;
    public int dodgeTimer = 0;
    public int dodgeSpeed = 15;
    public boolean dodge = false;

    




    public Player(GamePanel gp, KeyHandler keyH, AssetSetter aSetter, MessageManager mManager) {

        super(gp);
        this.keyH = keyH;
        this.aSetter = aSetter;
        this.mManager = mManager;


        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 16;
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
        
        down = true;
        width = gp.tileSize;
        height = gp.tileSize + gp.tileSize/6;

        animationSpeed = 6;
        // Player stats

 
        maxLife = 100;
        life = maxLife;
        speed = 3;   // WAS 3
        dodgeCooldown = 200;
        


    }
    


    @SuppressWarnings("CallToPrintStackTrace")
    public final void getPlayerImage() {


        up1 = setup("./res/player/up1");
        up2 = setup("./res/player/up2");
        up3 = setup("./res/player/up3");
        up4 = setup("./res/player/up4");
        up5 = setup("./res/player/up5");

        down1 = setup("./res/player/down1");
        down2 = setup("./res/player/down2");
        down3 = setup("./res/player/down3");
        down4 = setup("./res/player/down4");
        down5 = setup("./res/player/down5");
        down6 = setup("./res/player/down6");

        left1 = setup("./res/player/left1");
        left2 = setup("./res/player/left2");
        left3 = setup("./res/player/left3");
        left4 = setup("./res/player/left4");
        left5 = setup("./res/player/left5");
        
        right1 = setup("./res/player/right1");
        right2 = setup("./res/player/right2");
        right3 = setup("./res/player/right3");
        right4 = setup("./res/player/right4");
        right5 = setup("./res/player/right5");
        
        idle1 = setup("./res/player/idle1");
        idle2 = setup("./res/player/idle2");
        idle3 = setup("./res/player/idle3");
        idle4 = setup("./res/player/idle4");

        upIdle1 = setup("./res/player/idleUp1");

        leftIdle1 = setup("./res/player/idleLeft1");

        rightIdle1 = setup("./res/player/idleRight1");


    }

    @SuppressWarnings("CallToPrintStackTrace")
    public final void updatePlayerImageBoots() {


        up1 = setup("./res/player/up1B");
        up2 = setup("./res/player/up2B");
        up3 = setup("./res/player/up3B");
        up4 = setup("./res/player/up4B");
        up5 = setup("./res/player/up5B");

        down1 = setup("./res/player/down1B");
        down2 = setup("./res/player/down2B");
        down3 = setup("./res/player/down3B");
        down4 = setup("./res/player/down4B");
        down5 = setup("./res/player/down5B");
        down6 = setup("./res/player/down6B");

        left1 = setup("./res/player/left1B");
        left2 = setup("./res/player/left2B");
        left3 = setup("./res/player/left3B");
        left4 = setup("./res/player/left4B");
        left5 = setup("./res/player/left5B");
        
        right1 = setup("./res/player/right1B");
        right2 = setup("./res/player/right2B");
        right3 = setup("./res/player/right3B");
        right4 = setup("./res/player/right4B");
        right5 = setup("./res/player/right5B");
        
        idle1 = setup("./res/player/idle1B");
        idle2 = setup("./res/player/idle2B");
        idle3 = setup("./res/player/idle3B");
        idle4 = setup("./res/player/idle4B");

        upIdle1 = setup("./res/player/idleUp1B");

        leftIdle1 = setup("./res/player/idleLeft1B");

        rightIdle1 = setup("./res/player/idleRight1B");




    }

    @Override
    public void update() {

        int objIndex;

        // animations

        keyCheck();

        updateMovingAnimation();
        updateIdleAnimation();

        image = drawMovingAnimation(image);
        image = drawIdleAnimation(image);

        // check tile collision
        collisionUp = false;
        collisionDown = false;
        collisionLeft = false;
        collisionRight = false;
        //NPC COLLISION

        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        //CHECK MONSTERS

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        monsterContact(monsterIndex);

        //FOILAGE COLLSISION
        gp.cChecker.checkFoilage(this, gp.foilage);


        //EVENTS 
        gp.eHandler.checkEvent();

        objIndex = collisionDetector(); 



        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
                    // Checks key and chest objects
            keyUpdate(objIndex);
            chestOpen();

        // Items

            

        // Door functions
            doorUpdateOpen(objIndex);
        }

        dodge();

        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }




        bootsUpdate(objIndex);

        doorUpdateClose(doorOpen);

        closeAllDoorsWhenSwitched();
        
    }

    
    public void draw(Graphics2D g2) {


        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
        }

        g2.drawImage(image, screenX, screenY-12, width, height, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        
        //g2.setFont(new Font("Arial", Font.BOLD, 26));
        ///g2.setColor(Color.white);
        //g2.drawString("Invincible: "+invincibleCounter, 10, 400);
        

    }



    public void interactNPC(int i) {
        //System.out.println(i);

        if (i != 999) {
            
            gp.npc[gp.numMap][i].idle = true;
            
            
            
            if (gp.keyH.enterPressed) {
                if (gp.npc[gp.numMap][i].dialogues[dialogueIndex] != null) {
                    

                    currentNPC = i;
                    gp.npc[gp.numMap][i].speak();
                    gp.gameState = gp.dialogueState;
                    
                    
        
            
                }
                gp.keyH.enterPressed = false;
            }


            
        }
        if (gp.gameState == gp.playState) {
            currentNPC = 999;
        }
        

    }

    public void monsterContact(int i) {
        if(i != 999) {

            if (!invincible) {
                life--;
                invincible = true;
            }
            
        }
    }





    


    public void keyUpdate(int i) {
        if (i != 999) {

            if (gp.obj[gp.numMap][i] != null) {
                String objectName = gp.obj[gp.numMap][i].name;

                switch(objectName) {
                    case "Key" -> {
                        hasKey++;
                        gp.obj[gp.numMap][i] = null;
                    }
                    case "Chest" -> {
                        //System.out.println("Keys: " + hasKey + " enterPressed: " + gp.keyH.enterPressed); 
                        if(hasKey > 0) {
                            gp.obj[gp.numMap][i].down1 = gp.obj[gp.numMap][i].alt[1];
                            hasKey--;
                            chestOpen = true;
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

    ////////////////////////////////////////////////////////////////


    public void dodge() {

        if (dodgeTimer > dodgeCooldown) {
            //System.out.println(dodgeTimer);
        
            if (hasBoots && gp.keyH.shiftPressed == true) {

                dodge = true;
                dodgeTimer = 0;
            } 
            

        }
        dodgeTimer++;

        
        if (dodgeSpeed > 0  && dodge) {
                    
            if(up && !collisionUp) {
                worldY -= 5;
                if (left && !collisionLeft) {
                    worldX -= 5;
                } else if (right && !collisionRight) {
                    worldX += 5;
                }
            } else
            if(down && !collisionDown) {
                worldY += 5;
                if (left && !collisionLeft) {
                    worldX -= 5;
                } else if (right && !collisionRight) {
                    worldX += 5;
                }
            } else
            if(left && !collisionLeft) {
                worldX -= 5;
            } else
            if(right && !collisionRight) {
                worldX += 5;
            }

                dodgeSpeed--;

                if(dodgeSpeed == 0) {
                    dodge = false;
                }

        } else {
            dodgeSpeed = 15;
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
                            
                            gp.obj[gp.numMap][i].down1 = gp.obj[gp.numMap][i].alt[1];
                            doorOpen = i;
                            checkMap = gp.numMap;
                        }
                    }
                    case "Door2" -> {
                        if (gp.obj[gp.numMap][i].collision) {
                            gp.obj[gp.numMap][i].collision = false;
                            //System.out.println("Collision on: " + gp.obj[gp.numMap][i].collision);
                            gp.obj[gp.numMap][i].down1 = gp.obj[gp.numMap][i].alt[1];
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
                                gp.obj[e][j].down1 = gp.obj[e][j].alt[0];
                                gp.obj[e][j].collision = true;

                            }
                            case "Door2" -> {
                                gp.obj[e][j].down1 = gp.obj[e][j].alt[0];
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
                int xDistance = Math.abs(gp.player.worldX - gp.obj[checkMap][i].worldX);
                int yDistance = Math.abs(gp.player.worldY - gp.obj[checkMap][i].worldY);
                int distance = Math.max(xDistance, yDistance);
                if(distance > gp.tileSize && !closeDoor && !gp.obj[checkMap][i].collision) {
                    closeDoor = true;
                }

                //System.out.println("Close door: " + closeDoor);
                //!gp.obj[checkMap][i].collision
                

            timer = 30;

            }
            if (closeDoor) {
                gp.obj[checkMap][i].collision = true;
                gp.obj[checkMap][i].down1 = gp.obj[gp.numMap][i].alt[0];
                doorOpen = 999;
                closeDoor = false;
            }

        timer--;
        }
        
    }


    public BufferedImage drawMovingAnimation(BufferedImage image) {
        
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

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
            } 
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
                    image = down6;
                }
            }
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
            }
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
   }

        return image;
    }



    public BufferedImage drawIdleAnimation(BufferedImage image) {
        if (keyH.upPressed == false && keyH.downPressed == false && keyH.leftPressed == false && keyH.rightPressed == false) {
                if (up) {
                    if (spriteNumIdle <= 6) {
                        image = upIdle1;
                    }
                }
                if (down) {
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
                        image = idle3;
                    }
                    if (spriteNumIdle == 6) {
                        image = idle2;
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


       }
       return image;




    }



    public void keyCheck() {

        
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (gp.keyH.upPressed == true) {
                direction = "up";
            }
            if (gp.keyH.downPressed == true) {
                direction = "down";
            }
            if (gp.keyH.leftPressed == true) {
                direction = "left";
            }
            if (gp.keyH.rightPressed == true) {
                direction = "right";
            }

            


            up = keyH.upPressed == true;
            down = keyH.downPressed == true;
            left = keyH.leftPressed == true;
            right = keyH.rightPressed == true;
        }

        // prevDirection = direction;
        // if ("up".equals(prevDirection)) {
        //     up = true;
        //     down = false;
        //     left = false;
        //     right = false;
        // } else if ("down".equals(prevDirection)) {
        //     up = false;
        //     down = true;
        //     left = false;
        //     right = false;
        // } else if ("left".equals(prevDirection)) {
        //     up = false;
        //     down = false;
        //     left = true;
        //     right = false;
        // } else if ("right".equals(prevDirection)) {
        //     up = false;
        //     down = false;
        //     left = false;
        //     right = true;
        // }
        
        
    
    }

    


    @Override
    public void updateMovingAnimation() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

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

        }

    }

    @Override
    public void updateIdleAnimation() {

        if (keyH.upPressed == false || keyH.downPressed == false || keyH.leftPressed == false || keyH.rightPressed == false) {
                
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

    @Override
    public int collisionDetector() {
        


        gp.cChecker.checkTile(this, gp.numMap);
        int i = gp.cChecker.checkObject(this, gp.obj);
        //int i = gp.cChecker.checkEntity(this, gp.obj);

            if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

          //  if(collisionOn == false) {

            if(up && !collisionUp) {
                worldY -= speed;
                if (left && !collisionLeft) {
                    worldX -= speed;
                } else if (right && !collisionRight) {
                    worldX += speed;
                } 
            } else
            if(down && !collisionDown) {
                worldY += speed;
                if (left && !collisionLeft) {
                    worldX -= speed;
                } else if (right && !collisionRight) {
                    worldX += speed;
                } 
            } else 
            if(left && !collisionLeft) {
                worldX -= speed;
                if (up && !collisionUp) {
                    worldY -= speed;
                } else if (down && !collisionDown) {
                    worldY += speed;
                } 

            } else
            if(right && !collisionRight) {
                worldX += speed;
                if (up && !collisionUp) {
                    worldY -= speed;
                } else if (down && !collisionDown) {
                    worldY += speed;
                } 
            } else 
            if(down && collisionDown) {
                //System.out.println(left + "\nCollisionLeft: " + collisionLeft);
                if (left && !collisionLeft) {
                    worldX -= speed;
                } else if (right && !collisionRight) {
                    worldX += speed;
                } 
            }
                // ADD MORE CONDITIONALS USING IF, SO IF UP OR UP AND LEFT IS TRUE, BUT COLLISION UP TRUE, MOVE LEFT AND IGHT
                //System.out.println(collisionUp);
                //System.out.println(collisionDown);
                //System.out.println(collisionLeft);
                //System.out.println(collisionRight);
                //switch(direction) {
                //case "up" -> worldY -= speed;
                //case "down" -> worldY += speed;
                //case "left" -> worldX -= speed;
                //case "right" -> worldX += speed;
                        
                //}
           // }
            return i;
        }
        return 999;
    }

    















}
