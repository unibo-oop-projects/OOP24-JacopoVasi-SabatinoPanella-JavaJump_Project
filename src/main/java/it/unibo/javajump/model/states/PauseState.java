package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;

import static it.unibo.javajump.utility.Constants.*;

public class PauseState implements GameStateHandler {
	private int state = PAUSEMENUCONTINUE;
	private final GameState gameState = GameState.PAUSE;

	@Override
	public void handleAction(GameModel model, GameAction action) {
		switch (action) {
			case CONFIRM_SELECTION -> {
				switch (state) {
					case PAUSEMENUCONTINUE -> model.setState(new InGameState());

					case PAUSEMENUMAINMENU -> model.setState(new MenuState());

					case PAUSEMENUQUIT -> System.exit(0);

					default -> {
					}
				}
			}
			case MOVE_MENU_UP -> {
				state--;
				if (state > PAUSEMENUQUIT) {
					state = PAUSEMENUCONTINUE;
				}

			}
			case MOVE_MENU_DOWN -> {
				state++;
				if (state < PAUSEMENUCONTINUE) {
					state = PAUSEMENUQUIT;
				}
			}
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

	@Override
	public int getState() {
		return state;
	}
}
