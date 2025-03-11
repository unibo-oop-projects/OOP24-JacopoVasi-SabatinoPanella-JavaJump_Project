package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;

public class BouncePlatformImpl extends PlatformImpl implements BouncePlatform {
    private final float bounceFactor;

    public BouncePlatformImpl(final float x, final float y, final float width, final float height, final float bounceFactor) {
        super(x, y, width, height);
        this.bounceFactor = bounceFactor;
    }

    @Override
    public float getBounceFactor() {
        return bounceFactor;
    }

    @Override
    public void onCollision(final GameObject other) {
        super.onCollision(other);
    }

    @Override
    public void update(final float deltaTime) {

        super.update(deltaTime);
    }
}
