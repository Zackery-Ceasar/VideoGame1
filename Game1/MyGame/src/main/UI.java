package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import object.OBJ_Health;
import object.SuperObject;

public final class UI {

    GamePanel gp;
    Font garamond_20;
    Graphics2D g2;

    public boolean messageOn = false;
    public String text = "";
    int textTimer = 0;

    BufferedImage idle1, idle2, idle3, idle4;
    BufferedImage image;

    BufferedImage healthBarEnding, healthBar, emptyHealthBar;

    int imageCounter = 0, imageNum = 1;
    
    

    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        garamond_20 = new Font("Garamond", Font.BOLD, 20);

        idle1 = setup("./res/player/idle1");
        idle2 = setup("./res/player/idle2");
        idle3 = setup("./res/player/idle3");
        idle4 = setup("./res/player/idle4");

        SuperObject health = new OBJ_Health(gp);
        healthBarEnding = health.alt[0];
        healthBar = health.alt[1];
        emptyHealthBar = health.alt[2];
        


    }

    public void showMessage(String text) {
        this.text = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(garamond_20);
        g2.setColor(Color.white);

        // Title state

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }


        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE

        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        
    }


    public void drawPlayerLife() {
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        //BLANK HEALTH BAR AT MAX HEALTH
        g2.drawImage(healthBarEnding, x, y, null);
        x+=2;
        while(i < gp.player.maxLife) {

            g2.drawImage(emptyHealthBar, x, y, null);
            i++;
            x+=2;
            
        }
        g2.drawImage(healthBarEnding, x, y, null);
        

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        x+=2;

        // Curr health

        while (i < gp.player.life) {
            g2.drawImage(healthBar, x, y, null);
            i++;
            x+=2;
        }

    }

    public void drawTitleScreen() {

        g2.setColor(new Color(48, 48, 48));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        g2.setColor(new Color(55, 55, 55));
        g2.fillRoundRect(10,10, gp.screenWidth-20, gp.screenHeight-20, 15, 15);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "What Lies Within";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;
        //SHADOW

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);
        //MAIN

        g2.setColor(new Color(219, 219, 219));
        g2.drawString(text, x, y);

        x = gp.screenWidth/2 - (gp.tileSize)/2;
        int y2 = y + gp.tileSize*3;

        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillOval(x-10, y2-4, gp.tileSize + 20, gp.tileSize/2);

        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*1;

        image = drawIdleAnimation(image);
        updateIdleAnimation();

        g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2 + gp.tileSize/3, null);
        
        
        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        


        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);

        if (commandNum == 0) {

            g2.drawString("-", x-gp.tileSize/2+5, y);
            g2.setColor(new Color(255, 255, 255));
            g2.drawString("-", x-gp.tileSize/2, y-5);
        } else {
            g2.setColor(new Color(200, 200, 200));
        }
        
        g2.drawString(text, x, y);

        




        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*1;

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);

        if (commandNum == 1) {
            g2.drawString("-", x-gp.tileSize/2+5, y);
            g2.setColor(new Color(255, 255, 255));
            g2.drawString("-", x-gp.tileSize/2, y-5);
        } else {
            g2.setColor(new Color(200, 200, 200));
        }
        g2.drawString(text, x, y);

        


        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize*1;

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);

        if (commandNum == 2) {
            g2.drawString("-", x-gp.tileSize/2+5, y);
            g2.setColor(new Color(255, 255, 255));
            g2.drawString("-", x-gp.tileSize/2, y-5);
        } else {
            g2.setColor(new Color(200, 200, 200));
        }
        g2.drawString(text, x, y);

        // MENU SHADOW



    }





    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/1;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y+= 40;
        }
        

    }

    public void drawSubWindow(int x, int y, int width, int height) {


        Color c = new Color(0,0,0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 15, 15);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x+2, y+2, width-4, height-4, 15, 15);

    }

    public void drawPauseScreen() {
        g2.setColor(new Color(48, 48, 48, 200));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2 - 50;

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);

        g2.setColor(new Color(200, 200, 200));
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));


        text = "RETURN";
        x = getXforCenteredText(text);
        y += gp.tileSize*3;

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);

        if (commandNum == 0) {
            g2.drawString("-", x-gp.tileSize/2+5, y);
            g2.setColor(new Color(255, 255, 255));
            g2.drawString("-", x-gp.tileSize/2, y-5);
        } else {
            g2.setColor(new Color(200, 200, 200));
        }
        g2.drawString(text, x, y);

        


        text = "MAIN MENU";
        x = getXforCenteredText(text);
        y += gp.tileSize*1;

        g2.setColor(new Color(0, 20, 0));
        g2.drawString(text, x+5, y+5);

        if (commandNum == 1) {
            g2.drawString("-", x-gp.tileSize/2+5, y);
            g2.setColor(new Color(255, 255, 255));
            g2.drawString("-", x-gp.tileSize/2, y-5);
        } else {
            g2.setColor(new Color(200, 200, 200));
        }
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
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


    public void updateIdleAnimation() {

      
                
            imageCounter++;
                            
            if (imageCounter > 45) {
                switch (imageNum) {
                    case 1 -> imageNum = 2;
                    case 2 -> imageNum = 3;
                    case 3 -> imageNum = 4;
                    case 4 -> imageNum = 5;
                    case 5 -> imageNum = 6;
                    case 6 -> imageNum = 1;
                        
                }
                imageCounter = 0;
            }


        
    }






    public BufferedImage drawIdleAnimation(BufferedImage image) {
    
            if (imageNum == 1) {
                image = idle1;
            }
            if (imageNum == 2) {
                image = idle2;
            }
            if (imageNum == 3) {
                image = idle3;
            }
            if (imageNum == 4) {
                image = idle4;
            }
            if (imageNum == 5) {
                image = idle3;
            }
            if (imageNum == 6) {
                image = idle2;
            }
                        

       return image;




    }

}
