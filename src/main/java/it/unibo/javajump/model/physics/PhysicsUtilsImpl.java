package it.unibo.javajump.model.physics;

import static it.unibo.javajump.utility.Constants.NULLVELOCITY;

public class PhysicsUtilsImpl implements PhysicsUtils {

	public static float accelerateToRight(float vx, float deltaTime, float acceleration, float maxSpeed) {
		vx += acceleration * deltaTime;
		return Math.min(vx, maxSpeed);
	}


	public static float accelerateToLeft(float vx, float deltaTime, float acceleration, float maxSpeed) {
		vx -= acceleration * deltaTime;
		return Math.max(vx, -maxSpeed);
	}


	public static float decelerate(float vx, float deltaTime, float deceleration) {
		if (vx > NULLVELOCITY) {
			vx -= deceleration * deltaTime;
			if (vx < NULLVELOCITY) {
				vx = NULLVELOCITY;
			}
		} else if (vx < NULLVELOCITY) {
			vx += deceleration * deltaTime;
			if (vx > NULLVELOCITY) {
				vx = NULLVELOCITY;
			}
		}
		return vx;
	}
}
