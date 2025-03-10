package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.view.graphics.GameGraphicsImpl;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.utility.Constants.HIGHSCORERENDERY;

public interface ScoreUIRenderer {

	void drawScoreAndUI(Graphics2D g2, GameModel model, boolean isNewHighScore, boolean showHighScoreMessage);
}
