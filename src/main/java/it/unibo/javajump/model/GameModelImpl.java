package it.unibo.javajump.model;

import it.unibo.javajump.controller.GameAction;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.level.CleanupManagerImpl;
import it.unibo.javajump.model.level.SpawnManagerImpl;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManagerImpl;
import it.unibo.javajump.model.physics.PhysicsManagerImpl;
import it.unibo.javajump.model.score.ScoreManagerImpl;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.MenuState;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.*;

public class GameModelImpl implements GameModel {


	private GameStateHandler currentState;
	private final PhysicsManagerImpl physicsManagerImpl;
	private final CollisionManager collisionManager;
	private final SpawnManagerImpl spawnManagerImpl;
	private final CameraManager cameraManager;
	private final ScoreManagerImpl scoreManagerImpl;
	private final CleanupManagerImpl cleanupManagerImpl;
	private final DifficultyManagerImpl difficultyManagerImpl;


	private final List<GameObjectImpl> gameObjectImpls;
	private CharacterImpl player;


	private final int screenWidth;
	private final int screenHeight;


	private final List<GameModelObserver> observers;

	public GameModelImpl(
			int screenWidth,
			int screenHeight,
			PhysicsManagerImpl physicsManagerImpl,
			CollisionManager collisionManager,
			SpawnManagerImpl spawnManagerImpl,
			CameraManager cameraManager,
			ScoreManagerImpl scoreManagerImpl,
			CleanupManagerImpl cleanupManagerImpl,
			DifficultyManagerImpl difficultyManagerImpl
	) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		this.physicsManagerImpl = physicsManagerImpl;
		this.collisionManager = collisionManager;
		this.spawnManagerImpl = spawnManagerImpl;
		this.cameraManager = cameraManager;
		this.scoreManagerImpl = scoreManagerImpl;
		this.difficultyManagerImpl = difficultyManagerImpl;

		this.gameObjectImpls = new ArrayList<>();
		this.observers = new ArrayList<>();

		this.cleanupManagerImpl = cleanupManagerImpl;


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
		gameObjectImpls.clear();
		scoreManagerImpl.reset();
		cameraManager.resetCamera();
		spawnManagerImpl.reset();
		difficultyManagerImpl.reset();

		this.player = spawnManagerImpl.getFactory()
				.createCharacter(screenWidth / CHARACTERCREATIONWIDTHDIV, screenHeight * CHARACTERCREATIONHEIGHTMUL);
		gameObjectImpls.add(player);
		spawnManagerImpl.generateInitialLevel(this);
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
		return scoreManagerImpl.getCurrentScore();
	}

	public void addPointsToScore(int amount) {
		scoreManagerImpl.addPoints(amount);
	}

	public PhysicsManagerImpl getPhysicsManager() {
		return physicsManagerImpl;
	}

	public CollisionManager getCollisionManager() {
		return collisionManager;
	}

	public SpawnManagerImpl getSpawnManager() {
		return spawnManagerImpl;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public ScoreManagerImpl getScoreManager() {
		return scoreManagerImpl;
	}

	public CleanupManagerImpl getCleanupManager() {
		return cleanupManagerImpl;
	}

	public DifficultyManagerImpl getDifficultyManager() {
		return difficultyManagerImpl;
	}

	public GameStateHandler getCurrentState() {
		return currentState;
	}

	public List<GameObjectImpl> getGameObjects() {
		return this.gameObjectImpls;
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
