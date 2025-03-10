package it.unibo.javajump.model.level.spawn.platformspawn;

import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactory;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

import java.util.Random;

import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.randomInRange;

public class PlatformSpawnerImpl implements PlatformSpawner {

	private final GameObjectFactory factory;
	private final Random rand;

	public PlatformSpawnerImpl(GameObjectFactory factory) {
		this.factory = factory;
		this.rand = new Random();
	}

	@Override
	public Platform spawnPlatform(float x, float y, int screenWidth, DifficultyState difficulty) {
		float chance = rand.nextFloat();
		float breakableChance = difficulty.getBreakableChance();
		float movingChance = difficulty.getMovingChance();
		float bounceChance = difficulty.getBounceChance();

		float thresholdBounce = bounceChance;
		float thresholdBreakable = thresholdBounce + breakableChance;
		float thresholdMoving = thresholdBreakable + movingChance;

		if (chance < thresholdBounce) {
			return factory.createBouncePlatform(x, y, randomInRange(rand, 1.5f, 2.2f));
		} else if (chance < thresholdBreakable) {
			return factory.createBreakablePlatform(x, y);
		} else if (chance < thresholdMoving) {
			return factory.createMovingPlatform(x, y, screenWidth);
		} else {

			return factory.createRandomPlatform(x, y);
		}
	}
}
