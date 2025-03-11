package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.spawnPlatformBelowPlayer;
import static it.unibo.javajump.utility.Constants.INITIAL_PLATFORMS_NUMBER;
import static it.unibo.javajump.utility.Constants.INITIAL_Y_SPAWN_OFFSET;
import static it.unibo.javajump.utility.Constants.PROCEDURAL_PLATFORMS_NUMBER;
import static it.unibo.javajump.utility.Constants.SPAWN_THRESHOLD;
import static it.unibo.javajump.utility.Constants.TOP_PLATFORM_Y_INIT;

/**
 * The type Spawn manager.
 */
public final class SpawnManagerImpl implements SpawnManager {

    private final SpawnStrategy spawnStrategy;
    private float topPlatformY;


    /**
     * Instantiates a new Spawn manager.
     *
     * @param spawnStrategy the spawn strategy
     */
    public SpawnManagerImpl(final SpawnStrategy spawnStrategy) {
        this.spawnStrategy = spawnStrategy;
        this.topPlatformY = TOP_PLATFORM_Y_INIT;
    }


    @Override
    public void generateInitialLevel(final GameModel model) {
        spawnPlatformBelowPlayer(model, getFactory());
        final float startY = model.getScreenHeight() - INITIAL_Y_SPAWN_OFFSET;
        spawnStrategy.spawnBatch(model, startY, INITIAL_PLATFORMS_NUMBER);
        this.topPlatformY = spawnStrategy.returnCurrentY();
    }


    @Override
    public void generateOnTheFly(final GameModel model) {
        final float playerY = model.getPlayer().getY();
        final float gap = playerY - topPlatformY;

        if (gap < SPAWN_THRESHOLD) {
            spawnStrategy.spawnBatch(model, topPlatformY, PROCEDURAL_PLATFORMS_NUMBER);
            final float newTop = spawnStrategy.returnCurrentY();

            if (newTop < topPlatformY) {
                topPlatformY = newTop;
            }
        }
    }

    @Override
    public void reset() {
        this.topPlatformY = TOP_PLATFORM_Y_INIT;
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
