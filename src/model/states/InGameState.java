package model.states;

import controller.GameAction;
import model.GameModel;
import model.entities.GameObject;
import model.entities.character.Character;
import model.physics.MovementDirection;

import static utility.Constants.*;
import static model.states.gameutilities.InGameUtilities.applyPacManEffect;
import static model.states.gameutilities.InGameUtilities.convertIntToMovementDirection;
import static model.states.gameutilities.InGameUtilities.checkGameOver;

public class InGameState implements GameStateHandler {
	private final GameState gameState = GameState.IN_GAME;

	private int horizontalDirection = NULLDIRECTION;

	@Override
	public void onEnter(GameModel model) {
	}

	@Override
	public void handleAction(GameModel model, GameAction action) {
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
	public void update(GameModel model, float deltaTime) {
		Character player = model.getPlayer();
		MovementDirection md = convertIntToMovementDirection(horizontalDirection);
		model.getPhysicsManager().updateCharacterMovement(player, deltaTime, md);

		for (GameObject go : model.getGameObjects()) {
			go.update(deltaTime);
			if (go instanceof Character character) {
				applyPacManEffect(character, model.getScreenWidth());
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
}