package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.objectstrategies.HorizontalOscillationMovement;
import it.unibo.javajump.model.entities.objectstrategies.MovementBehaviour;

import static it.unibo.javajump.utility.Constants.NULL_PLATFORM_VELOCITY;

public class MovingPlatformImpl extends PlatformImpl implements MovingPlatform {

    private final MovementBehaviour movementBehaviour;

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
