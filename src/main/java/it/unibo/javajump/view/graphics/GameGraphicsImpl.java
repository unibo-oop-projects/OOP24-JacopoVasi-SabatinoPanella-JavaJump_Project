package it.unibo.javajump.view.graphics;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static it.unibo.javajump.utility.Constants.RESOURCES_BACKGROUND_EASY;
import static it.unibo.javajump.utility.Constants.RESOURCES_BACKGROUND_HARD;
import static it.unibo.javajump.utility.Constants.RESOURCES_BACKGROUND_MEDIUM;
import static it.unibo.javajump.utility.Constants.RESOURCES_CLOUDS_EASY;
import static it.unibo.javajump.utility.Constants.RESOURCES_CLOUDS_HARD;
import static it.unibo.javajump.utility.Constants.RESOURCES_CLOUDS_MEDIUM;
import static it.unibo.javajump.utility.Constants.RESOURCES_COIN;
import static it.unibo.javajump.utility.Constants.RESOURCES_FONT_1;
import static it.unibo.javajump.utility.Constants.RESOURCES_FONT_2;
import static it.unibo.javajump.utility.Constants.RESOURCES_FONT_3;
import static it.unibo.javajump.utility.Constants.RESOURCES_GAMEOVER;
import static it.unibo.javajump.utility.Constants.RESOURCES_PATH;
import static it.unibo.javajump.utility.Constants.RESOURCES_PLAYER;
import static it.unibo.javajump.utility.Constants.RESOURCES_SCORE_CONTAINER;
import static it.unibo.javajump.utility.Constants.RESOURCES_TITLE;
import static it.unibo.javajump.utility.Constants.SIZE_FONT_1;
import static it.unibo.javajump.utility.Constants.SIZE_FONT_2;
import static it.unibo.javajump.utility.Constants.SIZE_FONT_3;

/**
 * The type Game graphics.
 */
public final class GameGraphicsImpl implements GameGraphics {
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

    /**
     * Instantiates a new Game graphics.
     */
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
            throw new NullPointerException(ex.getMessage());
        }
    }

    private BufferedImage copyImage(final BufferedImage source) {
        if (source == null) {
            return null;
        }
        final BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        final Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        return copy;
    }

    @Override
    public Font getGameFont1() {
        return gameFont1;
    }

    @Override
    public Font getGameFont2() {
        return gameFont2;
    }

    @Override
    public Font getGameFont3() {
        return gameFont3;
    }

    @Override
    public BufferedImage getPlayerSheet() {
        return copyImage(playerSheet);
    }

    @Override
    public BufferedImage getBackgroundEasy() {
        return copyImage(backgroundEasy);
    }

    @Override
    public BufferedImage getCloudsEasy() {
        return copyImage(cloudsEasy);
    }

    @Override
    public BufferedImage getBackgroundMedium() {
        return copyImage(backgroundMedium);
    }

    @Override
    public BufferedImage getCloudsMedium() {
        return copyImage(cloudsMedium);
    }

    @Override
    public BufferedImage getBackgroundDifficult() {
        return copyImage(backgroundDifficult);
    }

    @Override
    public BufferedImage getCloudsDifficult() {
        return copyImage(cloudsDifficult);
    }

    @Override
    public BufferedImage getCoinSheet() {
        return copyImage(coinSheet);
    }

    @Override
    public BufferedImage getGameOver() {
        return copyImage(gameOver);
    }

    @Override
    public BufferedImage getTitle() {
        return copyImage(title);
    }

    @Override
    public BufferedImage getScoreContainer() {
        return copyImage(scoreContainer);
    }
}
