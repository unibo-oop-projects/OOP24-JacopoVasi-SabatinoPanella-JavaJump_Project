package it.unibo.javajump.view;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelObserver;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.view.renderers.RenderManager;
import it.unibo.javajump.view.renderers.RendererManagerImpl;
import it.unibo.javajump.view.sound.music.MusicManager;
import it.unibo.javajump.view.sound.music.MusicManagerImpl;
import it.unibo.javajump.view.sound.sfx.SoundEffectsManager;
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

	private final MusicManager musicManager;
	private final SoundEffectsManager soundEffectsManager;

	public MainGameViewImpl(GameModel model) {
		this.model = model;
		this.musicManager = new MusicManagerImpl(RESOURCESWINDOWSPATH + RESOURCESMUSIC, AUDIOVOLUME);
		this.soundEffectsManager = new SoundEffectsManager(AUDIOVOLUME);
		setDoubleBuffered(true);

		this.virtualWidth = model.getScreenWidth();
		this.virtualHeight = model.getScreenHeight();

		RenderManager rendererManager = new RendererManagerImpl(soundEffectsManager);
		setBackground(Color.decode(BACKGROUND_DEFAULT_COLOR));

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

		Rectangle scaledRect = ScaleUtils.computeScaledRectangle(virtualWidth, virtualHeight, getSize());
		g.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
		g.fillRect(MAINVIEWRECTX, MAINVIEWRECTY, getWidth(), getHeight());
		g.drawImage(tempScreen, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
	}

	
	private void drawToTempScreen() {
		Graphics2D g2 = tempScreen.createGraphics();
		g2.setColor(Color.BLACK);
		g2.fillRect(MAINVIEWRECTX, MAINVIEWRECTY, virtualWidth, virtualHeight);

		GameState currentState = model.getCurrentState().getGameState();
		switch (currentState) {
			case MENU -> menuView.draw(g2, model);
			case IN_GAME -> inGameView.draw(g2, model);
			case PAUSE -> pauseView.draw(g2, model);
			case GAME_OVER -> {
				inGameView.draw(g2, model);
				gameOverView.draw(g2, model);
			}
			default -> {
			}
		}
		g2.dispose();
	}

	@Override
	public void onModelUpdate(GameModel model) {
		GameState currentState = model.getCurrentState().getGameState();

		if (currentState != lastState) {
			switch (currentState) {
				case MENU -> musicManager.stopMusic();
				case IN_GAME -> {
					if (lastState == GameState.PAUSE) {
						musicManager.resumeMusic();
					} else {
						musicManager.stopMusic();
						musicManager.startMusic();
					}
				}
				case PAUSE -> musicManager.pauseMusic();
				case GAME_OVER -> {
					musicManager.fadeOut(MAINVIEWAUDIOFADE);
					gameOverView.startFade();
				}
				default -> {
				}
			}
		}
		lastState = currentState;
		repaint();
	}
}
