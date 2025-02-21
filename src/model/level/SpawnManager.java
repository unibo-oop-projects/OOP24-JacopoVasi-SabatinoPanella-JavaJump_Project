package model.level;

import model.GameModel;
import model.factories.AbstractGameObjectFactory;
import model.entities.Coin;
import model.entities.Platform;

import java.util.Random;

public class SpawnManager
{
	private final AbstractGameObjectFactory factory;
	private final Random random;


	private final int numberOfPlatforms;
	private final float minPlatformYSpacing;
	private final float maxPlatformYSpacing;
	private final float coinSpawnChance;

	public SpawnManager(AbstractGameObjectFactory factory)
	{
		this.factory = factory;
		this.random = new Random();
this.numberOfPlatforms = 10;
		this.minPlatformYSpacing = 80;
		this.maxPlatformYSpacing = 140;
		this.coinSpawnChance = 0.3f;
	}

	
	public void generateInitialLevel(GameModel model)
	{
		float currentY = model.getScreenHeight() - 50;for (int i = 0; i < numberOfPlatforms; i++)
		{
			float gap = randomInRange(minPlatformYSpacing, maxPlatformYSpacing);
			currentY -= gap;
	float platformWidth = 100;
			float x = random.nextFloat() * (model.getScreenWidth() - platformWidth);
	Platform p = factory.createRandomPlatform(x, currentY);
			model.getGameObjects().add(p);
	if (random.nextFloat() < coinSpawnChance)
			{
				float coinX = x + platformWidth / 2f - 10;
				float coinY = currentY - 30;
				Coin c = factory.createCoin(coinX, coinY);
				model.getGameObjects().add(c);
			}
		}
	}

	
	public void generateOnTheFly(GameModel model) {




float playerY = model.getPlayer().getY();



	}

	private float randomInRange(float min, float max) {
		return min + random.nextFloat() * (max - min);
	}

	public AbstractGameObjectFactory getFactory()
	{
		return this.factory;
	}
}
