package model.states.gameutilities;

import model.GameModel;
import model.physics.MovementDirection;
import model.entities.character.Character;
import model.states.GameOverState;

import static Utility.Constants.*;


public final class InGameUtilities {
	
	private InGameUtilities() {
		throw new AssertionError("This is a utility class, it should not be instantiated!");
	}

	
	public static MovementDirection convertIntToMovementDirection(int dir) {
		if (dir < NULLDIRECTION) {
			return MovementDirection.LEFT;
		} else if (dir > NULLDIRECTION) {
			return MovementDirection.RIGHT;
		} else {
			return MovementDirection.NONE;
		}
	}

	
	public static void applyPacManEffect(Character player, int screenWidth) {
		if (player.getX() + player.getWidth() < ZERO) {
			player.setX(screenWidth);
		} else if (player.getX() > screenWidth) {
			player.setX(-player.getWidth());
		}
	}

	
	public static void checkGameOver(GameModel model, Character player) {
		float offset = model.getCameraManager().getCurrentOffset();
		float drawY = player.getY() - offset;
		if (drawY > model.getScreenHeight()) {
			model.setState(new GameOverState());
		}
	}
}
