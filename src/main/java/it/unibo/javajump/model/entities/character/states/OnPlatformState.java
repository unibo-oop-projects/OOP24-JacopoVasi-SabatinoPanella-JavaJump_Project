package it.unibo.javajump.model.entities.character.states;

import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.character.CharacterState;

public class OnPlatformState implements CharacterState {
	@Override
	public void updateCharacter(CharacterImpl characterImpl, float deltaTime) {

	}

	@Override
	public void onEnter(CharacterImpl characterImpl) {

	}

	@Override
	public void onExit(CharacterImpl characterImpl) {

	}

	@Override
	public boolean isOnPlatform() {
		return true;
	}
}
