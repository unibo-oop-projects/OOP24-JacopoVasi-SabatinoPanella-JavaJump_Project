package model;

import model.entities.GameObject;
import model.entities.Character;
import model.entities.Platform;
import model.entities.Coin;
import model.factories.AbstractGameObjectFactory;

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
	private AbstractGameObjectFactory factory;


	public GameModel(int screenWidth, int screenHeight, AbstractGameObjectFactory factory)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.gameObjects = new ArrayList<>();
		this.observers = new ArrayList<>();
		this.currentState = GameState.MENU;  // stato iniziale
		this.score = 0;
		this.factory = factory;
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

		Platform p1 = factory.createStandardPlatform(screenWidth / 2f - 50, screenHeight - 50);
		Platform p2 = factory.createRandomPlatform(screenWidth / 2f - 50, screenHeight - 150);
		Platform p3 = factory.createRandomPlatform(screenWidth / 2f - 50, screenHeight - 250);
		gameObjects.add(p1);
		gameObjects.add(p2);
		gameObjects.add(p3);


		Coin c1 = factory.createCoin(screenWidth / 2f, screenHeight - 300);
		gameObjects.add(c1);

		notifyObservers();
	}


	public void update(float deltaTime)
	{
		// Se non siamo in IN_GAME, non aggiornare la logica
		if (this.currentState != GameState.IN_GAME)
		{
			return;
		}

		for (GameObject go : gameObjects)
		{
			go.update(deltaTime);
		}

		checkCollisions();


		checkGameOverCondition();


		notifyObservers();
	}


	private void checkCollisions() {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject a = gameObjects.get(i);
			for (int j = i + 1; j < gameObjects.size(); j++) {
				GameObject b = gameObjects.get(j);

				if (isColliding(a, b)) {
					a.onCollision(b);
					b.onCollision(a);

					if (a instanceof Character && b instanceof Coin) {

						gameObjects.remove(b);

						increaseScore(50);

						break;
					} else if (b instanceof Character && a instanceof Coin) {
						gameObjects.remove(a);
						increaseScore(50);
						break;
					}
				}
			}
		}
	}


	private boolean isColliding(GameObject a, GameObject b) {
		return a.getX() < b.getX() + b.getWidth() &&
				a.getX() + a.getWidth() > b.getX() &&
				a.getY() < b.getY() + b.getHeight() &&
				a.getY() + a.getHeight() > b.getY();
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
