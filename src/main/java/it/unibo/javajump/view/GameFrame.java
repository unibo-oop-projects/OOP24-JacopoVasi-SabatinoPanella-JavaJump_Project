package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;


public interface GameFrame {
     void setUp(InputManager inputManager, int height, int width, MainGameView view);
     void closeGame();
}
