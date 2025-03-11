package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.graphics.GameGraphicsImpl;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Implementation of the ScoreUIRenderer interface, used for graphical rendering of the score and UI.
 */
public class ScoreUIRendererImpl implements ScoreUIRenderer {
	/**
	 * Image of the score container, used to contain the score during gameplay.
	 */
	private final BufferedImage scoreContainer;

	/**
	 * Constructor of the ScoreUIRendererImpl class.
	 *
	 * @param container the image of the score container
	 */
	public ScoreUIRendererImpl(BufferedImage container) {
		this.scoreContainer = container;
	}

	/**
	 * {@inheritDoc}
	 * The method draws the container, then checks the score and best score from the GameModel (ScoreManager).
	 * If the score is less than the best score, it uses the normal color, otherwise it uses the highlight color.
	 * It also shows a message if the score is a new high score.
	 */
	@Override
	public void drawScoreAndUI(Graphics2D g2, GameModel model, boolean isNewHighScore, boolean showHighScoreMessage) {
		g2.drawImage(scoreContainer, RENDERUISCORECONTAINERX, RENDERUISCORECONTAINERY, null);
		int score = model.getScore();
		int bestScore = model.getScoreManager().getBestScore();

		if (score < bestScore || score == 0) {
			g2.setColor(Color.WHITE);
			g2.setFont(GameGraphicsImpl.getGameFont2());
			g2.drawString(SCORERENDERTEXT + score, SCORERENDERX, SCORERENDERY);
			isNewHighScore = false;
		} else {
			g2.setColor(Color.decode(GOLD_TEXT_COLOR));
			g2.setFont(GameGraphicsImpl.getGameFont2());
			g2.drawString(SCORERENDERTEXT + score, SCORERENDERX, SCORERENDERY);
			isNewHighScore = true;
		}

		if (isNewHighScore && showHighScoreMessage) {
			g2.setColor(Color.decode(RED_TEXT_COLOR));
			g2.setFont(GameGraphicsImpl.getGameFont3());
			g2.drawString(HIGHSCORERENDERTEXT, HIGHSCORERENDERX, HIGHSCORERENDERY);
		}
	}
}
