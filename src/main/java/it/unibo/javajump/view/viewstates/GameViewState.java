package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;

import java.awt.*;


public interface GameViewState {

    void draw(Graphics g, GameModel model);

    void startFade();

    void update();

    void stopFade();
}
