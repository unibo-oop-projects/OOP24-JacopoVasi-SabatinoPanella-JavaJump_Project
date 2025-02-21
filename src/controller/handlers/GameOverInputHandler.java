package controller.handlers;

import model.GameModel;
import model.GameState;
import java.awt.event.KeyEvent;



public class GameOverInputHandler implements InputHandler
{
	private final GameModel model;

	public GameOverInputHandler(GameModel model)
	{
		this.model = model;
	}

	@Override
	public void handleInput(KeyEvent e)
	{
		int code = e.getKeyCode();





	}
}
