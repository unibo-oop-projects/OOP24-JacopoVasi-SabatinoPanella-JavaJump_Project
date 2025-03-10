package it.unibo.javajump.view.graphics;

import it.unibo.javajump.view.sound.AudioManagerImpl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static it.unibo.javajump.utility.Constants.*;

public class GameGraphicsImpl implements GameGraphics {
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
	private static BufferedImage gameOver;
	private static BufferedImage title;

	public void LoadGraphics() {

	}

	static {
		try {
			title = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESTITLE));
			gameOver = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESGAMEOVER));
			playerSheet = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESPLAYER));
			coinSheet = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESCOIN));
			background1 = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESBACKGROUNDONE));
			background2 = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESBACKGROUNDTWO));
			scoreContainer = ImageIO.read(new File(RESOURCESWINDOWSPATH + RESOURCESSCORECONTAINER));
			AudioManagerImpl.loadBackgroundMusic(RESOURCESWINDOWSPATH + RESOURCESMUSIC);
			gameFont1 = FontLoaderImpl.loadFont(RESOURCESWINDOWSPATH + RESOURCESGAMEFONTONE, SIZEGAMEFONTONE);
			gameFont2 = FontLoaderImpl.loadFont(RESOURCESWINDOWSPATH + RESOURCESGAMEFONTTWO, SIZEGAMEFONTTWO);
			gameFont3 = FontLoaderImpl.loadFont(RESOURCESWINDOWSPATH + RESOURCESGAMEFONTTHREE, SIZEGAMEFONTTHREE);
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

	public static BufferedImage getGameOver() {
		return gameOver;
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

}
