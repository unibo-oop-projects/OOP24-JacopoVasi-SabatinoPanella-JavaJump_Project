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
	private final BackgroundRenderer backgroundRenderer;
	private final ScoreUIRenderer scoreUIRenderer;

	public RendererManager() {
		this.platformRenderer = new PlatformRenderer(2f, 10, 10);
		this.coinRenderer = new CoinRenderer(GameGraphics.getCoinSheet());
		this.playerRenderer = new PlayerRenderer(GameGraphics.getPlayerSheet(), 48, 50);
		this.backgroundRenderer = new BackgroundRenderer(GameGraphics.getBackground(), 0.2f);
		this.scoreUIRenderer = new ScoreUIRenderer(GameGraphics.getScoreContainer());
	}

	public void drawBackground(Graphics2D g2, GameModel model) {
		backgroundRenderer.drawBackground(g2, model);
	}

	
	public void drawPlayer(Graphics2D g2, Character player, float offsetY) {
		playerRenderer.drawPlayer(g2, player, offsetY);
	}

	public void drawCoin(Graphics2D g2, Coin coin, float offsetY) {
		coinRenderer.drawCoin(g2, coin, offsetY);
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
