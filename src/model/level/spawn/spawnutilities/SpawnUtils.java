package model.level.spawn.spawnutilities;

import model.GameModel;
import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;

import java.util.Random;

import static utility.Constants.*;

public class SpawnUtils {
	public static float randomInRange(Random rand, float min, float max) {
		return min + rand.nextFloat() * (max - min);
	}

	public static void spawnPlatformBelowPlayer(GameModel model, AbstractGameObjectFactory factory) {
		float px = model.getPlayer().getX() - XOFFSET;
		float py = model.getPlayer().getY() + YOFFSET;
		Platform p = factory.createStandardPlatform(px, py);
		model.getGameObjects().add(p);
	}
}
