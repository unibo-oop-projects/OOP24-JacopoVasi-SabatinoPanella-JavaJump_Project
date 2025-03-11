package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;

/**
 * The type Platform, implements Platform.
 */
public class PlatformImpl extends GameObjectImpl implements Platform {
    private boolean touched;

    /**
     * Instantiates a new Platform.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public PlatformImpl(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.touched = false;
    }

    @Override
    public final void triggerTouched() {
        touched = true;
    }

    @Override
    public final boolean consumeTouched() {
        if (touched) {
            touched = false;
            return true;
        }
        return false;
    }

    @Override
    public void update(final float deltaTime) {
    }

    @Override
    public void onCollision(final GameObject other) {
    }

}
