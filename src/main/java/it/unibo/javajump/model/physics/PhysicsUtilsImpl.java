package it.unibo.javajump.model.physics;

import static it.unibo.javajump.utility.Constants.NULL_VELOCITY;

public class PhysicsUtilsImpl implements PhysicsUtils {

    public static float accelerateToRight(final float vx, final float deltaTime, final float acceleration, final float maxSpeed) {
        final float new_vx = vx + acceleration * deltaTime;
        return Math.min(new_vx, maxSpeed);
    }


    public static float accelerateToLeft(final float vx, final float deltaTime, final float acceleration, final float maxSpeed) {
        final float new_vx = vx - acceleration * deltaTime;
        return Math.max(new_vx, -maxSpeed);
    }


    public static float decelerate(final float vxx, final float deltaTime, final float deceleration) {
        float vx = vxx;
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
