package model.physics;

public class PhysicsUtils {
	
	public static float accelerateToRight(float vx, float deltaTime, float acceleration, float maxSpeed) {
		vx += acceleration * deltaTime;
		return Math.min(vx, maxSpeed);
	}

	
	public static float accelerateToLeft(float vx, float deltaTime, float acceleration, float maxSpeed) {
		vx -= acceleration * deltaTime;
		return Math.max(vx, -maxSpeed);
	}

	
	public static float decelerate(float vx, float deltaTime, float deceleration) {
		if (vx > 0) {
			vx -= deceleration * deltaTime;
			if (vx < 0) {
				vx = 0;
			}
		} else if (vx < 0) {
			vx += deceleration * deltaTime;
			if (vx > 0) {
				vx = 0;
			}
		}
		return vx;
	}
}
