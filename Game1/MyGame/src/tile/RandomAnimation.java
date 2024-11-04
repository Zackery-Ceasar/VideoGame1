package tile;

import java.awt.Graphics2D;
import java.util.Random;
import main.GamePanel;






final public class RandomAnimation {

    GamePanel gp;
    Random randomNum = new Random();

    int randomCol;
    int randomRow;

    int maxRow;
    int maxCol;
    int minRow;
    int minCol;

    int tileNum;

    int animatedCol;
    int animatedRow;

    int count = 0;
    int activateSpeed = 2;

    boolean animationGoing = false;

    public int nextFrame;










    public RandomAnimation(GamePanel gp) {
        
        this.gp = gp;

        maxRow = gp.maxWorldRow; // changes

        maxCol = gp.maxWorldCol; // stays the same

        setAnimatedImages();

    }

    public void animateRandom(Graphics2D g2, int numMap) {

        count++;
            if (count > activateSpeed) {

                getMaxMin();
                getRandomTile(numMap);
                animate(g2, numMap);

                count = 0;
            }

        



    }


    public void getMaxMin() {



        minRow = gp.player.worldY/gp.tileSize - gp.player.screenY/gp.tileSize - 2;
        maxRow = gp.player.worldY/gp.tileSize + gp.player.screenY/gp.tileSize + 2;

        minCol = gp.player.worldX/gp.tileSize - gp.player.screenX/gp.tileSize - 2;
        maxCol = gp.player.worldX/gp.tileSize + gp.player.screenX/gp.tileSize + 2;


        //System.out.println("Min row: " + minRow + " Max row: " + maxRow + " Min col: " + minCol + " Max col " + maxCol);
    }



    


    public void getRandomTile(int numMap) {
            
        int randomTile;
        //if (!animationGoing) {

            randomCol = randomNum.nextInt(maxCol - minCol) + minCol;
            randomRow = randomNum.nextInt(maxRow - minRow) + minRow;


            // Min row: 18 Max row: 32 Min col: 3 Max col 21
        
            randomTile = gp.tileM.mapTileNum[numMap][randomCol][randomRow];

            //System.out.println("random tile: " + randomTile + "  randomCol " + randomCol + "  randomRow " + randomRow);

            if (gp.tileM.tile[randomTile].animated) {
                tileNum = randomTile;
                animatedCol = randomCol;
                animatedRow = randomRow;
                animationGoing = true;
            }






        //}

        

    }

    // player pos 12 25

    // 20, 19 smallest

    // 35, 30 biggest

    // diff of 15 for x, diff of 11 for y - normal for intervals

    // diff of 8 and -6 from player pos, should be around -8

    // so 20 - 16 and 35 - 16. Theres a difference of 16 here here which is about the size of a tile


    public void animate(Graphics2D g2, int numMap) {


        if (animationGoing) {
            //System.out.println("Animated Image; " + animatedCol + "," + animatedRow);
            gp.tileM.mapTileNum[numMap][animatedCol][animatedRow] = gp.tileM.tile[tileNum].linked;
            animationGoing = false;
        }

        
            
        // draws, but doesn't keep drawing
        // in order to save tile that was drawn, all info needs to be stored temporarily. This is a lot of data needs to be stored, so maybe a diffferent approach.
        // instead of painting over, change in number within the map, so we feed animated frame into gp.tileM.mapTileNum[randomCol][randomRow], 
        // at its previous "randomTile location" we swap in new tile image.
        // this means we have to store animated tiles into the tile manager, i think. It uses maps each image to a number.

    }

        // setAnimatedTiles;

    public void setAnimatedImages() {

        // grass
        
        gp.tileM.tile[1].animated = true;
        gp.tileM.tile[1].linked = 116;

        gp.tileM.tile[116].animated = true;
        gp.tileM.tile[116].linked = 1;

        // trees

        gp.tileM.tile[17].animated = true;
        gp.tileM.tile[17].linked = 117;
        
        gp.tileM.tile[117].animated = true;
        gp.tileM.tile[117].linked = 17;



        

    }
    





}
