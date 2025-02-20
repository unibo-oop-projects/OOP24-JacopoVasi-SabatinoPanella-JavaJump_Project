package model.states;

import controller.GameAction;
import model.GameModel;

public class PauseState implements GameStateHandler
{
	@Override
	public void handleAction(GameModel model, GameAction action)
	{
		if (action == GameAction.RESUME_GAME)
		{
			model.setState(new InGameState());
		}
		else if (action == GameAction.GO_TO_MENU)
		{
			model.setState(new MenuState());
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {}
}
