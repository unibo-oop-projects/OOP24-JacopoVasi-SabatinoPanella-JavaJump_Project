package view;
import model.entities.Character;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    public GameView() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }

    @Override
    public void update(Graphics g) {
                super.update(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.black);
                g2d.drawRect(getX(),getY(), getWidth(), getHeight());

    }

}
