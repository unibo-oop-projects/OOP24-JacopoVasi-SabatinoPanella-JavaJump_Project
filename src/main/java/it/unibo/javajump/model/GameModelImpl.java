package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.camera.CameraManagerImpl;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.collision.CollisionManagerImpl;
import it.unibo.javajump.model.entities.GameObject;
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

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.ACCELERATION;
import static it.unibo.javajump.utility.Constants.CHARACTER_CREATION_HEIGHT_MUL;
import static it.unibo.javajump.utility.Constants.CHARACTER_CREATION_WIDTH_DIV;
import static it.unibo.javajump.utility.Constants.COIN_CHANCE;
import static it.unibo.javajump.utility.Constants.DECELERATION;
import static it.unibo.javajump.utility.Constants.GRAVITY;
import static it.unibo.javajump.utility.Constants.MAX_SPACING;
import static it.unibo.javajump.utility.Constants.MAX_SPEED;
import static it.unibo.javajump.utility.Constants.MIN_SPACING;
import static it.unibo.javajump.utility.Constants.SCORE_FACTOR;

public class GameModelImpl implements GameModel {


    private GameStateHandler currentState;
    private final PhysicsManager physicsManager;
    private final CollisionManager collisionManager;
    private final SpawnManager spawnManager;
    private final CameraManager cameraManager;
    private final ScoreManager scoreManager;
    private final CleanupManager cleanupManager;
    private final DifficultyManager difficultyManager;
    private float deltaTime;
    private final List<GameObject> gameObject;
    private Character player;

    private final int screenWidth;
    private final int screenHeight;

    private boolean isRunning;

    private final List<GameModelObserver> observers;

    public GameModelImpl(
            final int screenWidth,
            final  int screenHeight
    ) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.difficultyManager = new DifficultyManagerImpl();
        final GameObjectFactory factory = new GameObjectFactoryImpl();
        final RandomSpawnStrategy strategy = new RandomSpawnStrategy(factory, MIN_SPACING, MAX_SPACING, COIN_CHANCE, difficultyManager);
        this.physicsManager = new PhysicsManagerImpl(GRAVITY, ACCELERATION, MAX_SPEED, DECELERATION);
        this.collisionManager = new CollisionManagerImpl();
        this.spawnManager = new SpawnManagerImpl(strategy);
        this.scoreManager = new ScoreManagerImpl();
        this.cameraManager = new CameraManagerImpl(scoreManager, SCORE_FACTOR);

        this.isRunning = true;

        this.gameObject = new ArrayList<>();
        this.observers = new ArrayList<>();

        this.cleanupManager = new CleanupManagerImpl();

        this.currentState = new MenuState();
        this.currentState.onEnter(this);
    }


    @Override
    public void setState(final GameStateHandler newState) {
        this.currentState.onExit(this);
        this.currentState = newState;
        this.currentState.onEnter(this);
    }

    @Override
    public void handleAction(final GameAction action) {
        this.currentState.handleAction(this, action);
    }

    @Override
    public void update(final float deltaTime) {
        this.deltaTime = deltaTime;
        this.currentState.update(this, deltaTime);
    }

    @Override
    public void startGame() {
        gameObject.clear();
        scoreManager.reset();
        cameraManager.resetCamera();
        spawnManager.reset();
        difficultyManager.reset();

        this.player = spawnManager.getFactory()
                .createCharacter(screenWidth / CHARACTER_CREATION_WIDTH_DIV, screenHeight * CHARACTER_CREATION_HEIGHT_MUL);
        gameObject.add(player);
        spawnManager.generateInitialLevel(this);
    }

    @Override
    public void addObserver(final GameModelObserver obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(final GameModelObserver obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (final GameModelObserver obs : observers) {
            obs.onModelUpdate(this);
        }
    }

    @Override
    public int getScore() {
        return scoreManager.getCurrentScore();
    }

    @Override
    public void addPointsToScore(final int amount) {
        scoreManager.addPoints(amount);
    }

    @Override
    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }

    @Override
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    @Override
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    @Override
    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    @Override
    public CleanupManager getCleanupManager() {
        return cleanupManager;
    }

    @Override
    public DifficultyManager getDifficultyManager() {
        return difficultyManager;
    }

    @Override
    public GameStateHandler getCurrentState() {
        return currentState;
    }

    @Override
    public List<GameObject> getGameObjects() {
        return this.gameObject;
    }

    @Override
    public Character getPlayer() {
        return player;
    }

    @Override
    public int getScreenWidth() {
        return screenWidth;
    }

    @Override
    public int getScreenHeight() {
        return screenHeight;
    }

    @Override
    public float getDeltaTime() {
        return deltaTime;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void stopGame() {
        isRunning = false;
    }
}
