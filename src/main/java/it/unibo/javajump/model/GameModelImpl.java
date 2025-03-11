package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.level.CleanupManager;
import it.unibo.javajump.model.level.SpawnManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.physics.PhysicsManager;
import it.unibo.javajump.model.score.ScoreManager;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.*;

public class GameModelImpl implements GameModel {


	private GameStateHandler currentState;
	private final PhysicsManager physicsManager;
	private final CollisionManager collisionManager;
	private final SpawnManager spawnManager;
	private final CameraManager cameraManager;
	private final ScoreManager scoreManager;
	private final CleanupManager cleanupManager;
	private final DifficultyManager difficultyManager;
	private float deltaTime = 0;
	private final List<GameObject> gameObject;
	private Character player;

	private final int screenWidth;
	private final int screenHeight;


	private final List<GameModelObserver> observers;

	public GameModelImpl(
			int screenWidth,
			int screenHeight,
			PhysicsManager physicsManager,
			CollisionManager collisionManager,
			SpawnManager spawnManager,
			CameraManager cameraManager,
			ScoreManager scoreManager,
			CleanupManager cleanupManager,
			DifficultyManager difficultyManager
	) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		this.physicsManager = physicsManager;
		this.collisionManager = collisionManager;
		this.spawnManager = spawnManager;
		this.cameraManager = cameraManager;
		this.scoreManager = scoreManager;
		this.difficultyManager = difficultyManager;

		this.gameObject = new ArrayList<>();
		this.observers = new ArrayList<>();

		this.cleanupManager = cleanupManager;

		this.currentState = new MenuState();
		this.currentState.onEnter(this);
	}


	@Override
	public void setState(GameStateHandler newState) {
		this.currentState.onExit(this);
		this.currentState = newState;
		this.currentState.onEnter(this);
	}

	@Override
	public void handleAction(GameAction action) {
		this.currentState.handleAction(this, action);
	}

	@Override
	public void update(float deltaTime) {
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
				.createCharacter(screenWidth / CHARACTERCREATIONWIDTHDIV, screenHeight * CHARACTERCREATIONHEIGHTMUL);
		gameObject.add(player);
		spawnManager.generateInitialLevel(this);
	}

	@Override
	public void addObserver(GameModelObserver obs) {
		observers.add(obs);
	}

	@Override
	public void removeObserver(GameModelObserver obs) {
		observers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for (GameModelObserver obs : observers) {
			obs.onModelUpdate(this);
		}
	}

	@Override
	public int getScore() {
		return scoreManager.getCurrentScore();
	}

	@Override
	public void addPointsToScore(int amount) {
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

	public float getDeltaTime() {
		return deltaTime;
	}
}
