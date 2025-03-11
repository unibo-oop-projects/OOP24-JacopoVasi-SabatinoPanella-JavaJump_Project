package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private GameModelImpl gameModel;


    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    @BeforeEach
    void setUp() {
        // Instantiate real implementations of the dependencies

        // Create the GameModelImpl instance
        gameModel = new GameModelImpl(
                SCREEN_WIDTH, SCREEN_HEIGHT
        );
    }

    @Test
    void testInitialStateIsMenu() {
        assertInstanceOf(MenuState.class, gameModel.getCurrentState(), "Initial state should be MenuState");
    }

    @Test
    void testSetStateChangesState() {
        GameStateHandler newState = new MenuState();
        gameModel.setState(newState);
        assertEquals(newState, gameModel.getCurrentState(), "State should change correctly");
    }

    @Test
    void testHandleAction() {
        GameAction action = GameAction.CONFIRM_SELECTION; // Provide an empty implementation
        gameModel.handleAction(action);
        assertNotNull(gameModel.getCurrentState(), "Action should be handled without errors");
    }

    @Test
    void testUpdate() {
        float deltaTime = 0.016f;
        gameModel.update(deltaTime);
        assertEquals(deltaTime, gameModel.getDeltaTime(), "Delta time should be updated correctly");
    }

    @Test
    void testStartGameInitializesEntities() {
        gameModel.startGame();
        Character player = gameModel.getPlayer();
        assertNotNull(player, "Player should be initialized");
        assertTrue(gameModel.getGameObjects().contains(player), "Player should be part of game objects");
        assertEquals(0, gameModel.getScore(), "Score should reset to zero");
    }

    @Test
    void testObserverNotification() {
        TestObserver observer = new TestObserver();
        gameModel.addObserver(observer);
        gameModel.notifyObservers();
        assertTrue(observer.updated, "Observer should be notified");
    }

    @Test
    void testScoreManagement() {
        gameModel.addPointsToScore(50);
        assertEquals(50, gameModel.getScore(), "Score should increase correctly");
    }



    // Inner class to test observer pattern
    private static class TestObserver implements GameModelObserver {
        boolean updated = false;

        @Override
        public void onModelUpdate(GameModel model) {
            updated = true;
        }
    }
}
