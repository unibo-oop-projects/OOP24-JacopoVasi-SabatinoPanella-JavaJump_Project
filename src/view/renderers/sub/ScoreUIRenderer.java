package view.renderers.sub;

import model.GameModel;
import view.graphics.GameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utility.Constants.*;

public class ScoreUIRenderer {

	private final BufferedImage scoreContainer;

	public ScoreUIRenderer(BufferedImage container) {
		this.scoreContainer = container;
	}

	public void drawScoreAndUI(Graphics2D g2,
							   GameModel model,
							   boolean isNewHighScore,
							   boolean showHighScoreMessage) {


		g2.drawImage(scoreContainer, ZERO, ZERO, null);


		int score = model.getScore();
		int bestScore = model.getScoreManager().getBestScore();


		if (score < bestScore) {
			g2.setColor(Color.WHITE);
			g2.setFont(GameGraphics.getGameFont2());
			g2.drawString(SCORERENDERTEXT + score, SCORERENDERX, SCORERENDERY);
			isNewHighScore = false;
		} else {
			g2.setColor(Color.decode("#eac10c"));
			g2.setFont(GameGraphics.getGameFont2());
			g2.drawString(SCORERENDERTEXT + score, SCORERENDERX, SCORERENDERY);
			isNewHighScore = true;
		}


		if (isNewHighScore && showHighScoreMessage) {
			g2.setColor(Color.decode("#D83426"));
			g2.setFont(GameGraphics.getGameFont3());
			g2.drawString(HIGHSCORERENDERTEXT, HIGHSCORERENDERX, HIGHSCORERENDERY);
		}
	}
}
