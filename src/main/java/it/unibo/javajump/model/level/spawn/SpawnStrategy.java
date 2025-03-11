package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.GameObjectFactory;

public interface SpawnStrategy {
	/**
	 * Creates "numberOfPlatforms" platforms (and relative coin)
	 * starting from a startY.
	 *
	 * @param model             the GameModelImpl (to access screenWidth, addGameObject, etc.)
	 * @param startY            the starting quote from which to start generating
	 * @param numberOfPlatforms the number of platforms to generate in total in this batch
	 */
	void spawnBatch(GameModel model, float startY, int numberOfPlatforms);

	/**
	 * Returns the current Y position, so that SpawnManagerImpl can evaluate whether to spawn a new batch
	 */
	float returnCurrentY();

	/**
	 * Returns the factory used to create the objects
	 */
	GameObjectFactory getFactory();
}
