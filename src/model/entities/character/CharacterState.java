package model.entities.character;

public interface CharacterState {
	
	void updateCharacter(Character character, float deltaTime);

	
	void onEnter(Character character);

	
	void onExit(Character character);

	
	boolean isOnPlatform();
}
