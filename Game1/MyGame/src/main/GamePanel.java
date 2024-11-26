package main;


import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import messages.MessageManager;
import tile.MapManager;
import tile.RandomAnimation;
import tile.TileManager;



@SuppressWarnings("InitializerMayBeStatic")
public class GamePanel extends JPanel implements Runnable {

    // Screen Settings


    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World Settings
    public int maxWorldCol;
    public int maxWorldRow;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public int numMap = 0;

    public int  objectQTY = 10;
    public int  NPCQTY = 10;



    // FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public RandomAnimation rAnimate = new RandomAnimation(this);

    
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public MessageManager mManager = new MessageManager(this);
    public Player player = new Player(this, keyH, aSetter, mManager);
    public MapManager mapM = new MapManager(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    
    public Entity obj[][] = new Entity[tileM.numMaps][objectQTY];
    public Entity npc[][] = new Entity[tileM.numMaps][NPCQTY];
    public Entity monster[][] = new Entity[tileM.numMaps][15];
    public Entity foilage[][] = new Entity[tileM.numMaps][40];

    ArrayList<Entity> entityList0 = new ArrayList<>();
    ArrayList<Entity> entityList1 = new ArrayList<>();

    // GAME STATE

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setFoilage();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
       
        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
           
            update();

            repaint();

            delta--;

            if (timer >= 1000000000) {
                timer = 0;
            }

            }

        }


    }
     
    public void update() {

        if(gameState == playState) {
            // PLAYER
            player.update();
            //NPC
            for (int i = 0; i < NPCQTY; i++) {
                if(npc[numMap][i] != null) {
                    npc[numMap][i].update();
                }
            }

            for (int i = 0; i < monster[0].length; i++) {
                if(monster[numMap][i] != null) {
                    monster[numMap][i].update();
                }
            }


            //ENVIRONMENT
            aSetter.objectAnimations();
        }




        if (gameState == pauseState) {

        }
       
        
        //System.out.println("numMap: " + numMap);

    }


       
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
          
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG

        long drawStart = 0;
        
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }




        // Title screen

        if(gameState == titleState) {
            ui.draw(g2);

        } else { // OTHER




            // drawing map "0"

            numMap = mapM.checkLocation();
            tileM.draw(g2, numMap);
            rAnimate.animateRandom(g2, numMap);


            // ADDED ENTITIES TO LIST

            
            

            if (numMap == 0) {

                entityList0.add(player);
                for(int i = 0; i < npc[0].length; i++) {
                    if(npc[0][i] != null) {
                        entityList0.add(npc[0][i]);
                    }
                }
                //System.out.println("npc length " + npc[0].length);


                for(int i = 0; i < obj[0].length; i++) {
                    if(obj[0][i] != null) {
                        entityList0.add(obj[0][i]);
                    }
                }
                //System.out.println("obj length " + obj[0].length);

                for(int i = 0; i < monster[0].length; i++) {
                    if(monster[0][i] != null) {
                        entityList0.add(monster[0][i]);
                    }
                }

                for(int i = 0; i < foilage[0].length; i++) {
                    if(foilage[0][i] != null) {
                        entityList0.add(foilage[0][i]);
                    }
                }

                Collections.sort(entityList0, new Comparator<Entity>() {
                    @Override
                    public int compare(Entity e1, Entity e2) {
                        getLocationCollisionY(e1, e2);

                        int result = Integer.compare(e1.solidArea.y, e2.solidArea.y);

                        resetLocationCollisionY(e1, e2);
                        return result;
                        
                    }


                });


                // DRAW

                //System.out.println(entityList0.size());
                for(int i = 0; i < entityList0.size(); i++) {
                    //System.out.println(entityList0.get(i));
                    entityList0.get(i).draw(g2);
                }


                //EMPTY
                for(int i = 0; i < entityList0.size(); i++) {
                    entityList0.remove(i);
                }



            } else if (numMap == 1) {

                entityList1.add(player);

                //System.out.println(entityList1.size());
                for(int i = 0; i < npc[0].length; i++) {
                    if(npc[1][i] != null) {
                        entityList1.add(npc[1][i]);
                    }
                }


                for(int i = 0; i < obj[0].length; i++) {
                    if(obj[1][i] != null) {
                        entityList1.add(obj[1][i]);
                    }
                }

                for(int i = 0; i < monster[0].length; i++) {
                    if(monster[1][i] != null) {
                        entityList1.add(monster[1][i]);
                    }
                }

                for(int i = 0; i < foilage[0].length; i++) {
                    if(foilage[1][i] != null) {
                        entityList1.add(foilage[1][i]);
                    }
                }


                Collections.sort(entityList1, new Comparator<Entity>() {
                    @Override
                    public int compare(Entity e1, Entity e2) {
                        getLocationCollisionY(e1, e2);

                        int result = Integer.compare(e1.solidArea.y, e2.solidArea.y);

                        resetLocationCollisionY(e1, e2);
                        return result;
                    }

                    



                });

                // DRAW
                    
                for(int i = 0; i < entityList1.size(); i++) {
                    entityList1.get(i).draw(g2);
                }

                //EMPTY
                for(int i = 0; i < entityList1.size(); i++) {
                    entityList1.remove(i);
                }


            }




            ui.draw(g2);
            mManager.sendMessage(g2);

        }



        
        

        // debug

        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        

        

        g2.dispose();
       
    }

        



        
















    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public TileManager getTileM() {
        return tileM;
    }

    public void setTileM(TileManager tileM) {
        this.tileM = tileM;
    }

    void getLocationCollisionY(Entity entity1, Entity entity2) {
        entity1.solidArea.y = entity1.worldY + entity1.solidArea.y;
        entity2.solidArea.y = entity2.worldY + entity2.solidArea.y;
    }

    void resetLocationCollisionY(Entity entity1, Entity entity2) {
        entity1.solidArea.y = entity1.solidAreaDefaultY;
        entity2.solidArea.y = entity2.solidAreaDefaultY;
    }

    

    
}

