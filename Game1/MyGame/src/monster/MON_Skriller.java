package monster;

import entity.Entity;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.GamePanel;

public class MON_Skriller extends Entity {

    public MON_Skriller(GamePanel gp) {
        super(gp);

        type = 2;

        name = "Skriller";
        speed = 1;
        maxLife = 30;
        life = maxLife;

        width = gp.tileSize + gp.tileSize/4;
        height = gp.tileSize + gp.tileSize/12;
        // 60 width 52 height

        solidArea.x = 15;
        solidArea.y = 18;
        solidArea.width = 30;
        solidArea.height = 34;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        direction = "left1";

        idle = true;

        getImage();

        spriteNum = 1;
        spriteNumIdle = 1;

        animationSpeed = 4;




    }

    public void getImage() {
        up1 = setup("res/monster/skriller/up1");
        up2 = setup("res/monster/skriller/up2");
        up3 = setup("res/monster/skriller/up3");

        down1 = setup("res/monster/skriller/down1");
        down2 = setup("res/monster/skriller/down2");
        down3 = setup("res/monster/skriller/down3");
        
        left1 = setup("res/monster/skriller/left1");
        left2 = setup("res/monster/skriller/left2");
        left3 = setup("res/monster/skriller/left3");

        right1 = setup("res/monster/skriller/right1");
        right2 = setup("res/monster/skriller/right2");
        right3 = setup("res/monster/skriller/right3");
    } 

    //res\monster\skriller\left3.png
        @Override
    public void setAction() {

        //System.out.println("setAction: check!");

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
                            image = right3;
                        }
                        }
            
                    }
              //  }

        return image;
    }

    @Override
    public void updateMovingAnimation() {
            //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
                //System.out.println("updateMovingAnimation: check!  :  " + spriteNum);


                spriteCounter++;
                if (spriteCounter > animationSpeed) {
                    switch (spriteNum) {
                        case 1 -> spriteNum = 2;
                        case 2 -> spriteNum = 3;
                        case 3 -> spriteNum = 1;
                        default -> {
                        }
                    }
                    spriteCounter = 0;
                }

            //}

    }

    @Override
    public BufferedImage drawIdleAnimation() {

        //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        BufferedImage image = null;

                switch(direction) {
                    case "up" -> {
                        if (spriteNumIdle == 1) {
                            image = up1;
                        }
                        if (spriteNumIdle == 2) {
                            image = up2;
                        }
                        if (spriteNumIdle == 3) {
                            image = up3;
                        }
                        }
                    case "down" -> {
                        if (spriteNumIdle == 1) {
                            image = down1;
                        }
                        if (spriteNumIdle == 2) {
                            image = down2;
                        }
                        if (spriteNumIdle == 3) {
                            image = down3;
                        }
                        }
                    case "left" -> {
                        if (spriteNumIdle == 1) {
                            image = left1;
                        }
                        if (spriteNumIdle == 2) {
                            image = left2;
                        }
                        if (spriteNumIdle == 3) {
                            image = left3;
                        }
                        }
                    case "right" -> {
                        if (spriteNumIdle == 1) {
                            image = right1;
                        }
                        if (spriteNumIdle == 2) {
                            image = right2;
                        }
                        if (spriteNumIdle == 3) {
                            image = right3;
                        }
                        }
            
                    }
              //  }

        return image;
    }

   

    @Override
    public void updateIdleAnimation() {
        //System.out.println("updateIdleAnimation: check!  :  " + spriteNumIdle);

                
        spriteCounterIdle++;
                        
        if (spriteCounterIdle > animationSpeed) {
            switch (spriteNumIdle) {
                case 1 -> spriteNumIdle = 2;
                case 2 -> spriteNumIdle = 3;
                case 3 -> spriteNumIdle = 1;
                    
            }
            spriteCounterIdle = 0;
        }



    }

















}
