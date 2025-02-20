package model.entities;

import model.GameModel;

public class Character extends GameObject
{
	private float velocityX;
	private float velocityY;
	private float jumpForce;

	private static final float GRAVITY = 300.0f;

	public Character(float x, float y, float width, float height, float jumpForce)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.jumpForce = jumpForce;

		this.velocityX = 0;
		this.velocityY = 0;
	}

	@Override
	public void update(float deltaTime)
	{

		this.x += velocityX * deltaTime;


		this.y += velocityY * deltaTime;


		this.velocityY += GRAVITY * deltaTime;










	}

	@Override
	public void onCollision(GameObject other)
	{

		if (other instanceof Platform)
		{


			this.velocityY = -jumpForce;
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

	@Override
	public float getY()
	{
		return super.getY();
	}
}
