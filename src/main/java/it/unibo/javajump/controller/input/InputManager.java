package it.unibo.javajump.controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;

/**
 * Interface for the input manager, that will manage the input of the user and pass it to the game model.
 */
public interface InputManager extends KeyListener {
    /**
     * {@inheritDoc}
     */
    void keyPressed(KeyEvent e);

    /**
     * {@inheritDoc}
     */
    void keyReleased(KeyEvent e);

    /**
     * {@inheritDoc}
     */
    void keyTyped(KeyEvent e);

    /**
     * Getter for the queue of actions to be processed.
     *
     * @return the queue of actions to be processed
     */
    Queue<GameAction> getActionQueue();

    /**
     * Getter for the horizontal direction of the player.
     *
     * @return the current horizontal direction of the player based on the key pressed
     */
    int getHorizontalDirection();
}
