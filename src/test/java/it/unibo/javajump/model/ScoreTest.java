package it.unibo.javajump.model;

import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ScoreTest {
    private GameModel model;

    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREENWIDTH, SCREENHEIGHT);

        model.startGame();
        model.setState(new InGameState());
    }

    @Test
    void testDifficulty() {
        model.update(0);
        assertEquals(DifficultyState.EASY, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be EASY.");
        model.getScoreManager().addPoints(MEDIUMMAX);
        model.update(0);
        assertEquals(DifficultyState.MEDIUM, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be MEDIUM");
        model.getScoreManager().addPoints(HARDMAX-MEDIUMMAX);
        model.update(0);
        assertEquals(DifficultyState.HARD, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be HARD");
        model.getScoreManager().addPoints(VERYHARDMAX-HARDMAX);
        model.update(0);
        assertEquals(DifficultyState.VERY_HARD, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be VERY HARD");
        model.getScoreManager().addPoints(HELLMAX-VERYHARDMAX);
        model.update(0);
        assertEquals(DifficultyState.HELL, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be HELL");

    }

}
