package it.unibo.javajump.model.entities.character.states;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterState;

public class InAirState implements CharacterState {
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
		return false;
	}
}
