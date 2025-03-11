package it.unibo.javajump.model.level.spawn.difficulty;

/**
 * The enum Difficulty state.
 */
public enum DifficultyState {
    /**
     * Easy difficulty state.
     */
    EASY(0.03f, 0.10f, 0.015f),
    /**
     * Medium difficulty state.
     */
    MEDIUM(0.07f, 0.20f, 0.05f),
    /**
     * Hard difficulty state.
     */
    HARD(0.15f, 0.35f, 0.085f),
    /**
     * Very hard difficulty state.
     */
    VERY_HARD(0.30f, 0.40f, 0.1f),
    /**
     * Hell difficulty state.
     */
    HELL(0.40f, 0.47f, 0.13f);

    private final float breakableChance;
    private final float movingChance;
    private final float bounceChance;

    DifficultyState(final float breakableChance, final float movingChance, final float bounceChance) {
        this.breakableChance = breakableChance;
        this.movingChance = movingChance;
        this.bounceChance = bounceChance;
    }

    /**
     * Gets breakable chance.
     *
     * @return the breakable chance
     */
    public float getBreakableChance() {
        return breakableChance;
    }

    /**
     * Gets moving chance.
     *
     * @return the moving chance
     */
    public float getMovingChance() {
        return movingChance;
    }

    /**
     * Gets bounce chance.
     *
     * @return the bounce chance
     */
    public float getBounceChance() {
        return bounceChance;
    }
}
