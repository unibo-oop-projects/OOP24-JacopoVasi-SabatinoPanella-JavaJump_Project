package it.unibo.javajump;

import it.unibo.javajump.controller.GameController;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.camera.CameraManagerImpl;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.collision.CollisionManagerImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactory;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.CleanupManager;
import it.unibo.javajump.model.level.SpawnManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.physics.PhysicsManager;
import it.unibo.javajump.model.score.ScoreManager;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.view.MainGameView;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static it.unibo.javajump.utility.Constants.*;

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