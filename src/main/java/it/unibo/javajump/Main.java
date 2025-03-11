package it.unibo.javajump;

import it.unibo.javajump.controller.GameInitializer;
import it.unibo.javajump.controller.GameInitializerImpl;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        final GameInitializer newGame = new GameInitializerImpl();
        newGame.initialize();
    }
}
