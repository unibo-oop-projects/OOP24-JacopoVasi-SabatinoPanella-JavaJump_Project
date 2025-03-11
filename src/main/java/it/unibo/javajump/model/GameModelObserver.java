package it.unibo.javajump.model;

/**
 * The interface Game model observer.
 */
public interface GameModelObserver {

    /**
     * On model update.
     *
     * @param model the model
     */
    void onModelUpdate(GameModel model);
}
