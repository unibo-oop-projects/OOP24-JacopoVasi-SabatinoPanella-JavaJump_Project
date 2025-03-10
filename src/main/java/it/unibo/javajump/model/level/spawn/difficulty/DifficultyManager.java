package it.unibo.javajump.model.level.spawn.difficulty;

public interface DifficultyManager {
	void updateDifficulty(int score);

	DifficultyState getCurrentDifficulty();

	void reset();
}
