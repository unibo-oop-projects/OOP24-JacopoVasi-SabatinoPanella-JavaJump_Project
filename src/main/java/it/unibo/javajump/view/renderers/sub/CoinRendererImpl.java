package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.view.sound.sfx.SFXType;
import it.unibo.javajump.view.sound.sfx.SoundEffectsManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Class that implements the CoinRenderer interface, used for graphical rendering of the coins (animated).
 */
public class CoinRendererImpl implements CoinRenderer {
    /**
     * The coin sprite sheet, contains all the animation frames.
     */
    private final BufferedImage coinSheet;
    /**
     * The width of each frame.
     */
    private final int frameWidth;
    /**
     * The height of each frame.
     */
    private final int frameHeight;
    /**
     * The standard duration of a frame of animation.
     */
    private final float frameDuration;
    /**
     * A map that stores the animation timers for each coin.
     */
    private final Map<Coin, Float> coinAnimTimers = new HashMap<>();

    /**
     * A map that stores the last state of each coin.
     */
    private final Map<Coin, CoinState> coinLastStates = new HashMap<>();


    private final SoundEffectsManager soundEffectsManager;

    /**
     * Constructor for the CoinRendererImpl class.
     *
     * @param sheet         the coin sprite sheet
     * @param frameWidth    the width of each frame
     * @param frameHeight   the height of each frame
     * @param frameDuration the standard duration of a frame of animation
     */
    public CoinRendererImpl(BufferedImage sheet, int frameWidth, int frameHeight, float frameDuration, SoundEffectsManager soundEffectsManager) {
        this.coinSheet = sheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameDuration = frameDuration;
        this.soundEffectsManager = soundEffectsManager;
    }

    /**
     * {@inheritDoc}
     * The implementation checks the state of the Coin (in model) and updates the animation timer
     * accordingly.
     * The animation frames are then extracted from the sprite sheet and drawn on the screen.
     * The sprite sheet contains two rows of frames,
     * one for idle animation and one for collected animation.
     * This method also handles the removal of coins when they are collected,
     * by updating the coinLastStates and coinAnimTimers maps,
     * all while setting the coin's state to FINISHED.
     */
    @Override
    public void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime) {
        if (coin.getState() == CoinState.FINISHED) {
            return;
        }

        CoinState prevState = coinLastStates.get(coin);
        if (prevState == null || !prevState.equals(coin.getState())) {
            coinAnimTimers.put(coin, COIN_ANIM_TIMER_START);
            coinLastStates.put(coin, coin.getState());

            if (coin.getState() == CoinState.COLLECTING) {
                soundEffectsManager.playSound(SFXType.COIN);
            }
        }

        float timer = coinAnimTimers.get(coin);
        timer += deltaTime;
        coinAnimTimers.put(coin, timer);

        int frameIndex;
        int row;
        if (coin.getState() == CoinState.IDLE) {
            float cycle = frameDuration * COIN_ANIMATION_CYCLE_DURATION;
            float t = timer % cycle;
            frameIndex = (int) (t / frameDuration);
            row = RENDER_COIN_IDLE_ROW;
        } else {
            int idx = (int) (timer / frameDuration);
            if (idx >= COIN_ANIMATION_INDEX_MAX) {
                frameIndex = COIN_ANIMATION_CYCLE_DURATION;
                coin.markAsDone();
                removeCoin(coin);
            } else {
                frameIndex = idx;
            }
            row = RENDER_COIN_COLLECT_ROW;
        }

        int sx = frameIndex * frameWidth;
        int sy = row * frameHeight;
        BufferedImage frame = coinSheet.getSubimage(sx, sy, frameWidth, frameHeight);

        float drawX = coin.getX();
        float drawY = coin.getY() - offsetY;
        g2.drawImage(frame, (int) drawX, (int) drawY, null);
    }

    /**
     * Removes the data related to a coin in a Map, for example, when the
     * Coin(GameObject) is removed.
     *
     * @param coin the Coin to remove
     */
    private void removeCoin(Coin coin) {
        coinAnimTimers.remove(coin);
        coinLastStates.remove(coin);
    }
}
