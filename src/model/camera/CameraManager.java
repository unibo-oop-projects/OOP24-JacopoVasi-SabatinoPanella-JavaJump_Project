package model.camera;

import model.GameModel;


public interface CameraManager {
	
	void updateCamera(GameModel model, float deltaTime);

	
	void resetCamera();

	
	float getCurrentOffset();
}
