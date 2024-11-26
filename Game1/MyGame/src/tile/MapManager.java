package tile;

import main.GamePanel;

public class MapManager {

    int playerX;
    int playerY;

    int switchRow[];
    int switchCol[];
    int switchMap[];
    int switchMapTo[];
    int switchPosXTo[];
    int switchPosYTo[];
    GamePanel gp;

    //48, 33 -->
    //23, 20 

    public MapManager(GamePanel gp) {
        
        this.gp = gp;

        switchCol = new int[] {48, 23, 41, 15};
        switchRow = new int[] {33, 21, 22, 16};
        switchMap = new int[] {0, 1, 1, 0};
        switchMapTo = new int[] {1, 0, 0, 1};
        switchPosXTo = new int[] {23, 48, 15, 41};
        switchPosYTo = new int[] {19, 34, 17, 20};

    }

    public int checkLocation() {

        playerX = gp.player.worldX/gp.tileSize;
        playerY = gp.player.worldY/gp.tileSize;
        //System.out.println("Player x: " + playerX + "\nPlayer y: " + playerY);

        

        for (int i = 0; i < switchMap.length; i++) {

                int entityLeftWorldX = gp.player.worldX + gp.player.solidArea.x;
                int entityRightWorldX = gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width;
                int entityTopWorldY = gp.player.worldY + gp.player.solidArea.y;
                int entityBottomWorldY = gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height;

                int entityLeftCol = entityLeftWorldX/gp.tileSize;
                int entityRightCol = entityRightWorldX/gp.tileSize;
                int entityTopRow = entityTopWorldY/gp.tileSize;
                int entityBottomRow = entityBottomWorldY/gp.tileSize;

                


                    if (gp.player.up) {
                        entityTopRow = (entityTopWorldY - gp.player.speed)/gp.tileSize;
                    }
                    if (gp.player.down) {
                        entityBottomRow = (entityBottomWorldY + gp.player.speed)/gp.tileSize;
                    }
                    if (gp.player.left) {
                        entityLeftCol = (entityLeftWorldX - gp.player.speed)/gp.tileSize;
                    }
                    if (gp.player.right) {
                        entityRightCol = (entityRightWorldX + gp.player.speed)/gp.tileSize;
                    }
                

                if((entityTopRow == switchRow[i] || entityBottomRow == switchRow[i]) && entityLeftCol == switchCol[i] && switchMap[i] == gp.numMap) {
                    gp.player.worldX = switchPosXTo[i]*gp.tileSize;
                    gp.player.worldY = switchPosYTo[i]*gp.tileSize; 

                    gp.player.switchScreen = true;


                    return switchMapTo[i];                
                }



                
        }

        return gp.numMap;
        


    }





}



