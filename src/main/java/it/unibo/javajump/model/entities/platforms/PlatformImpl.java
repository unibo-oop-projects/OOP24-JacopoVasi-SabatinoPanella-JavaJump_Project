package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObjectImpl;

public class PlatformImpl extends GameObjectImpl implements Platform {

	public PlatformImpl(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void onCollision(GameObjectImpl other) {
	}

}
