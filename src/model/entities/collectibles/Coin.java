package model.entities.collectibles;

import model.entities.GameObject;
import model.entities.platforms.Platform;

public class Coin extends GameObject
{
	private CoinState state;
	private int frameIndex;
	private float animTime;
	private static final float FRAME_DURATION = 0.075f;
	private boolean isDone;

	private static final int FRAME_WIDTH = 44;
	private static final int FRAME_HEIGHT = 52;

	private Platform attachedPlatform;
	private float offsetX;

	public Coin(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = CoinState.IDLE;
		this.frameIndex = 0;
		this.animTime = 0;
		this.isDone = false;
		this.attachedPlatform = null;
		this.offsetX = 0;
	}

	@Override
	public void update(float deltaTime)
	{
		if (isDone) return;
		animTime += deltaTime;

		switch (state) {
			case IDLE:
				updateIdleAnimation();
				break;
			case COLLECTING:
				updateCollectAnimation();
				break;
		}

		if (attachedPlatform != null) {
			this.x = attachedPlatform.getX() + offsetX;
		}
	}


	public void attachToPlatform(Platform platform) {
		this.attachedPlatform = platform;
		this.offsetX = this.x - platform.getX();
	}

	private void updateIdleAnimation() {
		float cycle = FRAME_DURATION * 6;
		float t = animTime % cycle;

		this.frameIndex = (int) (t / FRAME_DURATION);
	}

	private void updateCollectAnimation() {
		int idx = (int)(animTime / FRAME_DURATION);
		if (idx >= 7) {
			this.frameIndex = 6;
			this.isDone = true;
		} else {
			this.frameIndex = idx;
		}
	}


	public void collect() {
		if (this.state == CoinState.IDLE) {
			this.state = CoinState.COLLECTING;
			this.animTime = 0;
			this.frameIndex = 0;
		}
	}

	public CoinState getState() {
		return this.state;
	}

	public int getFrameIndex() {
		return this.frameIndex;
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