package it.unibo.javajump.view;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelObserver;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.view.graphics.GameGraphics;
import it.unibo.javajump.view.graphics.GameGraphicsImpl;
import it.unibo.javajump.view.renderers.RenderManager;
import it.unibo.javajump.view.renderers.RendererManagerImpl;
import it.unibo.javajump.view.sound.music.MusicManager;
import it.unibo.javajump.view.sound.music.MusicManagerImpl;
import it.unibo.javajump.view.sound.sfx.SoundEffectsManager;
import it.unibo.javajump.view.viewstates.GameOverView;
import it.unibo.javajump.view.viewstates.GameViewState;
import it.unibo.javajump.view.viewstates.InGameView;
import it.unibo.javajump.view.viewstates.MenuView;
import it.unibo.javajump.view.viewstates.PauseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.BACKGROUND_DEFAULT_COLOR;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_AUDIO_FADE;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_RECT_X;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_RECT_Y;
import static it.unibo.javajump.utility.Constants.MUSIC_VOLUME;
import static it.unibo.javajump.utility.Constants.RESOURCES_MUSIC_1;
import static it.unibo.javajump.utility.Constants.RESOURCES_PATH;
import static it.unibo.javajump.utility.Constants.SOUND_EFFECTS_VOLUME;

/**
 * The type Main game view.
 */
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

    /**
     * Instantiates a new Main game view.
     *
     * @param model the model
     */
    public MainGameViewImpl(final GameModel model) {
        final GameGraphics gameGraphics = new GameGraphicsImpl();
        this.model = model;
        this.musicManager = new MusicManagerImpl(RESOURCES_PATH + RESOURCES_MUSIC_1, MUSIC_VOLUME);
        final SoundEffectsManager soundEffectsManager = new SoundEffectsManager(SOUND_EFFECTS_VOLUME);


        this.virtualWidth = model.getScreenWidth();
        this.virtualHeight = model.getScreenHeight();

        final RenderManager rendererManager = new RendererManagerImpl(soundEffectsManager, gameGraphics);


        this.menuView = new MenuView(gameGraphics);
        this.inGameView = new InGameView(rendererManager);
        this.pauseView = new PauseView(gameGraphics);
        this.gameOverView = new GameOverView(gameGraphics);

        this.lastState = model.getCurrentState().getGameState();

        tempScreen = new BufferedImage(virtualWidth, virtualHeight, BufferedImage.TYPE_INT_ARGB);

        initialize();
    }
    private void initialize(){
        setDoubleBuffered(true);
        setBackground(Color.decode(BACKGROUND_DEFAULT_COLOR));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }


    @Override
    public void updateView() {
        final GameStateHandler currentHandler = model.getCurrentState();
        final GameState gs = currentHandler.getGameState();
        if (gs == GameState.GAME_OVER) {
            gameOverView.update();
        } else {
            gameOverView.stopFade();
        }
        repaint();
    }


    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawToTempScreen();

        final Rectangle scaledRect = ScaleUtils.computeScaledRectangle(virtualWidth, virtualHeight, getSize());
        g.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
        g.fillRect(MAIN_VIEW_RECT_X, MAIN_VIEW_RECT_Y, getWidth(), getHeight());
        g.drawImage(tempScreen, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
    }


    private void drawToTempScreen() {
        final Graphics2D g2 = tempScreen.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(MAIN_VIEW_RECT_X, MAIN_VIEW_RECT_Y, virtualWidth, virtualHeight);

        final GameState currentState = model.getCurrentState().getGameState();
        switch (currentState) {
            case MENU -> menuView.draw(g2, model);
            case IN_GAME -> inGameView.draw(g2, model);
            case PAUSE -> pauseView.draw(g2, model);
            case GAME_OVER -> {
                inGameView.draw(g2, model);
                gameOverView.draw(g2, model);
            }

        }
        g2.dispose();
    }

    @Override
    public void onModelUpdate(final GameModel model) {
        final GameState currentState = model.getCurrentState().getGameState();

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
                    musicManager.fadeOut(MAIN_VIEW_AUDIO_FADE);
                    gameOverView.startFade();
                }

            }
        }
        lastState = currentState;
        repaint();
    }
}
