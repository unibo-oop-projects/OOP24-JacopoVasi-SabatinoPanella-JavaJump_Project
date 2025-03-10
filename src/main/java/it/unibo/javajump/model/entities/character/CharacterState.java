package it.unibo.javajump.model.entities.character;


public interface CharacterState {
	
	void updateCharacter(CharacterImpl character, float deltaTime);

	
	void onEnter(CharacterImpl character);

	
	void onExit(CharacterImpl character);

	
	boolean isOnPlatform();
}
