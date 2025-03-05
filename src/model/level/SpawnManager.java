package model.level;

import model.GameModel;
import model.factories.AbstractGameObjectFactory;
import model.level.spawn.RandomSpawnStrategy;
import model.level.spawn.SpawnStrategy;

import static model.level.spawn.utilities.SpawnUtils.spawnPlatformBelowPlayer;

public class SpawnManager {

	private final SpawnStrategy spawnStrategy;
	private float topPlatformY;


	private final int INITIAL_PLATFORMS_NUMBER = 10;
	private final int PROCEDURAL_PLATFORMS_NUMBER = 10;


	private final float SPAWN_THRESHOLD = 400f;

	public SpawnManager(SpawnStrategy spawnStrategy) {
		this.spawnStrategy = spawnStrategy;
		this.topPlatformY = 0;
	}

	
	public void generateInitialLevel(GameModel model) {
		spawnPlatformBelowPlayer(model, getFactory());
		float startY = model.getScreenHeight() - 50;
		spawnStrategy.spawnBatch(model, startY, INITIAL_PLATFORMS_NUMBER);
		this.topPlatformY = spawnStrategy.returnCurrentY();
	}

	
	public void generateOnTheFly(GameModel model) {

		float playerY = model.getPlayer().getY();
		float gap = playerY - topPlatformY;

		if (gap < SPAWN_THRESHOLD) {

			spawnStrategy.spawnBatch(model, topPlatformY, PROCEDURAL_PLATFORMS_NUMBER);

			float newTop = spawnStrategy.returnCurrentY();
			if (newTop < topPlatformY) {
				topPlatformY = newTop;
			}
		}
	}

	public void reset() {
		this.topPlatformY = 0;
	}

	public SpawnStrategy getSpawnStrategy() {
		return this.spawnStrategy;
	}

	public AbstractGameObjectFactory getFactory() {

		if (spawnStrategy instanceof RandomSpawnStrategy) {
			return spawnStrategy.getFactory();
		}

		return null;
	}
}