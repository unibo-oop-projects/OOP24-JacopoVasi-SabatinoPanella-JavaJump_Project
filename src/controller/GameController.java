package controller;

import model.GameModel;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener
{
	private final GameModel model;

	private volatile boolean running;

	public GameController(GameModel model)
	{
		this.model = model;
		this.running = false;
	}
	public void startGameLoop()
	{
		running = true;
		Thread loopThread = new Thread(() ->
		{
			long previousTime = System.nanoTime();
			final double fps = 60.0;
			final double nsPerFrame = 1_000_000_000 / fps;

			while (running)
			{
				long currentTime = System.nanoTime();
				double elapsedNs = currentTime - previousTime;

				if (elapsedNs >= nsPerFrame)
				{
					float deltaTime = (float) (elapsedNs / 1_000_000_000.0);
					model.update(deltaTime);
					previousTime = currentTime;
				}

				try { Thread.sleep(1); }
				catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		loopThread.start();
	}

	
	public void stopGameLoop()
	{
		running = false;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		GameAction action = mapKeyToAction(e, true);
		if (action != null)
		{
			model.handleAction(action);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		GameAction action = mapKeyToAction(e, false);
		if (action != null)
		{
			model.handleAction(action);
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
	private GameAction mapKeyToAction(KeyEvent e, boolean pressed)
	{
		int code = e.getKeyCode();
		switch (code)
		{
			case KeyEvent.VK_LEFT:
				return pressed ? GameAction.MOVE_LEFT : GameAction.STOP_HORIZONTAL;
			case KeyEvent.VK_RIGHT:
				return pressed ? GameAction.MOVE_RIGHT : GameAction.STOP_HORIZONTAL;
			case KeyEvent.VK_ENTER:
				return pressed ? GameAction.CONFIRM_SELECTION : null;
			case KeyEvent.VK_ESCAPE:
				return pressed ? GameAction.PAUSE_GAME : null;
			default:
				return null;
		}
	}
}