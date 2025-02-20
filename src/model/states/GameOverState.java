package model.states;

import controller.GameAction;
import model.GameModel;

public class GameOverState implements GameStateHandler
{
	@Override
	public void handleAction(GameModel model, GameAction action)
	{
		model.setState(new MenuState());
	}

	@Override
	public void update(GameModel model, float deltaTime)
	{


	}
}
