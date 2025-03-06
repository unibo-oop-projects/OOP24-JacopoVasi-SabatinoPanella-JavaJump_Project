package view.renderers;

import model.GameModel;
import model.entities.collectibles.Coin;
import model.entities.platforms.Platform;
import model.entities.character.Character;
import view.graphics.GameGraphics;
import view.renderers.sub.*;

import java.awt.*;

public class RendererManager {
	private final PlatformRenderer platformRenderer;
	private final CoinRenderer coinRenderer;
	private final PlayerRenderer playerRenderer;
	private final BackgroundRenderer backgroundRenderer1;
	private final BackgroundRenderer backgroundRenderer2;
	private final ScoreUIRenderer scoreUIRenderer;

	public RendererManager() {
		this.platformRenderer = new PlatformRenderer(2f, 10, 10);
		this.coinRenderer = new CoinRenderer(GameGraphics.getCoinSheet(), 44, 52, 0.05f);
		this.playerRenderer = new PlayerRenderer(GameGraphics.getPlayerSheet(), 48, 50, 0.2f);
		this.backgroundRenderer1 = new BackgroundRenderer(GameGraphics.getBackground1(), 0.2f, 0);
		this.backgroundRenderer2 = new BackgroundRenderer(GameGraphics.getBackground2(), 0.4f, 20f);
		this.scoreUIRenderer = new ScoreUIRenderer(GameGraphics.getScoreContainer());
	}

	
	public void drawBackground1(Graphics2D g2, GameModel model, float deltaTime) {
		backgroundRenderer1.drawBackground(g2, model, deltaTime);
	}

	
	public void drawBackground2(Graphics2D g2, GameModel model, float deltaTime) {
		backgroundRenderer2.drawBackground(g2, model, deltaTime);
	}

	
	public void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime) {
		playerRenderer.drawPlayer(g2, player, offsetY, deltaTime);
	}

	
	public void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime) {
		coinRenderer.drawCoin(g2, coin, offsetY, deltaTime);
	}

	
	public void drawPlatform(Graphics2D g2, Platform platform, float offsetY) {
		platformRenderer.drawPlatform(g2, platform, offsetY);
	}

	
	public void drawScoreUI(Graphics2D g2, GameModel model,
							boolean isNewHighScore,
							boolean showHighScoreMessage) {
		scoreUIRenderer.drawScoreAndUI(g2, model, isNewHighScore, showHighScoreMessage);
	}

}
