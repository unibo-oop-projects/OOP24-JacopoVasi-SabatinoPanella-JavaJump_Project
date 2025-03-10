package it.unibo.javajump.model.entities.character;

import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.character.states.InAirState;
import it.unibo.javajump.model.entities.character.states.OnPlatformState;

import static it.unibo.javajump.utility.Constants.*;

public class CharacterImpl extends GameObjectImpl {

	private float velocityX;
	private float velocityY;
	private float jumpForce;
	private float oldX;
	private float oldY;
	private boolean facingRight;

	private CharacterState currentState;

	public CharacterImpl(float x, float y, float width, float height, float jumpForce) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.jumpForce = jumpForce;
		this.velocityX = VELOCITYINIT;
		this.velocityY = VELOCITYINIT;
		this.oldX = x;
		this.oldY = y;
		this.currentState = new InAirState();
	}

	@Override
	public void update(float deltaTime) {
		currentState.updateCharacter(this, deltaTime);
	}

	@Override
	public void onCollision(GameObjectImpl other) {
	}


	public void changeState(CharacterState newState) {
		currentState.onExit(this);
		this.currentState = newState;
		currentState.onEnter(this);
	}


	public void landOnPlatform() {
		changeState(new OnPlatformState());
	}


	public void goInAir() {
		changeState(new InAirState());
	}


	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public float getJumpForce() {
		return jumpForce;
	}

	public void setJumpForce(float jumpForce) {
		this.jumpForce = jumpForce;
	}

	public float getOldX() {
		return oldX;
	}

	public void setOldX(float oldX) {
		this.oldX = oldX;
	}

	public float getOldY() {
		return oldY;
	}

	public void setOldY(float oldY) {
		this.oldY = oldY;
	}

	
	public boolean isOnPlatform() {
		return currentState.isOnPlatform();
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}
}
