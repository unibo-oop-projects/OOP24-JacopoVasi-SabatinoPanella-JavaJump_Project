package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.MARGIN;

public class CleanupManagerImpl implements CleanupManager {

    @Override
    public void cleanupObjects(GameModel model) {
        List<GameObject> toRemove = new ArrayList<>();

        float cameraOffset = model.getCameraManager().getCurrentOffset();
        float screenH = model.getScreenHeight();
        float margin = MARGIN;

        for (GameObject go : model.getGameObjects()) {
            if (go instanceof Coin c) {
                if (c.getState() == CoinState.FINISHED) {
                    toRemove.add(c);
                    continue;
                }
            }

            if (go instanceof BreakablePlatform bp) {
                if (bp.isBroken() && bp.isFinished()) {
                    toRemove.add(bp);
                    continue;
                }
            }

            float drawY = go.getY() - cameraOffset;
            if (drawY > screenH + margin) {
                toRemove.add(go);
            }

        }
        model.getGameObjects().removeAll(toRemove);
    }
}
