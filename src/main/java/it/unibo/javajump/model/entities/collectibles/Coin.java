package it.unibo.javajump.model.entities.collectibles;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.platforms.Platform;

import static it.unibo.javajump.utility.Constants.*;

public class Coin extends GameObject {

	private CoinState state;
	private Platform attachedPlatform;
	private float offsetX;

	public Coin(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = CoinState.IDLE;
		this.attachedPlatform = null;
		this.offsetX = OFFSETINIT;
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
		this.state = CoinState.FINISHED;
	}


	public void attachToPlatform(Platform platform) {
		this.attachedPlatform = platform;
		this.offsetX = this.x - platform.getX();
	}

	public CoinState getState() {
		return this.state;
	}

	public Platform getAttachedPlatform() {
		return attachedPlatform;
	}

	@Override
	public void onCollision(GameObject other) {
	}
}
