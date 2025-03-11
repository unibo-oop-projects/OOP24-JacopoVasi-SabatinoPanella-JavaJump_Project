package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;


/**
 * The interface Game frame.
 */
public interface GameFrame {
    /**
     * Sets up.
     *
     * @param inputManager the input manager
     * @param height       the height
     * @param width        the width
     * @param view         the view
     */
    void setUp(InputManager inputManager, int height, int width, MainGameView view, String title);

    /**
     * Close game.
     */
    void closeGame();
}
