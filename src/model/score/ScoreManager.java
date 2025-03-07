package model.score;

import static Utility.Constants.*;

public class ScoreManager
{
	private int currentScore;
	private int bestScore;
	private boolean bestScoreReached;

	public ScoreManager()
	{
		this.currentScore = ZERO;
		this.bestScore = ZERO;
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
		this.currentScore = ZERO;
		this.bestScoreReached = false;
	}



}