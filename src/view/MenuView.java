package view;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class MenuView extends JPanel implements AbstractView {
    private final MenuBar menuBar;
    private final int centerX, centerY, offset;
    public MenuView() {
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(true);
        this.menuBar = new MenuBar();
        this.centerX = this.getWidth() / 2;
        this.centerY = this.getHeight() / 2;
        this.offset = 40;
    }

    @Override
    public void updateG() {
    this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Welcome to Java Jump Uni", centerX, centerY);
        g2d.setColor(Color.green);
        g2d.drawString("Start", centerX, centerY+offset);


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
