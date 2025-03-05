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
			int count=0;
			long countTime = System.currentTimeMillis();
			while (running)
			{

				long currentTime = System.nanoTime();
				double elapsedNs = currentTime - previousTime;
				long countElapsed = System.currentTimeMillis() - countTime;
				if(countElapsed>=1000){
					countTime=System.currentTimeMillis();
					System.out.println("FPS: "+count);
					count=0;

				}
				if (elapsedNs >= nsPerFrame)
				{
					count=count+1;
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
}