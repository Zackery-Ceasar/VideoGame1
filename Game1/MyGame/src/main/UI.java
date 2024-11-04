package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font garamond_20;
    BufferedImage keys;
    public boolean messageOn = false;
    public String text = "";
    int textTimer = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        garamond_20 = new Font("Garamond", Font.BOLD, 20);
        OBJ_Key key = new OBJ_Key(gp);
        keys = key.image;
    }

    public void showMessage(String text) {
        this.text = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(garamond_20);
        g2.setColor(Color.white);
        g2.drawImage(keys, 20, 20, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 55, 50);

        if(messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(text, gp.tileSize*8 - text.length()*7 ,gp.tileSize*4);

            textTimer++;

            if (textTimer > 100) {
                textTimer = 0;
                messageOn = false;
            }
        }

        
    }

}
