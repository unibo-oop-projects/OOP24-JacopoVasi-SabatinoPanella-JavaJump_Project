package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BreakablePlatformImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.GameObjectImpl;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.*;

public class CleanupManagerImpl implements CleanupManager {

	GameModelImpl gameModelImpl;


	public void cleanupObjects(GameModelImpl model) {
		this.gameModelImpl = model;
		List<GameObjectImpl> toRemove = new ArrayList<>();

		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float screenH = model.getScreenHeight();
		float margin = MARGIN;

		for (GameObjectImpl go : gameModelImpl.getGameObjects()) {

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

		gameModelImpl.getGameObjects().removeAll(toRemove);
	}
}
