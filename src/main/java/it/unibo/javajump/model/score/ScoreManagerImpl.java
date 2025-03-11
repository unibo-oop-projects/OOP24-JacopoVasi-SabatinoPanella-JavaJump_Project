package it.unibo.javajump.model.score;

import static it.unibo.javajump.utility.Constants.SCORE_INIT;

public class ScoreManagerImpl implements ScoreManager {

    private int currentScore;
    private int bestScore;
    private boolean bestScoreReached;

    public ScoreManagerImpl() {
        this.currentScore = SCORE_INIT;
        this.bestScore = SCORE_INIT;
        this.bestScoreReached = false;
    }

    public void addPoints(int amount) {
        this.currentScore += amount;

        if (this.currentScore > this.bestScore) {
            this.bestScore = this.currentScore;
            this.bestScoreReached = true;
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    public boolean isBestScoreReached() {
        return bestScoreReached;
    }

    public void reset() {
        this.currentScore = SCORE_INIT;
        this.bestScoreReached = false;
    }


}
