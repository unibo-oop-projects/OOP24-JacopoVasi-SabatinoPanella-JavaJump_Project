package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.Character;

/**
 * The interface Physics manager.
 */
public interface PhysicsManager {
    /**
     * Update character movement.
     *
     * @param character the character
     * @param deltaTime the delta time
     * @param direction the direction
     */
    void updateCharacterMovement(Character character, float deltaTime, MovementDirection direction);
}
