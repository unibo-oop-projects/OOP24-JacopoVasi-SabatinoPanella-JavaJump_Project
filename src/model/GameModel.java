package model;

import model.camera.CameraManager;
import model.collision.CollisionManager;
import model.entities.GameObject;
import model.entities.Character;
import model.entities.Platform;
import model.entities.Coin;
import model.factories.AbstractGameObjectFactory;
import model.collision.CollisionManager;
import model.level.SpawnManager;
import model.score.ScoreManager;

import java.util.ArrayList;
import java.util.List;

public class GameModel
{
	private List<GameObject> gameObjects;
	private Character player;
	private GameState currentState;

	private int screenWidth;
	private int screenHeight;

	private final List<GameModelObserver> observers;
	private final AbstractGameObjectFactory factory;
	private final CollisionManager collisionManager;
	private final SpawnManager spawnManager;
	private final ScoreManager scoreManager;
	private final CameraManager cameraManager;


	public GameModel(int screenWidth, int screenHeight,
					 AbstractGameObjectFactory factory,
					 CollisionManager collisionManager,
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

		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();
		this.currentState = GameState.MENU;
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

	public void update(float deltaTime)
	{
		if (this.currentState != GameState.IN_GAME)
		{
			return;
		}

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

	public Character getPlayer()
	{
		return this.player;
	}

	public int getScreenWidth()
	{
		return screenWidth;
	}

	public int getScreenHeight()
	{
		return screenHeight;
	}
}
