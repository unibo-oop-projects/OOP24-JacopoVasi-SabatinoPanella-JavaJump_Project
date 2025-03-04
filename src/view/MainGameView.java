package view;

import model.GameModel;
import model.GameModelObserver;
import model.states.GameState;
import model.states.GameStateHandler;
import view.renderers.RendererManager;
import view.sound.AudioManager;
import view.view_states.*;

import javax.swing.*;
import java.awt.*;

public class MainGameView extends JPanel implements GameModelObserver
{

	private final GameModel model;


	private final GameViewState menuView;
	private final GameViewState inGameView;
	private final GameViewState pauseView;
	private final GameViewState gameOverView;

	private GameState lastState;
	public MainGameView(GameModel model) {
		this.model = model;
		RendererManager rendererManager = new RendererManager();
		this.setBackground(Color.BLACK);


		this.menuView = new MenuView();
		this.inGameView = new InGameView(rendererManager);
		this.pauseView = new PauseView();
		this.gameOverView = new GameOverView();

		this.lastState = model.getCurrentState().getGameState();
	}

	
	public void updateView(float deltaTime) {

		GameStateHandler currentHandler = model.getCurrentState();
		GameState gs = currentHandler.getGameState();

		if (gs == GameState.GAME_OVER) {

			gameOverView.update(deltaTime);
		} else {

			gameOverView.stopFade();
		}

		repaint();
	}

	@Override
	public void onModelUpdate(GameModel model) {
		GameStateHandler handler = model.getCurrentState();
		GameState currentGS = handler.getGameState();


		if (currentGS != this.lastState) {

			switch (currentGS) {
				case IN_GAME:

					break;
				case PAUSE:

					break;
				case GAME_OVER:

					System.out.println("Entering GAME_OVER => fadeOut");
					AudioManager.fadeOut(2f);
					gameOverView.startFade();
					break;
				case MENU:



					break;
			}
		}


		this.lastState = currentGS;

		repaint();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		GameStateHandler stateHandler = model.getCurrentState();
		GameState currentState = stateHandler.getGameState();

		switch (currentState) {
			case MENU:
				menuView.draw(g, model);
				AudioManager.stopMusic();
				break;
			case IN_GAME:
				inGameView.draw(g, model);
				AudioManager.startMusic();
				break;
			case PAUSE:
				pauseView.draw(g, model);
				AudioManager.pauseMusic();
				break;
			case GAME_OVER:

				inGameView.draw(g, model);

				gameOverView.draw(g, model);
				break;
			default:
				break;
		}
	}
}
