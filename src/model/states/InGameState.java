package model.states;

import controller.GameAction;
import model.GameModel;
import model.entities.GameObject;
import model.entities.Character;
import model.physics.MovementDirection;

public class InGameState implements GameStateHandler
{
	private final GameState gameState= GameState.IN_GAME;

	private int horizontalDirection = 0;

	@Override
	public void onEnter(GameModel model)
	{
	}

	@Override
	public void handleAction(GameModel model, GameAction action)
	{
		switch (action)
		{
			case GameAction.MOVE_LEFT:
				horizontalDirection = -1;
				break;
			case GameAction.MOVE_RIGHT:
				horizontalDirection = +1;
				break;
			case GameAction.STOP_HORIZONTAL:
				horizontalDirection = 0;
				break;
			case GameAction.PAUSE_GAME:
				model.setState(new PauseState());
				break;
			default:
				break;
		}
	}

	@Override
	public void update(GameModel model, float deltaTime)
	{

		Character player = model.getPlayer();
		MovementDirection md = convertIntToMovementDirection(horizontalDirection);
		model.getPhysicsManager().updateCharacterMovement
		(
				model.getPlayer(),
				deltaTime,
				md
		);


		for (GameObject go : model.getGameObjects())
		{
			go.update(deltaTime);
			if (go instanceof Character)
			{
				applyPacManEffect((Character)go, model);
			}
		}


		model.getCollisionManager().checkCollisions(model);


		model.getCameraManager().update(model, deltaTime);


		model.getSpawnManager().generateOnTheFly(model);

		model.getCleanupManager().cleanupObjects(model);


		float offset = model.getCameraManager().getCurrentOffset();
		float drawY = player.getY() - offset;
		if (drawY > model.getScreenHeight())
		{
			model.setState(new GameOverState());
		}


		model.notifyObservers();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	private MovementDirection convertIntToMovementDirection(int dir)
	{
		if (dir < 0) return MovementDirection.LEFT;
		if (dir > 0) return MovementDirection.RIGHT;
		return MovementDirection.NONE;
	}

	private void applyPacManEffect(Character player, GameModel model)
	{
		int screenWidth = model.getScreenWidth();

		if (player.getX() + player.getWidth() < 0)
		{
			player.setX(screenWidth);
		}
		else if (player.getX() > screenWidth)
		{
			player.setX(-player.getWidth());
		}
	}
}