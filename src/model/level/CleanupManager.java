package model.level;

import model.GameModel;
import model.entities.BreakablePlatform;
import model.entities.Coin;
import model.entities.GameObject;
import model.entities.Platform;

import java.util.ArrayList;
import java.util.List;

public class CleanupManager
{

	public void cleanupObjects(GameModel model) {
		List<GameObject> objects = model.getGameObjects();
		List<GameObject> toRemove = new ArrayList<>();

		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float screenH = model.getScreenHeight();
		float margin = 50f;

		for (GameObject go : objects) {

			if (go instanceof Coin c) {
				if (c.getIsDone()) {
					toRemove.add(c);
					continue;
				}
			}

			if (go instanceof BreakablePlatform bp) {
				if (bp.isBroken()) {
					toRemove.add(bp);
					continue;
				}
			}


			float drawY = go.getY() - cameraOffset;
			if (drawY > screenH + margin) {

				toRemove.add(go);
			}

		}

		objects.removeAll(toRemove);
	}
}
