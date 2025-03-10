package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;

public interface SpawnStrategy {
	
	void spawnBatch(GameModelImpl model, float startY, int numberOfPlatforms);

	float returnCurrentY();

	AbstractGameObjectFactoryImpl getFactory();
}
