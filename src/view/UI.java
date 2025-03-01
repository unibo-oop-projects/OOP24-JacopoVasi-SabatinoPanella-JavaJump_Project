package view;

import model.GameModel;
import model.states.GameState;
import model.states.GameStateHandler;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel implements AbstractView {
    private final GameModel model;
    private GameStateHandler stateHandler;
    public UI(GameModel gameModel) {
        this.model = gameModel;
        this.stateHandler= model.getCurrentState();
        this.setVisible(true);
        this.setSize(800,800);

        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }

    @Override
    public void updateG() {
    stateHandler=model.getCurrentState();
    this.repaint();

    }

    public void drawGameOver(Graphics2D g2d){

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("GAME OVER", this.getWidth()/3,this.getHeight()/2);

    }

    public void drawPause(Graphics2D g2d){

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("PAUSE", 80,80);
    }

    public void drawScore(Graphics2D g2d){

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Score: "+String.valueOf(model.getScore()), 80,80);
    }



    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        switch (model.getCurrentState().getGameState()){
            case GameState.PAUSE:
                drawPause(g2d);
                break;
            case GameState.IN_GAME:
                drawScore(g2d);
                break;
            case GameState.GAME_OVER:
                drawGameOver(g2d);
                break;
            default:
                break;
        }

        g2d.dispose();
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
