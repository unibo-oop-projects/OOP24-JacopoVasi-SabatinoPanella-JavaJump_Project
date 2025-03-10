package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.score.ScoreManagerImpl;

import static it.unibo.javajump.utility.Constants.*;


public class CameraManagerImpl implements CameraManager {

	private float currentOffset;
	private float previousOffset;
	private final ScoreManagerImpl scoreManager;
	private final float scoreFactor;

	public CameraManagerImpl(ScoreManagerImpl scoreManager, float scoreFactor) {
		this.scoreManager = scoreManager;
		this.scoreFactor = scoreFactor;
		this.currentOffset = OFFSETINIT;
		this.previousOffset = OFFSETINIT;
	}

	@Override
	public void updateCamera(GameModelImpl model, float deltaTime) {
		CharacterImpl player = model.getPlayer();
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

	private float getDesiredOffset(float screenHeight, CharacterImpl player) {
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
