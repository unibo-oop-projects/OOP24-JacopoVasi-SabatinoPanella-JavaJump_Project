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
import static it.unibo.javajump.utility.Constants.MAINVIEWAUDIOFADE;
import static it.unibo.javajump.utility.Constants.MAINVIEWRECTX;
import static it.unibo.javajump.utility.Constants.MAINVIEWRECTY;
import static it.unibo.javajump.utility.Constants.MUSIC_VOLUME;
import static it.unibo.javajump.utility.Constants.RESOURCES_MUSIC_1;
import static it.unibo.javajump.utility.Constants.RESOURCES_PATH;
import static it.unibo.javajump.utility.Constants.SOUND_EFFECTS_VOLUME;

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

    public MainGameViewImpl(GameModel model) {
        GameGraphics gameGraphics = new GameGraphicsImpl();
        this.model = model;
        this.musicManager = new MusicManagerImpl(RESOURCES_PATH + RESOURCES_MUSIC_1, MUSIC_VOLUME);
        SoundEffectsManager soundEffectsManager = new SoundEffectsManager(SOUND_EFFECTS_VOLUME);
        setDoubleBuffered(true);

        this.virtualWidth = model.getScreenWidth();
        this.virtualHeight = model.getScreenHeight();

        RenderManager rendererManager = new RendererManagerImpl(soundEffectsManager, gameGraphics);
        setBackground(Color.decode(BACKGROUND_DEFAULT_COLOR));

        this.menuView = new MenuView(gameGraphics);
        this.inGameView = new InGameView(rendererManager);
        this.pauseView = new PauseView(gameGraphics);
        this.gameOverView = new GameOverView(gameGraphics);

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
