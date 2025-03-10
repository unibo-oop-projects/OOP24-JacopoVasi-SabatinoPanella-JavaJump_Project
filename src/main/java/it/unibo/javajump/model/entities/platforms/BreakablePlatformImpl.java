package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObjectImpl;

public class BreakablePlatformImpl extends PlatformImpl implements Platform {

	private boolean broken;

	public BreakablePlatformImpl(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.broken = false;
	}

	@Override
	public void onCollision(GameObjectImpl other) {
		super.onCollision(other);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	public boolean isBroken() {
		return broken;
	}

	public void breakPlatform() {
		if (!broken) {
			broken = true;
		}
	}
}
