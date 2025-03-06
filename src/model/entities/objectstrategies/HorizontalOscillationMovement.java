package model.entities.objectstrategies;

import model.entities.GameObject;

public class HorizontalOscillationMovement implements MovementBehaviour {
	private final float minX;
	private final float maxX;
	private float speed;
	private boolean goingRight;

	public HorizontalOscillationMovement(float minX, float maxX, float speed) {
		this.minX = minX;
		this.maxX = maxX;
		this.speed = speed;
		this.goingRight = true;
	}

	@Override
	public void update(GameObject obj, float deltaTime) {
		float currentX = obj.getX();
		if (goingRight) {
			currentX += speed * deltaTime;
			if (currentX > maxX) {
				currentX = maxX;
				goingRight = false;
			}
		} else {
			currentX -= speed * deltaTime;
			if (currentX < minX) {
				currentX = minX;
				goingRight = true;
			}
		}
		obj.setX(currentX);
	}
}
