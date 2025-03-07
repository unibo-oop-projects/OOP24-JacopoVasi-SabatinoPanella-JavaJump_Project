package model.level.spawn;

import model.GameModel;
import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;
import model.level.spawn.collectiblespawn.CollectiblesSpawner;
import model.level.spawn.difficulty.DifficultyManager;
import model.level.spawn.difficulty.DifficultyState;
import model.level.spawn.platformspawn.PlatformSpawner;
import model.level.spawn.spawnutilities.SpawnUtils;

import java.util.Random;

import static Utility.Constants.*;

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
		this.currentY = ZERO;
		this.collectiblesSpawner = new CollectiblesSpawner(factory, coinChance);
		this.platformSpawner = new PlatformSpawner(factory);
		this.difficultyManager = difficultyManager;
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
		if (diff == DifficultyState.EASY || diff == DifficultyState.MEDIUM){
			gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing - GAPOFFSETTEN, maxPlatformYSpacing - GAPOFFSETTHIRTY);
		} else if (diff == DifficultyState.HARD || diff == DifficultyState.VERY_HARD){
			gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing, maxPlatformYSpacing - GAPOFFSETTEN);
		} else if (diff == DifficultyState.HELL){
			gap = SpawnUtils.randomInRange(rand, minPlatformYSpacing + GAPOFFSETTEN, maxPlatformYSpacing);
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
