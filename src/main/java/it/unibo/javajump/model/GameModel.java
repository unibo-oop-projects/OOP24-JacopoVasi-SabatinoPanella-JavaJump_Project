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


public interface GameModel {

    void setState(GameStateHandler newState);

    void handleAction(GameAction action);

    void update(float deltaTime);

    void startGame();

    void addObserver(GameModelObserver obs);

    void removeObserver(GameModelObserver obs);

    void notifyObservers();

    int getScore();

    void addPointsToScore(int amount);

    PhysicsManager getPhysicsManager();

    CollisionManager getCollisionManager();

    SpawnManager getSpawnManager();

    CameraManager getCameraManager();

    ScoreManager getScoreManager();

    CleanupManager getCleanupManager();

    DifficultyManager getDifficultyManager();

    GameStateHandler getCurrentState();

    List<GameObject> getGameObjects();

    Character getPlayer();

    int getScreenWidth();

    int getScreenHeight();

    float getDeltaTime();

    boolean isRunning();

    void stopGame();
}
