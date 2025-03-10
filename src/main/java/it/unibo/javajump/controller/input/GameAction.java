package it.unibo.javajump.controller.input;

/**
 * Enum that represents the abstract possible game actions, that will be handled by the model.
 */
public enum GameAction {
	MOVE_LEFT,
	MOVE_RIGHT,
	STOP_HORIZONTAL,
	START_GAME,
	PAUSE_GAME,
	RESUME_GAME,
	CONFIRM_SELECTION,
	MOVE_MENU_UP,
	MOVE_MENU_DOWN,
	GO_TO_MENU
}
