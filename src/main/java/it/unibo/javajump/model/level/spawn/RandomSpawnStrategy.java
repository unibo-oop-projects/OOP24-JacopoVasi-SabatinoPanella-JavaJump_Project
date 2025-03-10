package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;
import it.unibo.javajump.model.level.spawn.collectiblespawn.CollectiblesSpawnerImpl;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManagerImpl;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;
import it.unibo.javajump.model.level.spawn.platformspawn.PlatformSpawnerImpl;
import it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class RandomSpawnStrategy implements SpawnStrategy {

	private final AbstractGameObjectFactoryImpl factory;
	private final Random rand;
	@SuppressWarnings("FieldMayBeFinal")
	private float minPlatformYSpacing;
	@SuppressWarnings("FieldMayBeFinal")
	private float maxPlatformYSpacing;
	private float currentY;
	private final CollectiblesSpawnerImpl collectiblesSpawner;
	private final PlatformSpawnerImpl platformSpawner;

	public RandomSpawnStrategy(AbstractGameObjectFactoryImpl factory,
							   float minSpacing,
							   float maxSpacing,
							   float coinChance,
							   DifficultyManagerImpl difficultyManager) {
		this.factory = factory;
		this.rand = new Random();
		this.minPlatformYSpacing = minSpacing;
		this.maxPlatformYSpacing = maxSpacing;
		this.currentY = ZERO;
		this.collectiblesSpawner = new CollectiblesSpawnerImpl(factory, coinChance);
		this.platformSpawner = new PlatformSpawnerImpl(factory);
	}

	@Override
	public void spawnBatch(GameModelImpl model, float startY, int numberOfPlatforms) {
		DifficultyState diff = model.getDifficultyManager().getCurrentDifficulty();
		System.out.println(DIFFICULTYTEXT + diff);
		currentY = startY;
		float maxPlatformWidth = MAXPLATFORMWIDTH;

		for (int i = 0; i < numberOfPlatforms; i++) {

			float gap = setSpawnGap(diff);
			currentY -= gap;

			float x = rand.nextFloat() * (model.getScreenWidth() - maxPlatformWidth);

			PlatformImpl p = platformSpawner.spawnPlatform(x, currentY, model.getScreenWidth(), diff);
			model.getGameObjects().add(p);

			float platformWidth = p.getWidth();

			collectiblesSpawner.spawnCollectible(model, x, currentY, platformWidth, p);
		}
	}

	private float setSpawnGap(DifficultyState diff) {
		float gap = GAPINIT;
		if (null != diff) {
			switch (diff) {
				case EASY, MEDIUM ->
						gap = SpawnUtilsImpl.randomInRange(rand, minPlatformYSpacing - GAPOFFSETTEN, maxPlatformYSpacing - GAPOFFSETTHIRTY);
				case HARD, VERY_HARD ->
						gap = SpawnUtilsImpl.randomInRange(rand, minPlatformYSpacing, maxPlatformYSpacing - GAPOFFSETTEN);
				case HELL ->
						gap = SpawnUtilsImpl.randomInRange(rand, minPlatformYSpacing + GAPOFFSETTEN, maxPlatformYSpacing);
				default -> {
				}
			}
		}
		return gap;
	}

	@Override
	public float returnCurrentY() {
		return this.currentY;
	}

	@Override
	public AbstractGameObjectFactoryImpl getFactory() {
		return this.factory;
	}
}
