package model.entities.platforms;

import model.entities.objectstrategies.HorizontalOscillationMovement;
import model.entities.objectstrategies.MovementBehaviour;

import static utility.Constants.*;

public class MovingPlatform extends Platform {

	private final MovementBehaviour movementBehaviour;

	public MovingPlatform(float x, float y, float width, float height,
						  float range, float screenWidth, float speed, float acceleration, float deceleration) {
		super(x, y, width, height);

		if (x < ZERO) {
			x = ZERO;
		}
		if (x > screenWidth - width) {
			x = screenWidth - width;
		}
		this.x = x;


		float potentialMin = x - range;
		float potentialMax = x + range;
		if (potentialMin < ZERO) {
			potentialMin = ZERO;
		}
		if (potentialMax > screenWidth - width) {
			potentialMax = screenWidth - width;
		}


		this.movementBehaviour = new HorizontalOscillationMovement(potentialMin, potentialMax, speed);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		movementBehaviour.update(this, deltaTime);
	}
}
