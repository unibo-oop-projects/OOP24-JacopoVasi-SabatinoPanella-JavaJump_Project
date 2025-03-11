package it.unibo.javajump.view.graphics;

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
	private static BufferedImage backgroundEasy;
	private static BufferedImage cloudsEasy;
	private static BufferedImage backgroundMedium;
	private static BufferedImage cloudsMedium;
	private static BufferedImage backgroundDifficult;
	private static BufferedImage cloudsDifficult;
	private static BufferedImage scoreContainer;
	private static BufferedImage coinSheet;
	private static BufferedImage gameOver;
	private static BufferedImage title;

	static {
		try {
			title = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_TITLE));
			gameOver = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_GAMEOVER));
			playerSheet = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_PLAYER));
			coinSheet = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_COIN));
			backgroundEasy = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_BACKGROUND_EASY));
			cloudsEasy = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_CLOUDS_EASY));
			backgroundMedium = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_BACKGROUND_MEDIUM));
			cloudsMedium = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_CLOUDS_MEDIUM));
			backgroundDifficult = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_BACKGROUND_HARD));
			cloudsDifficult = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_CLOUDS_HARD));
			scoreContainer = ImageIO.read(new File(RESOURCES_PATH + RESOURCES_SCORE_CONTAINER));
			gameFont1 = FontLoaderImpl.loadFont(RESOURCES_PATH + RESOURCES_FONT_1, SIZE_FONT_1);
			gameFont2 = FontLoaderImpl.loadFont(RESOURCES_PATH + RESOURCES_FONT_2, SIZE_FONT_2);
			gameFont3 = FontLoaderImpl.loadFont(RESOURCES_PATH + RESOURCES_FONT_3, SIZE_FONT_3);
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

	public static BufferedImage getBackground_Easy() {
		return backgroundEasy;
	}

	public static BufferedImage getClouds_Easy() {
		return cloudsEasy;
	}

	public static BufferedImage getBackground_Medium() {
		return backgroundMedium;
	}

	public static BufferedImage getClouds_Medium() {
		return cloudsMedium;
	}

	public static BufferedImage getBackground_Difficult() {
		return backgroundDifficult;
	}

	public static BufferedImage getClouds_Difficult() {
		return cloudsDifficult;
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

	public static BufferedImage getScoreContainer() {
		return scoreContainer;
	}
}
