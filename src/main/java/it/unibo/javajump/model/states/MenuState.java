package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.GameAction;
import it.unibo.javajump.model.GameModelImpl;

public class MenuState implements GameStateHandler {
	private final GameState gameState = GameState.MENU;

	@Override
	public void onEnter(GameModelImpl model) {
	}

	@Override
	public void handleAction(GameModelImpl model, GameAction action) {
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
	public void update(GameModelImpl model, float deltaTime) {

		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}


}