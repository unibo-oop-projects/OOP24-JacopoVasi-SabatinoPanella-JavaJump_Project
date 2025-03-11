package it.unibo.javajump.model.level.spawn.spawnutilities;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.XOFFSET;
import static it.unibo.javajump.utility.Constants.YOFFSET;

public class SpawnUtilsImpl implements SpawnUtils {

    /**
     * Returns a random value between min and max
     */
    public static float randomInRange(Random rand, float min, float max) {
        return min + rand.nextFloat() * (max - min);
    }

    /**
     * Creates and adds to the model a standard platform below the player.
     */
    public static void spawnPlatformBelowPlayer(GameModel model, GameObjectFactory factory) {
        float px = model.getPlayer().getX() - XOFFSET;
        float py = model.getPlayer().getY() + YOFFSET;
        Platform p = factory.createStandardPlatform(px, py);
        model.getGameObjects().add(p);
    }
}
