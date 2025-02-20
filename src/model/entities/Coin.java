package model.entities;

public class Coin extends GameObject
{
	public Coin(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void update(float deltaTime)
	{

	}

	@Override
	public void onCollision(GameObject other)
	{


	}
}