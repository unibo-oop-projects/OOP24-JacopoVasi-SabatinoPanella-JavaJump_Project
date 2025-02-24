package view;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

 
public void addComponents(GameView view) {
        this.add(view);

}
}
