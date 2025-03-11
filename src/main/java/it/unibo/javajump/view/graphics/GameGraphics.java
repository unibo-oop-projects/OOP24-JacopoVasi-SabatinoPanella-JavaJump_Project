package it.unibo.javajump.view.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface GameGraphics {
	Font getGameFont1();

	Font getGameFont2();

	Font getGameFont3();

	BufferedImage getPlayerSheet();

	BufferedImage getBackground_Easy();

	BufferedImage getClouds_Easy();

	BufferedImage getBackground_Medium();

	BufferedImage getClouds_Medium();

	BufferedImage getBackground_Difficult();

	BufferedImage getClouds_Difficult();

	BufferedImage getCoinSheet();

	BufferedImage getGameOver();

	BufferedImage getTitle();

	BufferedImage getScoreContainer();
}
