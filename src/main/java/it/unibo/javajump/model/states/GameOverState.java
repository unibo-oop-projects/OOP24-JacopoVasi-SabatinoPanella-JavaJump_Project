package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.GameAction;
import it.unibo.javajump.model.GameModelImpl;

public class GameOverState implements GameStateHandler {

	private final GameState gameState = GameState.GAME_OVER;

	@Override
	public void handleAction(GameModelImpl model, GameAction action) {
		switch (action) {
			case CONFIRM_SELECTION -> model.setState(new MenuState());
			default -> {
			}
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
