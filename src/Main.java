import controller.GameController;
import model.GameModel;
import model.collision.CollisionManager;
import model.factories.AbstractGameObjectFactory;
import model.factories.GameObjectFactory;
import model.level.CleanupManager;
import model.level.SpawnManager;
import model.physics.PhysicsManager;
import model.score.ScoreManager;
import model.camera.CameraManager;

import view.MainGameView;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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


		PhysicsManager physicsManager = new PhysicsManager(3000f, 400f, 15000f);

		CleanupManager cleanupManager = new CleanupManager();

		JFrame frame = new JFrame("JAVA JUMP");


		GameModel model = new GameModel(screenWidth,
										screenHeight,
										physicsManager,
										collisionManager,
										spawnManager,
										cameraManager,
										scoreManager,
										cleanupManager);

		MainGameView view = new MainGameView(model);
		model.addObserver(view);


		GameController controller = new GameController(model, view);



		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);


		frame.addKeyListener(controller);
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				frame.setPreferredSize(e.getComponent().getSize());
				frame.pack();
				view.setNewSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
			}
		});

		frame.add(view);

		frame.setVisible(true);


		controller.startGameLoop();



	}
}