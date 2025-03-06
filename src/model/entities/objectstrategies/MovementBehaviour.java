package model.entities.objectstrategies;

import model.entities.GameObject;

public interface MovementBehaviour {
	
	void update(GameObject obj, float deltaTime);
}
