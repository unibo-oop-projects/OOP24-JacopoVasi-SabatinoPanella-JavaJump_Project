package model.entities;

import model.GameModel;

public abstract class GameObject
{
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	
	public abstract void update(float deltaTime);


	public abstract void onCollision(GameObject other);


	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}
}
