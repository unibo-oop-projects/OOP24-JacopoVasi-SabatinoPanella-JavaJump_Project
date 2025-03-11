package it.unibo.javajump.model.level.spawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;
import it.unibo.javajump.model.level.spawn.collectiblespawn.CollectiblesSpawner;
import it.unibo.javajump.model.level.spawn.collectiblespawn.CollectiblesSpawnerImpl;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;
import it.unibo.javajump.model.level.spawn.platformspawn.PlatformSpawner;
import it.unibo.javajump.model.level.spawn.platformspawn.PlatformSpawnerImpl;
import it.unibo.javajump.model.level.spawn.spawnutilities.SpawnUtilsImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class RandomSpawnStrategy implements SpawnStrategy {

    private final GameObjectFactory factory;
    private final Random rand;
    private final float minPlatformYSpacing;
    private final float maxPlatformYSpacing;
    private float currentY;
    private final CollectiblesSpawner collectiblesSpawner;
    private final PlatformSpawner platformSpawner;

    public RandomSpawnStrategy(GameObjectFactory factory,
                               float minSpacing,
                               float maxSpacing,
                               float coinChance,
                               DifficultyManager difficultyManager) {
        this.factory = factory;
        this.rand = new Random();
        this.minPlatformYSpacing = minSpacing;
        this.maxPlatformYSpacing = maxSpacing;
        this.currentY = SPAWNY_INIT;
        this.collectiblesSpawner = new CollectiblesSpawnerImpl(factory, coinChance);
        this.platformSpawner = new PlatformSpawnerImpl(factory);
    }

    @Override
    public void spawnBatch(GameModel model, float startY, int numberOfPlatforms) {
        DifficultyState diff = model.getDifficultyManager().getCurrentDifficulty();
        currentY = startY;

        for (int i = 0; i < numberOfPlatforms; i++) {
            float gap = setSpawnGap(diff);
            currentY -= gap;

            float x = rand.nextFloat() * (model.getScreenWidth() - (float) MAXPLATFORMWIDTH);

            Platform p = platformSpawner.spawnPlatform(x, currentY, model.getScreenWidth(), diff);
            model.getGameObjects().add(p);

            float platformWidth = p.getWidth();
            collectiblesSpawner.spawnCollectible(model, x, currentY, platformWidth, p);
        }
    }

    private float setSpawnGap(DifficultyState diff) {
        float gap = GAPINIT;
        if (null != diff) {
            switch (diff) {
                case EASY, MEDIUM ->
                        gap = SpawnUtilsImpl.randomInRange(rand, minPlatformYSpacing - GAPOFFSETTEN, maxPlatformYSpacing - GAPOFFSETTHIRTY);
                case HARD, VERY_HARD ->
                        gap = SpawnUtilsImpl.randomInRange(rand, minPlatformYSpacing, maxPlatformYSpacing - GAPOFFSETTEN);
                case HELL ->
                        gap = SpawnUtilsImpl.randomInRange(rand, minPlatformYSpacing + GAPOFFSETTEN, maxPlatformYSpacing);
                default -> {
                }
            }
        }
        return gap;
    }

    @Override
    public float returnCurrentY() {
        return this.currentY;
    }

    @Override
    public GameObjectFactory getFactory() {
        return this.factory;
    }
}
