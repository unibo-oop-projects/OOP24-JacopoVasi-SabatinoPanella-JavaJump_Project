package view;
import model.entities.Character;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    public GameView() {
        this.setLayout(null);
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(true);
        this.setSize(800, 600);
    }
    @Override
    public void update(Graphics g) {
                super.update(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.black);
                g2d.drawRect(getX(),getY(), getWidth(), getHeight());

    }

}
