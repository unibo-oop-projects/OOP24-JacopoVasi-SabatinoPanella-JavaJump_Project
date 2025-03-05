package model.level.spawn;

import model.GameModel;
import model.factories.AbstractGameObjectFactory;

public interface SpawnStrategy {
	
	void spawnBatch(GameModel model, float startY, int numberOfPlatforms);

	float returnCurrentY();

	AbstractGameObjectFactory getFactory();
}
