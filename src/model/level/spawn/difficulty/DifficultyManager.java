package model.level.spawn.difficulty;

import java.util.Random;

import static model.level.spawn.spawnutilities.SpawnUtils.randomInRange;

public class DifficultyManager
{
	public enum Difficulty {
		EASY(0.03f, 0.10f, 0.015f),
		MEDIUM(0.07f, 0.20f, 0.05f),
		HARD(0.15f, 0.35f, 0.085f),
		VERY_HARD (0.30f, 0.40f, 0.1f),
		HELL (0.40f, 0.47f, 0.13f);

		private final float breakableChance;
		private final float movingChance;
		private final float bounceChance;

		Difficulty(float breakableChance, float movingChance, float bounceChance) {
			this.breakableChance = breakableChance;
			this.movingChance = movingChance;
			this.bounceChance = bounceChance;
		}

		public float getBreakableChance() {
			return breakableChance;
		}

		public float getMovingChance() {
			return movingChance;
		}

		public float getBounceChance() {
			return bounceChance;
		}
	}

	private int currentScore;
	private Difficulty currentDifficulty;
	private final Random rand;

	public DifficultyManager() {
		this.currentScore = 0;
		this.currentDifficulty = Difficulty.EASY;
		this.rand = new Random();
	}

	
	public void updateDifficulty(int score) {
		this.currentScore = score;
		System.out.println("Score: " + currentScore);

		if (currentScore >= randomInRange(rand, 16000, 25000)) {
			currentDifficulty = Difficulty.HELL;
		} else if (currentScore >= randomInRange(rand, 8000, 10000)) {
			currentDifficulty = Difficulty.VERY_HARD;
		} else if (currentScore >= randomInRange(rand, 4000, 6000)) {
			currentDifficulty = Difficulty.HARD;
		} else if (currentScore >= randomInRange(rand, 1500, 3000)) {
			currentDifficulty = Difficulty.MEDIUM;
		} else {
			currentDifficulty = Difficulty.EASY;
		}
	}

	public Difficulty getCurrentDifficulty() {
		return currentDifficulty;
	}

	public void reset() {
		this.currentDifficulty = Difficulty.EASY;
	}
}
