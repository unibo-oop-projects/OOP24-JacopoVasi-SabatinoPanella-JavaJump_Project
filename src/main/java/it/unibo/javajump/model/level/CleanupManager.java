package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BreakablePlatformImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.GameObjectImpl;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.*;

public class CleanupManager {

	GameModel gameModel;


	public void cleanupObjects(GameModel model) {
		this.gameModel = model;
		List<GameObjectImpl> toRemove = new ArrayList<>();

		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float screenH = model.getScreenHeight();
		float margin = MARGIN;

		for (GameObjectImpl go : gameModel.getGameObjects()) {

			if (go instanceof CoinImpl c) {
				if (c.getState() == CoinState.FINISHED) {
					toRemove.add(c);
					continue;
				}
			}

			if (go instanceof BreakablePlatformImpl bp) {
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
