package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.Character;

public interface PhysicsManager {
    void updateCharacterMovement(Character character, float deltaTime, MovementDirection direction);
}
