package model.entities.platforms;

import model.entities.GameObject;

public class Platform extends GameObject
{

	public Platform(float x, float y, float width, float height)
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
