package model.states.gameutilities;

import model.GameModel;
import model.physics.MovementDirection;
import model.entities.character.Character;
import model.states.GameOverState;

public class InGameUtilities {
	
	public static MovementDirection convertIntToMovementDirection(int dir) {
		if (dir < 0) {
			return MovementDirection.LEFT;
		} else if (dir > 0) {
			return MovementDirection.RIGHT;
		} else {
			return MovementDirection.NONE;
		}
	}

	
	public static void applyPacManEffect(Character player, int screenWidth) {
		if (player.getX() + player.getWidth() < 0) {
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
