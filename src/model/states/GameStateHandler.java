package model.states;

import controller.GameAction;

import model.GameModel;

public interface GameStateHandler
{
	
	default void onEnter(GameModel model) {}

	
	default void onExit(GameModel model) {}

	
	void handleAction(GameModel model, GameAction action);

	
	void update(GameModel model, float deltaTime);

	public GameState getGameState();
}