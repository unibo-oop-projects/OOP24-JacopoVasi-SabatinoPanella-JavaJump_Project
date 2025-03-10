package it.unibo.javajump.model.entities.collectibles;

import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import static it.unibo.javajump.utility.Constants.*;

public class CoinImpl extends GameObjectImpl {

	private CoinState state;
	private PlatformImpl attachedPlatform;
	private float offsetX;

	public CoinImpl(float x, float y, float width, float height) {
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


	public void attachToPlatform(PlatformImpl platform) {
		this.attachedPlatform = platform;
		this.offsetX = this.x - platform.getX();
	}

	public CoinState getState() {
		return this.state;
	}

	public PlatformImpl getAttachedPlatform() {
		return attachedPlatform;
	}

	@Override
	public void onCollision(GameObjectImpl other) {
	}
}
