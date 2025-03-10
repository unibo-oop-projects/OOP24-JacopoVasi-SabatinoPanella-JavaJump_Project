package it.unibo.javajump.model.level.spawn.collectiblespawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class CollectiblesSpawner {

	private final AbstractGameObjectFactoryImpl factory;
	private final Random rand;
	private final float coinSpawnChance;

	public CollectiblesSpawner(AbstractGameObjectFactoryImpl factory, float coinSpawnChance) {
		this.factory = factory;
		this.rand = new Random();
		this.coinSpawnChance = coinSpawnChance;
	}


	public void spawnCollectible(GameModel model, float platformX, float platformY, float platformWidth, PlatformImpl platform) {
		if (rand.nextFloat() < coinSpawnChance) {
			float coinX = platformX + (platformWidth / COINXDIV) - (platformWidth * COINXMUL);
			float coinY = platformY - COINOFFSET;
			CoinImpl coin = factory.createCoin(coinX, coinY);

			if (platform instanceof MovingPlatformImpl) {
				coin.attachToPlatform(platform);
			}
			model.getGameObjects().add(coin);
		}
	}
}
