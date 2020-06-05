import java.awt.*;

/**
 * class that creates the tile objects in the game
 */
public class Tile {
    
    // fields
    private int value;
    private TileImage loadImage;
    private boolean hasMerged; // checks if a tile was just merged (can only merge once per move)
   
    public Tile(int value) {
        this.value = value;
        loadImage = new TileImage();
        hasMerged = false;
    }

    // getter and setter functions
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    // check if a box is filled
    public boolean isEmpty() {
        return value == 0;
    }
    
    public boolean getMerged() {
        return hasMerged;
    }
    
    public void setMerged(boolean merge) {
        hasMerged = merge;
    }
    
    // load pics for tiles
    public void draw(Graphics g, int x, int y) {
        if (value == 0) {
            g.drawImage(TileImage.tile0, x, y, 100, 100, null);
        }
        if (value == 2) {
            g.drawImage(TileImage.tile2, x, y, 100, 100, null);
        }
        if (value == 4) {
            g.drawImage(TileImage.tile4, x, y, 100, 100, null);
        }
        if (value == 8) {
            g.drawImage(TileImage.tile8, x, y, 100, 100, null);
        }
        if (value == 16) {
            g.drawImage(TileImage.tile16, x, y, 100, 100, null);
        }
        if (value == 32) {
            g.drawImage(TileImage.tile32, x, y, 100, 100, null);
        }
        if (value == 64) {
            g.drawImage(TileImage.tile64, x, y, 100, 100, null);
        }
        if (value == 128) {
            g.drawImage(TileImage.tile128, x, y, 100, 100, null);
        }
        if (value == 256) {
            g.drawImage(TileImage.tile256, x, y, 100, 100, null);
        }
        if (value == 512) {
            g.drawImage(TileImage.tile512, x, y, 100, 100, null);
        }
        if (value == 1024) {
            g.drawImage(TileImage.tile1024, x, y, 100, 100, null);
        }
        if (value == 2048) {
            g.drawImage(TileImage.tile2048, x, y, 100, 100, null);
        }
    }

}

