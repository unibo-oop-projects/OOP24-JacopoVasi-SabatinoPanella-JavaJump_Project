package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.GameAction;
import it.unibo.javajump.model.GameModelImpl;

public class PauseState implements GameStateHandler {
	private final GameState gameState = GameState.PAUSE;

	@Override
	public void handleAction(GameModelImpl model, GameAction action) {
		if (action == GameAction.PAUSE_GAME) {
			model.setState(new InGameState());
		} else if (action == GameAction.GO_TO_MENU) {
			model.setState(new MenuState());
		}
	}

	@Override
	public void update(GameModelImpl model, float deltaTime) {
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

}
