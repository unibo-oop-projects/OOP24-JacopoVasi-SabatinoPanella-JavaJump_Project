package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.GameAction;

import it.unibo.javajump.model.GameModelImpl;

public interface GameStateHandler {

	default void onEnter(GameModelImpl model) {
	}


	default void onExit(GameModelImpl model) {
	}


	void handleAction(GameModelImpl model, GameAction action);


	void update(GameModelImpl model, float deltaTime);

	GameState getGameState();


}