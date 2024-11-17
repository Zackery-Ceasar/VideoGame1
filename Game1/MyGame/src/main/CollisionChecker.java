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
            entityBottomRow = (entityBottomWorldY + entity.speed+5)/gp.tileSize;
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

        for (int i = 0; i < gp.objectQTY; i++) {

                int entityLeftWorldX = entity.worldX + entity.solidArea.x;
                int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
                int entityTopWorldY = entity.worldY + entity.solidArea.y;
                int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
                int entityLeftCol = entityLeftWorldX/gp.tileSize;
                int entityRightCol = entityRightWorldX/gp.tileSize;
                int entityTopRow = entityTopWorldY/gp.tileSize;
                int entityBottomRow = entityBottomWorldY/gp.tileSize;

                

                
                if (gp.obj[gp.numMap][i] != null) {

                    //System.out.println(gp.obj[gp.numMap][i].worldX);
                    // row -> gp.obj[gp.numMap][i].worldY

                    int checkTileRow = gp.obj[gp.numMap][i].worldY/gp.tileSize;
                    int checkTileCol = gp.obj[gp.numMap][i].worldX/gp.tileSize;
                    
                
                    switch(gp.player.direction) {
                    case "up" -> {
                        entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                        if(entityTopRow == checkTileRow && (entityLeftCol == checkTileCol || entityRightCol == checkTileCol)) {

                            if(gp.obj[gp.numMap][i].collision == true) {
                                
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }


                            return index;
                        }
                    }
                    case "down" -> {
                        entityBottomRow = (entityBottomWorldY + entity.speed+5)/gp.tileSize;
                        if(entityBottomRow == checkTileRow && (entityLeftCol == checkTileCol || entityRightCol == checkTileCol)) {

                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }


                            return index;                
                        }
                    }
                    case "left" -> {
                        entityRightCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                        if((entityTopRow == checkTileRow || entityBottomRow == checkTileRow) && entityRightCol == checkTileCol) {
                            
                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }

                            return index;                
                        }
                    }
                    case "right" -> {
                        entityLeftCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                        if((entityTopRow == checkTileRow || entityBottomRow == checkTileRow) && entityLeftCol == checkTileCol) {

                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }


                            return index;               
                        }
                    }
                    }
                }    
        }

        return index;
        


    }

    
    public int checkObject(Entity entity) {
        int index = 999;

        for (int i = 0; i < gp.objectQTY; i++) {

                int entityLeftWorldX = entity.worldX + entity.solidArea.x;
                int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
                int entityTopWorldY = entity.worldY + entity.solidArea.y;
                int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
                int entityLeftCol = entityLeftWorldX/gp.tileSize;
                int entityRightCol = entityRightWorldX/gp.tileSize;
                int entityTopRow = entityTopWorldY/gp.tileSize;
                int entityBottomRow = entityBottomWorldY/gp.tileSize;

                

                
                if (gp.obj[gp.numMap][i] != null) {

                    //System.out.println(gp.obj[gp.numMap][i].worldX);
                    // row -> gp.obj[gp.numMap][i].worldY

                    int checkTileRow = gp.obj[gp.numMap][i].worldY/gp.tileSize;
                    int checkTileCol = gp.obj[gp.numMap][i].worldX/gp.tileSize;
                    
                
                    switch(gp.player.direction) {
                    case "up" -> {
                        entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                        if(entityTopRow == checkTileRow && (entityLeftCol == checkTileCol || entityRightCol == checkTileCol)) {

                            if(gp.obj[gp.numMap][i].collision == true) {
                                
                                entity.collisionOn = true;
                            }


                            return index;
                        }
                    }
                    case "down" -> {
                        entityBottomRow = (entityBottomWorldY + entity.speed+5)/gp.tileSize;
                        if(entityBottomRow == checkTileRow && (entityLeftCol == checkTileCol || entityRightCol == checkTileCol)) {

                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }


                            return index;                
                        }
                    }
                    case "left" -> {
                        entityRightCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                        if((entityTopRow == checkTileRow || entityBottomRow == checkTileRow) && entityRightCol == checkTileCol) {
                            
                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }

                            return index;                
                        }
                    }
                    case "right" -> {
                        entityLeftCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                        if((entityTopRow == checkTileRow || entityBottomRow == checkTileRow) && entityLeftCol == checkTileCol) {

                            if(gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionOn = true;
                            }


                            return index;               
                        }
                    }
                    }
                }    
        }

        return index;
        


    }

    //NPC or MONSTER
    public int checkEntity(Entity entity, Entity[][] target) { 
        int index = 999;

        for (int i = 0; i < target[0].length; i++) {

                
                if (target[gp.numMap][i] != null) {

                    //System.out.println(target[gp.numMap][i]);

                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    target[gp.numMap][i].solidArea.x = target[gp.numMap][i].worldX + target[gp.numMap][i].solidArea.x;
                    target[gp.numMap][i].solidArea.y = target[gp.numMap][i].worldY + target[gp.numMap][i].solidArea.y;
                    switch(entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }          
                        
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }

                    }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[gp.numMap][i].solidArea.x = target[gp.numMap][i].solidAreaDefaultX;
                target[gp.numMap][i].solidArea.y = target[gp.numMap][i].solidAreaDefaultY;
                }    
        }

        return index;
        


    }

    public void checkPlayer(Entity entity) {
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                    gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
                    switch(entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                        }          
                        
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                        }
                    }

                    }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
 

}