package controller.managers;

import java.awt.event.KeyEvent;

public class CharacterMovementManager
{
	private boolean pressingLeft = false;
	private boolean pressingRight = false;

	public void onKeyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			pressingLeft = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			pressingRight = true;
		}
	}

	public void onKeyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			pressingLeft = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			pressingRight = false;
		}
	}

	public int getDirection()
	{
		if (pressingLeft && !pressingRight) return -1;
		if (pressingRight && !pressingLeft) return 1;
		return 0;
	}
}