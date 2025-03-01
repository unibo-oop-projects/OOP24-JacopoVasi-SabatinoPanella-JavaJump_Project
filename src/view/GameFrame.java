package view;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(true);
        this.setBackground(Color.black);
    }


public void addComponents(GameView view) {
        this.add(view);


}
}
