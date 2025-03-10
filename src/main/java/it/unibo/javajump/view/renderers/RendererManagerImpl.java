package it.unibo.javajump.view.renderers;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.view.graphics.GameGraphicsImpl;
import it.unibo.javajump.view.renderers.sub.*;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.*;

public class RendererManagerImpl implements RenderManager {

	private final PlatformRenderer platformRenderer;
	private final CoinRenderer coinRenderer;
	private final PlayerRenderer playerRenderer;
	private final BackgroundRenderer backgroundRenderer1;
	private final BackgroundRenderer backgroundRenderer2;
	private final ScoreUIRenderer scoreUIRenderer;

	public RendererManagerImpl() {
		this.platformRenderer = new PlatformRenderer(RENDERMANAGERPLATFORMOUTLINE, RENDERMANAGERPLATFORMARCW, RENDERMANAGERPLATFORMARCH);
		this.coinRenderer = new CoinRenderer(GameGraphicsImpl.getCoinSheet(), RENDERMANAGERCOINWIDTH, RENDERMANAGERCOINHEIGHT, RENDERMANAGERCOINFRAMEDURATION);
		this.playerRenderer = new PlayerRenderer(GameGraphicsImpl.getPlayerSheet(), RENDERMANAGERPLAYERWIDTH, RENDERMANAGERPLAYERHEIGHT, RENDERMANAGERPLAYERFRAMEDURATION);
		this.backgroundRenderer1 = new BackgroundRenderer(GameGraphicsImpl.getBackground1(), RENDERMANAGERBACKGROUNDPARALLAXONE, RENDERMANAGERBACKGROUNDSPEEDXONE);
		this.backgroundRenderer2 = new BackgroundRenderer(GameGraphicsImpl.getBackground2(), RENDERMANAGERBACKGROUNDPARALLAXTWO, RENDERMANAGERBACKGROUNDSPEEDTWO);
		this.scoreUIRenderer = new ScoreUIRenderer(GameGraphicsImpl.getScoreContainer());
	}


	public void drawBackground1(Graphics2D g2, GameModelImpl model, float deltaTime) {
		backgroundRenderer1.drawBackground(g2, model, deltaTime);
	}


	public void drawBackground2(Graphics2D g2, GameModelImpl model, float deltaTime) {
		backgroundRenderer2.drawBackground(g2, model, deltaTime);
	}


	public void drawPlayer(Graphics2D g2, CharacterImpl player, float offsetY, float deltaTime) {
		playerRenderer.drawPlayer(g2, player, offsetY, deltaTime);
	}


	public void drawCoin(Graphics2D g2, CoinImpl coinImpl, float offsetY, float deltaTime) {
		coinRenderer.drawCoin(g2, coinImpl, offsetY, deltaTime);
	}


	public void drawPlatform(Graphics2D g2, PlatformImpl platformImpl, float offsetY) {
		platformRenderer.drawPlatform(g2, platformImpl, offsetY);
	}


	public void drawScoreUI(Graphics2D g2, GameModelImpl model,
							boolean isNewHighScore,
							boolean showHighScoreMessage) {
		scoreUIRenderer.drawScoreAndUI(g2, model, isNewHighScore, showHighScoreMessage);
	}

}
