package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.controller.input.InputManagerImpl;
import it.unibo.javajump.utility.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class InputTest {

    private InputManagerImpl inputManager;
    private JTextField testComponent; // A dummy component for KeyEvent source

    @BeforeEach
    void setUp() {
        inputManager = new InputManagerImpl();
        testComponent = new JTextField(); // Using a JTextField as a valid event source
    }

    @Test
    void testPressingLeftKeySetsPressingLeftFlag() {
        KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        inputManager.keyPressed(event);

        assertEquals(Constants.LEFT_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testPressingRightKeySetsPressingRightFlag() {
        KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        inputManager.keyPressed(event);

        assertEquals(Constants.RIGHT_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testReleasingLeftKeyResetsPressingLeftFlag() {
        KeyEvent pressEvent = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        KeyEvent releaseEvent = new KeyEvent(testComponent, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');

        inputManager.keyPressed(pressEvent);
        inputManager.keyReleased(releaseEvent);

        assertEquals(Constants.NULL_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testSimultaneousLeftAndRightKeyPressResultsInNullDirection() {
        KeyEvent leftEvent = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        KeyEvent rightEvent = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');

        inputManager.keyPressed(leftEvent);
        inputManager.keyPressed(rightEvent);

        assertEquals(Constants.NULL_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testActionQueueWhenEnterKeyIsPressed() {
        KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, ' ');
        inputManager.keyPressed(event);

        Queue<GameAction> actionQueue = inputManager.getActionQueue();
        assertFalse(actionQueue.isEmpty());
        assertEquals(GameAction.CONFIRM_SELECTION, actionQueue.poll());
    }

    @Test
    void testActionQueueWhenUpKeyIsPressed() {
        KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' ');
        inputManager.keyPressed(event);

        Queue<GameAction> actionQueue = inputManager.getActionQueue();
        assertFalse(actionQueue.isEmpty());
        assertEquals(GameAction.MOVE_MENU_UP, actionQueue.poll());
    }

    @Test
    void testActionQueueWhenDownKeyIsPressed() {
        KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');
        inputManager.keyPressed(event);

        Queue<GameAction> actionQueue = inputManager.getActionQueue();
        assertFalse(actionQueue.isEmpty());
        assertEquals(GameAction.MOVE_MENU_DOWN, actionQueue.poll());
    }

    @Test
    void testActionQueueWhenEscapeKeyIsPressed() {

        KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, ' ');
        inputManager.keyPressed(event);

        Queue<GameAction> actionQueue = inputManager.getActionQueue();
        assertFalse(actionQueue.isEmpty());
        assertEquals(GameAction.PAUSE_GAME, actionQueue.poll());
    }
}