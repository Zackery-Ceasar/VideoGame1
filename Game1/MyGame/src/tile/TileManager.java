package tile;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

final public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];   
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("./res/maps/world1.txt");

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void getTileImage() {


        try {

            // grass
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./res/tiles/grass.png"));

            // tree

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./res/tiles/tree.png"));
            tile[1].collision = true;
            // brick tiles w grass

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./res/tiles/brickGrassT.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("./res/tiles/brickGrassL.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("./res/tiles/brickGrassTL.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("./res/tiles/brickGrassR.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("./res/tiles/brickGrassTR.png"));
            tile[6].collision = true;

            // water tiles

            tile[7] = new Tile();
            tile[7].image1 = ImageIO.read(new File("./res/tiles/water1.png"));
            tile[7].image2 = ImageIO.read(new File("./res/tiles/water2.png"));
            tile[7].collision = true;
            tile[7].animated = true;

            tile[8] = new Tile();
            tile[8].image1 = ImageIO.read(new File("./res/tiles/waterGrassB.png"));
            tile[8].image2 = ImageIO.read(new File("./res/tiles/waterGrassB1.png"));
            tile[8].collision = true;
            tile[8].animated = true;

            tile[9] = new Tile();
            tile[9].image1 = ImageIO.read(new File("./res/tiles/waterGrassBL.png"));
            tile[9].image2 = ImageIO.read(new File("./res/tiles/waterGrassBL1.png"));
            tile[9].collision = true;
            tile[9].animated = true;

            tile[10] = new Tile();
            tile[10].image1 = ImageIO.read(new File("./res/tiles/waterGrassBR.png"));
            tile[10].image2 = ImageIO.read(new File("./res/tiles/waterGrassBR1.png"));
            tile[10].collision = true;
            tile[10].animated = true;

            tile[11] = new Tile();
            tile[11].image1 = ImageIO.read(new File("./res/tiles/waterGrassCBL.png"));
            tile[11].image2 = ImageIO.read(new File("./res/tiles/waterGrassCBL1.png"));
            tile[11].collision = true;
            tile[11].animated = true;

            tile[12] = new Tile();
            tile[12].image1 = ImageIO.read(new File("./res/tiles/waterGrassCBR.png"));
            tile[12].image2 = ImageIO.read(new File("./res/tiles/waterGrassCBR1.png"));
            tile[12].collision = true;
            tile[12].animated = true;

            tile[13] = new Tile();
            tile[13].image1 = ImageIO.read(new File("./res/tiles/waterGrassT.png"));
            tile[13].image2 = ImageIO.read(new File("./res/tiles/waterGrassT1.png"));
            tile[13].collision = true;
            tile[13].animated = true;

            tile[14] = new Tile();
            tile[14].image1 = ImageIO.read(new File("./res/tiles/waterGrassTL.png"));
            tile[14].image2 = ImageIO.read(new File("./res/tiles/waterGrassTL1.png"));
            tile[14].collision = true;
            tile[14].animated = true;

            tile[15] = new Tile();
            tile[15].image1 = ImageIO.read(new File("./res/tiles/waterGrassTR.png"));
            tile[15].image2 = ImageIO.read(new File("./res/tiles/waterGrassTR1.png"));
            tile[15].collision = true;
            tile[15].animated = true;

            tile[16] = new Tile();
            tile[16].image1 = ImageIO.read(new File("./res/tiles/waterGrassCTL.png"));
            tile[16].image2 = ImageIO.read(new File("./res/tiles/waterGrassCTL1.png"));
            tile[16].collision = true;
            tile[16].animated = true;

            tile[17] = new Tile();
            tile[17].image1 = ImageIO.read(new File("./res/tiles/waterGrassCTR.png"));
            tile[17].image2 = ImageIO.read(new File("./res/tiles/waterGrassCTR1.png"));
            tile[17].collision = true;
            tile[17].animated = true;

            tile[18] = new Tile();
            tile[18].image1 = ImageIO.read(new File("./res/tiles/waterGrassL.png"));
            tile[18].image2 = ImageIO.read(new File("./res/tiles/waterGrassL1.png"));
            tile[18].collision = true;
            tile[18].animated = true;

            tile[19] = new Tile();
            tile[19].image1 = ImageIO.read(new File("./res/tiles/waterGrassR.png"));
            tile[19].image2 = ImageIO.read(new File("./res/tiles/waterGrassR1.png"));
            tile[19].collision = true;
            tile[19].animated = true;

            // grass path tiles

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(new File("./res/tiles/grassPathV.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(new File("./res/tiles/grassPathH.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(new File("./res/tiles/grassPathTL.png"));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(new File("./res/tiles/grassPathBL.png"));

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(new File("./res/tiles/grassPathTR.png"));

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(new File("./res/tiles/grassPathBR.png"));

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(new File("./res/tiles/grassPathCross.png"));

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(new File("./res/tiles/grassPathET.png"));

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(new File("./res/tiles/grassPathEL.png"));

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(new File("./res/tiles/grassPathER.png"));

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(new File("./res/tiles/grassPathEB.png"));

            // more brick tiles

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(new File("./res/tiles/brickGrassCTL.png"));
            tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(new File("./res/tiles/brickGrassCTR.png"));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(new File("./res/tiles/brickGrassCBL.png"));
            tile[33].collision = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(new File("./res/tiles/brickGrassCBR.png"));
            tile[34].collision = true;


            // wood

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(new File("./res/tiles/wood.png"));

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(new File("./res/tiles/brickWoodL.png"));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(new File("./res/tiles/brickWoodR.png"));
            tile[37].collision = true;

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(new File("./res/tiles/brickWoodCTR.png"));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(new File("./res/tiles/brickWoodCTL.png"));
            tile[39].collision = true;

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(new File("./res/tiles/brickWoodCBR.png"));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(new File("./res/tiles/brickWoodCBL.png"));
            tile[41].collision = true;


            // more paths
            tile[42] = new Tile();
            tile[42].image = ImageIO.read(new File("./res/tiles/grassPathCrossU.png"));

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(new File("./res/tiles/grassPathCrossL.png"));

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(new File("./res/tiles/grassPathCrossR.png"));

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(new File("./res/tiles/grassPathCrossD.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @SuppressWarnings({"UseSpecificCatch", "ConvertToTryWithResources"})
    public void loadMap(String filePath) {
        try {

            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                String numbers[] = line.split("  ");

                while(col < gp.maxWorldCol) {

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();


        } catch (Exception e) {
        }
    }


    public void draw(Graphics2D g2) {
        
        int worldCol = 0;
        int worldRow = 0;
            
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(tileNum != 7) {

            tile[tileNum].update(1800);
            tile[tileNum].animate();

            } else {
            tile[tileNum].update(10000);
            tile[tileNum].animate();
            }
            

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }







}
