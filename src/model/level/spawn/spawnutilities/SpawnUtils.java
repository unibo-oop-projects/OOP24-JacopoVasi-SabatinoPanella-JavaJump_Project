package model.level.spawn.spawnutilities;

import model.GameModel;
import model.entities.platforms.Platform;
import model.factories.AbstractGameObjectFactory;

import java.util.Random;

public class SpawnUtils
{
	public static float randomInRange(Random rand, float min, float max) {
		return min + rand.nextFloat() * (max - min);
	}

	public static void spawnPlatformBelowPlayer(GameModel model, AbstractGameObjectFactory factory) {
		float px = model.getPlayer().getX() - 40;
		float py = model.getPlayer().getY() + 60;
		Platform p = factory.createStandardPlatform(px, py);
		model.getGameObjects().add(p);
	}
}
