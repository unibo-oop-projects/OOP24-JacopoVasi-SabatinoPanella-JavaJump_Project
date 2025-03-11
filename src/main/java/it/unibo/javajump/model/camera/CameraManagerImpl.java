package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.score.ScoreManager;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Implementation of the CameraManager interface.
 */
public class CameraManagerImpl implements CameraManager {
    /**
     * Field used to store the current offset of the camera during gameplay.
     */
    private float currentOffset;
    /**
     * Field used to store the previous offset reached by the camera during gameplay.
     */
    private float previousOffset;
    /**
     * Field to inject the ScoreManager, used to update the score based on how much the camera has risen.
     */
    private final ScoreManager scoreManager;
    /**
     * Field to determine by how much should the points increase per pixel "scrolled".
     */
    private final float scoreFactor;

    /**
     * Constructor for the effective camera manager.
     *
     * @param scoreManager the current score manager
     * @param scoreFactor  a desired game design value to multiply the score, for better game feel
     */
    public CameraManagerImpl(ScoreManager scoreManager, float scoreFactor) {
        this.scoreManager = scoreManager;
        this.scoreFactor = scoreFactor;
        this.currentOffset = OFFSET_INIT;
        this.previousOffset = OFFSET_INIT;
    }

    /**
     * Updates the camera position and increments the score based on player
     * movement.
     *
     * @param model     the game model
     * @param deltaTime the time passed from the last update (in seconds)
     */
    @Override
    public void updateCamera(GameModel model, float deltaTime) {
        Character player = model.getPlayer();
        float screenHeight = model.getScreenHeight();
        float desiredOffset = getDesiredOffset(screenHeight, player);

        if (currentOffset < previousOffset) {
            float deltaOffset = previousOffset - currentOffset;
            int points = (int) (deltaOffset * scoreFactor);
            scoreManager.addPoints(points);
        }
        previousOffset = currentOffset;
        currentOffset = desiredOffset;
    }

    /**
     * Private method, calculates the desired offset based on the player's
     * position and the screen height. Uses a progressionScreenPoint to
     * determine the desired offset (based on the half of the screen height with
     * tolerance).
     *
     * @param screenHeight the height of the screen
     * @param player       the player character
     * @return the desired offset
     */
    private float getDesiredOffset(float screenHeight, Character player) {
        float progressionScreenPoint = screenHeight / HEIGHT_DIV - screenHeight * WIDTH_DIV;
        float desiredOffset = currentOffset;

        if (player.getY() < progressionScreenPoint - currentOffset) {
            desiredOffset = player.getY() - progressionScreenPoint;
        }

        if (desiredOffset > currentOffset) {
            desiredOffset = currentOffset;
        }
        return desiredOffset;
    }

    /**
     * Resets the camera offset to the initial value.
     */
    @Override
    public void resetCamera() {
        this.currentOffset = OFFSET_INIT;
        this.previousOffset = OFFSET_INIT;
    }

    /**
     * Returns the current camera offset.
     *
     * @return the current camera offset
     */
    @Override
    public float getCurrentOffset() {
        return currentOffset;
    }
}
