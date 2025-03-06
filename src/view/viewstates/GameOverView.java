package view.viewstates;

import model.GameModel;
import view.graphics.GameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverView implements GameViewState {

	private float fadeAlpha = 0f;
	private final float fadeDuration = 1f;
	private float elapsedTime = 0f;
	private boolean fading = false;



	public void startFade() {
		this.fadeAlpha = 0f;
		this.elapsedTime = 0f;
		this.fading = true;
	}


	public void stopFade() {
		this.fadeAlpha = 1f;
		this.fading = false;
	}



	public void update(float deltaTime) {
		if (fading) {
			elapsedTime += deltaTime;
			fadeAlpha = Math.min(1f, elapsedTime / fadeDuration);
			if (fadeAlpha >= 1f) {
				fading = false;
			}
		}
	}



	@Override
	public void draw(Graphics g, GameModel model) {
		Graphics2D g2 = (Graphics2D) g;
		Composite oldComposite = g2.getComposite();

		int w = model.getScreenWidth();
		int h = model.getScreenHeight();

		int centerX = w / 2;
		int centerY = h / 2;


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
		g2.setColor(Color.decode("#05051C"));
		g2.fillRect(0, 0, w, h);


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		BufferedImage img = GameGraphics.getGameOver();
		g2.drawImage(img, (int)(centerX - img.getWidth()/1.72), (int)(centerY - h*0.15), (int)(img.getWidth() * 1.1), (int)(img.getHeight() * 1.1),null);


		if (fadeAlpha >= 1f) {
			if (model.getScoreManager().isBestScoreReached()) {
				g.setColor(Color.decode("#eac10c"));
				g.setFont(GameGraphics.getGameFont2());
				g.drawString("New Best Score:   " + model.getScoreManager().getBestScore() + " !!", (int)(centerX*0.65), centerY + 50);
			}
			else {
				g.setColor(Color.WHITE);
				g.setFont(GameGraphics.getGameFont2());
				g.drawString("Your Score:   " + model.getScore(), (int)(centerX*0.65), centerY + 50);

				g.setColor(Color.decode("#F84534"));
				g.setFont(GameGraphics.getGameFont2());
				g.drawString("Best Score:   " + model.getScoreManager().getBestScore(), (int)(centerX*0.65), centerY + 80);
			}

			g2.setColor(Color.decode("#F84534"));
			g2.setFont(GameGraphics.getGameFont3());
			g2.drawString("Press ENTER to continue...", (int)(centerX*0.65), centerY + 150);

		}


		g2.setComposite(oldComposite);
	}
}
