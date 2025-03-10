package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.ZERO;

public interface BackgroundRenderer {

	void updateBackground(float deltaTime);

	void drawBackground(Graphics2D g2, GameModel model, float deltaTime);
}
