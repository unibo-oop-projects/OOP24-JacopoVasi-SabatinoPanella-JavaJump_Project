package model.physics;

import model.entities.Character;public class PhysicsManager
{
	private final float ACCELERATION;
	private final float MAX_SPEED;
	private final float DECELERATION;	public PhysicsManager(float acceleration, float maxSpeed, float deceleration)
	{
		this.ACCELERATION = acceleration;
		this.MAX_SPEED = maxSpeed;
		this.DECELERATION = deceleration;
	}	public void updateCharacterMovement(Character character, float deltaTime, MovementDirection direction)
	{
		final float ACCELERATION = 500f;
		final float MAX_SPEED = 250f;
		final float DECELERATION = 400f;

		float vx = character.getVelocityX();

		switch (direction)
		{
			case RIGHT:
				vx = accelerateToRight(vx, deltaTime);
				break;
			case LEFT:
				vx = accelerateToLeft(vx, deltaTime);
				break;
			case NONE:
			default:
				vx = decelerate(vx, deltaTime);
				break;
		}

		character.setVelocityX(vx);
	}	private float accelerateToRight(float vx, float deltaTime)
	{
		vx += ACCELERATION * deltaTime;
		return Math.min(vx, MAX_SPEED);
	}	private float accelerateToLeft(float vx, float deltaTime)
	{
		vx -= ACCELERATION * deltaTime;
		return Math.max(vx, -MAX_SPEED);
	}

	
	private float decelerate(float vx, float deltaTime)
	{
		if (vx > 0)
		{
			vx -= DECELERATION * deltaTime;
			if (vx < 0)
			{
				vx = 0;
			}
		}
		else if (vx < 0)
		{
			vx += DECELERATION * deltaTime;
			if (vx > 0)
			{
				vx = 0;
			}
		}
		return vx;
	}
}
