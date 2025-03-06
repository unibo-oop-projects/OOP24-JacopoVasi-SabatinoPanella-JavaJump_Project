package model.level.spawn;

import model.GameModel;
import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;
import model.level.spawn.collectiblespawn.CollectiblesSpawner;
import model.level.spawn.difficulty.DifficultyManager;
import model.level.spawn.platformspawn.PlatformSpawner;
import model.level.spawn.spawnutilities.SpawnUtils;

import java.util.Random;

public class RandomSpawnStrategy implements SpawnStrategy {

	private final AbstractGameObjectFactory factory;
	private final Random rand;
	private float minPlatformYSpacing;
	private float maxPlatformYSpacing;
	private final float coinSpawnChance;
	private float currentY;
	private final CollectiblesSpawner collectiblesSpawner;
	private final PlatformSpawner platformSpawner;
	private final DifficultyManager difficultyManager;

	public RandomSpawnStrategy(AbstractGameObjectFactory factory,
							   float minSpacing,
							   float maxSpacing,
							   float coinChance,
							   DifficultyManager difficultyManager) {
		this.factory = factory;
		this.rand = new Random();
		this.minPlatformYSpacing = minSpacing;
		this.maxPlatformYSpacing = maxSpacing;
		this.coinSpawnChance = coinChance;
		this.currentY = 0;
		this.collectiblesSpawner = new CollectiblesSpawner(factory, coinChance);
		this.platformSpawner = new PlatformSpawner(factory);
		this.difficultyManager = difficultyManager;
	}

	@Override
	public void spawnBatch(GameModel model, float startY, int numberOfPlatforms) {
		DifficultyManager.Difficulty diff = difficultyManager.getCurrentDifficulty();
		System.out.println("Difficulty: " + diff);
		currentY = startY;
		float maxPlatformWidth = 120;

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

	private float setSpawnGap(DifficultyManager.Difficulty diff) {
		float gap = 0;
		if (diff == DifficultyManager.Difficulty.EASY || diff == DifficultyManager.Difficulty.MEDIUM){
			gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing - 10, maxPlatformYSpacing - 30);
		} else if (diff == DifficultyManager.Difficulty.HARD || diff == DifficultyManager.Difficulty.VERY_HARD){
			gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing, maxPlatformYSpacing - 10);
		} else if (diff == DifficultyManager.Difficulty.HELL){
			gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing + 10, maxPlatformYSpacing);
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
