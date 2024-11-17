package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public final class treeManager {

    public GamePanel gp;
    public int treeTypes = 3;
    public int environmentTypes = 1;
    public BufferedImage trees[][];
    @SuppressWarnings("Convert2Diamond")
    public ArrayList<Integer> worldN = new ArrayList<Integer>();
    @SuppressWarnings("Convert2Diamond")
    public ArrayList<Integer> rowN = new ArrayList<Integer>();
    @SuppressWarnings("Convert2Diamond")
    public ArrayList<Integer> colN = new ArrayList<Integer>();




    public treeManager(GamePanel gp) {
        this.gp = gp;
        //worldN = new ArrayList<int>();

        trees = new BufferedImage[environmentTypes][treeTypes];
        loadTrees();
        //getTreeLocations();
    }



    public void loadTrees() {
        UtilityTool uTool = new UtilityTool();

        try {

            trees[0][0] = ImageIO.read(new File("./res/trees/000.png"));
            trees[0][0] = uTool.scaleImage(trees[0][0], gp.tileSize, gp.tileSize);

            trees[0][1] = ImageIO.read(new File("./res/trees/001.png"));
            trees[0][1] = uTool.scaleImage(trees[0][1], gp.tileSize, gp.tileSize);

            trees[0][2] = ImageIO.read(new File("./res/trees/002.png"));
            trees[0][2] = uTool.scaleImage(trees[0][2], gp.tileSize, gp.tileSize);


        } catch (Exception e) {
        }
        
    }

    public void drawFront(Graphics2D g2, int numMap, int environmenetType) {
        
        int worldCol = 0;
        int worldRow = 0;
            
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = gp.tileM.mapTileNum[numMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;



            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;



            if (worldX + 2*gp.tileSize > gp.player.worldX - gp.player.screenX && // normal case:
                worldX - 2*gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + 2*gp.tileSize > gp.player.worldY - gp.player.screenY  &&
                worldY - 2*gp.tileSize < gp.player.worldY + gp.player.screenY &&
                gp.player.worldY < worldY) {
                                     

                    if (tileNum == 163 && trees[numMap][0] != null) {
                            //System.out.println("Screen x: " + screenX + "\nScreen Y: " + screenY);
                            g2.drawImage(trees[environmenetType][0], screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
                    }
                    if (tileNum == 164 && trees[numMap][0] != null) {
                        //System.out.println("Screen x: " + screenX + "\nScreen Y: " + screenY);
                        g2.drawImage(trees[environmenetType][1], screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
                    }
                    if (tileNum == 165 && trees[numMap][0] != null) {
                        //System.out.println("Screen x: " + screenX + "\nScreen Y: " + screenY);
                        g2.drawImage(trees[environmenetType][2], screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
                    }

            }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }

    public void drawBehind(Graphics2D g2, int numMap, int environmenetType) {
        
        int worldCol = 0;
        int worldRow = 0;
            
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = gp.tileM.mapTileNum[numMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;



            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;



            if (worldX + 2*gp.tileSize > gp.player.worldX - gp.player.screenX && // normal case:
                worldX - 2*gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + 2*gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - 2*gp.tileSize < gp.player.worldY + gp.player.screenY &&
                gp.player.worldY >= worldY) {
                                     

                    if (tileNum == 163 && trees[numMap][0] != null) {
                            //System.out.println("Screen x: " + screenX/gp.tileSize + "\nScreen Y: " + screenY/gp.tileSize );
                            g2.drawImage(trees[environmenetType][0], screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
                    }
                    if (tileNum == 164 && trees[numMap][0] != null) {
                        //System.out.println("Screen x: " + screenX + "\nScreen Y: " + screenY);
                        g2.drawImage(trees[environmenetType][1], screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
                    }
                    if (tileNum == 165 && trees[numMap][0] != null) {
                        //System.out.println("Screen x: " + screenX + "\nScreen Y: " + screenY);
                        g2.drawImage(trees[environmenetType][2], screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
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
