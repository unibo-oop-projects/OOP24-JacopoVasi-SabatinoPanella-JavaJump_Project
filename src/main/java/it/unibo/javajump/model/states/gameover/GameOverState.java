package it.unibo.javajump.model.states.gameover;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;


import java.util.Objects;

public class GameOverState implements GameStateHandler {
	private float deltaTime = 0;
	private final GameState gameState = GameState.GAME_OVER;

	@Override
	public void handleAction(GameModel model, GameAction action) {

		if (Objects.requireNonNull(action) == GameAction.CONFIRM_SELECTION) {
			model.setState(new MenuState());
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {
		this.deltaTime = deltaTime;
		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	public int getState() {
		return 0;
	}

	public float getDeltaTime() {
		return deltaTime;
	}
}
