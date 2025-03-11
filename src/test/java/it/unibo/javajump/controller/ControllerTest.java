package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.controller.input.InputManager;
import it.unibo.javajump.controller.input.InputManagerImpl;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.camera.CameraManagerImpl;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.collision.CollisionManagerImpl;
import it.unibo.javajump.model.entities.character.Character;
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
import static it.unibo.javajump.utility.Constants.DECELERATION;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private GameControllerImpl gameController;
    private GameModel gameModel;

    private InputManager inputManager;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private  JTextField testComponent = new JTextField();
    @BeforeEach
    void setUp() {
        // Create actual instances of required dependencies
        GameObjectFactory factory = new GameObjectFactoryImpl();
        DifficultyManager difficultyManager = new DifficultyManagerImpl();
        RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MINSPACING, MAXSPACING, COINCHANCE, difficultyManager);
        CollisionManager collisionManager = new CollisionManagerImpl();
        SpawnManager spawnManager = new SpawnManagerImpl(strategy);
        ScoreManager scoreManager = new ScoreManagerImpl();
        CameraManager cameraManager = new CameraManagerImpl(scoreManager, SCOREFACTOR);
        PhysicsManager physicsManager = new PhysicsManagerImpl(GRAVITY, ACCELERATION, MAXSPEED, DECELERATION);
        CleanupManager cleanupManager = new CleanupManagerImpl();

        gameModel = new GameModelImpl(
                SCREEN_WIDTH, SCREEN_HEIGHT, physicsManager, collisionManager, spawnManager,
                cameraManager, scoreManager, cleanupManager, difficultyManager
        );
        MainGameView view = new MainGameViewImpl(gameModel);

        inputManager = new InputManagerImpl(); // Using a test implementation

        gameController = new GameControllerImpl(gameModel, view, inputManager);
    }
/*
    @Test
    void testStartAndStopGameLoop() throws InterruptedException {
        gameController.startGameLoop();
       Thread.sleep(2000); // Allow some time for the loop to run
        gameController.stopGameLoop();

        assertFalse(isGameLoopRunning(), "Game loop should stop successfully");
    }
*/
    @Test
    void testUpdateModelHandlesInput() {
        gameModel.startGame();
        Character playerBefore = gameModel.getPlayer();
        assertNotNull(playerBefore, "Player should be initialized");

        inputManager.getActionQueue().add(GameAction.MOVE_RIGHT);
        gameController.startGameLoop();
        gameController.stopGameLoop(); // Stop loop after one iteration

        assertNotNull(gameModel.getPlayer(), "Player should still exist after movement");
    }

    @Test
    void testProcessDiscreteInput() {

        inputManager.getActionQueue().add(GameAction.MOVE_LEFT);
        gameController.startGameLoop();
        gameController.stopGameLoop(); // Process one frame
        assertEquals(NULLDIRECTION, inputManager.getHorizontalDirection());
    }

    //; Helper method to check if the game loop is running
    private boolean isGameLoopRunning() {
        try {
            Thread.sleep(50);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }


}
