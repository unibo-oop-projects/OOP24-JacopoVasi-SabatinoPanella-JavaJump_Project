package it.unibo.javajump.model.score;

/**
 * The interface Score manager.
 */
public interface ScoreManager {
    /**
     * Add points.
     *
     * @param amount the amount
     */
    void addPoints(int amount);

    /**
     * Gets current score.
     *
     * @return the current score
     */
    int getCurrentScore();

    /**
     * Gets best score.
     *
     * @return the best score
     */
    int getBestScore();

    /**
     * Is best score reached boolean.
     *
     * @return the boolean
     */
    boolean isBestScoreReached();

    /**
     * Reset.
     */
    void reset();
}
