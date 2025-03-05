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
import java.awt.image.BufferedImage;

public class MainGameView extends JPanel implements GameModelObserver
{
	private final GameModel model;

	private final GameViewState menuView;
	private final GameViewState inGameView;
	private final GameViewState pauseView;
	private final GameViewState gameOverView;
	private int screenWidth, screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
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
		this.screenWidth = model.getScreenWidth();
		this.screenHeight = model.getScreenHeight();
		this.tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		this.g2 = (Graphics2D) tempScreen.getGraphics();
	}

	
	public void updateView(float deltaTime) {

		GameStateHandler currentHandler = model.getCurrentState();
		GameState gs = currentHandler.getGameState();

		if (gs == GameState.GAME_OVER) {

			gameOverView.update(deltaTime);
		} else {

			gameOverView.stopFade();
		}

		drawToTempScreen();
		drawToScreen();
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

		drawToTempScreen();
		drawToScreen();
	}

	public void drawToTempScreen() {
		GameStateHandler stateHandler = model.getCurrentState();
		GameState currentState = stateHandler.getGameState();

		switch (currentState) {
			case MENU:
				menuView.draw(g2, model);
				AudioManager.stopMusic();
				break;
			case IN_GAME:
				inGameView.draw(g2, model);
				AudioManager.startMusic();
				break;
			case PAUSE:
				pauseView.draw(g2, model);
				AudioManager.pauseMusic();
				break;
			case GAME_OVER:

				inGameView.draw(g2, model);

				gameOverView.draw(g2, model);
				break;
			default:
				break;
		}
	}

	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth, screenHeight, null);
		g.dispose();
	}

	public void setNewSize(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

}
