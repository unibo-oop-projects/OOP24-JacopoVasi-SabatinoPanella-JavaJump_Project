package model.level.spawn.difficulty;

import java.util.Random;

import static model.level.spawn.spawnutilities.SpawnUtils.randomInRange;

public class DifficultyManager {
	private int currentScore;
	private DifficultyState currentDifficulty;
	private final Random rand;

	public DifficultyManager() {
		this.currentScore = 0;
		this.currentDifficulty = DifficultyState.EASY;
		this.rand = new Random();
	}

	
	public void updateDifficulty(int score) {
		this.currentScore = score;
		System.out.println("Score: " + currentScore);

		if (currentScore >= randomInRange(rand, 16000, 25000)) {
			currentDifficulty = DifficultyState.HELL;
		} else if (currentScore >= randomInRange(rand, 8000, 10000)) {
			currentDifficulty = DifficultyState.VERY_HARD;
		} else if (currentScore >= randomInRange(rand, 4000, 6000)) {
			currentDifficulty = DifficultyState.HARD;
		} else if (currentScore >= randomInRange(rand, 1500, 3000)) {
			currentDifficulty = DifficultyState.MEDIUM;
		} else {
			currentDifficulty = DifficultyState.EASY;
		}
	}

	public DifficultyState getCurrentDifficulty() {
		return currentDifficulty;
	}

	public void reset() {
		this.currentDifficulty = DifficultyState.EASY;
	}
}
