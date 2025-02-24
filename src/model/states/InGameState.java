package model.states;

import controller.GameAction;
import model.GameModel;
import model.entities.GameObject;
import model.entities.Character;
import model.physics.MovementDirection;

public class InGameState implements GameStateHandler
{

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
			case MOVE_LEFT:
				horizontalDirection = -1;
				break;
			case MOVE_RIGHT:
				horizontalDirection = +1;
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
	public void update(GameModel model, float deltaTime)
	{


		Character player = model.getPlayer();
		model.getPhysicsManager().updateCharacterMovement
		(
				model.getPlayer(),
				deltaTime,
				convertIntToMovementDirection(horizontalDirection)
		);


		for (GameObject go : model.getGameObjects())
		{
			go.update(deltaTime);
		}


		model.getCollisionManager().checkCollisions(model);


		model.getCameraManager().update(model, deltaTime);


		model.getSpawnManager().generateOnTheFly(model);


		if (player.getY() > model.getScreenHeight())
		{
			model.setState(new GameOverState());
		}


		model.notifyObservers();
	}


	private MovementDirection convertIntToMovementDirection(int dir)
	{
		if (dir < 0) return MovementDirection.LEFT;
		if (dir > 0) return MovementDirection.RIGHT;
		return MovementDirection.NONE;
	}

}

