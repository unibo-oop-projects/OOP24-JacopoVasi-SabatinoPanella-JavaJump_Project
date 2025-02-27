package view;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel implements AbstractView {

    @Override
    public void updateG() {
    this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

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
