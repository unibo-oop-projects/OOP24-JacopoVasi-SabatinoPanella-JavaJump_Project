package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.character.Character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.utility.Constants.ZERO;

public interface PlayerRenderer {

	void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime);
}
