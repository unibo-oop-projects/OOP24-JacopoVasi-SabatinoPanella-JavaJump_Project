package it.unibo.javajump.model.physics;

import static it.unibo.javajump.utility.Constants.NULL_VELOCITY;

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
        if (vx > NULL_VELOCITY) {
            vx -= deceleration * deltaTime;
            if (vx < NULL_VELOCITY) {
                vx = NULL_VELOCITY;
            }
        } else if (vx < NULL_VELOCITY) {
            vx += deceleration * deltaTime;
            if (vx > NULL_VELOCITY) {
                vx = NULL_VELOCITY;
            }
        }
        return vx;
    }
}
