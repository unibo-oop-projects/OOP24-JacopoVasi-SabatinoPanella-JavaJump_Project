package it.unibo.javajump.model;

import it.unibo.javajump.controller.GameAction;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.level.CleanupManager;
import it.unibo.javajump.model.level.SpawnManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.physics.PhysicsManager;
import it.unibo.javajump.model.score.ScoreManager;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.MenuState;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.*;

public class GameModel {


	private GameStateHandler currentState;
	private final PhysicsManager physicsManager;
	private final CollisionManager collisionManager;
	private final SpawnManager spawnManager;
	private final CameraManager cameraManager;
	private final ScoreManager scoreManager;
	private final CleanupManager cleanupManager;
	private final DifficultyManager difficultyManager;


	private final List<GameObjectImpl> gameObjects;
	private CharacterImpl player;


	private final int screenWidth;
	private final int screenHeight;


	private final List<GameModelObserver> observers;

	public GameModel(
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

		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();

		this.cleanupManager = cleanupManager;


		this.currentState = new MenuState();
		this.currentState.onEnter(this);
	}


	public void setState(GameStateHandler newState) {
		this.currentState.onExit(this);
		this.currentState = newState;
		this.currentState.onEnter(this);
	}

	public void handleAction(GameAction action) {
		this.currentState.handleAction(this, action);
	}

	public void update(float deltaTime) {
		this.currentState.update(this, deltaTime);
	}

	public void startGame() {
		gameObjects.clear();
		scoreManager.reset();
		cameraManager.resetCamera();
		spawnManager.reset();
		difficultyManager.reset();

		this.player = spawnManager.getFactory()
				.createCharacter(screenWidth / CHARACTERCREATIONWIDTHDIV, screenHeight * CHARACTERCREATIONHEIGHTMUL);
		gameObjects.add(player);
		spawnManager.generateInitialLevel(this);
	}

	public void addObserver(GameModelObserver obs) {
		observers.add(obs);
	}

	public void removeObserver(GameModelObserver obs) {
		observers.remove(obs);
	}

	public void notifyObservers() {
		for (GameModelObserver obs : observers) {
			obs.onModelUpdate(this);
		}
	}


	public int getScore() {
		return scoreManager.getCurrentScore();
	}

	public void addPointsToScore(int amount) {
		scoreManager.addPoints(amount);
	}

	public PhysicsManager getPhysicsManager() {
		return physicsManager;
	}

	public CollisionManager getCollisionManager() {
		return collisionManager;
	}

	public SpawnManager getSpawnManager() {
		return spawnManager;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public ScoreManager getScoreManager() {
		return scoreManager;
	}

	public CleanupManager getCleanupManager() {
		return cleanupManager;
	}

	public DifficultyManager getDifficultyManager() {
		return difficultyManager;
	}

	public GameStateHandler getCurrentState() {
		return currentState;
	}

	public List<GameObjectImpl> getGameObjects() {
		return this.gameObjects;
	}

	public CharacterImpl getPlayer() {
		return player;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
}
