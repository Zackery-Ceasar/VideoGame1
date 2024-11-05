package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Door2;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public boolean animationActive = false;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }



    public void setObject() {

        gp.obj[0][1] = new OBJ_Door(gp);
        gp.obj[0][1].worldX = 48 * gp.tileSize;
        gp.obj[0][1].worldY = 33 * gp.tileSize;

        gp.obj[0][3] = new OBJ_Door(gp);
        gp.obj[0][3].worldX = 15 * gp.tileSize;
        gp.obj[0][3].worldY = 16 * gp.tileSize;

        gp.obj[1][0] = new OBJ_Door2(gp);
        gp.obj[1][0].worldX = 23 * gp.tileSize;
        gp.obj[1][0].worldY = 20 * gp.tileSize;
        
        gp.obj[1][1] = new OBJ_Door2(gp);
        gp.obj[1][1].worldX = 41 * gp.tileSize;
        gp.obj[1][1].worldY = 21 * gp.tileSize;        

        gp.obj[1][2] = new OBJ_Key(gp);
        gp.obj[1][2].worldX = 23 * gp.tileSize;
        gp.obj[1][2].worldY = 18 * gp.tileSize;  

        gp.obj[1][3] = new OBJ_Chest(gp);
        gp.obj[1][3].worldX = 41 * gp.tileSize;
        gp.obj[1][3].worldY = 18 * gp.tileSize;





    }

    public void dropObject() {
        chestOne();

    }


    public void chestOne() {
        if (gp.obj[1][3].image == gp.obj[1][3].open && !gp.player.hasBoots) {
            gp.obj[1][6] = new OBJ_Boots(gp);
            gp.obj[1][6].worldX = gp.obj[1][3].worldX;
            gp.obj[1][6].worldY = gp.obj[1][3].worldY - (2 * gp.tileSize);
            animationActive = true;
        }
    }

    public void objectAnimations() {
        if (animationActive) {
            bootsAnimation();
        }
    }

    public void bootsAnimation() {
        while (gp.obj[1][6].worldY != gp.obj[1][3].worldY + gp.tileSize && gp.obj[1][6] != null) {
            gp.obj[1][6].worldY += 1;
            return;
        }
        animationActive = false;
    }


}
