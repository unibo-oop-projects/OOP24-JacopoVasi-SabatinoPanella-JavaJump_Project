package it.unibo.javajump.model.entities.objectstrategies;

import it.unibo.javajump.model.entities.GameObject;

public interface MovementBehaviour {
	
	void update(GameObject obj, float deltaTime);
}
