package it.unibo.javajump.model.entities.collectibles;

import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import static it.unibo.javajump.utility.Constants.*;

public class CoinImpl extends GameObjectImpl implements Coin {

	private CoinState state;
	private PlatformImpl attachedPlatformImpl;
	private float offsetX;

	public CoinImpl(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = CoinState.IDLE;
		this.attachedPlatformImpl = null;
		this.offsetX = OFFSETINIT;
	}

	@Override
	public void update(float deltaTime) {
		if (attachedPlatformImpl != null) {
			this.x = attachedPlatformImpl.getX() + offsetX;
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


	public void attachToPlatform(PlatformImpl platformImpl) {
		this.attachedPlatformImpl = platformImpl;
		this.offsetX = this.x - platformImpl.getX();
	}

	public CoinState getState() {
		return this.state;
	}

	public PlatformImpl getAttachedPlatform() {
		return attachedPlatformImpl;
	}

	@Override
	public void onCollision(GameObjectImpl other) {
	}
}
