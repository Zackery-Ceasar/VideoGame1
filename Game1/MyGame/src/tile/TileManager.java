package tile;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

final public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    public int numMaps = 1;

    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();


















    // Constructor

    @SuppressWarnings({ "UseSpecificCatch", "resource", "CallToPrintStackTrace" })
    public TileManager(GamePanel gp) {

        this.gp = gp;



        //Read tile data file

        InputStream is;

        BufferedReader br;
        
        try {

        
            is = new FileInputStream("res/maps/tiledata.txt");
            
            br = new BufferedReader(new InputStreamReader(is));

            // Getting tile names and collision info from file

            String line;

            try {
                while((line = br.readLine()) != null) {
                    fileNames.add(line);
                    collisionStatus.add(br.readLine());
                }
                
                
                br.close();
            } catch (Exception e) {

            }
            
            
            tile = new Tile[fileNames.size()];  

            getTileImage();

            
                //Get max world col and row



            // Can use 3d array.

            

            is = new FileInputStream("res/maps/worldMap1.txt");
            
            br = new BufferedReader(new InputStreamReader(is));

            try {

                String line2 = br.readLine();
                String maxTile[] = line2.split(" ");

                gp.maxWorldCol = maxTile.length;
                gp.maxWorldRow = maxTile.length;
                br.close();

            } catch (Exception e) {

            }
            


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }

        mapTileNum = new int[numMaps][gp.maxWorldCol][gp.maxWorldRow];


        // loads map into map "0"
        loadMap("res/maps/worldMap1.txt", 0);

    }












    // read tiles in

    @SuppressWarnings("CallToPrintStackTrace")
    public void getTileImage() {


        try {


            for (int i = 0; i < fileNames.size(); i++) {
                String fileName;
                boolean collision;
                
                fileName = fileNames.get(i);
                if (collisionStatus.get(i).equals("true")) {
                    collision = true;
                } else {
                    collision = false;
                }
                System.out.println(fileName);
                System.out.println(collision);

                setup(i, fileName, collision);



                
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    // scale tiles to boost preformance




    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new File("./res/tiles/" + imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    // reads from map file
    // whatever filepath is is what map is read
    // in order to implement other maps in which the player can navigate to, so interiors, exteriors, etc, 




    @SuppressWarnings({"UseSpecificCatch", "ConvertToTryWithResources"})
    public void loadMap(String filePath, int numMap) {
        try {

            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                String numbers[] = line.split(" ");

                while(col < gp.maxWorldCol) {

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[numMap][col][row] = num;
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





    // draws the map with tiles



    // choose which map to draw from

    public void draw(Graphics2D g2, int numMap) {
        
        int worldCol = 0;
        int worldRow = 0;
            
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[numMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;



            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;



            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // normal case:
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                                     

                    if (tile[tileNum].image != null) {
                        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
            }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }







}
