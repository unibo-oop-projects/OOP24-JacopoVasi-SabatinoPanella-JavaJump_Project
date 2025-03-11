package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.Character;


/**
 * The type Physics manager.
 */
public final class PhysicsManagerImpl implements PhysicsManager {
    private final float acceleration;
    private final float maxSpeed;
    private final float deceleration;
    private final float gravity;


    /**
     * Instantiates a new Physics manager.
     *
     * @param gravity      the gravity
     * @param acceleration the acceleration
     * @param maxSpeed     the max speed
     * @param deceleration the deceleration
     */
    public PhysicsManagerImpl(final float gravity, final float acceleration, final float maxSpeed, final float deceleration) {
        this.gravity = gravity;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.deceleration = deceleration;
    }


    @Override
    public void updateCharacterMovement(final Character character, final float deltaTime, final MovementDirection direction) {
        float vx = character.getVelocityX();

        switch (direction) {
            case RIGHT:
                vx = PhysicsUtilsImpl.accelerateToRight(vx, deltaTime, acceleration, maxSpeed);
                character.setFacingRight(true);
                break;
            case LEFT:
                vx = PhysicsUtilsImpl.accelerateToLeft(vx, deltaTime, acceleration, maxSpeed);
                character.setFacingRight(false);
                break;
            case NONE:

            default: vx = PhysicsUtilsImpl.decelerate(vx, deltaTime, deceleration);
                break;

        }
        character.setVelocityX(vx);
        updateCharacterPosition(character, deltaTime);
    }


    private void updateCharacterPosition(final Character character, final float deltaTime) {
        character.setOldX(character.getX());
        character.setOldY(character.getY());

        final float newX = character.getX() + character.getVelocityX() * deltaTime;
        final float newY = character.getY() + character.getVelocityY() * deltaTime;
        final float newVy = character.getVelocityY() + gravity * deltaTime;

        character.setX(newX);
        character.setY(newY);
        character.setVelocityY(newVy);
    }
}
