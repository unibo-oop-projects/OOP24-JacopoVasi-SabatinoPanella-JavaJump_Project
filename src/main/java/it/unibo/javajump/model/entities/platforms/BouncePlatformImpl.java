package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObjectImpl;

public class BouncePlatformImpl extends PlatformImpl {
	private final float bounceFactor;

	public BouncePlatformImpl(float x, float y, float width, float height, float bounceFactor) {
		super(x, y, width, height);
		this.bounceFactor = bounceFactor;
	}

	public float getBounceFactor() {
		return bounceFactor;
	}

	@Override
	public void onCollision(GameObjectImpl other) {
		super.onCollision(other);
	}

	@Override
	public void update(float deltaTime) {

		super.update(deltaTime);
	}
}
