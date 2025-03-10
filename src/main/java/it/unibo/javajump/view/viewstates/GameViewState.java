package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;

import java.awt.Graphics;



public interface GameViewState {

	void draw(Graphics g, GameModel model);

	void startFade();

	void update(float deltaTime);

	void stopFade();
}
