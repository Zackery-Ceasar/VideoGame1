package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public boolean animationActive = false;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }



    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 18 * gp.tileSize;
        gp.obj[0].worldY = 45 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 17 * gp.tileSize;
        gp.obj[1].worldY = 47 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 47 * gp.tileSize;
        gp.obj[2].worldY = 25 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 43 * gp.tileSize;
        gp.obj[3].worldY = 19 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 46 * gp.tileSize;
        gp.obj[4].worldY = 13 * gp.tileSize;

        gp.obj[5] = new OBJ_Chest();
        gp.obj[5].worldX = 46 * gp.tileSize;
        gp.obj[5].worldY = 10 * gp.tileSize;
        
        

    }

    public void dropObject() {
        chestOne();

    }


    public void chestOne() {
        if (gp.obj[5].image == gp.obj[5].open && !gp.player.hasBoots) {
            gp.obj[6] = new OBJ_Boots();
            gp.obj[6].worldX = 46 * gp.tileSize;
            gp.obj[6].worldY = 8 * gp.tileSize;
            animationActive = true;
        }
    }

    public void objectAnimations() {
        if (animationActive) {
            bootsAnimation();
        }
    }

    public void bootsAnimation() {
        while (gp.obj[6].worldY != 11 * gp.tileSize && gp.obj[6] != null) {
            gp.obj[6].worldY += 1;
            return;
        }
        animationActive = false;
    }


}
