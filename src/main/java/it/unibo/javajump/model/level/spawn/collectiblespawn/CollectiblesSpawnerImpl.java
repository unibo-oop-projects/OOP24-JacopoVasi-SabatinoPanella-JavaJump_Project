package it.unibo.javajump.model.level.spawn.collectiblespawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactory;
import it.unibo.javajump.model.factories.GameObjectFactory;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class CollectiblesSpawnerImpl implements CollectiblesSpawner {

	private final GameObjectFactory factory;
	private final Random rand;
	private final float coinSpawnChance;

	public CollectiblesSpawnerImpl(GameObjectFactory factory, float coinSpawnChance) {
		this.factory = factory;
		this.rand = new Random();
		this.coinSpawnChance = coinSpawnChance;
	}


	@Override
	public void spawnCollectible(GameModel model, float platformX, float platformY, float platformWidth, Platform platform) {
		if (rand.nextFloat() < coinSpawnChance) {
			float coinX = platformX + (platformWidth / COINXDIV) - (platformWidth * COINXMUL);
			float coinY = platformY - COINOFFSET;
			Coin coin = factory.createCoin(coinX, coinY);

			if (platform instanceof MovingPlatform) {
				coin.attachToPlatform(platform);
			}
			model.getGameObjects().add(coin);
		}
	}
}
