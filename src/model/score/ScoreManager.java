package model.score;

public class ScoreManager
{
	private int currentScore;
	private int bestScore;
	private boolean bestScoreReached;

	public ScoreManager()
	{
		this.currentScore = 0;
		this.bestScore = 0;
		this.bestScoreReached = false;
	}

	public void addPoints(int amount)
	{
		this.currentScore += amount;

		if (this.currentScore > this.bestScore)
		{
			this.bestScore = this.currentScore;
			this.bestScoreReached = true;
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

	public boolean isBestScoreReached()
	{
		return bestScoreReached;
	}

	public void reset()
	{
		this.currentScore = 0;
		this.bestScoreReached = false;
	}

}