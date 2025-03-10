package it.unibo.javajump.model.level.spawn.collectiblespawn;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class CollectiblesSpawnerImpl implements CollectiblesSpawner {

	private final AbstractGameObjectFactoryImpl factory;
	private final Random rand;
	private final float coinSpawnChance;

	public CollectiblesSpawnerImpl(AbstractGameObjectFactoryImpl factory, float coinSpawnChance) {
		this.factory = factory;
		this.rand = new Random();
		this.coinSpawnChance = coinSpawnChance;
	}


	public void spawnCollectible(GameModelImpl model, float platformX, float platformY, float platformWidth, PlatformImpl platformImpl) {
		if (rand.nextFloat() < coinSpawnChance) {
			float coinX = platformX + (platformWidth / COINXDIV) - (platformWidth * COINXMUL);
			float coinY = platformY - COINOFFSET;
			CoinImpl coinImpl = factory.createCoin(coinX, coinY);

			if (platformImpl instanceof MovingPlatformImpl) {
				coinImpl.attachToPlatform(platformImpl);
			}
			model.getGameObjects().add(coinImpl);
		}
	}
}
