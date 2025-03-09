package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.AbstractGameObjectFactory;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtils.spawnPlatformBelowPlayer;

public class SpawnManager {

	private final SpawnStrategy spawnStrategy;
	private float topPlatformY;


	public SpawnManager(SpawnStrategy spawnStrategy) {
		this.spawnStrategy = spawnStrategy;
		this.topPlatformY = ZERO;
	}


	public void generateInitialLevel(GameModel model) {
		spawnPlatformBelowPlayer(model, getFactory());
		float startY = model.getScreenHeight() - INITIALYOFFSET;
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
		this.topPlatformY = ZERO;
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
