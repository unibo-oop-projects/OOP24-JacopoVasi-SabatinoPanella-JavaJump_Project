package it.unibo.javajump.model.entities.character.states;

import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.character.CharacterState;

public class OnPlatformState implements CharacterState {
	@Override
	public void updateCharacter(CharacterImpl character, float deltaTime) {

	}

	@Override
	public void onEnter(CharacterImpl character) {

	}

	@Override
	public void onExit(CharacterImpl character) {

	}

	@Override
	public boolean isOnPlatform() {
		return true;
	}
}
