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

		model.getViewManager().setCvMenu();
	}

	@Override
	public void handleAction(GameModel model, GameAction action)
	{
		switch(action)
		{
			case CONFIRM_SELECTION:

				if (selection==1){
					model.startGame();
					model.setState(new InGameState());
				}else if (selection==maxSelection){
					System.exit(0);
				}

				break;
			case MOVE_MENU_DOWN:
				selection++;
				if(selection>maxSelection){
					selection=1;
				}


				break;
			case MOVE_MENU_UP:
				System.out.println("Menu DOWN");
				selection--;
				if(selection==0){
					selection=3;
				}
				break;
			default:

				break;
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
	public int getValue() {
		return selection;
	}

	public int getSelection() {
		return selection;
	}
}