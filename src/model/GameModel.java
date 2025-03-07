package model;

import controller.GameAction;
import model.camera.CameraManager;
import model.collision.CollisionManager;
import model.entities.GameObject;
import model.entities.character.Character;
import model.level.CleanupManager;
import model.level.SpawnManager;
import model.level.spawn.difficulty.DifficultyManager;
import model.physics.PhysicsManager;
import model.score.ScoreManager;
import model.states.GameStateHandler;
import model.states.MenuState;

import java.util.ArrayList;
import java.util.List;

public class GameModel
{


	private GameStateHandler currentState;
	private final PhysicsManager physicsManager;
	private final CollisionManager collisionManager;
	private final SpawnManager spawnManager;
	private final CameraManager cameraManager;
	private final ScoreManager scoreManager;
	private final CleanupManager cleanupManager;
	private final DifficultyManager difficultyManager;


	private final List<GameObject> gameObjects;
	private Character player;


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


	public void setState(GameStateHandler newState)
	{
		this.currentState.onExit(this);
		this.currentState = newState;
		this.currentState.onEnter(this);
	}

	public void handleAction(GameAction action)
	{
		this.currentState.handleAction(this, action);
	}

	public void update(float deltaTime) {
		this.currentState.update(this, deltaTime);
	}

	public void startGame()
	{
		gameObjects.clear();
		scoreManager.reset();
		cameraManager.resetCamera();
		spawnManager.reset();
		difficultyManager.reset();

		this.player = spawnManager.getFactory()
								  .createCharacter(screenWidth / 2f, screenHeight*0.8f);
		gameObjects.add(player);
		spawnManager.generateInitialLevel(this);
	}

	public void addObserver(GameModelObserver obs) { observers.add(obs); }
	public void removeObserver(GameModelObserver obs) { observers.remove(obs); }
	public void notifyObservers()
	{
		for (GameModelObserver obs : observers)
		{
			obs.onModelUpdate(this);
		}
	}

	public int getScore()
	{
		return scoreManager.getCurrentScore();
	}
	public void addPointsToScore(int amount)
	{
		scoreManager.addPoints(amount);
		System.out.println("Amount: "+amount);
	}

	public PhysicsManager getPhysicsManager() { return physicsManager; }
	public CollisionManager getCollisionManager() { return collisionManager; }
	public SpawnManager getSpawnManager() { return spawnManager; }
	public CameraManager getCameraManager() { return cameraManager; }
	public ScoreManager getScoreManager() { return scoreManager; }
	public CleanupManager getCleanupManager() { return cleanupManager; }
	public DifficultyManager getDifficultyManager() { return difficultyManager; }
	public GameStateHandler getCurrentState() {return currentState;}
	public List<GameObject> getGameObjects() { return this.gameObjects; }
	public Character getPlayer() { return player; }
	public int getScreenWidth() { return screenWidth; }
	public int getScreenHeight() { return screenHeight; }
}
