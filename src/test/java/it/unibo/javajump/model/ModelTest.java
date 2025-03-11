package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
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
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.utility.Constants.DECELERATION;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private GameModelImpl gameModel;
    private PhysicsManager physicsManager;
    private CollisionManager collisionManager;
    private SpawnManager spawnManager;
    private CameraManager cameraManager;
    private ScoreManager scoreManager;
    private CleanupManager cleanupManager;
    private DifficultyManager difficultyManager;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    @BeforeEach
    void setUp() {
        // Instantiate real implementations of the dependencies
        GameObjectFactory factory = new GameObjectFactoryImpl();
         difficultyManager = new DifficultyManagerImpl();
        RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MINSPACING, MAXSPACING, COINCHANCE, difficultyManager);
         collisionManager = new CollisionManagerImpl();
         spawnManager = new SpawnManagerImpl(strategy);
         scoreManager = new ScoreManagerImpl();
         cameraManager = new CameraManagerImpl(scoreManager, SCOREFACTOR);
         physicsManager = new PhysicsManagerImpl(GRAVITY, ACCELERATION, MAXSPEED, DECELERATION);
         cleanupManager = new CleanupManagerImpl();

        // Create the GameModelImpl instance
        gameModel = new GameModelImpl(
                SCREEN_WIDTH, SCREEN_HEIGHT, physicsManager, collisionManager, spawnManager,
                cameraManager, scoreManager, cleanupManager, difficultyManager
        );
    }

    @Test
    void testInitialStateIsMenu() {
        assertInstanceOf(MenuState.class, gameModel.getCurrentState(), "Initial state should be MenuState");
    }

    @Test
    void testSetStateChangesState() {
        GameStateHandler newState = new MenuState();
        gameModel.setState(newState);
        assertEquals(newState, gameModel.getCurrentState(), "State should change correctly");
    }

    @Test
    void testHandleAction() {
        GameAction action = GameAction.CONFIRM_SELECTION; // Provide an empty implementation
        gameModel.handleAction(action);
        assertNotNull(gameModel.getCurrentState(), "Action should be handled without errors");
    }

    @Test
    void testUpdate() {
        float deltaTime = 0.016f;
        gameModel.update(deltaTime);
        assertEquals(deltaTime, gameModel.getDeltaTime(), "Delta time should be updated correctly");
    }

    @Test
    void testStartGameInitializesEntities() {
        gameModel.startGame();
        Character player = gameModel.getPlayer();
        assertNotNull(player, "Player should be initialized");
        assertTrue(gameModel.getGameObjects().contains(player), "Player should be part of game objects");
        assertEquals(0, gameModel.getScore(), "Score should reset to zero");
    }

    @Test
    void testObserverNotification() {
        TestObserver observer = new TestObserver();
        gameModel.addObserver(observer);
        gameModel.notifyObservers();
        assertTrue(observer.updated, "Observer should be notified");
    }

    @Test
    void testScoreManagement() {
        gameModel.addPointsToScore(50);
        assertEquals(50, gameModel.getScore(), "Score should increase correctly");
    }

    @Test
    void testGetters() {
        assertEquals(SCREEN_WIDTH, gameModel.getScreenWidth());
        assertEquals(SCREEN_HEIGHT, gameModel.getScreenHeight());
        assertEquals(physicsManager, gameModel.getPhysicsManager());
        assertEquals(collisionManager, gameModel.getCollisionManager());
        assertEquals(spawnManager, gameModel.getSpawnManager());
        assertEquals(cameraManager, gameModel.getCameraManager());
        assertEquals(scoreManager, gameModel.getScoreManager());
        assertEquals(cleanupManager, gameModel.getCleanupManager());
        assertEquals(difficultyManager, gameModel.getDifficultyManager());
    }

    // Inner class to test observer pattern
    private static class TestObserver implements GameModelObserver {
        boolean updated = false;

        @Override
        public void onModelUpdate(GameModel model) {
            updated = true;
        }
    }
}
