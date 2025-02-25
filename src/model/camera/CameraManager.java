package model.camera;

import model.GameModel;
import model.entities.Character;
import model.score.ScoreManager;


public class CameraManager
{
	private float currentOffset;
	private float previousOffset;

	private final ScoreManager scoreManager;
	private final float scoreFactor;


	public CameraManager(ScoreManager scoreManager, float scoreFactor)
	{
		this.scoreManager = scoreManager;
		this.scoreFactor = scoreFactor;
		this.currentOffset = 0;
		this.previousOffset = 0;
	}

	
	public void update(GameModel model, float deltaTime)
	{
		Character player = model.getPlayer();
		float screenHeight = model.getScreenHeight();
		float halfScreen = screenHeight / 2f;


		float desiredOffset = 0;
		if (player.getY() < halfScreen - currentOffset)
		{


			desiredOffset = player.getY() - (halfScreen);
		}

		currentOffset = desiredOffset;



		if (currentOffset < previousOffset)
		{

			float deltaOffset = previousOffset - currentOffset;



			int points = (int) (deltaOffset * scoreFactor);
			scoreManager.addPoints(points);
		}


		previousOffset = currentOffset;
	}

	public float getCurrentOffset()
	{
		return currentOffset;
	}
}
