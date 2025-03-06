package model.level.spawn.collectiblespawn;

import model.GameModel;
import model.entities.collectibles.Coin;
import model.entities.platforms.MovingPlatform;
import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;

import java.util.Random;

public class CollectiblesSpawner
{
	private final AbstractGameObjectFactory factory;
	private final Random rand;
	private final float coinSpawnChance;

	public CollectiblesSpawner(AbstractGameObjectFactory factory, float coinSpawnChance) {
		this.factory = factory;
		this.rand = new Random();
		this.coinSpawnChance = coinSpawnChance;
	}


	public void spawnCollectible(GameModel model, float platformX, float platformY, float platformWidth, Platform platform) {
		if (rand.nextFloat() < coinSpawnChance) {
			float coinX = platformX + (platformWidth / 2f) - (platformWidth * 0.2f);
			float coinY = platformY - 50;
			Coin coin = factory.createCoin(coinX, coinY);

			if (platform instanceof MovingPlatform) {
				coin.attachToPlatform(platform);
			}
			model.getGameObjects().add(coin);
		}
	}
}
