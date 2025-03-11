package it.unibo.javajump.view.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static it.unibo.javajump.utility.Constants.*;

public class GameGraphicsImpl implements GameGraphics {
	private final Font gameFont1;
	private final Font gameFont2;
	private final Font gameFont3;
	private final BufferedImage playerSheet;
	private final BufferedImage backgroundEasy;
	private final BufferedImage cloudsEasy;
	private final BufferedImage backgroundMedium;
	private final BufferedImage cloudsMedium;
	private final BufferedImage backgroundDifficult;
	private final BufferedImage cloudsDifficult;
	private final BufferedImage scoreContainer;
	private final BufferedImage coinSheet;
	private final BufferedImage gameOver;
	private final BufferedImage title;

	public GameGraphicsImpl() {
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
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public Font getGameFont1() {
		return gameFont1;
	}

	public Font getGameFont2() {
		return gameFont2;
	}

	public Font getGameFont3() {
		return gameFont3;
	}

	public BufferedImage getPlayerSheet() {
		return playerSheet;
	}

	public BufferedImage getBackground_Easy() {
		return backgroundEasy;
	}

	public BufferedImage getClouds_Easy() {
		return cloudsEasy;
	}

	public BufferedImage getBackground_Medium() {
		return backgroundMedium;
	}

	public BufferedImage getClouds_Medium() {
		return cloudsMedium;
	}

	public BufferedImage getBackground_Difficult() {
		return backgroundDifficult;
	}

	public BufferedImage getClouds_Difficult() {
		return cloudsDifficult;
	}

	public BufferedImage getCoinSheet() {
		return coinSheet;
	}

	public BufferedImage getGameOver() {
		return gameOver;
	}

	public BufferedImage getTitle() {
		return title;
	}

	public BufferedImage getScoreContainer() {
		return scoreContainer;
	}
}
