package model.level.spawn.platformspawn;

import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;
import model.level.spawn.difficulty.DifficultyManager;

import java.util.Random;

import static model.level.spawn.spawnutilities.SpawnUtils.randomInRange;

public class PlatformSpawner {

	private final AbstractGameObjectFactory factory;
	private final Random rand;

	public PlatformSpawner(AbstractGameObjectFactory factory) {
		this.factory = factory;
		this.rand = new Random();
	}

	
	public Platform spawnPlatform(float x, float y, int screenWidth, DifficultyManager.Difficulty difficulty) {
		float chance = rand.nextFloat();
		float breakableChance = difficulty.getBreakableChance();
		float movingChance = difficulty.getMovingChance();
		float bounceChance = difficulty.getBounceChance();

		if (chance < bounceChance) {
			return factory.createBouncePlatform(x, y, randomInRange(new Random(), 1.5f, 2.2f));
		} else if (chance < breakableChance) {
			return factory.createBreakablePlatform(x, y);
		} else if (chance < breakableChance + movingChance) {
			return factory.createMovingPlatform(x, y, screenWidth);
		} else {

			return factory.createRandomPlatform(x, y);
		}
	}
}
