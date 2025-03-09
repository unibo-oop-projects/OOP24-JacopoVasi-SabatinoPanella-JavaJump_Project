package it.unibo.javajump.model.level.spawn.difficulty;

public enum DifficultyState {
	EASY(0.03f, 0.10f, 0.015f),
	MEDIUM(0.07f, 0.20f, 0.05f),
	HARD(0.15f, 0.35f, 0.085f),
	VERY_HARD(0.30f, 0.40f, 0.1f),
	HELL(0.40f, 0.47f, 0.13f);

	private final float breakableChance;
	private final float movingChance;
	private final float bounceChance;

	DifficultyState(float breakableChance, float movingChance, float bounceChance) {
		this.breakableChance = breakableChance;
		this.movingChance = movingChance;
		this.bounceChance = bounceChance;
	}

	public float getBreakableChance() {
		return breakableChance;
	}

	public float getMovingChance() {
		return movingChance;
	}

	public float getBounceChance() {
		return bounceChance;
	}
}
