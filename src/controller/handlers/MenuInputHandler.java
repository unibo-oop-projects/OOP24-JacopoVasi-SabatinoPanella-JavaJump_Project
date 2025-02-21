package controller.handlers;

import model.GameModel;
import java.awt.event.KeyEvent;


public class MenuInputHandler implements InputHandler
{
	private final GameModel model;

	public MenuInputHandler(GameModel model)
	{
		this.model = model;
	}

	@Override
	public void handleInput(KeyEvent e)
	{
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER)
		{
			model.startGame();
		}
		else if (code == KeyEvent.VK_UP)
		{

		}
		else if (code == KeyEvent.VK_DOWN)
		{

		}
	}
}