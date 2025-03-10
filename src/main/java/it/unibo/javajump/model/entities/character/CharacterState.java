package it.unibo.javajump.model.entities.character;

public interface CharacterState {

	void updateCharacter(CharacterImpl characterImpl, float deltaTime);


	void onEnter(CharacterImpl characterImpl);


	void onExit(CharacterImpl characterImpl);

	boolean isOnPlatform();
}
