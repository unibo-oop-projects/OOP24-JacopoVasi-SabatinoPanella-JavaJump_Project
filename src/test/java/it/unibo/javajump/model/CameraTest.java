package it.unibo.javajump.model;

import it.unibo.javajump.model.states.ingame.InGameState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    private GameModel model;

    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREENWIDTH, SCREENHEIGHT);
        model.startGame();
        model.setState(new InGameState());

    }

    @Test
    void testInitialOffset() {
        assertEquals(OFFSETINIT, model.getCameraManager().getCurrentOffset(), "Initial offset should be equal to OFFSETINIT.");
    }

    @Test
    void testUpdateCameraIncreasesScoreWhenMovingUp() {
        float initialOffset = model.getCameraManager().getCurrentOffset();
        float initialScore = model.getScoreManager().getCurrentScore();

        // Simulate player moving upwards
        model.getPlayer().setY(initialOffset - 150);

        model.update(0);
        model.getCameraManager().updateCamera(model, 0); // Assume a frame update of ~16ms

        assertTrue(model.getScoreManager().getCurrentScore() > initialScore, "Score should increase when the camera moves up.");
    }

    @Test
    void testUpdateCameraDoesNotDecreaseOffset() {
        float initialOffset = model.getCameraManager().getCurrentOffset();

        // Move the player down (should not affect the offset)
        model.getPlayer().setY((float) SCREENHEIGHT /2 + 100);
        model.update(0);
        model.getCameraManager().updateCamera(model, 0);

        assertEquals(initialOffset, model.getCameraManager().getCurrentOffset(), "Camera offset should not increase when the player moves down.");
    }

    @Test
    void testResetCamera() {
        model.getCameraManager().resetCamera();
        assertEquals(OFFSETINIT, model.getCameraManager().getCurrentOffset(), "Camera offset should be reset to OFFSETINIT.");
    }
}
