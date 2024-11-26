package main;

import entity.NPC_WiseWarrior;
import foilage.FOIL_Bush1;
import foilage.FOIL_Tree1;
import monster.MON_Skriller;
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

          

        gp.obj[1][3] = new OBJ_Chest(gp);
        gp.obj[1][3].worldX = 39 * gp.tileSize;
        gp.obj[1][3].worldY = 18 * gp.tileSize;

        gp.obj[1][8] = new OBJ_Key(gp);
        gp.obj[1][8].worldX = 24 * gp.tileSize;
        gp.obj[1][8].worldY = 16 * gp.tileSize;



    }

    public void setNPC() {
        gp.npc[0][0] = new NPC_WiseWarrior(gp);
        gp.npc[0][0].worldX = gp.tileSize*11;
        gp.npc[0][0].worldY = gp.tileSize*25;
    }

    public void setMonster() {
        gp.monster[0][0] = new MON_Skriller(gp);
        gp.monster[0][0].worldX = 34*gp.tileSize;
        gp.monster[0][0].worldY = 16*gp.tileSize;

    }

    public void setFoilage() {
        gp.foilage[0][0] = new FOIL_Tree1(gp);
        gp.foilage[0][0].worldX = 18*gp.tileSize - gp.foilage[0][0].solidAreaDefaultX;
        gp.foilage[0][0].worldY = 30*gp.tileSize - gp.foilage[0][0].solidAreaDefaultY;

        gp.foilage[0][1] = new FOIL_Tree1(gp);
        gp.foilage[0][1].worldX = 31*gp.tileSize - gp.foilage[0][1].solidAreaDefaultX;
        gp.foilage[0][1].worldY = 17*gp.tileSize - gp.foilage[0][1].solidAreaDefaultY;

        gp.foilage[0][2] = new FOIL_Tree1(gp);
        gp.foilage[0][2].worldX = 53*gp.tileSize - gp.foilage[0][2].solidAreaDefaultX;
        gp.foilage[0][2].worldY = 32*gp.tileSize - gp.foilage[0][2].solidAreaDefaultY;

        gp.foilage[0][3] = new FOIL_Tree1(gp);
        gp.foilage[0][3].worldX = 12*gp.tileSize - gp.foilage[0][3].solidAreaDefaultX;
        gp.foilage[0][3].worldY = 17*gp.tileSize - gp.foilage[0][3].solidAreaDefaultY;

        gp.foilage[0][4] = new FOIL_Tree1(gp);
        gp.foilage[0][4].worldX = 44*gp.tileSize - gp.foilage[0][4].solidAreaDefaultX;
        gp.foilage[0][4].worldY = 10*gp.tileSize - gp.foilage[0][4].solidAreaDefaultY;

        gp.foilage[0][12] = new FOIL_Tree1(gp);
        gp.foilage[0][12].worldX = 45*gp.tileSize - gp.foilage[0][12].solidAreaDefaultX;
        gp.foilage[0][12].worldY = 21*gp.tileSize - gp.foilage[0][12].solidAreaDefaultY;

        gp.foilage[0][14] = new FOIL_Tree1(gp);
        gp.foilage[0][14].worldX = 61*gp.tileSize - gp.foilage[0][14].solidAreaDefaultX;
        gp.foilage[0][14].worldY = 25*gp.tileSize - gp.foilage[0][14].solidAreaDefaultY;

        gp.foilage[0][5] = new FOIL_Bush1(gp);
        gp.foilage[0][5].worldX = 8*gp.tileSize;
        gp.foilage[0][5].worldY = 20*gp.tileSize;

        gp.foilage[0][6] = new FOIL_Bush1(gp);
        gp.foilage[0][6].worldX = 27*gp.tileSize;
        gp.foilage[0][6].worldY = 20*gp.tileSize;

        gp.foilage[0][7] = new FOIL_Bush1(gp);
        gp.foilage[0][7].worldX = 39*gp.tileSize;
        gp.foilage[0][7].worldY = 19*gp.tileSize;

        gp.foilage[0][8] = new FOIL_Bush1(gp);
        gp.foilage[0][8].worldX = 47*gp.tileSize;
        gp.foilage[0][8].worldY = 26*gp.tileSize;

        gp.foilage[0][9] = new FOIL_Bush1(gp);
        gp.foilage[0][9].worldX = 54*gp.tileSize;
        gp.foilage[0][9].worldY = 23*gp.tileSize;

        gp.foilage[0][10] = new FOIL_Bush1(gp);
        gp.foilage[0][10].worldX = 45*gp.tileSize;
        gp.foilage[0][10].worldY = 20*gp.tileSize;

        gp.foilage[0][11] = new FOIL_Bush1(gp);
        gp.foilage[0][11].worldX = 19*gp.tileSize;
        gp.foilage[0][11].worldY = 14*gp.tileSize;

        gp.foilage[0][13] = new FOIL_Bush1(gp);
        gp.foilage[0][13].worldX = 59*gp.tileSize;
        gp.foilage[0][13].worldY = 29*gp.tileSize;

        gp.foilage[0][15] = new FOIL_Bush1(gp);
        gp.foilage[0][15].worldX = 28*gp.tileSize;
        gp.foilage[0][15].worldY = 10*gp.tileSize;

        gp.foilage[0][16] = new FOIL_Bush1(gp);
        gp.foilage[0][16].worldX = 35*gp.tileSize;
        gp.foilage[0][16].worldY = 12*gp.tileSize;



    }
















    public void dropObject() {
        chestOne();

    }


    public void chestOne() {
        if (gp.obj[1][3].down1 == gp.obj[1][3].alt[1] && !gp.player.hasBoots) {
            gp.obj[1][6] = new OBJ_Boots(gp);
            gp.obj[1][6].worldX = gp.obj[1][3].worldX + gp.tileSize/5;
            gp.obj[1][6].worldY = gp.obj[1][3].worldY;
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
