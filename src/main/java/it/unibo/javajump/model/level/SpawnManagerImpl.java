package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.spawnPlatformBelowPlayer;
import static it.unibo.javajump.utility.Constants.*;

public class SpawnManagerImpl implements SpawnManager {

	private final SpawnStrategy spawnStrategy;
	private float topPlatformY;


	public SpawnManagerImpl(SpawnStrategy spawnStrategy) {
		this.spawnStrategy = spawnStrategy;
		this.topPlatformY = TOPPLATFORMY_INIT;
	}


	@Override
	public void generateInitialLevel(GameModel model) {
		spawnPlatformBelowPlayer(model, getFactory());
		float startY = model.getScreenHeight() - INITIALYOFFSET;
		spawnStrategy.spawnBatch(model, startY, INITIAL_PLATFORMS_NUMBER);
		this.topPlatformY = spawnStrategy.returnCurrentY();
	}


	@Override
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

	@Override
	public void reset() {
		this.topPlatformY = TOPPLATFORMY_INIT;
	}

	@Override
	public SpawnStrategy getSpawnStrategy() {
		return this.spawnStrategy;
	}

	@Override
	public GameObjectFactory getFactory() {
		if (spawnStrategy instanceof RandomSpawnStrategy) {
			return spawnStrategy.getFactory();
		}
		return null;
	}
}
