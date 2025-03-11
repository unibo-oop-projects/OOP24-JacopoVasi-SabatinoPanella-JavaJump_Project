package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.InputManager;
import it.unibo.javajump.controller.input.InputManagerImpl;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.GameModelObserver;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.camera.CameraManagerImpl;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.collision.CollisionManagerImpl;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.factories.GameObjectFactoryImpl;
import it.unibo.javajump.model.level.CleanupManager;
import it.unibo.javajump.model.level.CleanupManagerImpl;
import it.unibo.javajump.model.level.SpawnManager;
import it.unibo.javajump.model.level.SpawnManagerImpl;
import it.unibo.javajump.model.level.spawn.RandomSpawnStrategy;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManagerImpl;
import it.unibo.javajump.model.physics.PhysicsManager;
import it.unibo.javajump.model.physics.PhysicsManagerImpl;
import it.unibo.javajump.model.score.ScoreManager;
import it.unibo.javajump.model.score.ScoreManagerImpl;
import it.unibo.javajump.view.MainGameView;
import it.unibo.javajump.view.MainGameViewImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static it.unibo.javajump.utility.Constants.*;

public class GameInitializerImpl implements GameInitializer {
    private final GameModel model;
    private final JFrame frame;
    private final MainGameView view;
    private final InputManager inputManager;
    private final GameController controller;

    public GameInitializerImpl() {
        GameObjectFactory factory = new GameObjectFactoryImpl();
        DifficultyManager difficultyManager = new DifficultyManagerImpl();
        RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MINSPACING, MAXSPACING, COINCHANCE, difficultyManager);
        CollisionManager collisionManager = new CollisionManagerImpl();
        SpawnManager spawnManager = new SpawnManagerImpl(strategy);
        ScoreManager scoreManager = new ScoreManagerImpl();
        CameraManager cameraManager = new CameraManagerImpl(scoreManager, SCOREFACTOR);
        PhysicsManager physicsManager = new PhysicsManagerImpl(GRAVITY, ACCELERATION, MAXSPEED, DECELERATION);
        CleanupManager cleanupManager = new CleanupManagerImpl();
        this.model = new GameModelImpl(SCREENWIDTH,
                SCREENHEIGHT,
                physicsManager,
                collisionManager,
                spawnManager,
                cameraManager,
                scoreManager,
                cleanupManager,
                difficultyManager);
        this.frame = new JFrame(GAMETITLE);
        this.view = new MainGameViewImpl(model);
        this.inputManager = new InputManagerImpl();
        this.controller = new GameControllerImpl(model, view, inputManager);


    }

    public void initialize() {
        model.addObserver((GameModelObserver) view);
        frame.addKeyListener(inputManager);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREENWIDTH, SCREENHEIGHT);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setPreferredSize(e.getComponent().getSize());
                frame.pack();
            }
        });
        frame.add((Component) view);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        controller.startGameLoop();
    }

}
