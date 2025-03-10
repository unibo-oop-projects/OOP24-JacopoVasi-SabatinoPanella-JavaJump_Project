package it.unibo.javajump;

import it.unibo.javajump.controller.GameControllerImpl;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.camera.CameraManagerImpl;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.collision.CollisionManagerImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;
import it.unibo.javajump.model.factories.GameObjectFactoryImpl;
import it.unibo.javajump.model.level.CleanupManagerImpl;
import it.unibo.javajump.model.level.SpawnManagerImpl;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManagerImpl;
import it.unibo.javajump.model.physics.PhysicsManagerImpl;
import it.unibo.javajump.model.score.ScoreManagerImpl;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.view.MainGameViewImpl;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static it.unibo.javajump.utility.Constants.*;

public class Main {

	public static void main(String[] args) {
		int screenWidth = SCREENWIDTH;
		int screenHeight = SCREENHEIGHT;

		AbstractGameObjectFactoryImpl factory = new GameObjectFactoryImpl();
		DifficultyManagerImpl difficultyManagerImpl = new DifficultyManagerImpl();
		RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MINSPACING, MAXSPACING, COINCHANCE, difficultyManagerImpl);
		CollisionManager collisionManager = new CollisionManagerImpl();
		SpawnManagerImpl spawnManagerImpl = new SpawnManagerImpl(strategy);
		ScoreManagerImpl scoreManagerImpl = new ScoreManagerImpl();
		CameraManager cameraManager = new CameraManagerImpl(scoreManagerImpl, SCOREFACTOR);
		PhysicsManagerImpl physicsManagerImpl = new PhysicsManagerImpl(GRAVITY, ACCELERATION, MAXSPEED, DECELERATION);
		CleanupManagerImpl cleanupManagerImpl = new CleanupManagerImpl();
		JFrame frame = new JFrame(GAMETITLE);

		GameModelImpl model = new GameModelImpl(screenWidth,
				screenHeight,
				physicsManagerImpl,
				collisionManager,
				spawnManagerImpl,
				cameraManager,
				scoreManagerImpl,
				cleanupManagerImpl,
				difficultyManagerImpl);

		MainGameViewImpl view = new MainGameViewImpl(model);
		model.addObserver(view);
		GameControllerImpl controller = new GameControllerImpl(model, view);

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