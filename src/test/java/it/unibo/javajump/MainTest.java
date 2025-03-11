package it.unibo.javajump;

import it.unibo.javajump.controller.GameController;
import it.unibo.javajump.controller.GameControllerImpl;
import it.unibo.javajump.controller.input.InputManager;
import it.unibo.javajump.controller.input.InputManagerImpl;
import it.unibo.javajump.model.*;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static it.unibo.javajump.utility.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private GameModel model;
    private GameController controller;
    private ScoreManager scoreManager;
    private JFrame frame;
    private MainGameView view;

    @BeforeEach
    void setUp() {
        // Initialize game components

        GameObjectFactory factory = new GameObjectFactoryImpl();
        DifficultyManager difficultyManager = new DifficultyManagerImpl();
        RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MINSPACING, MAXSPACING, COINCHANCE, difficultyManager);
        CollisionManager collisionManager = new CollisionManagerImpl();
        SpawnManager spawnManager = new SpawnManagerImpl(strategy);
        scoreManager = new ScoreManagerImpl();
        CameraManager cameraManager = new CameraManagerImpl(scoreManager, SCOREFACTOR);
        PhysicsManager physicsManager = new PhysicsManagerImpl(GRAVITY, ACCELERATION, MAXSPEED, DECELERATION);
        CleanupManager cleanupManager = new CleanupManagerImpl();

        model = new GameModelImpl(SCREENWIDTH, SCREENHEIGHT, physicsManager, collisionManager, spawnManager, cameraManager, scoreManager, cleanupManager, difficultyManager);

        // Initialize UI
        frame = new JFrame(GAMETITLE);
        view = new MainGameViewImpl(model);
        model.addObserver((GameModelObserver) view);

        // Initialize Input and Controller
        InputManager inputManager = new InputManagerImpl();
        frame.addKeyListener(inputManager);
        controller = new GameControllerImpl(model, view, inputManager);
    }

    @Test
    void testGameModelInitialization() {
        assertNotNull(model, "Game model should be initialized.");
        assertEquals(SCREENWIDTH, model.getScreenWidth(), "Screen width should match the constant.");
        assertEquals(SCREENHEIGHT, model.getScreenHeight(), "Screen height should match the constant.");
    }

    @Test
    void testGameControllerInitialization() {
        assertNotNull(controller, "Game controller should be initialized.");
    }

    @Test
    void testScoreManagerStartsAtZero() {
        assertEquals(0, scoreManager.getCurrentScore(), "Initial score should be zero.");
    }

    @Test
    void testFrameInitialization() {
        assertNotNull(frame, "Game window should be initialized.");
        assertEquals(GAMETITLE, frame.getTitle(), "Frame title should match the constant.");
    }

    @Test
    void testViewIsAddedToFrame() {
        frame.add((JComponent) view);
        assertEquals(1, frame.getContentPane().getComponentCount(), "View should be added to the frame.");
    }

}
