package model.level.spawn.platforms;

import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;

import java.util.Random;

public class PlatformSpawner {

	private final AbstractGameObjectFactory factory;
	private final Random rand;

	public PlatformSpawner(AbstractGameObjectFactory factory) {
		this.factory = factory;
		this.rand = new Random();
	}

	
	public Platform spawnPlatform(float x, float y, int screenWidth) {
		float chance = rand.nextFloat();
		if (chance < 0.05f) {

			return factory.createBreakablePlatform(x, y);
		} else if (chance < 0.20f) {

			return factory.createMovingPlatform(x, y, screenWidth);
		} else {

			return factory.createRandomPlatform(x, y);
		}
	}
}
