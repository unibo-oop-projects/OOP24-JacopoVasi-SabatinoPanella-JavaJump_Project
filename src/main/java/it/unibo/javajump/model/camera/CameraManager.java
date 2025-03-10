package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModelImpl;


public interface CameraManager {

	void updateCamera(GameModelImpl model, float deltaTime);

	void resetCamera();

	float getCurrentOffset();
}
