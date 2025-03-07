package model.physics;

import model.entities.character.Character;


public class PhysicsManager {
	private final float ACCELERATION;
	private final float MAX_SPEED;
	private final float DECELERATION;
	private final float GRAVITY;

	
	public PhysicsManager(float gravity,float acceleration, float maxSpeed, float deceleration) {
		this.GRAVITY = gravity;
		this.ACCELERATION = acceleration;
		this.MAX_SPEED = maxSpeed;
		this.DECELERATION = deceleration;
	}

	
	public void updateCharacterMovement(Character character, float deltaTime, MovementDirection direction) {
		float vx = character.getVelocityX();

		switch (direction) {
			case RIGHT:
				vx = PhysicsUtils.accelerateToRight(vx, deltaTime, ACCELERATION, MAX_SPEED);
				character.setFacingRight(true);
				break;
			case LEFT:
				vx = PhysicsUtils.accelerateToLeft(vx, deltaTime, ACCELERATION, MAX_SPEED);
				character.setFacingRight(false);
				break;
			case NONE:
			default:
				vx = PhysicsUtils.decelerate(vx, deltaTime, DECELERATION);
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
