package model.entities;

public class Character extends GameObject
{

	private float velocityY;
	private float jumpForce;
	private float moveSpeed;

	public Character(float x, float y, float width, float height, float jumpForce, float moveSpeed)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.jumpForce = jumpForce;
		this.moveSpeed = moveSpeed;
		this.velocityY = 0;
	}

	@Override
	public void update(float deltaTime)
	{

		y += velocityY * deltaTime;

		velocityY += 300 * deltaTime;
	}

	@Override
	public void onCollision(GameObject other)
	{

		if (other instanceof Platform)
		{
			velocityY = -jumpForce;
		}
		if (other instanceof Coin)
		{




		}
	}


	public void moveLeft(float deltaTime)
	{
		this.x -= moveSpeed * deltaTime;
	}

	public void moveRight(float deltaTime)
	{
		this.x += moveSpeed * deltaTime;
	}
}
