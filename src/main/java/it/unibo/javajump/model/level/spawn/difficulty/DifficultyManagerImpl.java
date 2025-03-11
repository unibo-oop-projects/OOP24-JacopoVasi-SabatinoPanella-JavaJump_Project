package it.unibo.javajump.model.level.spawn.difficulty;

import it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class DifficultyManagerImpl implements DifficultyManager {

	private int currentScore;
	private DifficultyState currentDifficulty;
	private final Random rand;

	private final float thresholdMedium;
	private final float thresholdHard;
	private final float thresholdVeryHard;
	private final float thresholdHell;

	public DifficultyManagerImpl() {
		this.currentScore = SCOREINIT;
		this.currentDifficulty = DifficultyState.EASY;
		this.rand = new Random();

		this.thresholdMedium = SpawnUtilsImpl.randomInRange(rand, MEDIUMMIN, MEDIUMMAX);
		this.thresholdHard = SpawnUtilsImpl.randomInRange(rand, HARDMIN, HARDMAX);
		this.thresholdVeryHard = SpawnUtilsImpl.randomInRange(rand, VERYHARDMIN, VERYHARDMAX);
		this.thresholdHell = SpawnUtilsImpl.randomInRange(rand, HELLMIN, HELLMAX);
	}


	@Override
	public void updateDifficulty(int score) {
		this.currentScore = score;

		if (currentScore >= thresholdHell) {
			currentDifficulty = DifficultyState.HELL;
		} else if (currentScore >= thresholdVeryHard) {
			currentDifficulty = DifficultyState.VERY_HARD;
		} else if (currentScore >= thresholdHard) {
			currentDifficulty = DifficultyState.HARD;
		} else if (currentScore >= thresholdMedium) {
			currentDifficulty = DifficultyState.MEDIUM;
		} else {
			currentDifficulty = DifficultyState.EASY;
		}
	}

	@Override
	public DifficultyState getCurrentDifficulty() {
		return currentDifficulty;
	}

	@Override
	public void reset() {
		this.currentScore = 0;
		this.currentDifficulty = DifficultyState.EASY;
	}
}
