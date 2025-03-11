package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static it.unibo.javajump.utility.TestConstants.SCORE_POINTS;
import static it.unibo.javajump.utility.TestConstants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.TestConstants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.TestConstants.STARTING_SCORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelTest {

    private GameModelImpl gameModel;




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
        final GameStateHandler newState = new MenuState();
        gameModel.setState(newState);
        assertEquals(newState, gameModel.getCurrentState(), "State should change correctly");
    }

    @Test
    void testHandleAction() {
        final GameAction action = GameAction.CONFIRM_SELECTION; // Provide an empty implementation
        gameModel.handleAction(action);
        assertNotNull(gameModel.getCurrentState(), "Action should be handled without errors");
    }

    @Test
    void testUpdate() {
        final float deltaTime = DELTA_TIME;
        gameModel.update(deltaTime);
        assertEquals(deltaTime, gameModel.getDeltaTime(), "Delta time should be updated correctly");
    }

    @Test
    void testStartGameInitializesEntities() {
        gameModel.startGame();
        final Character player = gameModel.getPlayer();
        assertNotNull(player, "Player should be initialized");
        assertTrue(gameModel.getGameObjects().contains(player), "Player should be part of game objects");
        assertEquals(STARTING_SCORE, gameModel.getScore(), "Score should reset to zero");
    }

    @Test
    void testObserverNotification() {
        final TestObserver observer = new TestObserver();
        gameModel.addObserver(observer);
        gameModel.notifyObservers();
        assertTrue(observer.updated, "Observer should be notified");
    }

    @Test
    void testScoreManagement() {
        gameModel.addPointsToScore(SCORE_POINTS);
        assertEquals(SCORE_POINTS, gameModel.getScore(), "Score should increase correctly");
    }


    // Inner class to test observer pattern
    final private static class TestObserver implements GameModelObserver {
        boolean updated;

        @Override
        public void onModelUpdate(GameModel model) {
            updated = true;
        }
    }
}
