package it.unibo.javajump.controller;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.MainGameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static it.unibo.javajump.utility.Constants.*;

public class GameController implements KeyListener {

	private final GameModel model;
	private final MainGameView view;
	private volatile boolean running;

	private boolean pressingLeft = false;
	private boolean pressingRight = false;

	public GameController(GameModel model, MainGameView view) {
		this.model = model;
		this.view = view;
		this.running = false;
	}

	public void startGameLoop() {
		running = true;
		Thread loopThread = new Thread(()
				-> {
			long previousTime = System.nanoTime();

			final double nsPerFrame = NANOSECONDS_PER_SECOND / FPS;

			while (running) {

				long currentTime = System.nanoTime();
				double elapsedNs = currentTime - previousTime;

				if (elapsedNs >= nsPerFrame) {

					float deltaTime = (float) (elapsedNs / NANOSECONDS_PER_SECOND);

					updateModel(deltaTime);
					view.updateView(deltaTime);
					previousTime = currentTime;
				}

				try {
					Thread.sleep(SLEEPTHREAD);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		loopThread.start();
	}

	private void updateModel(float deltaTime) {

		updateHorizontalDirectionInModel();
		model.update(deltaTime);
	}


	private void updateHorizontalDirectionInModel() {
		int direction = NULLDIRECTION;
		if (pressingLeft && !pressingRight) {
			direction = LEFTDIRECTION;
		} else if (pressingRight && !pressingLeft) {
			direction = RIGHTDIRECTION;
		}


		if (direction < NULLDIRECTION) {
			model.handleAction(GameAction.MOVE_LEFT);
		} else if (direction > NULLDIRECTION) {
			model.handleAction(GameAction.MOVE_RIGHT);
		} else {
			model.handleAction(GameAction.STOP_HORIZONTAL);
		}
	}


	public void stopGameLoop() {
		running = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				pressingLeft = true;
				break;
			case KeyEvent.VK_RIGHT:
				pressingRight = true;
				break;
			case KeyEvent.VK_ENTER:
				model.handleAction(GameAction.CONFIRM_SELECTION);
				break;
			case KeyEvent.VK_ESCAPE:
				model.handleAction(GameAction.PAUSE_GAME);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				pressingLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				pressingRight = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
