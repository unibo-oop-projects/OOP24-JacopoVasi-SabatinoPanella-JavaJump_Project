package it.unibo.javajump.model.entities.character.states;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterState;

/**
 * Class implementation of the CharacterState interface, representing the state when the character is in the air.
 * Useful for future implementations and extensibility.
 */
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
