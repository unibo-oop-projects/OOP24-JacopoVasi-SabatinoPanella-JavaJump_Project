package it.unibo.javajump.model.states.pause;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.ingame.InGameState;
import it.unibo.javajump.model.states.menu.MenuState;


import static it.unibo.javajump.utility.Constants.*;

public class PauseState implements GameStateHandler {
	private float deltaTime = 0;
	private PauseOption selection = PauseOption.CONTINUE;
	private final GameState gameState = GameState.PAUSE;

	@Override
	public void handleAction(GameModel model, GameAction action) {
		switch (action) {
			case CONFIRM_SELECTION -> {
				switch (selection) {
					case CONTINUE -> model.setState(new InGameState());
					case MAIN_MENU -> model.setState(new MenuState());
					case QUIT -> System.exit(0);
				}
			}
			case MOVE_MENU_UP -> {
				if (selection == PauseOption.CONTINUE) {
					selection = PauseOption.QUIT;
				} else if (selection == PauseOption.MAIN_MENU) {
					selection = PauseOption.CONTINUE;
				} else if (selection == PauseOption.QUIT) {
					selection = PauseOption.MAIN_MENU;
				}
			}
			case MOVE_MENU_DOWN -> {
				if (selection == PauseOption.CONTINUE) {
					selection = PauseOption.MAIN_MENU;
				} else if (selection == PauseOption.MAIN_MENU) {
					selection = PauseOption.QUIT;
				} else if (selection == PauseOption.QUIT) {
					selection = PauseOption.CONTINUE;
				}
			}
			default -> {
			}
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {
		this.deltaTime += deltaTime;
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	public PauseOption getSelection() {
		return selection;
	}

	public float getDeltaTime() {
		return deltaTime;
	}
}
