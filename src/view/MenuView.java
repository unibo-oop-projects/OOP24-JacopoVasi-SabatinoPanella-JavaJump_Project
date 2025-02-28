package view;

import model.GameModel;
import model.states.GameStateHandler;
import model.states.MenuState;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class MenuView extends JPanel implements AbstractView {
    private final MenuBar menuBar;
    private final int centerX, centerY, offset;
    private final GameModel model;
    private GameStateHandler menuState;
    public MenuView(GameModel model) {
        this.setBackground(Color.BLACK);
        this.setSize(800, 800);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(false);
        this.model = model;
        this.menuBar = new MenuBar();
        this.centerX = this.getWidth() / 2;
        this.centerY = this.getHeight() / 2;
        this.offset = 40;
    }

    @Override
    public void updateG() {
    this.menuState=model.getCurrentState();
    this.repaint();
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Welcome to Java Jump Uni", 0, centerY);
        g2d.setColor(Color.green);
        g2d.drawString("Start", 0, centerY+offset);
        g2d.drawString("Start", 0, centerY+offset*2);

    }


    @Override
    public void toggleVisibility() {
        if (this.isVisible()){
            this.setVisible(false);
        }else {
            this.setVisible(true);
        }
    }
}
