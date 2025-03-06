package model.level.spawn;

import model.GameModel;
import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;
import model.level.spawn.collectiblespawn.CollectiblesSpawner;
import model.level.spawn.platformspawn.PlatformSpawner;
import model.level.spawn.spawnutilities.SpawnUtils;

import java.util.Random;

public class RandomSpawnStrategy implements SpawnStrategy {

	private final AbstractGameObjectFactory factory;
	private final Random rand;
	private final float minPlatformYSpacing;
	private final float maxPlatformYSpacing;
	private final float coinSpawnChance;
	private float currentY;
	private final CollectiblesSpawner collectiblesSpawner;
	private final PlatformSpawner platformSpawner;

	public RandomSpawnStrategy(AbstractGameObjectFactory factory,
							   float minSpacing,
							   float maxSpacing,
							   float coinChance) {
		this.factory = factory;
		this.rand = new Random();
		this.minPlatformYSpacing = minSpacing;
		this.maxPlatformYSpacing = maxSpacing;
		this.coinSpawnChance = coinChance;
		this.currentY = 0;
		this.collectiblesSpawner = new CollectiblesSpawner(factory, coinChance);
		this.platformSpawner = new PlatformSpawner(factory);
	}

	@Override
	public void spawnBatch(GameModel model, float startY, int numberOfPlatforms) {
		currentY = startY;
		float maxPlatformWidth = 120;

		for (int i = 0; i < numberOfPlatforms; i++) {

			float gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing, maxPlatformYSpacing);
			currentY -= gap;


			float x = rand.nextFloat() * (model.getScreenWidth() - maxPlatformWidth);


			Platform p = platformSpawner.spawnPlatform(x, currentY, model.getScreenWidth());
			model.getGameObjects().add(p);

			float platformWidth = p.getWidth();

			collectiblesSpawner.spawnCollectible(model, x, currentY, platformWidth, p);
		}
	}

	@Override
	public float returnCurrentY() {
		return this.currentY;
	}

	public AbstractGameObjectFactory getFactory() {
		return this.factory;
	}
}
