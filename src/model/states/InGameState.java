package model.states;

import controller.GameAction;
import model.GameModel;
import model.entities.GameObject;
import model.entities.character.Character;
import model.physics.MovementDirection;
import model.states.gameutilities.InGameUtilities;

public class InGameState implements GameStateHandler {
	private final GameState gameState= GameState.IN_GAME;

	private int horizontalDirection = 0;

	@Override
	public void onEnter(GameModel model) {
	}

	@Override
	public void handleAction(GameModel model, GameAction action) {
		switch (action) {
			case MOVE_LEFT:
				horizontalDirection = -1;
				break;
			case MOVE_RIGHT:
				horizontalDirection = 1;
				break;
			case STOP_HORIZONTAL:
				horizontalDirection = 0;
				break;
			case PAUSE_GAME:
				model.setState(new PauseState());
				break;
			default:
				break;
		}
	}

	@Override
	public void update(GameModel model, float deltaTime) {
		Character player = model.getPlayer();
		MovementDirection md = InGameUtilities.convertIntToMovementDirection(horizontalDirection);
		model.getPhysicsManager().updateCharacterMovement(player, deltaTime, md);

		for (GameObject go : model.getGameObjects()) {
			go.update(deltaTime);
			if (go instanceof Character) {
				InGameUtilities.applyPacManEffect((Character)go, model.getScreenWidth());
			}
		}

		model.getCollisionManager().checkCollisions(model);

		model.getCameraManager().cameraUpdate(model, deltaTime);

		model.getSpawnManager().generateOnTheFly(model);

		model.getCleanupManager().cleanupObjects(model);

		model.getDifficultyManager().updateDifficulty(model.getScore());

		InGameUtilities.checkGameOver(model, player);

		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}
}