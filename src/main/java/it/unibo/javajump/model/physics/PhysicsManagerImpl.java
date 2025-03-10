package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.CharacterImpl;


public class PhysicsManagerImpl implements PhysicsManager {
	private final float ACCELERATION;
	private final float MAX_SPEED;
	private final float DECELERATION;
	private final float GRAVITY;


	public PhysicsManagerImpl(float gravity, float acceleration, float maxSpeed, float deceleration) {
		this.GRAVITY = gravity;
		this.ACCELERATION = acceleration;
		this.MAX_SPEED = maxSpeed;
		this.DECELERATION = deceleration;
	}


	public void updateCharacterMovement(CharacterImpl characterImpl, float deltaTime, MovementDirection direction) {
		float vx = characterImpl.getVelocityX();

		switch (direction) {
			case RIGHT:
				vx = PhysicsUtilsImpl.accelerateToRight(vx, deltaTime, ACCELERATION, MAX_SPEED);
				characterImpl.setFacingRight(true);
				break;
			case LEFT:
				vx = PhysicsUtilsImpl.accelerateToLeft(vx, deltaTime, ACCELERATION, MAX_SPEED);
				characterImpl.setFacingRight(false);
				break;
			case NONE:
			default:
				vx = PhysicsUtilsImpl.decelerate(vx, deltaTime, DECELERATION);
				break;
		}
		characterImpl.setVelocityX(vx);
		updateCharacterPosition(characterImpl, deltaTime);
	}


	private void updateCharacterPosition(CharacterImpl characterImpl, float deltaTime) {
		characterImpl.setOldX(characterImpl.getX());
		characterImpl.setOldY(characterImpl.getY());

		float newX = characterImpl.getX() + characterImpl.getVelocityX() * deltaTime;
		float newY = characterImpl.getY() + characterImpl.getVelocityY() * deltaTime;
		float newVy = characterImpl.getVelocityY() + GRAVITY * deltaTime;

		characterImpl.setX(newX);
		characterImpl.setY(newY);
		characterImpl.setVelocityY(newVy);
	}
}
