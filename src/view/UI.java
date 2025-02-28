package view;
import model.GameModel;
import javax.swing.*;
import java.awt.*;
public class UI extends JPanel implements AbstractView {
    private final GameModel model;
    public UI(GameModel gameModel) {
        this.model = gameModel;
    }

    @Override
    public void updateG() {
    this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Score: "+String.valueOf(model.getScore()), 80,80);
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
