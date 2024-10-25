package tile;


import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;










    
    public BufferedImage image1, image2; 
    public boolean animated = false;
    public int phaseCounter = 0;
    public int phase = 1;







    public void update(int n) {
        if (animated == true) {
            phaseCounter++;
                if (phaseCounter > n) {
                    if (phase == 1) {
                        phase = 2;
                    } else if (phase == 2) {
                        phase = 1;
                    }
                    phaseCounter = 0;
                }
        }
    }

    public void animate() {
        if (animated == true) {
            if (phase == 1) {
                image = image1;
            }
            if (phase == 2) {
                image = image2;
            }
        }
    }

}
