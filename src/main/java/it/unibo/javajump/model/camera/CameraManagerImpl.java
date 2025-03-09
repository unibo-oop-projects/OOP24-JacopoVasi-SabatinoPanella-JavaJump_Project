package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.score.ScoreManager;

import static it.unibo.javajump.utility.Constants.*;


public class CameraManagerImpl implements CameraManager {

	private float currentOffset;
	private float previousOffset;
	private final ScoreManager scoreManager;
	private final float scoreFactor;

	public CameraManagerImpl(ScoreManager scoreManager, float scoreFactor) {
		this.scoreManager = scoreManager;
		this.scoreFactor = scoreFactor;
		this.currentOffset = OFFSETINIT;
		this.previousOffset = OFFSETINIT;
	}

	@Override
	public void updateCamera(GameModel model, float deltaTime) {
		Character player = model.getPlayer();
		float screenHeight = model.getScreenHeight();
		float desiredOffset = getDesiredOffset(screenHeight, player);

		if (currentOffset < previousOffset) {
			float deltaOffset = previousOffset - currentOffset;
			int points = (int) (deltaOffset * scoreFactor);
			scoreManager.addPoints(points);
		}
		previousOffset = currentOffset;
		currentOffset = desiredOffset;
	}

	private float getDesiredOffset(float screenHeight, Character player) {
		float progressionScreenPoint = screenHeight / HEIGHTDIV - screenHeight * WIDTHDIV;
		float desiredOffset = currentOffset;

		if (player.getY() < progressionScreenPoint - currentOffset) {
			desiredOffset = player.getY() - progressionScreenPoint;
		}

		if (desiredOffset > currentOffset) {
			desiredOffset = currentOffset;
		}
		return desiredOffset;
	}

	@Override
	public void resetCamera() {
		this.currentOffset = OFFSETINIT;
		this.previousOffset = OFFSETINIT;
	}

	@Override
	public float getCurrentOffset() {
		return currentOffset;
	}
}
