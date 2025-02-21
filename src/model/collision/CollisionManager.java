package model.collision;

import model.entities.GameObject;
import model.entities.Character;
import model.entities.Coin;
import model.GameModel;

import java.util.List;


public class CollisionManager
{
	
	public void checkCollisions(GameModel model)
	{
		List<GameObject> objects = model.getGameObjects();
for (int i = 0; i < objects.size(); i++)
		{
			GameObject a = objects.get(i);
			for (int j = i + 1; j < objects.size(); j++)
			{
				GameObject b = objects.get(j);		if (isColliding(a, b))
				{			a.onCollision(b);
					b.onCollision(a);
			if (a instanceof Character && b instanceof Coin)
					{
						objects.remove(b);
				break;
					}
					else if (b instanceof Character && a instanceof Coin)
					{
						objects.remove(a);				break;
					}
				}
			}
		}
	}

	
	private boolean isColliding(GameObject a, GameObject b) {
		return a.getX() < b.getX() + b.getWidth()
				&& a.getX() + a.getWidth() > b.getX()
				&& a.getY() < b.getY() + b.getHeight()
				&& a.getY() + a.getHeight() > b.getY();
	}
}