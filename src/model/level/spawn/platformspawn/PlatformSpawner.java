package model.level.spawn.platformspawn;

import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;
import model.level.spawn.difficulty.DifficultyState;

import java.util.Random;

import static model.level.spawn.spawnutilities.SpawnUtils.randomInRange;

public class PlatformSpawner {

	private final AbstractGameObjectFactory factory;
	private final Random rand;

	public PlatformSpawner(AbstractGameObjectFactory factory) {
		this.factory = factory;
		this.rand = new Random();
	}

	
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
