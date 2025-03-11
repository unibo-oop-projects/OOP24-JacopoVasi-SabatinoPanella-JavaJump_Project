package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

/**
 * The interface Spawn manager.
 */
public interface SpawnManager {
    /**
     * Generate initial level.
     *
     * @param model the model
     */
    void generateInitialLevel(GameModel model);

    /**
     * Generate on the fly.
     *
     * @param model the model
     */
    void generateOnTheFly(GameModel model);

    /**
     * Reset.
     */
    void reset();

    /**
     * Gets spawn strategy.
     *
     * @return the spawn strategy
     */
    SpawnStrategy getSpawnStrategy();

    /**
     * Gets factory.
     *
     * @return the factory
     */
    GameObjectFactory getFactory();
}
