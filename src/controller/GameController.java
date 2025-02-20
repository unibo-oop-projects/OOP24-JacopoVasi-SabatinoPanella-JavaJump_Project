package controller;

import controller.handlers.GameOverInputHandler;
import controller.handlers.InGameInputHandler;
import controller.handlers.MenuInputHandler;
import model.GameModel;
import model.GameState;
import model.entities.Character;
import model.physics.MovementDirection;
import model.physics.PhysicsManager;
import view.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener
{
	private final GameModel model;
	private final GameView view;
	private volatile boolean running;

	public GameController(GameModel model, GameView view)
	{
		this.model = model;
		this.view= view;
		this.running = false;
	}	public void startGameLoop()
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
					float deltaTime = (float) (elapsedNs / 1_000_000_000.0);					model.update(deltaTime);					previousTime = currentTime;
				}

				try { Thread.sleep(1); }
				catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		loopThread.start();
	}	public void stopGameLoop()
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

	}	private GameAction mapKeyToAction(KeyEvent e, boolean pressed)
	{
		int code = e.getKeyCode();
		switch (code)
		{
			case KeyEvent.VK_LEFT:
				return pressed ? GameAction.MOVE_LEFT : GameAction.STOP_HORIZONTAL;
			case KeyEvent.VK_RIGHT:
				return pressed ? GameAction.MOVE_RIGHT : GameAction.STOP_HORIZONTAL;
			case KeyEvent.VK_ENTER:				return pressed ? GameAction.CONFIRM_SELECTION : null;
			case KeyEvent.VK_ESCAPE:
				return pressed ? GameAction.PAUSE_GAME : null;
			default:
				return null;
		}
	}

}