package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.GOLD_TEXT_COLOR;
import static it.unibo.javajump.utility.Constants.HIGH_SCORE_RENDER_TEXT;
import static it.unibo.javajump.utility.Constants.HIGH_SCORE_RENDER_X_POSITION;
import static it.unibo.javajump.utility.Constants.HIGH_SCORE_RENDER_Y_POSITION;
import static it.unibo.javajump.utility.Constants.RED_TEXT_COLOR;
import static it.unibo.javajump.utility.Constants.RENDER_UI_SCORE_CONTAINER_X;
import static it.unibo.javajump.utility.Constants.RENDER_UI_SCORE_CONTAINER_Y;
import static it.unibo.javajump.utility.Constants.SCORE_RENDER_TEXT;
import static it.unibo.javajump.utility.Constants.SCORE_RENDER_X_POSITION;
import static it.unibo.javajump.utility.Constants.SCORE_RENDER_Y_POSITION;

/**
 * Implementation of the ScoreUIRenderer interface, used for graphical rendering of the score and UI.
 */
public class ScoreUIRendererImpl implements ScoreUIRenderer {
    /**
     * Image of the score container, used to contain the score during gameplay.
     */
    private final BufferedImage scoreContainer;
    private final Font gameFont1;
    private final Font gameFont2;
    private final Font gameFont3;

    /**
     * Constructor of the ScoreUIRendererImpl class.
     *
     * @param container the image of the score container
     */
    public ScoreUIRendererImpl(BufferedImage container, Font font1, Font font2, Font font3) {
        this.scoreContainer = container;
        this.gameFont1 = font1;
        this.gameFont2 = font2;
        this.gameFont3 = font3;
    }

    /**
     * {@inheritDoc}
     * The method draws the container, then checks the score and best score from the GameModel (ScoreManager).
     * If the score is less than the best score, it uses the normal color, otherwise it uses the highlight color.
     * It also shows a message if the score is a new high score.
     */
    @Override
    public void drawScoreAndUI(Graphics2D g2, GameModel model, boolean isNewHighScore, boolean showHighScoreMessage) {
        g2.drawImage(scoreContainer, RENDER_UI_SCORE_CONTAINER_X, RENDER_UI_SCORE_CONTAINER_Y, null);
        int score = model.getScore();
        int bestScore = model.getScoreManager().getBestScore();

        if (score < bestScore || score == 0) {
            g2.setColor(Color.WHITE);
            g2.setFont(gameFont2);
            g2.drawString(SCORE_RENDER_TEXT + score, SCORE_RENDER_X_POSITION, SCORE_RENDER_Y_POSITION);
            isNewHighScore = false;
        } else {
            g2.setColor(Color.decode(GOLD_TEXT_COLOR));
            g2.setFont(gameFont2);
            g2.drawString(SCORE_RENDER_TEXT + score, SCORE_RENDER_X_POSITION, SCORE_RENDER_Y_POSITION);
            isNewHighScore = true;
        }

        if (isNewHighScore && showHighScoreMessage) {
            g2.setColor(Color.decode(RED_TEXT_COLOR));
            g2.setFont(gameFont3);
            g2.drawString(HIGH_SCORE_RENDER_TEXT, HIGH_SCORE_RENDER_X_POSITION, HIGH_SCORE_RENDER_Y_POSITION);
        }
    }
}
