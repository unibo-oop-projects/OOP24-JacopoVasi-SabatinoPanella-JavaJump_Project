package it.unibo.javajump.model.entities.objectstrategies;

import it.unibo.javajump.model.entities.GameObjectImpl;

public interface MovementBehaviour {
	
	void update(GameObjectImpl obj, float deltaTime);
}
