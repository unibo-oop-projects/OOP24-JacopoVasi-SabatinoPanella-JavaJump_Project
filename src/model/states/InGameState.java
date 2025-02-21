package model.states;

import controller.GameAction;
import model.GameModel;
import model.entities.GameObject;
import model.entities.Character;
import model.physics.MovementDirection;

public class InGameState implements GameStateHandler
{

	private MovementDirection currentDirection = MovementDirection.NONE;

	@Override
	public void onEnter(GameModel model)
	{


	}

	@Override
	public void handleAction(GameModel model, GameAction action)
	{
		switch(action)
		{
			case MOVE_LEFT:
				currentDirection = MovementDirection.LEFT;
				break;
			case MOVE_RIGHT:
				currentDirection = MovementDirection.RIGHT;
				break;
			case STOP_HORIZONTAL:
				currentDirection = MovementDirection.NONE;
				break;
			case PAUSE_GAME:
				model.setState(new PauseState());
				break;
			default:		break;
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
				currentDirection
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

}

