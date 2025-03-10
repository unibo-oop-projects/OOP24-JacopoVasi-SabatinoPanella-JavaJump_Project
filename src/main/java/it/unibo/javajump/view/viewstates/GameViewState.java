package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModelImpl;

import java.awt.Graphics;


public interface GameViewState {

	void draw(Graphics g, GameModelImpl model);

	void startFade();

	void update(float deltaTime);

	void stopFade();
}
