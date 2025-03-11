package it.unibo.javajump;

import it.unibo.javajump.controller.GameInitializer;
import it.unibo.javajump.controller.GameInitializerImpl;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final GameInitializer newGame = new GameInitializerImpl();
        newGame.initialize();
    }
}
