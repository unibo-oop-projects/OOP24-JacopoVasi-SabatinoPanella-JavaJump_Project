package it.unibo.javajump.model.level.spawn.difficulty;

/**
 * The interface Difficulty manager.
 */
public interface DifficultyManager {
    /**
     * Update difficulty.
     *
     * @param score the score
     */
    void updateDifficulty(int score);

    /**
     * Gets current difficulty.
     *
     * @return the current difficulty
     */
    DifficultyState getCurrentDifficulty();

    /**
     * Reset.
     */
    void reset();
}
