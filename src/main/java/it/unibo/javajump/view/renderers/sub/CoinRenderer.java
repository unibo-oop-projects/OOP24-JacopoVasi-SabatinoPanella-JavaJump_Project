package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.collectibles.CoinState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public interface CoinRenderer {

	void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime);

	void removeCoin(Coin coin);
}
