package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.character.Character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Implementation of the PlayerRenderer interface, used for graphical rendering of the player (animated).
 */
public class PlayerRendererImpl implements PlayerRenderer {
    /**
     * The sprite sheet containing the player's animation frames.
     */
    private final BufferedImage playerSheet;
    /**
     * The width of the player's animation frames.
     */
    private final int frameWidth;
    /**
     * The height of the player's animation frames.
     */
    private final int frameHeight;
    /**
     * The duration of each animation frame.
     */
    private final float FRAME_DURATION;
    /**
     * Value to cycle in the desired frames.
     */
    private float animTimer;
    /**
     * Flag to memorize if the player has been on a Platform.
     */
    private boolean prevOnPlatform;

    /**
     * Constructor for the PlayerRendererImpl class.
     *
     * @param sheet          the sprite sheet containing the player's animation frames
     * @param frameWidth     the width of the player's animation frames
     * @param frameHeight    the height of the player's animation frames
     * @param FRAME_DURATION the duration of each animation frame
     */
    public PlayerRendererImpl(BufferedImage sheet, int frameWidth, int frameHeight, float FRAME_DURATION) {
        this.playerSheet = sheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.prevOnPlatform = false;
        this.animTimer = RENDER_PLAYER_ANIM_TIMER_INIT;
        this.FRAME_DURATION = FRAME_DURATION;
    }

    /**
     * {@inheritDoc}
     * The method checks the state of the Character (in model) and updates the animation timer accordingly.
     * The draw logic also uses the private methods to draw the correct frame and to flip the sheet.
     *
     * @param g2        the Graphics2D context
     * @param player    the Player to draw
     * @param offsetY   the vertical offset
     * @param deltaTime the time passed since the last frame (used for animation)
     */
    @Override
    public void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime) {
        if (player.isOnPlatform() != prevOnPlatform) {
            animTimer = RENDER_PLAYER_ANIM_TIMER_INIT;
            prevOnPlatform = player.isOnPlatform();
        } else {
            animTimer += deltaTime;
        }

        int sx = getAnimationFrame(player);
        BufferedImage frame = playerSheet.getSubimage(sx, RENDER_PLAYER_FRAME_GET_IMG_Y, frameWidth, frameHeight);

        float drawX = player.getX();
        float drawY = player.getY() - offsetY;

        flipSheet(g2, player, drawX, drawY, frame);
    }

    /**
     * Private method that returns the index of the frame to draw.
     * The animation frames are extracted from the sprite sheet.
     * The sheet contains a single row of frames,
     * with the first 2 frames for jumping and the last 2 for falling.
     * The animation logic for the character is:
     * if the character lands on a platform, the first 2 frames get drawn,
     * otherwise it draws the third frame and the fourth after, which will remain
     * until the character lands on another platform.
     *
     * @param player the player to check
     * @return the right frame to draw
     */
    private int getAnimationFrame(Character player) {
        int frameIndex;
        if (player.isOnPlatform()) {
            float cycle = FRAME_DURATION * PLAYER_ANIMATION_CYCLE_DURATION; //
            float t = animTimer % cycle;
            frameIndex = (t < FRAME_DURATION) ? PLAYER_LANDING_START_FRAME : PLAYER_LANDING_END_FRAME;
        } else {
            frameIndex = (animTimer < FRAME_DURATION) ? PLAYER_JUMP_START_FRAME : PLAYER_JUMP_END_FRAME;
        }
        return frameIndex * frameWidth;
    }

    /**
     * Private method that flips the sprite sheet horizontally if the player is facing left.
     *
     * @param g2     the Graphics2D context
     * @param player the player to check
     * @param drawX  the x position to draw
     * @param drawY  the y position to draw
     * @param frame  the frame to draw
     */
    private void flipSheet(Graphics2D g2, Character player, float drawX, float drawY, BufferedImage frame) {
        AffineTransform old = g2.getTransform();
        if (!player.isFacingRight()) {
            g2.translate(drawX + frameWidth, drawY);
            g2.scale(FLIP_MIN, FLIP_MAX);
            g2.drawImage(frame, RENDER_PLAYER_FRAME_X, RENDER_PLAYER_FRAME_Y, null);
        } else {
            g2.drawImage(frame, (int) drawX, (int) drawY, null);
        }
        g2.setTransform(old);
    }
}
