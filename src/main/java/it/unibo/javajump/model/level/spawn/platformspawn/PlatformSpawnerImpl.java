package it.unibo.javajump.model.level.spawn.platformspawn;

import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

import java.util.Random;

import static it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl.randomInRange;

public class PlatformSpawnerImpl implements PlatformSpawner {

    private final GameObjectFactory factory;
    private final Random rand;

    public PlatformSpawnerImpl(final GameObjectFactory factory) {
        this.factory = factory;
        this.rand = new Random();
    }

    @Override
    public Platform spawnPlatform(final float x, final float y, final int screenWidth, final DifficultyState difficulty) {
        final float chance = rand.nextFloat();
        final float breakableChance = difficulty.getBreakableChance();
        final float movingChance = difficulty.getMovingChance();

        final float thresholdBounce = difficulty.getBounceChance();
        final float thresholdBreakable = thresholdBounce + breakableChance;
        final float thresholdMoving = thresholdBreakable + movingChance;

        if (chance < thresholdBounce) {
            return factory.createBouncePlatform(x, y, randomInRange(rand, 1.5f, 2.2f));
        } else if (chance < thresholdBreakable) {
            return factory.createBreakablePlatform(x, y);
        } else if (chance < thresholdMoving) {
            return factory.createMovingPlatform(x, y, screenWidth);
        } else {

            return factory.createRandomPlatform(x, y);
        }
    }
}
