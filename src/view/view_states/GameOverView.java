package view.view_states;

import model.GameModel;

import java.awt.*;

public class GameOverView implements GameViewState {

	private float fadeAlpha = 0f;
	private float fadeDuration = 1f;
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


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, w, h);


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g2.setColor(Color.RED);
		g2.setFont(new Font("Arial", Font.BOLD, 40));
		String text = "GAME OVER";
		int strWidth = g2.getFontMetrics().stringWidth(text);
		g2.drawString(text, (w - strWidth)/2, h/2);


		if (fadeAlpha >= 1f) {
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			String pressKey = "Press ENTER to continue...";
			int pkWidth = g2.getFontMetrics().stringWidth(pressKey);
			g2.drawString(pressKey, (w - pkWidth)/2, h/2 + 40);
		}


		g2.setComposite(oldComposite);
	}
}
