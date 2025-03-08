import controller.GameController;
import model.GameModel;
import model.camera.CameraManagerImpl;
import model.collision.CollisionManager;
import model.collision.CollisionManagerImpl;
import model.factories.AbstractGameObjectFactory;
import model.factories.GameObjectFactory;
import model.level.CleanupManager;
import model.level.SpawnManager;
import model.level.spawn.difficulty.DifficultyManager;
import model.physics.PhysicsManager;
import model.score.ScoreManager;
import model.camera.CameraManager;
import model.level.spawn.RandomSpawnStrategy;
import view.MainGameView;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static utility.Constants.*;

public class Main {

	public static void main(String[] args) {
		int screenWidth = SCREENWIDTH;
		int screenHeight = SCREENHEIGHT;
		AbstractGameObjectFactory factory = new GameObjectFactory();
		DifficultyManager difficultyManager = new DifficultyManager();
		RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MINSPACING, MAXSPACING, COINCHANCE, difficultyManager);
		CollisionManager collisionManager = new CollisionManagerImpl();
		SpawnManager spawnManager = new SpawnManager(strategy);
		ScoreManager scoreManager = new ScoreManager();
		CameraManager cameraManager = new CameraManagerImpl(scoreManager, SCOREFACTOR);
		PhysicsManager physicsManager = new PhysicsManager(GRAVITY, ACCELERATION, MAXSPEED, DECELERATION);
		CleanupManager cleanupManager = new CleanupManager();
		JFrame frame = new JFrame(GAMETITLE);

		GameModel model = new GameModel(screenWidth,
				screenHeight,
				physicsManager,
				collisionManager,
				spawnManager,
				cameraManager,
				scoreManager,
				cleanupManager,
				difficultyManager);

		MainGameView view = new MainGameView(model);
		model.addObserver(view);
		GameController controller = new GameController(model, view);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		frame.addKeyListener(controller);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
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