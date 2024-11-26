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

        if (entity.up) {
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionUp = true;
            }
        }
        if (entity.down) {
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionDown = true;
            }
        }
        if (entity.left) {
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityBottomRow];

            //System.out.println("LEFT COL: " + entityLeftCol + " BottomRow: " + entityBottomRow*gp.tileSize + " ENTITY WORLDY: " + entity.worldY);

            //System.out.println("BOTTOMLEFT COLLISION: " + gp.tileM.tile[tileNum2].collision);

            if (entity.collisionDown) {
                tileNum2 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityBottomRow-1];
            } else if (entity.collisionUp) {
                tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityTopRow+1];
            }
            

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ) {
                entity.collisionLeft = true;          
            }

            

        }
        if (entity.right) {
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[numMap][entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[numMap][entityRightCol][entityBottomRow];

            if (entity.collisionDown) {
                tileNum2 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityBottomRow-1];
            } else if (entity.collisionUp) {
                tileNum1 = gp.tileM.mapTileNum[numMap][entityLeftCol][entityTopRow+1];
            }

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ) {
                entity.collisionRight = true;
            }

        }

        
    }



    /* 
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
        */




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
                    if (entity.up) {
                        entity.solidArea.y -=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity) {
                                entity.collisionUp = true;
                                index = i;
                            }
                            
                        }
                    }
                    if (entity.down) {
                        entity.solidArea.y +=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity) {
                                entity.collisionDown = true;
                                index = i;
                            }
                            
                        }
                    }
                    if (entity.left) {

                        entity.solidArea.x -=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity) {
                                if(!entity.collisionUp && !entity.collisionDown) {
                                    entity.collisionLeft = true;
                                    index = i;
                                }
                            }
                            
                        }
                    }
                    if (entity.right) {


                        entity.solidArea.x +=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity) {
                                if(!entity.collisionUp && !entity.collisionDown) {
                                    entity.collisionRight = true;
                                    index = i;
                                }
                            }
                            
                        }
                    }

                    //switch(entity.direction) {
                    //    case "up" -> entity.solidArea.y -=entity.speed;
                    //    case "down" -> entity.solidArea.y +=entity.speed;
                    //    case "left" -> entity.solidArea.x -=entity.speed;
                    //    case "right" -> entity.solidArea.x +=entity.speed;
                    //}



                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;

                    target[gp.numMap][i].solidArea.x = target[gp.numMap][i].solidAreaDefaultX;
                    target[gp.numMap][i].solidArea.y = target[gp.numMap][i].solidAreaDefaultY;

                    }

                
                }    
        

        return index;
        


    }

    public int checkFoilage(Entity entity, Entity[][] target) { 
        int index = 999;

        for (int i = 0; i < target[0].length; i++) {

                
                if (target[gp.numMap][i] != null) {

                    //System.out.println(target[gp.numMap][i]);

                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    target[gp.numMap][i].solidArea.x = target[gp.numMap][i].worldX + target[gp.numMap][i].solidArea.x;
                    target[gp.numMap][i].solidArea.y = target[gp.numMap][i].worldY + target[gp.numMap][i].solidArea.y;
                    if (entity.up) {
                        entity.solidArea.y -=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity && target[gp.numMap][i].collision) {
                                entity.collisionUp = true;
                                index = i;
                            }
                            
                        }
                    }
                    if (entity.down) {
                        entity.solidArea.y +=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity && target[gp.numMap][i].collision) {
                                entity.collisionDown = true;
                                index = i;
                            }
                            
                        }
                    }
                    if (entity.left) {

                        entity.solidArea.x -=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity && target[gp.numMap][i].collision) {
                                if(!entity.collisionUp && !entity.collisionDown) {
                                    entity.collisionLeft = true;
                                    index = i;
                                }
                            }
                            
                        }
                    }
                    if (entity.right) {


                        entity.solidArea.x +=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (target[gp.numMap][i] != entity && target[gp.numMap][i].collision) {
                                if(!entity.collisionUp && !entity.collisionDown) {
                                    entity.collisionRight = true;
                                    index = i;
                                }
                            }
                            
                        }
                    }

                    //switch(entity.direction) {
                    //    case "up" -> entity.solidArea.y -=entity.speed;
                    //    case "down" -> entity.solidArea.y +=entity.speed;
                    //    case "left" -> entity.solidArea.x -=entity.speed;
                    //    case "right" -> entity.solidArea.x +=entity.speed;
                    //}



                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;

                    target[gp.numMap][i].solidArea.x = target[gp.numMap][i].solidAreaDefaultX;
                    target[gp.numMap][i].solidArea.y = target[gp.numMap][i].solidAreaDefaultY;

                    }

                
                }    
        

        return index;
        


    }

    public int checkObject(Entity entity, Entity[][] target) { 
        int index = 999;

        for (int i = 0; i < target[0].length; i++) {

                
                if (target[gp.numMap][i] != null) {

                    //System.out.println(target[gp.numMap][i]);

                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    target[gp.numMap][i].solidArea.x = target[gp.numMap][i].worldX + target[gp.numMap][i].solidArea.x;
                    target[gp.numMap][i].solidArea.y = target[gp.numMap][i].worldY + target[gp.numMap][i].solidArea.y;

                    if (entity.up) {
                        entity.solidArea.y -=entity.speed-1;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionUp = true;
                                index = i;
                            } else {
                                index = i;
                            }
                        }
                    }
                    if (entity.down) {
                        entity.solidArea.y +=entity.speed+1;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (gp.obj[gp.numMap][i].collision == true) {
                                entity.collisionDown = true;
                                index = i;
                            } else {
                                index = i;
                            }
                        }
                    }
                    if (entity.left) {
                        entity.solidArea.x -=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (gp.obj[gp.numMap][i].collision == true) {
                                if(!entity.collisionUp && !entity.collisionDown) {
                                    entity.collisionLeft = true;
                                    index = i;
                                } else {
                                    entity.collisionLeft = true;
                                    index = i;
                                }
                            } else {
                                index = i;
                            }
                        }
                    }
                    if (entity.right) {
                        entity.solidArea.x +=entity.speed;
                        if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
                            if (gp.obj[gp.numMap][i].collision == true) {
                                if(!entity.collisionUp && !entity.collisionDown) {
                                    entity.collisionRight = true;
                                    index = i;
                                } else {
                                    entity.collisionRight = true;
                                    index = i;
                                }
                                
                            } else {
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

    public boolean checkPlayer(Entity entity) {

                    boolean contactPlayer = false;

                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                    gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
                    if (entity.up) {
                        entity.solidArea.y -=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionUp = true;
                            contactPlayer = true;
                        }                        
                    }
                    if (entity.down) {
                        entity.solidArea.y +=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionDown = true;
                            contactPlayer = true;
                        }
                    }
                    if (entity.left) {
                        entity.solidArea.x -=entity.speed;

                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionRight = true;
                            contactPlayer = true;
                        }
                    }
                    if (entity.right) {
                        entity.solidArea.x +=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionLeft = true;
                            contactPlayer = true;
                        }
                    }

                    

                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;

                    gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                    gp.player.solidArea.y = gp.player.solidAreaDefaultY;

                    return contactPlayer;
    }
    
 

}