package it.unibo.javajump.model.entities.platforms;

/**
 * The interface Breakable platform.
 */
public interface BreakablePlatform extends Platform {

    /**
     * Is broken boolean.
     *
     * @return true if is broken, false otherwise
     */
    boolean isBroken();

    /**
     * Breaks the platform.
     */
    void breakPlatform();

    /**
     * Sets finished.
     */
    void setFinished();

    /**
     * Is finished boolean.
     *
     * @return true if is finished, false otherwise
     */
    boolean isFinished();
}
