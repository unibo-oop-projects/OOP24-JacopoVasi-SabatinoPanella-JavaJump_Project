package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;

public class PlatformImpl extends GameObjectImpl implements Platform {
    private boolean touched ;

    public PlatformImpl(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.touched = false;
    }

    @Override
    public void triggerTouched() {
        touched = true;
        System.out.println("Touched");
    }

    @Override
    public boolean consumeTouched() {
        if (touched) {
            touched = false;
            return true;
        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void onCollision(GameObject other) {
    }

}
