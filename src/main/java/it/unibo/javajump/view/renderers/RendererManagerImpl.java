package it.unibo.javajump.view.renderers;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;
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
		this.platformRenderer = new PlatformRendererImpl(RENDERMANAGERPLATFORMOUTLINE, RENDERMANAGERPLATFORMARCW, RENDERMANAGERPLATFORMARCH);
		this.coinRenderer = new CoinRendererImpl(GameGraphicsImpl.getCoinSheet(), RENDERMANAGERCOINWIDTH, RENDERMANAGERCOINHEIGHT, RENDERMANAGERCOINFRAMEDURATION);
		this.playerRenderer = new PlayerRendererImpl(GameGraphicsImpl.getPlayerSheet(), RENDERMANAGERPLAYERWIDTH, RENDERMANAGERPLAYERHEIGHT, RENDERMANAGERPLAYERFRAMEDURATION);
		this.backgroundRenderer1 = new BackgroundRendererImpl(GameGraphicsImpl.getBackground1(), RENDERMANAGERBACKGROUNDPARALLAXONE, RENDERMANAGERBACKGROUNDSPEEDXONE);
		this.backgroundRenderer2 = new BackgroundRendererImpl(GameGraphicsImpl.getBackground2(), RENDERMANAGERBACKGROUNDPARALLAXTWO, RENDERMANAGERBACKGROUNDSPEEDTWO);
		this.scoreUIRenderer = new ScoreUIRendererImpl(GameGraphicsImpl.getScoreContainer());
	}


	@Override
	public void drawBackground1(Graphics2D g2, GameModel model, float deltaTime) {
		backgroundRenderer1.drawBackground(g2, model, deltaTime);
	}


	@Override
	public void drawBackground2(Graphics2D g2, GameModel model, float deltaTime) {
		backgroundRenderer2.drawBackground(g2, model, deltaTime);
	}


	@Override
	public void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime) {
		playerRenderer.drawPlayer(g2, player, offsetY, deltaTime);
	}


	@Override
	public void drawCoin(Graphics2D g2, Coin coinImpl, float offsetY, float deltaTime) {
		coinRenderer.drawCoin(g2, coinImpl, offsetY, deltaTime);
	}


	@Override
	public void drawPlatform(Graphics2D g2, Platform platformImpl, float offsetY) {
		platformRenderer.drawPlatform(g2, platformImpl, offsetY);
	}


	@Override
	public void drawScoreUI(Graphics2D g2, GameModel model,
							boolean isNewHighScore,
							boolean showHighScoreMessage) {
		scoreUIRenderer.drawScoreAndUI(g2, model, isNewHighScore, showHighScoreMessage);
	}

}
