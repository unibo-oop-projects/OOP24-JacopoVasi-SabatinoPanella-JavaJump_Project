package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;

/**
 * The type Game frame.
 */
public class GameFrameImpl extends JFrame implements GameFrame {

    /**
     * Instantiates a new Game frame.
     *
     *
     */
    public GameFrameImpl() {


    }

    @Override
    public void setUp(final InputManager inputManager, int height, int width, final MainGameView view, final String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.addKeyListener(inputManager);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.add((Component) view);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                GameFrameImpl.this.setPreferredSize(e.getComponent().getSize());
                GameFrameImpl.this.pack();
            }
        });
        this.setVisible(true);
    }

    @Override
    public void closeGame() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

