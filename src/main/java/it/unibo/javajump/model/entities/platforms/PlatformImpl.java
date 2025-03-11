package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;

public class PlatformImpl extends GameObjectImpl implements Platform {
    private boolean touched = false;

    public PlatformImpl(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
