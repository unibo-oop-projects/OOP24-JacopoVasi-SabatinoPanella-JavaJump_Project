package model.entities.platforms;

import model.entities.objectstrategies.HorizontalOscillationMovement;
import model.entities.objectstrategies.HorizontalOscillationMovementDecelerating;
import model.entities.objectstrategies.MovementBehaviour;

public class MovingPlatform extends Platform {

	private final MovementBehaviour movementBehaviour;

	public MovingPlatform(float x, float y, float width, float height,
						  float range, float screenWidth, float speed, float acceleration, float deceleration) {
		super(x, y, width, height);

		if (x < 0) x = 0;
		if (x > screenWidth - width) x = screenWidth - width;
		this.x = x;


		float potentialMin = x - range;
		float potentialMax = x + range;
		if (potentialMin < 0) potentialMin = 0;
		if (potentialMax > screenWidth - width) potentialMax = screenWidth - width;

		this.movementBehaviour = new HorizontalOscillationMovement(potentialMin, potentialMax, speed);
	}

	private float clampXinScreen(float x, float width, float screenWidth) {
		if (x < 0) x = 0;
		if (x > screenWidth - width) x = screenWidth - width;
		return x;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		movementBehaviour.update(this, deltaTime);
	}
}
