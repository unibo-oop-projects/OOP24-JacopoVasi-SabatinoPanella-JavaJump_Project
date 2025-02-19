package model;

import model.collision.CollisionManager;
import model.entities.GameObject;
import model.entities.Character;
import model.entities.Platform;
import model.entities.Coin;
import model.factories.AbstractGameObjectFactory;
import model.collision.CollisionManager;

import java.util.ArrayList;
import java.util.List;

public class GameModel
{
	private List<GameObject> gameObjects;
	private Character player;
	private GameState currentState;
	private int score;

	private int screenWidth;
	private int screenHeight;

	// OBSERVER
	private final List<GameModelObserver> observers;

	// FACTORY
	private final AbstractGameObjectFactory factory;

	private final CollisionManager collisionManager;

	public GameModel(int screenWidth, int screenHeight, AbstractGameObjectFactory factory, CollisionManager collisionManager)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.collisionManager = collisionManager;
		this.factory = factory;

		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();
		this.currentState = GameState.MENU;
		this.score = 0;
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
		this.score = 0;
		this.currentState = GameState.IN_GAME;

		this.player = factory.createCharacter
		(
				screenWidth / 2f,
				screenHeight - 100
		);
		gameObjects.add(player);

		gameObjects.add(factory.createStandardPlatform(screenWidth / 2f - 50, screenHeight - 50));
		gameObjects.add(factory.createRandomPlatform(screenWidth / 2f - 50, screenHeight - 150));
		gameObjects.add(factory.createRandomPlatform(screenWidth / 2f - 50, screenHeight - 250));

		gameObjects.add(factory.createCoin(screenWidth / 2f, screenHeight - 300));

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

		checkGameOverCondition();

		notifyObservers();
	}

	private void checkGameOverCondition() {
		if (player.getY() > screenHeight) {
			this.currentState = GameState.GAME_OVER;
		}
	}

	public void increaseScore(int amount) {
		this.score += amount;
	}


	public int getScore() {
		return this.score;
	}

	public GameState getState() {
		return this.currentState;
	}

	public void setState(GameState newState) {
		this.currentState = newState;
		notifyObservers();
	}

	public List<GameObject> getGameObjects() {
		return this.gameObjects;
	}

	public Character getPlayer() {
		return this.player;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
}
