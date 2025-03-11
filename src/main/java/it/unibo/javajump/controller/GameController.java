package it.unibo.javajump.controller;

/**
 * Interface for the game controller, that manages the game loop,
 * updates the model and the view using the current delta time.
 */
public interface GameController {
    /**
     * Starts the game loop, in a thread.
     */
    void startGameLoop();

    /**
     * Stops the game loop thread.
     */
    void stopGameLoop();
}
