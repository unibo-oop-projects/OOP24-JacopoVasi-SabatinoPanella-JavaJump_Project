package model.entities;

public class BreakablePlatform extends Platform {

	private boolean broken;

	public BreakablePlatform(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.broken = false;
	}

	@Override
	public void onCollision(GameObject other) {
		super.onCollision(other);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);




		if (broken) {




		}
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
