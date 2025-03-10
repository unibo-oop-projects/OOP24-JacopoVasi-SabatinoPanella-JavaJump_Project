package it.unibo.javajump.view.graphics;

import it.unibo.javajump.view.sound.AudioManagerImpl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameGraphicsImpl {
	private final String path = "";
	private static Font gameFont1;
	private static Font gameFont2;
	private static Font gameFont3;
	private static BufferedImage playerSheet;
	private static BufferedImage background1;
	private static BufferedImage background2;
	private static BufferedImage scoreContainer;
	private static BufferedImage platform;
	private static BufferedImage coinSheet;
	private static BufferedImage obstacle;
	private static BufferedImage life;
	private static BufferedImage gameOver;
	private static BufferedImage pause;
	private static BufferedImage title;

	public void LoadGraphics() {

	}

	static {
		try {
			title = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/JJ_Title.png"));
			gameOver = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/GameOver.png"));
			playerSheet = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/Coffee-SheetBIG.png"));
			coinSheet = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/Coin-Sheet.png"));
			background1 = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/Background4.png"));
			background2 = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/Clouds2.png"));
			scoreContainer = ImageIO.read(new File("src/main/java/it/unibo/javajump/view/resources/Score_Underlay_small.png"));
			AudioManagerImpl.loadBackgroundMusic("src/main/java/it/unibo/javajump/view/resources/GameMusic.wav");
			gameFont1 = FontLoaderImpl.loadFont("src/main/java/it/unibo/javajump/view/resources/Daydream.ttf", 20);
			gameFont2 = FontLoaderImpl.loadFont("src/main/java/it/unibo/javajump/view/resources/Daydream.ttf", 15);
			gameFont3 = FontLoaderImpl.loadFont("src/main/java/it/unibo/javajump/view/resources/Daydream.ttf", 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Font getGameFont1() {
		return gameFont1;
	}

	public static Font getGameFont2() {
		return gameFont2;
	}

	public static Font getGameFont3() {
		return gameFont3;
	}

	public static BufferedImage getPlayerSheet() {
		return playerSheet;
	}

	public static BufferedImage getBackground1() {
		return background1;
	}

	public static BufferedImage getBackground2() {
		return background2;
	}

	public static BufferedImage getCoinSheet() {
		return coinSheet;
	}

	public static BufferedImage getLife() {
		return life;
	}

	public static BufferedImage getGameOver() {
		return gameOver;
	}

	public static BufferedImage getPause() {
		return pause;
	}

	public static BufferedImage getTitle() {
		return title;
	}

	public static BufferedImage getGameOverImage() {
		return gameOver;
	}

	public static BufferedImage getScoreContainer() {
		return scoreContainer;
	}


	public BufferedImage getGraphics() {
		return platform;
	}

	public void resizeGraphics() {

	}
}
