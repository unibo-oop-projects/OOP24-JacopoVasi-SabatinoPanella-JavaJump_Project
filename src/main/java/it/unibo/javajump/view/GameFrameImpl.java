package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

import static it.unibo.javajump.utility.Constants.SCREENHEIGHT;
import static it.unibo.javajump.utility.Constants.SCREENWIDTH;

public class GameFrameImpl extends JFrame implements GameFrame {

    public GameFrameImpl(String title) {
        this.setTitle(title);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                GameFrameImpl.this.setPreferredSize(e.getComponent().getSize());
                GameFrameImpl.this.pack();
            }
        });
    }

    public void setUp(InputManager inputManager, int height, int width, MainGameView view) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.addKeyListener(inputManager);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add((Component) view);
        this.setVisible(true);
    }

    public void closeGame(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

