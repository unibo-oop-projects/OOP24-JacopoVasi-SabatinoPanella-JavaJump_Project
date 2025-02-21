package model.score;

public class ScoreManager
{
	private int currentScore;
	private int bestScore;

	public ScoreManager()
	{
		this.currentScore = 0;
		this.bestScore = 0;
	}

	public void addPoints(int amount)
	{
		this.currentScore += amount;if (this.currentScore > this.bestScore)
		{
			this.bestScore = this.currentScore;
		}
	}

	public int getCurrentScore()
	{
		return currentScore;
	}

	public int getBestScore()
	{
		return bestScore;
	}

	public void reset()
	{
		this.currentScore = 0;
	}



}