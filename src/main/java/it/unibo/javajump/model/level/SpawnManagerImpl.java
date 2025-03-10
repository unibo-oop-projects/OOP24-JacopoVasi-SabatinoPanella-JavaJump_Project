package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.spawnPlatformBelowPlayer;

public class SpawnManagerImpl implements SpawnManager {

	private final SpawnStrategy spawnStrategy;
	private float topPlatformY;


	public SpawnManagerImpl(SpawnStrategy spawnStrategy) {
		this.spawnStrategy = spawnStrategy;
		this.topPlatformY = ZERO;
	}


	public void generateInitialLevel(GameModelImpl model) {
		spawnPlatformBelowPlayer(model, getFactory());
		float startY = model.getScreenHeight() - INITIALYOFFSET;
		spawnStrategy.spawnBatch(model, startY, INITIAL_PLATFORMS_NUMBER);
		this.topPlatformY = spawnStrategy.returnCurrentY();
	}


	public void generateOnTheFly(GameModelImpl model) {

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

	public AbstractGameObjectFactoryImpl getFactory() {

		if (spawnStrategy instanceof RandomSpawnStrategy) {
			return spawnStrategy.getFactory();
		}

		return null;
	}
}
