package it.unibo.javajump.view.renderers;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import java.awt.*;

public interface RenderManager {

	void drawBackground1(Graphics2D g2, GameModel model, float deltaTime);

	void drawBackground2(Graphics2D g2, GameModel model, float deltaTime);

	void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime);

	void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime);

	void drawPlatform(Graphics2D g2, Platform platform, float offsetY);

	void drawScoreUI(Graphics2D g2, GameModel model,
					 boolean isNewHighScore,
					 boolean showHighScoreMessage);
}
