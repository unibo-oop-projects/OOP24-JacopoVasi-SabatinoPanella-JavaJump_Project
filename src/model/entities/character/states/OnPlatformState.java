package model.entities.character.states;

import model.entities.character.Character;
import model.entities.character.CharacterState;

public class OnPlatformState implements CharacterState {
	@Override
	public void updateCharacter(Character character, float deltaTime) {

	}

	@Override
	public void onEnter(Character character) {

	}

	@Override
	public void onExit(Character character) {

	}

	@Override
	public boolean isOnPlatform() {
		return true;
	}
}
