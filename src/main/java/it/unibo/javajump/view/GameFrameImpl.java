package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;

public class GameFrameImpl extends JFrame implements GameFrame {

    public GameFrameImpl(final String title) {
        this.setTitle(title);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                GameFrameImpl.this.setPreferredSize(e.getComponent().getSize());
                GameFrameImpl.this.pack();
            }
        });
    }

    @Override
    public void setUp(final InputManager inputManager, int height, int width, final MainGameView view) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.addKeyListener(inputManager);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.add((Component) view);
        this.setVisible(true);
    }

    @Override
    public void closeGame() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

