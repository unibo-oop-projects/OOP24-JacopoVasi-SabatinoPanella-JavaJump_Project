package it.unibo.javajump.model.score;

import static it.unibo.javajump.utility.Constants.SCOREINIT;

public class ScoreManagerImpl implements ScoreManager {

    private int currentScore;
    private int bestScore;
    private boolean bestScoreReached;

    public ScoreManagerImpl() {
        this.currentScore = SCOREINIT;
        this.bestScore = SCOREINIT;
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
        this.currentScore = SCOREINIT;
        this.bestScoreReached = false;
    }


}
