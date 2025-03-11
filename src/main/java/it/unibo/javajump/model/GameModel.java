package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.camera.CameraManager;
import it.unibo.javajump.model.collision.CollisionManager;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.level.CleanupManager;
import it.unibo.javajump.model.level.SpawnManager;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyManager;
import it.unibo.javajump.model.physics.PhysicsManager;
import it.unibo.javajump.model.score.ScoreManager;
import it.unibo.javajump.model.states.GameStateHandler;

import java.util.List;


/**
 * The interface Game model.
 */
public interface GameModel {

    /**
     * Sets state.
     *
     * @param newState the new state
     */
    void setState(GameStateHandler newState);

    /**
     * Handle action.
     *
     * @param action the action
     */
    void handleAction(GameAction action);

    /**
     * Update.
     *
     * @param deltaTime the delta time
     */
    void update(float deltaTime);

    /**
     * Start game.
     */
    void startGame();

    /**
     * Add observer.
     *
     * @param obs the obs
     */
    void addObserver(GameModelObserver obs);

    /**
     * Remove observer.
     *
     * @param obs the obs
     */
    void removeObserver(GameModelObserver obs);

    /**
     * Notify observers.
     */
    void notifyObservers();

    /**
     * Gets score.
     *
     * @return the score
     */
    int getScore();

    /**
     * Add points to score.
     *
     * @param amount the amount
     */
    void addPointsToScore(int amount);

    /**
     * Gets physics manager.
     *
     * @return the physics manager
     */
    PhysicsManager getPhysicsManager();

    /**
     * Gets collision manager.
     *
     * @return the collision manager
     */
    CollisionManager getCollisionManager();

    /**
     * Gets spawn manager.
     *
     * @return the spawn manager
     */
    SpawnManager getSpawnManager();

    /**
     * Gets camera manager.
     *
     * @return the camera manager
     */
    CameraManager getCameraManager();

    /**
     * Gets score manager.
     *
     * @return the score manager
     */
    ScoreManager getScoreManager();

    /**
     * Gets cleanup manager.
     *
     * @return the cleanup manager
     */
    CleanupManager getCleanupManager();

    /**
     * Gets difficulty manager.
     *
     * @return the difficulty manager
     */
    DifficultyManager getDifficultyManager();

    /**
     * Gets current state.
     *
     * @return the current state
     */
    GameStateHandler getCurrentState();

    /**
     * Gets game objects.
     *
     * @return the game objects
     */
    List<GameObject> getGameObjects();

    /**
     * Gets player.
     *
     * @return the player
     */
    Character getPlayer();

    /**
     * Gets screen width.
     *
     * @return the screen width
     */
    int getScreenWidth();

    /**
     * Gets screen height.
     *
     * @return the screen height
     */
    int getScreenHeight();

    /**
     * Gets delta time.
     *
     * @return the delta time
     */
    float getDeltaTime();

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    boolean isRunning();

    /**
     * Stop game.
     */
    void stopGame();
}
