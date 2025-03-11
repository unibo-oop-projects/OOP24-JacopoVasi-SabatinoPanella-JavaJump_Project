package it.unibo.javajump.model.level.spawn.platformspawn;

import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

public interface PlatformSpawner {

    Platform spawnPlatform(float x, float y, int screenWidth, DifficultyState difficulty);
}
