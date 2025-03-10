package it.unibo.javajump.model.states;

import it.unibo.javajump.controller.GameAction;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.physics.MovementDirection;

import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.model.states.gameutilities.InGameUtilities.applyPacManEffect;
import static it.unibo.javajump.model.states.gameutilities.InGameUtilities.convertIntToMovementDirection;
import static it.unibo.javajump.model.states.gameutilities.InGameUtilities.checkGameOver;

public class InGameState implements GameStateHandler {

	private final GameState gameState = GameState.IN_GAME;

	private int horizontalDirection = NULLDIRECTION;


	@Override
	public void handleAction(GameModelImpl model, GameAction action) {
		switch (action) {
			case MOVE_LEFT -> horizontalDirection = LEFTDIRECTION;
			case MOVE_RIGHT -> horizontalDirection = RIGHTDIRECTION;
			case STOP_HORIZONTAL -> horizontalDirection = NULLDIRECTION;
			case PAUSE_GAME -> model.setState(new PauseState());
			default -> {
			}
		}
	}

	@Override
	public void update(GameModelImpl model, float deltaTime) {

		CharacterImpl player = model.getPlayer();
		MovementDirection md = convertIntToMovementDirection(horizontalDirection);
		model.getPhysicsManager().updateCharacterMovement(player, deltaTime, md);

		for (GameObjectImpl go : model.getGameObjects()) {
			go.update(deltaTime);
			if (go instanceof CharacterImpl characterImpl) {
				applyPacManEffect(characterImpl, model.getScreenWidth());
			}
		}

		model.getCollisionManager().checkCollisions(model);

		model.getCameraManager().updateCamera(model, deltaTime);

		model.getSpawnManager().generateOnTheFly(model);

		model.getCleanupManager().cleanupObjects(model);

		model.getDifficultyManager().updateDifficulty(model.getScore());

		checkGameOver(model, player);

		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	@Override
	public int getState() {
		return 0;
	}
}