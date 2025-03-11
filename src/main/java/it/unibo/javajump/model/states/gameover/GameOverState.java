package it.unibo.javajump.model.states.gameover;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;

import java.util.Objects;

/**
 * The type Game over state.
 */
public class GameOverState implements GameStateHandler {
    private float deltaTime;
    /**
     * The Game state.
     */
    static final GameState GAME_STATE = GameState.GAME_OVER;

    @Override
    public void handleAction(final GameModel model, final GameAction action) {

        if (Objects.requireNonNull(action) == GameAction.CONFIRM_SELECTION) {
            model.setState(new MenuState());
        }
    }

    @Override
    public void update(final GameModel model, final float deltaTime) {
        this.deltaTime = deltaTime;
        model.notifyObservers();
    }

    @Override
    public GameState getGameState() {
        return GAME_STATE;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public int getState() {
        return 0;
    }

    @Override
    public float getDeltaTime() {
        return deltaTime;
    }
}
