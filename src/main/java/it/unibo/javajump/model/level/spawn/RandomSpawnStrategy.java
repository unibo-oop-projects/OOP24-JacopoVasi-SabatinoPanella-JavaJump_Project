package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.AbstractGameObjectFactory;
import it.unibo.javajump.model.level.spawn.collectiblespawn.CollectiblesSpawner;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;
import it.unibo.javajump.model.level.spawn.platformspawn.PlatformSpawner;
import it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtils;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class RandomSpawnStrategy implements SpawnStrategy {

	private final AbstractGameObjectFactory factory;
	private final Random rand;
	@SuppressWarnings("FieldMayBeFinal")
	private float minPlatformYSpacing;
	@SuppressWarnings("FieldMayBeFinal")
	private float maxPlatformYSpacing;
	private float currentY;
	private final CollectiblesSpawner collectiblesSpawner;
	private final PlatformSpawner platformSpawner;

	public RandomSpawnStrategy(AbstractGameObjectFactory factory,
							   float minSpacing,
							   float maxSpacing,
							   float coinChance,
							   DifficultyManager difficultyManager) {
		this.factory = factory;
		this.rand = new Random();
		this.minPlatformYSpacing = minSpacing;
		this.maxPlatformYSpacing = maxSpacing;
		this.currentY = ZERO;
		this.collectiblesSpawner = new CollectiblesSpawner(factory, coinChance);
		this.platformSpawner = new PlatformSpawner(factory);
	}

	@Override
	public void spawnBatch(GameModel model, float startY, int numberOfPlatforms) {
		DifficultyState diff = model.getDifficultyManager().getCurrentDifficulty();
		System.out.println(DIFFICULTYTEXT + diff);
		currentY = startY;
		float maxPlatformWidth = MAXPLATFORMWIDTH;

		for (int i = 0; i < numberOfPlatforms; i++) {

			float gap = setSpawnGap(diff);
			currentY -= gap;

			float x = rand.nextFloat() * (model.getScreenWidth() - maxPlatformWidth);

			Platform p = platformSpawner.spawnPlatform(x, currentY, model.getScreenWidth(), diff);
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
						gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing - GAPOFFSETTEN, maxPlatformYSpacing - GAPOFFSETTHIRTY);
				case HARD, VERY_HARD ->
						gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing, maxPlatformYSpacing - GAPOFFSETTEN);
				case HELL ->
						gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing + GAPOFFSETTEN, maxPlatformYSpacing);
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
	public AbstractGameObjectFactory getFactory() {
		return this.factory;
	}
}
