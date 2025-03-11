package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;

public interface Platform extends GameObject {

    void triggerTouched();

    boolean consumeTouched();
}
