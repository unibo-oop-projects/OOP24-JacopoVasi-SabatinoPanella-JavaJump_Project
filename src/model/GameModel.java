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
import model.states.GameState;
import model.states.GameStateHandler;
import model.states.MenuState;
import view.GameFrame;
import view.GameView;
import view.ViewManager;

import java.util.ArrayList;
import java.util.List;


public class GameModel
{

	private GameStateHandler currentState;
	private final ViewManager viewManager;
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
			ScoreManager scoreManager)

	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.viewManager=new ViewManager(this);
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


	public void setState(GameStateHandler newState)
	{
		this.currentState.onExit(this);
		this.currentState = newState;
		this.currentState.onEnter(this);
	}

	public void handleAction(GameAction action)
	{
		this.currentState.handleAction(this, action);
		System.out.println("handleAction");
	}

	public void update(float deltaTime) {
		this.currentState.update(this, deltaTime);
		this.viewManager.draw();
		if (this.currentState.getGameState()== GameState.IN_GAME) {
			this.player.update(deltaTime);
			this.cameraManager.update(this, deltaTime);
			this.gameObjects.getFirst().update(deltaTime);
		}
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



	public int getScore()
	{
		return scoreManager.getCurrentScore();
	}

	public void addPointsToScore(int amount)
	{
		scoreManager.addPoints(amount);
	}


	public PhysicsManager getPhysicsManager() { return physicsManager; }
	public CollisionManager getCollisionManager() { return collisionManager; }
	public SpawnManager getSpawnManager() { return spawnManager; }
	public CameraManager getCameraManager() { return cameraManager; }
	public ScoreManager getScoreManager() { return scoreManager; }
	public GameStateHandler getCurrentState() {return currentState;}
	public List<GameObject> getGameObjects() { return this.gameObjects; }
	public Character getPlayer() { return player; }
	public int getScreenWidth() { return screenWidth; }
	public int getScreenHeight() { return screenHeight; }
	public ViewManager getViewManager() {return viewManager;}
}
