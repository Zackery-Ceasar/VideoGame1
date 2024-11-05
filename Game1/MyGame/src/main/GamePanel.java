package main;


import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import messages.MessageManager;
import object.SuperObject;
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



    // FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public RandomAnimation rAnimate = new RandomAnimation(this);

    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public MessageManager mManager = new MessageManager(this);
    public Player player = new Player(this, keyH, aSetter, mManager);
    public MapManager mapM = new MapManager(this);
    public UI ui = new UI(this);
    
    public SuperObject obj[][] = new SuperObject[tileM.numMaps][objectQTY];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
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
       
        player.update();
        aSetter.objectAnimations();
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

        



        // drawing map "0"

        numMap = mapM.checkLocation();
        tileM.draw(g2, numMap);







        rAnimate.animateRandom(g2, numMap);

        //Object
        for (int i = 0; i < objectQTY; i++) {
            if (obj[numMap][i] != null) {
                obj[numMap][i].draw(g2, this);
            }
        }
          

        // player
        player.draw(g2);

        ui.draw(g2);

        mManager.sendMessage(g2);


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

    
}

