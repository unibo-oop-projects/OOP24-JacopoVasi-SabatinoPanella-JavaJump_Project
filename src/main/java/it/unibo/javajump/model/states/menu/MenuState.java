package it.unibo.javajump.model.states.menu;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.ingame.InGameState;


/**
 * The type Menu state.
 */
public class MenuState implements GameStateHandler {
    /**
     * The Game state.
     */
    static final GameState gameState = GameState.MENU;
    private float deltaTime = 0;

    @Override
    public void handleAction(final GameModel model, final GameAction action) {
        switch (action) {
            case CONFIRM_SELECTION:
                model.startGame();
                model.setState(new InGameState());
                break;
            case PAUSE_GAME:
                model.stopGame();
                break;
            default:
                break;
        }
    }

    @Override
    public void update(final GameModel model, final float deltaTime) {
        this.deltaTime = deltaTime;
        model.notifyObservers();
    }

    @Override
    public GameState getGameState() {
        return gameState;
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