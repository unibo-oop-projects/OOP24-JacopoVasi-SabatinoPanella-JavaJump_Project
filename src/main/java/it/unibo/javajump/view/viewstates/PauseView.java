package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.*;

public class PauseView implements GameViewState {
	private int selectionYcor = PAUSECONTINUEY;

	@Override
	public void draw(Graphics g, GameModel model) {
		switch (model.getCurrentState().getState()) {
			case PAUSEMENUCONTINUE ->
					selectionYcor = PAUSECONTINUEY;
			case PAUSEMENUMAINMENU ->
					selectionYcor = PAUSEMAINMENUY;
			case PAUSEMENUQUIT ->
					selectionYcor = PAUSEQUITY;
			default -> {
			}
		}

		g.setColor(new Color(Color.black.getRGB()));
		g.fillRect(0, 0, model.getScreenWidth(), model.getScreenHeight());

		g.setColor(Color.WHITE);
		g.setFont(PAUSEFONT);
		g.drawString(PAUSETEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV);
		g.setFont(PAUSEMENUFONT);
		g.setColor(Color.WHITE);
		g.drawString(PAUSECONTINUETEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV + PAUSECONTINUEY);
		g.drawString(PAUSEMAINMENUTEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV + PAUSEMAINMENUY);
		g.drawString(PAUSEQUITTEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV + PAUSEQUITY);
		g.drawString(PAUSESELECTIONTEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF - PAUSESELECTIONX, model.getScreenHeight() / PAUSECENTERDIV + selectionYcor);
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
