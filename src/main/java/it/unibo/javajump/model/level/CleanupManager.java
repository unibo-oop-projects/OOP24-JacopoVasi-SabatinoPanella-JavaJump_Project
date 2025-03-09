package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.GameObject;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.*;

public class CleanupManager {

	GameModel gameModel;


	public void cleanupObjects(GameModel model) {
		this.gameModel = model;
		List<GameObject> toRemove = new ArrayList<>();

		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float screenH = model.getScreenHeight();
		float margin = MARGIN;

		for (GameObject go : gameModel.getGameObjects()) {

			if (go instanceof Coin c) {
				if (c.getState() == CoinState.FINISHED) {
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

		gameModel.getGameObjects().removeAll(toRemove);
	}
}
