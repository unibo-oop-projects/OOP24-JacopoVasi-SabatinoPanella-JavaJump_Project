package it.unibo.javajump.model.entities.objectstrategies;

import it.unibo.javajump.model.entities.GameObjectImpl;

public class HorizontalOscillationMovement implements MovementBehaviour {

	private final float minX;
	private final float maxX;
	@SuppressWarnings("FieldMayBeFinal")
	private float speed;
	private boolean goingRight;

	public HorizontalOscillationMovement(float minX, float maxX, float speed) {
		this.minX = minX;
		this.maxX = maxX;
		this.speed = speed;
		this.goingRight = true;
	}

	@Override
	public void update(GameObjectImpl obj, float deltaTime) {
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
