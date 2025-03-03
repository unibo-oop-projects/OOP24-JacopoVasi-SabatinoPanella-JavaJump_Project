package controller;

import model.GameModel;
import view.MainGameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener
{
	private final GameModel model;
	private final MainGameView view;
	private volatile boolean running;


	private boolean pressingLeft = false;
	private boolean pressingRight = false;

	public GameController(GameModel model, MainGameView view)
	{
		this.model = model;
		this.view = view;
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


					updateModel(deltaTime);
					view.updateView(deltaTime);

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


	private void updateModel(float deltaTime) {

		updateHorizontalDirectionInModel();
		model.update(deltaTime);
	}

	private void updateHorizontalDirectionInModel() {
		int direction = 0;
		if (pressingLeft && !pressingRight) {
			direction = -1;
		} else if (pressingRight && !pressingLeft) {
			direction = +1;
		}


		if (direction < 0) {
			model.handleAction(GameAction.MOVE_LEFT);
		} else if (direction > 0) {
			model.handleAction(GameAction.MOVE_RIGHT);
		} else {
			model.handleAction(GameAction.STOP_HORIZONTAL);
		}
	}

	
	public void stopGameLoop()
	{
		running = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				pressingLeft = true;
				break;
			case KeyEvent.VK_RIGHT:
				pressingRight = true;
				break;
			case KeyEvent.VK_ENTER:
				model.handleAction(GameAction.CONFIRM_SELECTION);
				break;
			case KeyEvent.VK_ESCAPE:
				model.handleAction(GameAction.PAUSE_GAME);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				pressingLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				pressingRight = false;
				break;
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
			case KeyEvent.VK_DOWN:
				return pressed ? GameAction.MOVE_MENU_DOWN : null;
			case KeyEvent.VK_UP:
				return pressed ? GameAction.MOVE_MENU_UP : null;


			default:
				return null;
		}
	}


}