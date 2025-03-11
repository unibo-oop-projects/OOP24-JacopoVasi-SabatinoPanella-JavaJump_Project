package it.unibo.javajump.controller.input;

import it.unibo.javajump.utility.Constants;

import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Implementation of the InputManager interface, this class is used to manage the input of the user.
 */
public class InputManagerImpl implements InputManager {
	/**
	 * Flag to check if the user is pressing the left arrow key
	 */
	private boolean pressingLeft = false;

	/**
	 * Flag to check if the user is pressing the right arrow key
	 */
	private boolean pressingRight = false;

	/**
	 * Queue to store the actions of the user
	 */
	private final Queue<GameAction> actionQueue = new ConcurrentLinkedQueue<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT -> pressingLeft = true;
			case KeyEvent.VK_RIGHT -> pressingRight = true;
			case KeyEvent.VK_ENTER -> actionQueue.offer(GameAction.CONFIRM_SELECTION);
			case KeyEvent.VK_UP -> actionQueue.offer(GameAction.MOVE_MENU_UP);
			case KeyEvent.VK_DOWN -> actionQueue.offer(GameAction.MOVE_MENU_DOWN);
			case KeyEvent.VK_ESCAPE -> actionQueue.offer(GameAction.PAUSE_GAME);
            default -> {}
        }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT -> pressingLeft = false;
			case KeyEvent.VK_RIGHT -> pressingRight = false;
			default -> {}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHorizontalDirection() {
		if (pressingLeft && pressingRight) {
			return Constants.NULLDIRECTION;
		} else if (pressingLeft) {
			return Constants.LEFTDIRECTION;
		} else if (pressingRight) {
			return Constants.RIGHTDIRECTION;
		}
		return Constants.NULLDIRECTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Queue<GameAction> getActionQueue() {
		return actionQueue;
	}
}
