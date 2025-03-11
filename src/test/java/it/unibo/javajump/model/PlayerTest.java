package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.entities.character.states.InAirState;
import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private GameModel model;

    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT);

        model.startGame();
        model.setState(new InGameState());
    }

    @Test
    void testJumping() {
        int counter = 0;
        final int maxcount = 200;
        while (!model.getPlayer().isOnPlatform() && counter < maxcount) {
            model.update(0.1f);
            counter++;
        }
        assertTrue(model.getPlayer().isOnPlatform(), "The player is not on the platform");
        model.update(0.1f);
        assertFalse(model.getPlayer().isOnPlatform(), "Jumping failed");
    }

    @Test
    void testPacman() {
        int counter = 0;
        final int maxcount = 100;
        model.getPlayer().setX(0);
        model.getPlayer().changeState(new InAirState());
        while (model.getPlayer().getX() < SCREEN_WIDTH && counter < maxcount) {
            model.handleAction(GameAction.MOVE_LEFT);
            model.update(0.1f);
            counter++;
        }
        assertTrue(model.getPlayer().getX() >= SCREEN_WIDTH, "The player is not looping");
    }

    @Test
    void testPhysics() {
        int counter = 0;
        final int maxcount = 6;
        model.getPlayer().changeState(new InAirState());
        while (counter < maxcount) {
            model.handleAction(GameAction.MOVE_LEFT);
            model.update(0.1f);
            counter++;
        }
        final float tempx = model.getPlayer().getX();
        model.handleAction(GameAction.MOVE_RIGHT);
        model.update(0.1f);
        assertTrue(model.getPlayer().getX() < tempx, "The player is not decelerating");
        counter = 0;
        while (counter < maxcount) {
            model.handleAction(GameAction.MOVE_RIGHT);
            model.update(0.1f);
            counter++;
        }
        assertTrue(model.getPlayer().getX() > tempx, "The player is not accelerating  ");
    }


}
