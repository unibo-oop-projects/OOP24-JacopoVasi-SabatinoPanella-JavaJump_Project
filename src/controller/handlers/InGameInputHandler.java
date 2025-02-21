package controller.handlers;

import model.GameModel;
import model.GameState;
import model.physics.MovementDirection;

import java.awt.event.KeyEvent;


public class InGameInputHandler implements InputHandler
{
	private final GameModel model;

	public InGameInputHandler(GameModel model)
	{
		this.model = model;
	}

	@Override
	public void handleInput(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:		break;
			case KeyEvent.VK_RIGHT:		break;
			case KeyEvent.VK_ESCAPE:
		break;
			default:
				break;
		}
	}
}