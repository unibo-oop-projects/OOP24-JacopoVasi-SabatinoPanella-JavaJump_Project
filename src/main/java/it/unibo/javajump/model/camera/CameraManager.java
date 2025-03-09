package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModel;


public interface CameraManager {

	void updateCamera(GameModel model, float deltaTime);

	void resetCamera();

	float getCurrentOffset();
}
