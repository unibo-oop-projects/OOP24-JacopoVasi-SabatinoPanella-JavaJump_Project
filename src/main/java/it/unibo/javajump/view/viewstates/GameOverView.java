package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public class GameOverView implements GameViewState {

	private float fadeAlpha = GAMEOVERALPHAINIT;
	private final float fadeDuration = GAMEOVERDURATIONINIT;
	private float elapsedTime = GAMEOVERTIMEINIT;
	private boolean fading = false;


	public void startFade() {
		this.fadeAlpha = GAMEOVERALPHAINIT;
		this.elapsedTime = GAMEOVERTIMEINIT;
		this.fading = true;
	}


	public void stopFade() {
		this.fadeAlpha = GAMEOVERALPHA;
		this.fading = false;
	}


	public void update(float deltaTime) {
		if (fading) {
			elapsedTime += deltaTime;
			fadeAlpha = Math.min(GAMEOVERALPHA, elapsedTime / fadeDuration);
			if (fadeAlpha >= GAMEOVERALPHA) {
				fading = false;
			}
		}
	}


	@Override
	public void draw(Graphics g, GameModelImpl model) {
		Graphics2D g2 = (Graphics2D) g;
		Composite oldComposite = g2.getComposite();

		int w = model.getScreenWidth();
		int h = model.getScreenHeight();

		int centerX = w / GAMEOVERCENTERDIV;
		int centerY = h / GAMEOVERCENTERDIV;


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
		g2.setColor(Color.decode("#05051C"));
		g2.fillRect(GAMEOVERRECTX, GAMEOVERRECTY, w, h);


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, GAMEOVERALPHA));
		BufferedImage img = GameGraphics.getGameOver();
		g2.drawImage(img, (int) (centerX - img.getWidth() / GAMEOVERIMGWOFF), (int) (centerY - h * GAMEOVERIMGHOFF), (int) (img.getWidth() * GAMEOVERIMGSCALEOFF), (int) (img.getHeight() * GAMEOVERIMGSCALEOFF), null);


		if (fadeAlpha >= GAMEOVERALPHA) {
			if (model.getScoreManager().isBestScoreReached()) {
				g.setColor(Color.decode("#eac10c"));
				g.setFont(GameGraphics.getGameFont2());
				g.drawString(GAMEOVERNEWTEXT + model.getScoreManager().getBestScore() + GAMEOVERNEWTEXTESC, (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTNEWYOFF);
			} else {
				g.setColor(Color.WHITE);
				g.setFont(GameGraphics.getGameFont2());
				g.drawString(GAMEOVERSCORETEXT + model.getScore(), (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTSCOREYOFF);

				g.setColor(Color.decode("#F84534"));
				g.setFont(GameGraphics.getGameFont2());
				g.drawString(GAMEOVERBESTTEXT + model.getScoreManager().getBestScore(), (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTBESTYOFF);
			}

			g2.setColor(Color.decode("#F84534"));
			g2.setFont(GameGraphics.getGameFont3());
			g2.drawString(GAMEOVERCONTINUETEXT, (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTCONTINUEYOFF);

		}


		g2.setComposite(oldComposite);
	}
}
