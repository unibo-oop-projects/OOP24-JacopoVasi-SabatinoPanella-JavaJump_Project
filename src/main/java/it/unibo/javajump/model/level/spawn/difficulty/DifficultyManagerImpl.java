package it.unibo.javajump.model.level.spawn.difficulty;

import it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.HARD_MAX;
import static it.unibo.javajump.utility.Constants.HARD_MIN;
import static it.unibo.javajump.utility.Constants.HELL_MAX;
import static it.unibo.javajump.utility.Constants.HELL_MIN;
import static it.unibo.javajump.utility.Constants.MEDIUM_MAX;
import static it.unibo.javajump.utility.Constants.MEDIUM_MIN;
import static it.unibo.javajump.utility.Constants.VERY_HARD_MAX;
import static it.unibo.javajump.utility.Constants.VERY_HARD_MIN;

/**
 * The type Difficulty manager.
 */
public final class DifficultyManagerImpl implements DifficultyManager {


    private DifficultyState currentDifficulty;

    private final float thresholdMedium;
    private final float thresholdHard;
    private final float thresholdVeryHard;
    private final float thresholdHell;

    /**
     * Instantiates a new Difficulty manager.
     */
    public DifficultyManagerImpl() {
        this.currentDifficulty = DifficultyState.EASY;
        final Random rand = new Random();

        this.thresholdMedium = SpawnUtilsImpl.randomInRange(rand, MEDIUM_MIN, MEDIUM_MAX);
        this.thresholdHard = SpawnUtilsImpl.randomInRange(rand, HARD_MIN, HARD_MAX);
        this.thresholdVeryHard = SpawnUtilsImpl.randomInRange(rand, VERY_HARD_MIN, VERY_HARD_MAX);
        this.thresholdHell = SpawnUtilsImpl.randomInRange(rand, HELL_MIN, HELL_MAX);
    }


    @Override
    public void updateDifficulty(final int score) {

        if (score >= thresholdHell) {
            currentDifficulty = DifficultyState.HELL;
        } else if (score >= thresholdVeryHard) {
            currentDifficulty = DifficultyState.VERY_HARD;
        } else if (score >= thresholdHard) {
            currentDifficulty = DifficultyState.HARD;
        } else if (score >= thresholdMedium) {
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
        this.currentDifficulty = DifficultyState.EASY;
    }
}
