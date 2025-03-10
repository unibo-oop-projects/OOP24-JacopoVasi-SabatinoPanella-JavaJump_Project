package it.unibo.javajump.model.level.spawn.spawnutilities;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.factories.AbstractGameObjectFactoryImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class SpawnUtils {
	public static float randomInRange(Random rand, float min, float max) {
		return min + rand.nextFloat() * (max - min);
	}

	public static void spawnPlatformBelowPlayer(GameModel model, AbstractGameObjectFactoryImpl factory) {
		float px = model.getPlayer().getX() - XOFFSET;
		float py = model.getPlayer().getY() + YOFFSET;
		PlatformImpl p = factory.createStandardPlatform(px, py);
		model.getGameObjects().add(p);
	}
}
