package main;


import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import messages.MessageManager;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{

    // Screen Settings


    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    // FPS
    int FPS = 60;


    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public MessageManager mManager = new MessageManager(this);
    public Player player = new Player(this, keyH, aSetter, mManager);
    
    public SuperObject obj[] = new SuperObject[10];


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

    }


       
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
          
        Graphics2D g2 = (Graphics2D)g;


        // tile
        tileM.draw(g2);

        //Object
        for (SuperObject obj1 : obj) {
            if (obj1 != null) {
                obj1.draw(g2, this);
            }
        }
          

        // player
        player.draw(g2);

        mManager.sendMessage(g2);

        g2.dispose();
       
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    
}
