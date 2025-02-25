package model.collision;

import model.entities.GameObject;
import model.entities.Character;
import model.entities.Coin;
import model.GameModel;
import model.entities.Platform;

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
				GameObject b = objects.get(j);

				if (isColliding(a, b))
				{
					a.onCollision(b);
					b.onCollision(a);
					if (a instanceof Character && b instanceof Coin)
					{
						handleCharacterCoinCollision((Character) a, (Coin) b, model);
						break;
					}
					else if (b instanceof Character && a instanceof Coin)
					{
						handleCharacterCoinCollision((Character) b, (Coin) a, model);
						break;
					}
					if (a instanceof Character && b instanceof Platform)
					{
						handleCharacterPlatformCollision((Character) a, (Platform) b, model);
					}
					else if (b instanceof Character && a instanceof Platform)
					{
						handleCharacterPlatformCollision((Character) b, (Platform) a, model);
					}
				}
			}
		}
	}

	
	private boolean isColliding(GameObject a, GameObject b)
	{
		return a.getX() < b.getX() + b.getWidth()
				&& a.getX() + a.getWidth() > b.getX()
				&& a.getY() < b.getY() + b.getHeight()
				&& a.getY() + a.getHeight() > b.getY();
	}

	private void handleCharacterCoinCollision(Character character, Coin coin, GameModel model)
	{
		model.getGameObjects().remove(coin);
		model.addPointsToScore(50);
	}

	private void handleCharacterPlatformCollision(Character player, Platform platform, GameModel model)
	{
		if (player.getVelocityY() > 0)
		{
			float playerOldBottom = player.getOldY() + player.getHeight();
			float platformTop = platform.getY();

			if (playerOldBottom <= platformTop)
			{
				player.setVelocityY(-player.getJumpForce());
				player.setY(platformTop - player.getHeight());
			}
		}
	}

}