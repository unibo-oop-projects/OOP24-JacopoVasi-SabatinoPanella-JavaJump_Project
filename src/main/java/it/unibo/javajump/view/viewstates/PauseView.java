package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.pause.PauseOption;
import it.unibo.javajump.model.states.pause.PauseState;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.BACKGROUND_DEFAULT_COLOR;
import static it.unibo.javajump.utility.Constants.GOLD_TEXT_COLOR;
import static it.unibo.javajump.utility.Constants.PAUSECENTERDIV;
import static it.unibo.javajump.utility.Constants.PAUSECONTINUETEXT;
import static it.unibo.javajump.utility.Constants.PAUSECONTINUEY;
import static it.unibo.javajump.utility.Constants.PAUSEMAINMENUTEXT;
import static it.unibo.javajump.utility.Constants.PAUSEMAINMENUY;
import static it.unibo.javajump.utility.Constants.PAUSEQUITTEXT;
import static it.unibo.javajump.utility.Constants.PAUSEQUITY;
import static it.unibo.javajump.utility.Constants.PAUSESELECTIONTEXT;
import static it.unibo.javajump.utility.Constants.PAUSESELECTIONX;
import static it.unibo.javajump.utility.Constants.PAUSETEXT;
import static it.unibo.javajump.utility.Constants.PAUSEWIDTHOFF;

public class PauseView implements GameViewState {
    private final Font font1;
    private final Font font2;

    public PauseView(GameGraphics graphics) {
        font1 = graphics.getGameFont1();
        font2 = graphics.getGameFont2();
    }

    @Override
    public void draw(Graphics g, GameModel model) {
        PauseState pauseState = (PauseState) model.getCurrentState();
        PauseOption selection = pauseState.getSelection();

        int selectionYcor;
        switch (selection) {
            case CONTINUE -> selectionYcor = PAUSECONTINUEY;
            case MAIN_MENU -> selectionYcor = PAUSEMAINMENUY;
            case QUIT -> selectionYcor = PAUSEQUITY;
            default -> selectionYcor = PAUSECONTINUEY;
        }

        g.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
        g.fillRect(0, 0, model.getScreenWidth(), model.getScreenHeight());

        g.setColor(Color.decode(GOLD_TEXT_COLOR));
        g.setFont(font1);
        g.drawString(PAUSETEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV);
        g.setColor(Color.WHITE);
        g.setFont(font2);
        g.drawString(PAUSECONTINUETEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV + PAUSECONTINUEY);
        g.drawString(PAUSEMAINMENUTEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV + PAUSEMAINMENUY);
        g.drawString(PAUSEQUITTEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF, model.getScreenHeight() / PAUSECENTERDIV + PAUSEQUITY);
        g.drawString(PAUSESELECTIONTEXT, model.getScreenWidth() / PAUSECENTERDIV - PAUSEWIDTHOFF - PAUSESELECTIONX, model.getScreenHeight() / PAUSECENTERDIV + selectionYcor);
    }

    @Override
    public void startFade() {

    }

    @Override
    public void update() {

    }

    @Override
    public void stopFade() {

    }
}
