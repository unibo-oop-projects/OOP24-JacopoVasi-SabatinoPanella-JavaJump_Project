package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.CLEAN_UP_MARGIN_OFFSET;

/**
 * The type Cleanup manager.
 */
public class CleanupManagerImpl implements CleanupManager {

    @Override
    public void cleanupObjects(final GameModel model) {
        final List<GameObject> toRemove = new ArrayList<>();

        final float cameraOffset = model.getCameraManager().getCurrentOffset();
        final float screenH = model.getScreenHeight();

        for (final GameObject go : model.getGameObjects()) {
            if (go instanceof Coin c && c.getState() == CoinState.FINISHED) {

                    toRemove.add(c);
                    continue;

            }

            if (go instanceof BreakablePlatform bp && bp.isBroken() && bp.isFinished()) {

                    toRemove.add(bp);
                    continue;

            }

            final float drawY = go.getY() - cameraOffset;
            if (drawY > screenH + CLEAN_UP_MARGIN_OFFSET) {
                toRemove.add(go);
            }

        }
        model.getGameObjects().removeAll(toRemove);
    }
}
