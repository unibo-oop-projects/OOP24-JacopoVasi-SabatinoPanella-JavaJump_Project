package view.viewstates;

import model.GameModel;

import java.awt.*;

public class PauseView implements GameViewState {
	@Override
	public void draw(Graphics g, GameModel model) {
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0,0, model.getScreenWidth(), model.getScreenHeight());

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("PAUSE", model.getScreenWidth()/2 - 50, model.getScreenHeight()/2);
	}

	@Override
	public void startFade()
	{

	}

	@Override
	public void update(float deltaTime)
	{

	}

	@Override
	public void stopFade()
	{

	}
}
