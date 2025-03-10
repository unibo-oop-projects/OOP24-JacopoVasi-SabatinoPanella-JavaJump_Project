package it.unibo.javajump.view;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelObserver;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.view.renderers.RenderManager;
import it.unibo.javajump.view.renderers.RendererManagerImpl;
import it.unibo.javajump.view.sound.AudioManagerImpl;
import it.unibo.javajump.view.viewstates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public class MainGameViewImpl extends JPanel implements MainGameView, GameModelObserver {

	private final GameModel model;

	private final GameViewState menuView;
	private final GameViewState inGameView;
	private final GameViewState pauseView;
	private final GameViewState gameOverView;

	private final int virtualWidth;
	private final int virtualHeight;

	private final BufferedImage tempScreen;

	private GameState lastState;


	public MainGameViewImpl(GameModel model) {
		this.model = model;
		setDoubleBuffered(true);

		this.virtualWidth = model.getScreenWidth();
		this.virtualHeight = model.getScreenHeight();

		RenderManager rendererManager = new RendererManagerImpl();
		setBackground(Color.decode("#05051C"));

		this.menuView = new MenuView();
		this.inGameView = new InGameView(rendererManager);
		this.pauseView = new PauseView();
		this.gameOverView = new GameOverView();

		this.lastState = model.getCurrentState().getGameState();

		tempScreen = new BufferedImage(virtualWidth, virtualHeight, BufferedImage.TYPE_INT_ARGB);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				repaint();
			}
		});
	}

	
	@Override
	public void updateView() {

		GameStateHandler currentHandler = model.getCurrentState();
		GameState gs = currentHandler.getGameState();
		if (gs == GameState.GAME_OVER) {
			gameOverView.update();
		} else {
			gameOverView.stopFade();
		}
		repaint();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawToTempScreen();

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		float targetAspect = (float) virtualWidth / virtualHeight;
		float panelAspect = (float) panelWidth / panelHeight;

		int drawWidth, drawHeight, drawX, drawY;
		if (panelAspect > targetAspect) {

			drawHeight = panelHeight;
			drawWidth = Math.round(drawHeight * targetAspect);
			drawX = (panelWidth - drawWidth) / MAINVIEWCENTERDIV;
			drawY = MAINVIEWDRAWYINIT;
		} else {

			drawWidth = panelWidth;
			drawHeight = Math.round(drawWidth / targetAspect);
			drawX = MAINVIEWDRAWYINIT;
			drawY = (panelHeight - drawHeight) / MAINVIEWCENTERDIV;
		}

		g.setColor(Color.decode("#05051C"));
		g.fillRect(MAINVIEWRECTX, MAINVIEWRECTY, panelWidth, panelHeight);

		g.drawImage(tempScreen, drawX, drawY, drawWidth, drawHeight, null);
	}

	
	private void drawToTempScreen() {
		Graphics2D g2 = tempScreen.createGraphics();

		g2.setColor(Color.BLACK);
		g2.fillRect(MAINVIEWRECTX, MAINVIEWRECTY, virtualWidth, virtualHeight);

		GameStateHandler stateHandler = model.getCurrentState();
		GameState currentState = stateHandler.getGameState();

		switch (currentState) {
			case MENU:
				AudioManagerImpl.stopMusic();
				menuView.draw(g2, model);
				break;
			case IN_GAME:
				AudioManagerImpl.startMusic();
				inGameView.draw(g2, model);
				break;
			case PAUSE:
				AudioManagerImpl.pauseMusic();
				pauseView.draw(g2, model);
				break;
			case GAME_OVER:

				inGameView.draw(g2, model);
				gameOverView.draw(g2, model);
				break;
			default:
				break;
		}
		g2.dispose();
	}

	@Override
	public void onModelUpdate(GameModel model) {
		GameStateHandler stateHandler = model.getCurrentState();
		GameState currentState = stateHandler.getGameState();

		if (currentState != lastState) {
			switch (currentState) {
				case IN_GAME:

					break;
				case PAUSE:

					break;
				case GAME_OVER:
					AudioManagerImpl.fadeOut(MAINVIEWAUDIOFADE);
					gameOverView.startFade();
					break;
				case MENU:

					break;
				default:
					break;
			}
		}
		lastState = currentState;

		repaint();
	}
}
