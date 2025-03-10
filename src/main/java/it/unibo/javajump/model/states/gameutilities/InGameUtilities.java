package it.unibo.javajump.model.states.gameutilities;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.physics.MovementDirection;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.states.GameOverState;

import static it.unibo.javajump.utility.Constants.*;

/**
 * utility class for the InGameState, specifies some useful static methods
 */
public final class InGameUtilities {
	/**
	 * Private constructor, to avoid instantiating this class
	 *
	 * @throws AssertionError if the constructor is called
	 */
	private InGameUtilities() {
		throw new AssertionError("This is a utility class, it should not be instantiated!");
	}

	/**
	 * Converts an integer (-1, 0, +1) in a MovementDirection.
	 *
	 * @param dir -1 = left, 0 = none, +1 = right
	 */
	public static MovementDirection convertIntToMovementDirection(int dir) {
		if (dir < NULLDIRECTION) {
			return MovementDirection.LEFT;
		} else if (dir > NULLDIRECTION) {
			return MovementDirection.RIGHT;
		} else {
			return MovementDirection.NONE;
		}
	}

	/**
	 * Applies the PacMan effect to the character, so that if the character
	 * trespasses the screen border, it will appear on the other side
	 *
	 * @param player      the character to apply the effect
	 * @param screenWidth the width of the screen
	 */
	public static void applyPacManEffect(Character player, int screenWidth) {
		if (player.getX() + player.getWidth() < ZERO) {
			player.setX(screenWidth);
		} else if (player.getX() > screenWidth) {
			player.setX(-player.getWidth());
		}
	}

	/**
	 * Verifies if the game has to be set to GameOverState, with the condition
	 * for losing being that the character goes below the bottom of the screen
	 *
	 * @param model  the game model, used to get the current offset and the
	 *               screen height
	 * @param player the character to check
	 */
	public static void checkGameOver(GameModel model, Character player) {
		float offset = model.getCameraManager().getCurrentOffset();
		float drawY = player.getY() - offset;
		if (drawY > model.getScreenHeight()) {
			model.setState(new GameOverState());
		}
	}
}
