package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;

/**
 * The interface Cleanup manager.
 */
public interface CleanupManager {
    /**
     * Cleanup objects.
     *
     * @param model the model
     */
    void cleanupObjects(GameModel model);
}
