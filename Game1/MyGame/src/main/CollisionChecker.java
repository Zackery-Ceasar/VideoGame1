package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;


    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity, int numMap) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
        case "up" -> {
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            }
        case "down" -> {
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }   }
        case "left" -> {
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }   }
        case "right" -> {
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }   }
        }
    }


    
    public int checkObject(Entity entity, boolean player) {


        int index = 999;

        for(int i = 0; i < gp.objectQTY; i++) {
            
            if(gp.obj[gp.numMap][i] != null) {
                //System.out.println("Collision status " + gp.obj[gp.numMap][i].name);
                // Get entities solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[gp.numMap][i].solidArea.x = gp.obj[gp.numMap][i].worldX + gp.obj[gp.numMap][i].solidArea.x;
                gp.obj[gp.numMap][i].solidArea.y = gp.obj[gp.numMap][i].worldY + gp.obj[gp.numMap][i].solidArea.y;


                switch(entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.numMap][i].solidArea)) {
                            if(gp.obj[gp.numMap][i].collision == true) {
                                //System.out.println("Collision");
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.numMap][i].solidArea)) {
                            if(gp.obj[gp.numMap][i].collision == true) {
                                //System.out.println("Collision");
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.numMap][i].solidArea)) {
                            //System.out.println("Collision");
                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.numMap][i].solidArea)) {
                            //System.out.println("Collision");
                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                    }

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.obj[gp.numMap][i].solidArea.x = gp.obj[gp.numMap][i].solidAreaDefaultX;
                gp.obj[gp.numMap][i].solidArea.y = gp.obj[gp.numMap][i].solidAreaDefaultY;
            }
        }

        return index;


    }
        
    
    
}
