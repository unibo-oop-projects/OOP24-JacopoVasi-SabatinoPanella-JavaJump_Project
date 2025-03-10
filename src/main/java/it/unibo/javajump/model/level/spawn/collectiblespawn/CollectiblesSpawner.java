package it.unibo.javajump.model.level.spawn.collectiblespawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.platforms.Platform;

public interface CollectiblesSpawner {

	void spawnCollectible(GameModel model, float platformX, float platformY, float platformWidth, Platform platform);
}
