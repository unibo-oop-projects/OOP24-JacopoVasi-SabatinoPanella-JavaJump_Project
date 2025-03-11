package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.objectstrategies.HorizontalOscillationMovement;
import it.unibo.javajump.model.entities.objectstrategies.MovementBehaviour;

import static it.unibo.javajump.utility.Constants.NULL_PLATFORM_VELOCITY;

/**
 * The MovingPlatformImpl class, an implementation of a moving platform.
 */
public class MovingPlatformImpl extends PlatformImpl implements MovingPlatform {

    private final MovementBehaviour movementBehaviour;

    /**
     * Instantiates a new Moving platform.
     *
     * @param xx          the xx
     * @param y           the y
     * @param width       the width
     * @param height      the height
     * @param range       the range
     * @param screenWidth the screen width
     * @param speed       the speed
     */
    public MovingPlatformImpl(final float xx, final float y, final float width, final float height,
                              final float range, final float screenWidth, final float speed) {

        super(xx, y, width, height);
        float x = xx;
        if (x < NULL_PLATFORM_VELOCITY) {
            x = NULL_PLATFORM_VELOCITY;
        }
        if (x > screenWidth - width) {
            x = screenWidth - width;
        }
        this.x = x;

        float potentialMin = x - range;
        float potentialMax = x + range;
        if (potentialMin < NULL_PLATFORM_VELOCITY) {
            potentialMin = NULL_PLATFORM_VELOCITY;
        }
        if (potentialMax > screenWidth - width) {
            potentialMax = screenWidth - width;
        }

        this.movementBehaviour = new HorizontalOscillationMovement(potentialMin, potentialMax, speed);
    }

    @Override
    public void update(final float deltaTime) {
        super.update(deltaTime);

        movementBehaviour.update(this, deltaTime);
    }
}
