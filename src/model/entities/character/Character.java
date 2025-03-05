package model.entities.character;

import model.entities.GameObject;

public class Character extends GameObject
{
	private float velocityX;
	private float velocityY;
	private float jumpForce;

	private static final float GRAVITY = 1350.0f;

	private boolean onPlatform;
	private int frameIndex;
	private float animTime;
	private static final float FRAME_DURATION = 0.2f;

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
		this.frameIndex = 0;
		this.animTime = 0;
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
		updateAnimation(deltaTime);
	}

	private void updateAnimation(float deltaTime)
	{
		animTime += deltaTime;

		if (onPlatform) {
			float cycle = FRAME_DURATION * 2;
			float t = animTime % cycle;
			if (t < FRAME_DURATION) {
				frameIndex = 0;
			} else {
				frameIndex = 1;
			}
		} else {
			if (animTime < FRAME_DURATION) {
				frameIndex = 2;
			} else {
				frameIndex = 3;
			}
		}
	}

	@Override
	public void onCollision(GameObject other)
	{

	}


	public void landOnPlatform() {
		this.onPlatform = true;
		this.animTime = 0;
	}


	public void goInAir() {
		if (this.onPlatform) {
			this.onPlatform = false;
			this.animTime = 0;
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

	public boolean isOnPlatform()
	{
		return onPlatform;
	}

	public int getFrameIndex()
	{
		return frameIndex;
	}

	public boolean isFacingRight()
	{
		return facingRight;
	}
}
