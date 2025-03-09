package it.unibo.javajump.model.level.spawn.difficulty;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;
import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtils.randomInRange;

public class DifficultyManager {

	private int currentScore;
	private DifficultyState currentDifficulty;
	private final Random rand;

	public DifficultyManager() {
		this.currentScore = SCOREINIT;
		this.currentDifficulty = DifficultyState.EASY;
		this.rand = new Random();
	}

	
	public void updateDifficulty(int score) {
		this.currentScore = score;
		System.out.println(SCORETEXT + currentScore);

		if (currentScore >= randomInRange(rand, HELLMIN, HELLMAX)) {
			currentDifficulty = DifficultyState.HELL;
		} else if (currentScore >= randomInRange(rand, VERYHARDMIN, VERYHARDMAX)) {
			currentDifficulty = DifficultyState.VERY_HARD;
		} else if (currentScore >= randomInRange(rand, HARDMIN, HARDMAX)) {
			currentDifficulty = DifficultyState.HARD;
		} else if (currentScore >= randomInRange(rand, MEDIUMMIN, MEDIUMMAX)) {
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
