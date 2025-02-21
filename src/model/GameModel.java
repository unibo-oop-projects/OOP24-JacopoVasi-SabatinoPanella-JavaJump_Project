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
import view.GameFrame;
import view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameModel
{
	private final GameFrame gameFrame;
	private final GameView gameView;
	private final AbstractGameObjectFactory gameFactory;
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
			ScoreManager scoreManager,
			GameView view,
			GameFrame frame,
			AbstractGameObjectFactory factory
	)

	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.gameFactory= factory;
		this.physicsManager = physicsManager;
		this.collisionManager = collisionManager;
		this.spawnManager = spawnManager;
		this.cameraManager = cameraManager;
		this.scoreManager = scoreManager;
		this.gameFrame = frame;
		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();
		this.gameView = view;this.gameFrame.add(gameView);
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

	public void update(float deltaTime)
	{
		this.currentState.update(this, deltaTime);
	}

	public void startGame()
	{
		gameObjects.clear();
		scoreManager.reset();



this.player = spawnManager.getFactory()
				.createCharacter(screenWidth / 2f, screenHeight - 100);gameObjects.add(this.player);
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
