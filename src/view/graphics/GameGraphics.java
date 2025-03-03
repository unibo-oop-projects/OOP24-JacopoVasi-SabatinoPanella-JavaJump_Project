package view.graphics;

import view.sound.AudioManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameGraphics {
    private final String path = "";
    private static Font gameFont1;
    private static Font gameFont2;
    private static Font gameFont3;
    private static BufferedImage playerSheet;
    private static BufferedImage background;
    private static BufferedImage scoreContainer;
    private static BufferedImage platform;
    private static BufferedImage coin;
    private static BufferedImage obstacle;
    private static BufferedImage life;
    private static BufferedImage gameOver;
    private static BufferedImage pause;
    private static BufferedImage title;
    public void LoadGraphics(){

    }

    static{
        try{
            title = ImageIO.read(new File("src/view/resources/JJ_Title.png"));
            gameOver = ImageIO.read(new File("src/view/resources/GameOver.png"));
            playerSheet = ImageIO.read(new File("src/view/resources/Coffee-SheetBIG.png"));
            background = ImageIO.read(new File("src/view/resources/Background3.png"));
            scoreContainer = ImageIO.read(new File("src/view/resources/Score_Underlay.png"));
            AudioManager.loadBackgroundMusic("src/view/resources/GameMusic.wav");
            gameFont1 = FontLoader.loadFont("src/view/resources/Daydream.ttf", 20);
            gameFont2 = FontLoader.loadFont("src/view/resources/Daydream.ttf", 15);
            gameFont3 = FontLoader.loadFont("src/view/resources/Daydream.ttf", 10);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Font getGameFont1(){
        return gameFont1;
    }

    public static Font getGameFont2(){
        return gameFont2;
    }

    public static Font getGameFont3(){
        return gameFont3;
    }

    public static BufferedImage getPlayerSheet(){
        return playerSheet;
    }

    public static BufferedImage getBackground(){
        return background;
    }

    public static BufferedImage getCoin(){
        return coin;
    }

    public static BufferedImage getLife(){
        return life;
    }

    public static BufferedImage getGameOver(){
        return gameOver;
    }

    public static BufferedImage getPause(){
        return pause;
    }

    public static BufferedImage getTitle(){
        return title;
    }

    public static BufferedImage getGameOverImage(){
        return gameOver;
    }

    public static BufferedImage getScoreContainer(){
        return scoreContainer;
    }


    public BufferedImage getGraphics(){
        return platform;
    }

    public void resizeGraphics(){

    }
}
