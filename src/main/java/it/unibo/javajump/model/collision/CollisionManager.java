package it.unibo.javajump.model.collision;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;

/**
 * Interface that defines the collision manager.
 */
public interface CollisionManager {
	/**
	 * Controls and manages collision events between all the GameObjects in the model.
	 *
	 * @param model the GameModelImpl
	 */
	void checkCollisions(GameModel model);
}
