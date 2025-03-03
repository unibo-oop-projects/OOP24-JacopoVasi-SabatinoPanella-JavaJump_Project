package model.states;

import controller.GameAction;
import model.GameModel;

public class MenuState implements GameStateHandler
{
	private final GameState gameState= GameState.MENU;
	private int selection;
	private final int maxSelection=2;
	private final int minSelection=0;
	@Override
	public void onEnter(GameModel model)
	{
	selection=0;
	}

	@Override
	public void handleAction(GameModel model, GameAction action) {
		switch (action) {
			case CONFIRM_SELECTION:

				model.startGame();
				model.setState(new InGameState());
				break;
			case PAUSE_GAME:

				System.exit(0);
				break;
			default:
				break;
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {

		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	@Override
	public int getValue() {
		return selection;
	}

	public int getSelection() {
		return selection;
	}
}