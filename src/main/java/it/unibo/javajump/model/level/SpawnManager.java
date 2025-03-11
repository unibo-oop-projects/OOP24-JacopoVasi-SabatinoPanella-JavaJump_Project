package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.SpawnStrategy;

public interface SpawnManager {
    void generateInitialLevel(GameModel model);

    void generateOnTheFly(GameModel model);

    void reset();

    SpawnStrategy getSpawnStrategy();

    GameObjectFactory getFactory();
}
