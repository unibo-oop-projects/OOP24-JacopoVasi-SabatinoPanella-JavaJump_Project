package it.unibo.javajump.model.entities.collectibles;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.platforms.Platform;

import static it.unibo.javajump.utility.Constants.OFFSET_INIT;

public class CoinImpl extends GameObjectImpl implements Coin {

    private CoinState state;
    private Platform attachedPlatform;
    private float offsetX;

    public CoinImpl(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = CoinState.IDLE;
        this.attachedPlatform = null;
        this.offsetX = OFFSET_INIT;
    }

    /**
     * {@inheritDoc}
     * In this case, if the Coin is attached to a platform, the coin moves with the platform during gameplay.
     */
    @Override
    public void update(float deltaTime) {
        if (attachedPlatform != null) {
            this.x = attachedPlatform.getX() + offsetX;
        }
    }

    @Override
    public void onCollision(GameObject other) {
    }

    @Override
    public CoinState getState() {
        return this.state;
    }

    @Override
    public void collect() {
        if (this.state == CoinState.IDLE) {
            this.state = CoinState.COLLECTING;
        }
    }

    @Override
    public void markAsDone() {
        this.state = CoinState.FINISHED;
    }

    @Override
    public Platform getAttachedPlatform() {
        return attachedPlatform;
    }

    @Override
    public void attachToPlatform(final Platform platform) {
        this.attachedPlatform = platform;
        this.offsetX = this.x - platform.getX();
    }
}
