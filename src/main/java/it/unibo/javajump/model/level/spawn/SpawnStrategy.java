package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;

public interface SpawnStrategy {
	
	void spawnBatch(GameModel model, float startY, int numberOfPlatforms);

	float returnCurrentY();

	AbstractGameObjectFactoryImpl getFactory();
}
