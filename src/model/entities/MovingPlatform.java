package model.entities;

public class MovingPlatform extends Platform{
	private final float minX;
	private final float maxX;
	private float speed;
	private boolean goingRight;

	public MovingPlatform(float x, float y, float width, float height,
						  float range, float screenWidth, float speed) {
		super(x, y, width, height);

		if (x < 0) x = 0;
		if (x > screenWidth - width) x = screenWidth - width;
		this.x = x;


		float potentialMin = x - range;
		float potentialMax = x + range;
		if (potentialMin < 0) potentialMin = 0;
		if (potentialMax > screenWidth - width) potentialMax = screenWidth - width;

		this.minX = potentialMin;
		this.maxX = potentialMax;

		this.speed = speed;
		this.goingRight = true;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);


		if (goingRight) {
			x += speed * deltaTime;
			if (x > maxX) {
				x = maxX;
				goingRight = false;
			}
		} else {
			x -= speed * deltaTime;
			if (x < minX) {
				x = minX;
				goingRight = true;
			}
		}
	}
}
