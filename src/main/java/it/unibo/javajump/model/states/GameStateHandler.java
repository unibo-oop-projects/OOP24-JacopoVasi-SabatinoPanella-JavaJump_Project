package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.input.GameAction;

import it.unibo.javajump.model.GameModel;


public interface GameStateHandler {
	default void onEnter(GameModel model) {
	}


	default void onExit(GameModel model) {
	}


	void handleAction(GameModel model, GameAction action);


	void update(GameModel model, float deltaTime);

	GameState getGameState();

	float getDeltaTime();
}