package model.entities.collectibles;

import model.entities.GameObject;
import model.entities.platforms.Platform;

public class Coin extends GameObject
{
	private CoinState state;
	private boolean isDone;

	private Platform attachedPlatform;
	private float offsetX;

	public Coin(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = CoinState.IDLE;
		this.isDone = false;
		this.attachedPlatform = null;
		this.offsetX = 0;
	}

	@Override
	public void update(float deltaTime) {
		if (attachedPlatform != null) {
			this.x = attachedPlatform.getX() + offsetX;
		}
	}

	
	public void collect() {
		if (this.state == CoinState.IDLE) {
			this.state = CoinState.COLLECTING;
		}
	}

	public void markAsDone() {
		this.isDone = true;
	}

	public void attachToPlatform(Platform platform) {
		this.attachedPlatform = platform;
		this.offsetX = this.x - platform.getX();
	}

	public CoinState getState() {
		return this.state;
	}

	public boolean getIsDone() {
		return this.isDone;
	}

	public Platform getAttachedPlatform() {
		return attachedPlatform;
	}

	@Override
	public void onCollision(GameObject other)
	{

	}
}