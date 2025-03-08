package model.states;

import controller.GameAction;
import model.GameModel;

public class GameOverState implements GameStateHandler {

	private final GameState gameState = GameState.GAME_OVER;

	@Override
	public void handleAction(GameModel model, GameAction action) {
		switch (action) {
			case CONFIRM_SELECTION -> model.setState(new MenuState());
			default -> {
			}
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {


	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

}
