package it.unibo.javajump.model.score;

import static it.unibo.javajump.utility.Constants.SCORE_INIT;

/**
 * The type Score manager.
 */
public class ScoreManagerImpl implements ScoreManager {

    private int currentScore;
    private int bestScore;
    private boolean bestScoreReached;

    /**
     * Instantiates a new Score manager.
     */
    public ScoreManagerImpl() {
        this.currentScore = SCORE_INIT;
        this.bestScore = SCORE_INIT;
        this.bestScoreReached = false;
    }

    @Override
    public void addPoints(final int amount) {
        this.currentScore += amount;

        if (this.currentScore > this.bestScore) {
            this.bestScore = this.currentScore;
            this.bestScoreReached = true;
        }
    }

    @Override
    public int getCurrentScore() {
        return currentScore;
    }

    @Override
    public int getBestScore() {
        return bestScore;
    }

    @Override
    public boolean isBestScoreReached() {
        return bestScoreReached;
    }

    @Override
    public void reset() {
        this.currentScore = SCORE_INIT;
        this.bestScoreReached = false;
    }


}
