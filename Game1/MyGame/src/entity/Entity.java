package entity;

import java.awt.Rectangle;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public String direction;





    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int spriteCounterIdle = 0;
    public int spriteNumIdle = 1;




    int doorOpen = 999;
    int timer = 30;





    

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
}
