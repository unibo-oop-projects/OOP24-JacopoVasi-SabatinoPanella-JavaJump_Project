package it.unibo.javajump.view.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The interface Game graphics.
 */
public interface GameGraphics {
    /**
     * Gets game font 1.
     *
     * @return the game font 1
     */
    Font getGameFont1();

    /**
     * Gets game font 2.
     *
     * @return the game font 2
     */
    Font getGameFont2();

    /**
     * Gets game font 3.
     *
     * @return the game font 3
     */
    Font getGameFont3();

    /**
     * Gets player sheet.
     *
     * @return the player sheet
     */
    BufferedImage getPlayerSheet();

    /**
     * Gets background easy.
     *
     * @return the background easy
     */
    BufferedImage getBackground_Easy();

    /**
     * Gets clouds easy.
     *
     * @return the clouds easy
     */
    BufferedImage getClouds_Easy();

    /**
     * Gets background medium.
     *
     * @return the background medium
     */
    BufferedImage getBackground_Medium();

    /**
     * Gets clouds medium.
     *
     * @return the clouds medium
     */
    BufferedImage getClouds_Medium();

    /**
     * Gets background difficult.
     *
     * @return the background difficult
     */
    BufferedImage getBackground_Difficult();

    /**
     * Gets clouds difficult.
     *
     * @return the clouds difficult
     */
    BufferedImage getClouds_Difficult();

    /**
     * Gets coin sheet.
     *
     * @return the coin sheet
     */
    BufferedImage getCoinSheet();

    /**
     * Gets game over.
     *
     * @return the game over
     */
    BufferedImage getGameOver();

    /**
     * Gets title.
     *
     * @return the title
     */
    BufferedImage getTitle();

    /**
     * Gets score container.
     *
     * @return the score container
     */
    BufferedImage getScoreContainer();
}
