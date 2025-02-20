package model;

import controller.GameAction;
import model.camera.CameraManager;
import model.collision.CollisionManager;
import model.entities.GameObject;
import model.entities.Character;
import model.entities.Platform;
import model.entities.Coin;
import model.factories.AbstractGameObjectFactory;
import model.collision.CollisionManager;
import model.level.SpawnManager;
import model.physics.MovementDirection;
import model.physics.PhysicsManager;
import model.score.ScoreManager;
import model.states.GameStateHandler;
import model.states.MenuState;

import java.util.ArrayList;
import java.util.List;

public class GameModel
{
	private List<GameObject> gameObjects;
	private Character player;
	private GameState currentState;
	private MovementDirection inputDirection;


	private int screenWidth;
	private int screenHeight;

	private final List<GameModelObserver> observers;
	private final AbstractGameObjectFactory factory;
	private final CollisionManager collisionManager;
	private final PhysicsManager physicsManager;
	private final SpawnManager spawnManager;
	private final ScoreManager scoreManager;
	private final CameraManager cameraManager;
	
	public GameModel(int screenWidth, int screenHeight,
					 AbstractGameObjectFactory factory,
					 CollisionManager collisionManager,
					 PhysicsManager physicsManager,
					 SpawnManager spawnManager,
					 ScoreManager scoreManager,
					 CameraManager cameraManager)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.collisionManager = collisionManager;
		this.factory = factory;
		this.spawnManager = spawnManager;
		this.scoreManager = scoreManager;
		this.cameraManager = cameraManager;
		this.physicsManager = physicsManager;

		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();
		this.currentState = GameState.MENU;
		this.inputDirection = MovementDirection.NONE;
	}

	
	public void addObserver(GameModelObserver obs)
	{
		observers.add(obs);
	}

	
	public void removeObserver(GameModelObserver obs)
	{
		observers.remove(obs);
	}

	
	private void notifyObservers()
	{
		for (GameModelObserver obs : observers)
		{
			obs.onModelUpdate(this);
		}
	}

	
	public void startGame()
	{

		this.gameObjects.clear();
		this.currentState = GameState.IN_GAME;
		scoreManager.reset();


		this.player = factory.createCharacter
		(
				screenWidth / 2f,
				screenHeight - 100
		);
		gameObjects.add(player);


		spawnManager.generateInitialLevel(this);

		notifyObservers();
	}

	public void setInputDirection(MovementDirection dir)
	{
		this.inputDirection = dir;
	}

	public MovementDirection getInputDirection()
	{
		return this.inputDirection;
	}

	
	public void update(float deltaTime)
	{

		if (this.currentState != GameState.IN_GAME)
		{
			return;
		}

		physicsManager.updateCharacterMovement(player, deltaTime, this.inputDirection);


		for (GameObject go : gameObjects)
		{
			go.update(deltaTime);
		}


		collisionManager.checkCollisions(this);


		cameraManager.update(this, deltaTime);


		spawnManager.generateOnTheFly(this);


		checkGameOverCondition();


		notifyObservers();
	}


	private void checkGameOverCondition()
	{

		if (player.getY() > screenHeight)
		{
			this.currentState = GameState.GAME_OVER;
		}
	}

	public void addPointsToScore(int amount)
	{
		scoreManager.addPoints(amount);
	}




	public int getScore()
	{
		return scoreManager.getCurrentScore();
	}

	public GameState getState()
	{
		return this.currentState;
	}

	public void setState(GameState newState)
	{
		this.currentState = newState;
		notifyObservers();
	}

	public List<GameObject> getGameObjects()
	{
		return this.gameObjects;
	}


}

public class GameModel
{
	private GameStateHandler currentState;

	private final PhysicsManager physicsManager;
	private final CollisionManager collisionManager;
	private final SpawnManager spawnManager;
	private final CameraManager cameraManager;
	private final ScoreManager scoreManager;

	private final List<GameObject> gameObjects;
	private Character player;

	private final List<GameModelObserver> observers;
	private int screenWidth;
	private int screenHeight;

	public GameModel(
			int screenWidth,
			int screenHeight,
			PhysicsManager physicsManager,
			CollisionManager collisionManager,
			SpawnManager spawnManager,
			CameraManager cameraManager,
			ScoreManager scoreManager
	) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		this.physicsManager = physicsManager;
		this.collisionManager = collisionManager;
		this.spawnManager = spawnManager;
		this.cameraManager = cameraManager;
		this.scoreManager = scoreManager;

		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();


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

	public void startGame()
	{
		gameObjects.clear();
		scoreManager.reset();





		this.player = spawnManager.getFactory()
				.createCharacter(screenWidth / 2f, screenHeight - 100);

		gameObjects.add(this.player);


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


	public PhysicsManager getPhysicsManager() { return physicsManager; }
	public CollisionManager getCollisionManager() { return collisionManager; }
	public SpawnManager getSpawnManager() { return spawnManager; }
	public CameraManager getCameraManager() { return cameraManager; }
	public ScoreManager getScoreManager() { return scoreManager; }

	public List<GameObject> getGameObjects() { return this.gameObjects; }
	public Character getPlayer() { return player; }

	public int getScreenWidth() { return screenWidth; }
	public int getScreenHeight() { return screenHeight; }
}
