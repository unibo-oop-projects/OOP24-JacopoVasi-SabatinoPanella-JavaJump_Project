package it.unibo.javajump.model.level.spawn.platformspawn;

import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

/**
 * The interface Platform spawner.
 */
public interface PlatformSpawner {

    /**
     * Spawn platform platform.
     *
     * @param x           the x
     * @param y           the y
     * @param screenWidth the screen width
     * @param difficulty  the difficulty
     * @return the platform
     */
    Platform spawnPlatform(float x, float y, int screenWidth, DifficultyState difficulty);
}
