package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModelImpl;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.*;

public class PauseView implements GameViewState {
	@Override
	public void draw(Graphics g, GameModelImpl model) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, model.getScreenWidth(), model.getScreenHeight());

		g.setColor(Color.WHITE);
		g.setFont(PAUSEFONT);
		g.drawString(PAUSETEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV);
	}

	@Override
	public void startFade() {

	}

	@Override
	public void update(float deltaTime) {

	}

	@Override
	public void stopFade() {

	}
}
