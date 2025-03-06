package model.entities.character;

import model.entities.GameObject;

public class Character extends GameObject
{
	private float velocityX;
	private float velocityY;
	private float jumpForce;

	private static final float GRAVITY = 1350.0f;

	private boolean onPlatform;
	private boolean facingRight;

	private float oldX;
	private float oldY;

	public Character(float x, float y, float width, float height, float jumpForce)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.jumpForce = jumpForce;
		this.velocityX = 0;
		this.velocityY = 0;
		this.oldX = x;
		this.oldY = y;

		this.onPlatform = false;
	}

	@Override
	public void update(float deltaTime)
	{
		oldX = x;
		oldY = y;

		this.x += velocityX * deltaTime;

		if (velocityX > 0) {
			facingRight = true;
		} else if (velocityX < 0) {
			facingRight = false;
		}

		this.y += velocityY * deltaTime;
		this.velocityY += GRAVITY * deltaTime;
	}


	@Override
	public void onCollision(GameObject other) {


	}

	
	public void landOnPlatform() {
		this.onPlatform = true;
	}

	
	public void goInAir() {
		if (this.onPlatform) {
			this.onPlatform = false;
		}
	}


	public float getVelocityX()
	{
		return velocityX;
	}

	public void setVelocityX(float velocityX)
	{
		this.velocityX = velocityX;
	}

	public float getVelocityY()
	{
		return velocityY;
	}

	public void setVelocityY(float velocityY)
	{
		this.velocityY = velocityY;
	}

	public float getJumpForce()
	{
		return jumpForce;
	}

	public void setJumpForce(float jumpForce)
	{
		this.jumpForce = jumpForce;
	}

	public float getOldX()
	{
		return oldX;
	}

	public float getOldY()
	{
		return oldY;
	}

	public boolean isOnPlatform() {
		return onPlatform;
	}

	public boolean isFacingRight()
	{
		return facingRight;
	}
}
