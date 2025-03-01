package view;

import model.GameModel;
import model.states.GameStateHandler;
import model.states.MenuState;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class MenuView extends JPanel implements AbstractView {
    private final int centerX, centerY, offset;
    private final GameModel model;
    private GameStateHandler menuState;
    private int selection;
    public MenuView(GameModel model) {
        this.setBackground(Color.BLACK);
        this.setSize(800, 800);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(false);
        this.model = model;
        this.centerX = this.getWidth() / 2;
        this.centerY = this.getHeight() / 2;
        this.offset = 40;
        this.selection = 0;
    }

    @Override
    public void updateG() {
    this.menuState=model.getCurrentState();
    this.repaint();
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        selection=this.model.getCurrentState().getValue();

        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, 40);
        FontMetrics metrics = g.getFontMetrics(font);
        String text = "Main menu";

        int x =  (this.getWidth() - metrics.stringWidth(text)) / 2;

        int y = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setFont(font);

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);
        font = new Font("Arial", Font.PLAIN, 20);
         metrics = g.getFontMetrics(font);
        text = "Start";
         x =  (this.getWidth() - metrics.stringWidth(text)) / 2;
        g2d.setFont(font);
        g2d.drawString(text, x, y+offset*2);
        text = "Quit";
        x =  (this.getWidth() - metrics.stringWidth(text)) / 2;
        g2d.drawString(text, x, y+offset*3);
        text="- ";
        x =  (this.getWidth() - metrics.stringWidth(text)) / 2;
        switch (selection) {
            case 0:
                break;
            case 1:
                g2d.drawString(text, x-offset, y+offset*2);
                break;
            case 2:
                g2d.drawString(text, x-offset, y+offset*3);
                break;
            default:
                break;

        }
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
