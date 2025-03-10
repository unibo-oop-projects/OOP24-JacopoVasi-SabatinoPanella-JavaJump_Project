package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;

public class MenuState implements GameStateHandler {
	private final GameState gameState = GameState.MENU;


	@Override
	public void handleAction(GameModel model, GameAction action) {
		switch (action) {
			case CONFIRM_SELECTION:

				model.startGame();
				model.setState(new InGameState());
				break;
			case PAUSE_GAME:

				System.exit(0);
				break;
			default:
				break;
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {

		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	@Override
	public int getState() {
		return 0;
	}


}