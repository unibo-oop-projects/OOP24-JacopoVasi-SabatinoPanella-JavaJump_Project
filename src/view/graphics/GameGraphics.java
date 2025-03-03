package view.graphics;

import view.sound.AudioManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameGraphics {
    private final String path = "";
    private BufferedImage platform;
    private static BufferedImage playerSheet;
    private static BufferedImage background;
    public void LoadGraphics(){

    }

    static{
        try{
            playerSheet = ImageIO.read(new File("src/view/resources/Coffee-SheetBIG.png"));
            background = ImageIO.read(new File("src/view/resources/Background3.png"));
            AudioManager.loadBackgroundMusic("src/view/resources/GameMusic.wav");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getPlayerSheet(){
        return playerSheet;
    }

    public static BufferedImage getBackground(){
        return background;
    }

    public BufferedImage getGraphics(){
        return platform;
    }

    public void resizeGraphics(){

    }
}
