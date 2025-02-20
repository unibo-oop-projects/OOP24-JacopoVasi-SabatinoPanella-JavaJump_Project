package controller;

import model.GameModel;
import model.GameState;
import view.GameView;

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
		this.view = view;
		this.running = false;
	}


	public void startGameLoop() {
		running = true;
		Thread loopThread = new Thread(() -> {
			long previousTime = System.nanoTime();
			final double fps = 60.0;
			final double nsPerFrame = 1_000_000_000 / fps;

			while (running) {
				long currentTime = System.nanoTime();
				double elapsedNs = currentTime - previousTime;

				if (elapsedNs >= nsPerFrame) {

					float deltaTime = (float) (elapsedNs / 1_000_000_000.0);


					model.update(deltaTime);



					previousTime = currentTime;
				}


				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
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
		switch (model.getState()) {
			case MENU:
				handleMenuInput(e);
				break;
			case IN_GAME:
				handleInGameInput(e);
				break;
			case GAME_OVER:
				handleGameOverInput(e);
				break;
			case PAUSE:


			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{


	}

	@Override
	public void keyTyped(KeyEvent e) {

	}


	private void handleMenuInput(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_ENTER) {

			model.startGame();


		} else if (code == KeyEvent.VK_UP) {

		} else if (code == KeyEvent.VK_DOWN) {

		}
	}


	private void handleInGameInput(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:

				break;
			case KeyEvent.VK_ESCAPE:

				model.setState(GameState.PAUSE);
				break;
			default:
				break;
		}
	}


	private void handleGameOverInput(KeyEvent e) {
		int code = e.getKeyCode();

		model.setState(GameState.MENU);
	}
}