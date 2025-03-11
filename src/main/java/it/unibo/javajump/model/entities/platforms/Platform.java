package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;

/**
 * The interface Platform.
 */
public interface Platform extends GameObject {

    /**
     * Triggers touched.
     */
    void triggerTouched();

    /**
     * Consume touched boolean.
     *
     * @return the boolean
     */
    boolean consumeTouched();
}
