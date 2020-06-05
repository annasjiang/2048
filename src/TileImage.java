import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileImage {
    public static final String IMAGE_WELCOMESCREEN = "files/welcomeScreen.png";
    public static final String IMAGE_LOSINGSCREEN = "files/losingScreen.png";
    public static final String IMAGE_WINNINGSCREEN = "files/winningScreen.png";
    public static final String IMAGE_TILE0 = "files/0.png";
    public static final String IMAGE_TILE2 = "files/2.png";
    public static final String IMAGE_TILE4 = "files/4.png";
    public static final String IMAGE_TILE8 = "files/8.png";
    public static final String IMAGE_TILE16 = "files/16.png";
    public static final String IMAGE_TILE32 = "files/32.png";
    public static final String IMAGE_TILE64 = "files/64.png";
    public static final String IMAGE_TILE128 = "files/128.png";
    public static final String IMAGE_TILE256 = "files/256.png";
    public static final String IMAGE_TILE512 = "files/512.png";
    public static final String IMAGE_TILE1024 = "files/1024.png";
    public static final String IMAGE_TILE2048 = "files/2048.png";
    
    public static BufferedImage welcomeScreen;
    public static BufferedImage losingScreen;
    public static BufferedImage winningScreen;
    public static BufferedImage tile0;
    public static BufferedImage tile2;
    public static BufferedImage tile4;
    public static BufferedImage tile8;
    public static BufferedImage tile16;
    public static BufferedImage tile32;
    public static BufferedImage tile64;
    public static BufferedImage tile128;
    public static BufferedImage tile256;
    public static BufferedImage tile512;
    public static BufferedImage tile1024;
    public static BufferedImage tile2048;

    
    public TileImage() {
        try {
            if (welcomeScreen == null) {
                welcomeScreen = ImageIO.read(new File(IMAGE_WELCOMESCREEN));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        } 
        try {
            if (losingScreen == null) {
                losingScreen = ImageIO.read(new File(IMAGE_LOSINGSCREEN));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        } 
        try {
            if (winningScreen == null) {
                winningScreen = ImageIO.read(new File(IMAGE_WINNINGSCREEN));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile0 == null) {
                tile0 = ImageIO.read(new File(IMAGE_TILE0));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile2 == null) {
                tile2 = ImageIO.read(new File(IMAGE_TILE2));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        
        try {
            if (tile4 == null) {
                tile4 = ImageIO.read(new File(IMAGE_TILE4));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile8 == null) {
                tile8 = ImageIO.read(new File(IMAGE_TILE8));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile16 == null) {
                tile16 = ImageIO.read(new File(IMAGE_TILE16));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile32 == null) {
                tile32 = ImageIO.read(new File(IMAGE_TILE32));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile64 == null) {
                tile64 = ImageIO.read(new File(IMAGE_TILE64));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile128 == null) {
                tile128 = ImageIO.read(new File(IMAGE_TILE128));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile256 == null) {
                tile256 = ImageIO.read(new File(IMAGE_TILE256));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile512 == null) {
                tile512 = ImageIO.read(new File(IMAGE_TILE512));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile1024 == null) {
                tile1024 = ImageIO.read(new File(IMAGE_TILE1024));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        try {
            if (tile2048 == null) {
                tile2048 = ImageIO.read(new File(IMAGE_TILE2048));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }         
    }
}
