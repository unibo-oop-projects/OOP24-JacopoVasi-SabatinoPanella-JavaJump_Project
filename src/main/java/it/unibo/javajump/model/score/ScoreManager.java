package it.unibo.javajump.model.score;

public interface ScoreManager {
    void addPoints(int amount);

    int getCurrentScore();

    int getBestScore();

    boolean isBestScoreReached();

    void reset();
}
