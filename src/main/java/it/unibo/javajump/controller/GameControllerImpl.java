package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.controller.input.InputManager;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.MainGameView;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Implementation of GameController interface.
 */
public class GameControllerImpl implements GameController {
	/**
	 * Private field to access the model.
	 */
	private final GameModel model;
	/**
	 * Private field to access the view.
	 */
	private final MainGameView view;
	/**
	 * Flag to set the current state of the GameLoop thread.
	 */
	private volatile boolean running;
	/**
	 * Private field to access the player inputs.
	 */
	private final InputManager inputManager;

	/**
	 * Constructor for the GameControllerImpl class.
	 *
	 * @param model        The game model
	 * @param view         The game view
	 * @param inputManager The input manager
	 */
	public GameControllerImpl(GameModel model, MainGameView view, InputManager inputManager) {
		this.model = model;
		this.view = view;
		this.running = false;
		this.inputManager = inputManager;
	}

	/**
	 * Starts the GameLoop in a separate thread (~60 FPS).
	 */
	@Override
	public void startGameLoop() {
		running = true;
		Thread loopThread = new Thread(() -> {
			long previousTime = System.nanoTime();
			final double nsPerFrame = NANOSECONDS_PER_SECOND / FPS;

			while (running) {
				long currentTime = System.nanoTime();
				double elapsedNs = currentTime - previousTime;
				if (elapsedNs >= nsPerFrame) {
					float deltaTime = (float) (elapsedNs / NANOSECONDS_PER_SECOND);
					processDiscreteInput();
					updateModel(deltaTime);
					view.updateView();
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

	/**
	 * Stops the GameLoop thread
	 */
	@Override
	public void stopGameLoop() {
		running = false;
	}

	/**
	 * Private method to update the model regarding the current pressed direction
	 *
	 * @param deltaTime time passed since last update (in seconds)
	 */
	private void updateModel(float deltaTime) {
		int horizontalDirection = inputManager.getHorizontalDirection();
		if (horizontalDirection < NULLDIRECTION) {
			model.handleAction(GameAction.MOVE_LEFT);
		} else if (horizontalDirection > NULLDIRECTION) {
			model.handleAction(GameAction.MOVE_RIGHT);
		} else {
			model.handleAction(GameAction.STOP_HORIZONTAL);
		}
		model.update(deltaTime);
	}

	/**
	 * Private method to process the GameAction(s) stored in the queue, it demands the model to process them accordingly
	 */
	private void processDiscreteInput() {
		GameAction action;
		while ((action = inputManager.getActionQueue().poll()) != null) {
			model.handleAction(action);
		}
	}

}
