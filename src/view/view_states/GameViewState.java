package view.view_states;

import model.GameModel;

import java.awt.Graphics;




public interface GameViewState {
	
	void draw(Graphics g, GameModel model);

	void startFade();

	void update(float deltaTime);

	void stopFade();
}
