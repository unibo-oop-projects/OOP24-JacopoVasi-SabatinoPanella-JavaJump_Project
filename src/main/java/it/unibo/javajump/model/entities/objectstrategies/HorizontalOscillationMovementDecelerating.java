package it.unibo.javajump.model.entities.objectstrategies;

import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.physics.PhysicsUtilsImpl;

import static it.unibo.javajump.utility.Constants.*;

public class HorizontalOscillationMovementDecelerating implements MovementBehaviour {

	private final float minX;
	private final float maxX;
	private final float baseSpeed;
	private final float acceleration;
	private final float deceleration;
	private float currentSpeed;
	private boolean goingRight;
	private final float threshold;
	private final float epsilon;

	public HorizontalOscillationMovementDecelerating(float minX, float maxX, float baseSpeed, float acceleration, float deceleration) {
		this.minX = minX;
		this.maxX = maxX;
		this.baseSpeed = baseSpeed;
		this.acceleration = acceleration;
		this.deceleration = deceleration;
		this.currentSpeed = baseSpeed;
		this.goingRight = true;
		this.threshold = THRESHOLD;
		this.epsilon = EPSILON;
	}

	@Override
	public void update(GameObjectImpl obj, float deltaTime) {
		float currentX = obj.getX();
		float objWidth = obj.getWidth();

		if (goingRight) {

			float distanceToRight = maxX - (currentX + objWidth);
			if (distanceToRight < threshold) {

				currentSpeed = PhysicsUtilsImpl.decelerate(currentSpeed, deltaTime, deceleration);

				if (currentSpeed < epsilon) {
					currentSpeed = NULLDIRECTION;

					obj.setX(maxX - objWidth);
					goingRight = false;
				}
			} else {

				currentSpeed = PhysicsUtilsImpl.accelerateToRight(currentSpeed, deltaTime, acceleration, baseSpeed);
			}
		} else {
			float distanceToLeft = currentX - minX;
			if (distanceToLeft < threshold) {
				currentSpeed = PhysicsUtilsImpl.decelerate(currentSpeed, deltaTime, deceleration);
				if (Math.abs(currentSpeed) < epsilon) {
					currentSpeed = NULLDIRECTION;
					obj.setX(minX);
					goingRight = true;
				}
			} else {
				currentSpeed = PhysicsUtilsImpl.accelerateToLeft(currentSpeed, deltaTime, acceleration, baseSpeed);
			}
		}


		obj.setX(currentX + currentSpeed * deltaTime);
	}
}
