package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.Character;


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


	@Override
	public void updateCharacterMovement(Character character, float deltaTime, MovementDirection direction) {
		float vx = character.getVelocityX();

		switch (direction) {
			case RIGHT:
				vx = PhysicsUtilsImpl.accelerateToRight(vx, deltaTime, ACCELERATION, MAX_SPEED);
				character.setFacingRight(true);
				break;
			case LEFT:
				vx = PhysicsUtilsImpl.accelerateToLeft(vx, deltaTime, ACCELERATION, MAX_SPEED);
				character.setFacingRight(false);
				break;
			case NONE:
			default:
				vx = PhysicsUtilsImpl.decelerate(vx, deltaTime, DECELERATION);
				break;
		}
		character.setVelocityX(vx);
		updateCharacterPosition(character, deltaTime);
	}


	private void updateCharacterPosition(Character character, float deltaTime) {
		character.setOldX(character.getX());
		character.setOldY(character.getY());

		float newX = character.getX() + character.getVelocityX() * deltaTime;
		float newY = character.getY() + character.getVelocityY() * deltaTime;
		float newVy = character.getVelocityY() + GRAVITY * deltaTime;

		character.setX(newX);
		character.setY(newY);
		character.setVelocityY(newVy);
	}
}
