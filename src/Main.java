import controller.GameController;
import model.GameModel;
import model.collision.CollisionManager;
import model.factories.AbstractGameObjectFactory;
import model.factories.GameObjectFactory;
import model.level.SpawnManager;
import model.physics.PhysicsManager;
import model.score.ScoreManager;
import model.camera.CameraManager;
import view.GameFrame;
import view.GameView;

import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main(String[] args)
	{
		int screenWidth = 800;
		int screenHeight = 600;


		AbstractGameObjectFactory factory = new GameObjectFactory();


		CollisionManager collisionManager = new CollisionManager();


		SpawnManager spawnManager = new SpawnManager(factory);


		ScoreManager scoreManager = new ScoreManager();


		CameraManager cameraManager = new CameraManager(scoreManager, 0.5f);


		PhysicsManager physicsManager = new PhysicsManager(0.5f, 5.0f, 0.5f);


		GameModel model = new GameModel(screenWidth,
										screenHeight,
										physicsManager,
										collisionManager,
										spawnManager,
										cameraManager,
										scoreManager
		);



		GameController controller = new GameController(model
		);



		controller.startGameLoop();
		model.startGame();



	}
}