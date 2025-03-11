package it.unibo.javajump.model.physics;

import static it.unibo.javajump.utility.Constants.NULL_VELOCITY;

/**
 * The type Physics utils.
 */
public class PhysicsUtilsImpl implements PhysicsUtils {

    /**
     * Accelerate to right float.
     *
     * @param vx           the vx
     * @param deltaTime    the delta time
     * @param acceleration the acceleration
     * @param maxSpeed     the max speed
     * @return the float
     */
    public static float accelerateToRight(final float vx, final float deltaTime, final float acceleration, final float maxSpeed) {
        final float vxx = vx + acceleration * deltaTime;
        return Math.min(vxx, maxSpeed);
    }


    /**
     * Accelerate to left float.
     *
     * @param vx           the vx
     * @param deltaTime    the delta time
     * @param acceleration the acceleration
     * @param maxSpeed     the max speed
     * @return the float
     */
    public static float accelerateToLeft(final float vx, final float deltaTime, final float acceleration, final float maxSpeed) {
        final float vxx = vx - acceleration * deltaTime;
        return Math.max(vxx, -maxSpeed);
    }


    /**
     * Decelerate float.
     *
     * @param vxx          the vxx
     * @param deltaTime    the delta time
     * @param deceleration the deceleration
     * @return the float
     */
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
