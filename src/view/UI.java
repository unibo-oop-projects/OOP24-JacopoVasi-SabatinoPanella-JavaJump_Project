package view;

import model.GameModel;
import model.states.GameStateHandler;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel implements AbstractView {
    private final GameModel model;
    private GameStateHandler stateHandler;
    public UI(GameModel gameModel) {
        this.model = gameModel;
        this.stateHandler= model.getCurrentState();
    }

    @Override
    public void updateG() {
    stateHandler=model.getCurrentState();
    this.repaint();
    }

    public void drawGameOver(Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("GAME OVER", 80,80);
    }

    public void drawPause(Graphics2D g2d){

    }

    public void drawScore(Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Score: "+String.valueOf(model.getScore()), 80,80);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        switch (model.getCurrentState().getGameState()){
            case PAUSE -> drawPause(g2d);
            case IN_GAME -> drawScore(g2d);
            case GAME_OVER -> drawGameOver(g2d);
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
