package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;

public class BreakablePlatformImpl extends PlatformImpl implements BreakablePlatform {

    private boolean broken;
    private boolean finished;

    public BreakablePlatformImpl(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.broken = false;
        this.finished = false;
    }

    @Override
    public void onCollision(GameObject other) {
        super.onCollision(other);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean isBroken() {
        return broken;
    }

    @Override
    public void breakPlatform() {
        if (!broken) {
            broken = true;
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished() {
        if (!finished) {
            finished = true;
        }
    }
}
