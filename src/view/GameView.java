package view;
import model.GameModel;
import model.GameModelObserver;
import model.entities.Character;
import model.entities.GameObject;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements AbstractView
{
    private final GameModel model;
    private GameObject gameObject;
    private Character player;

    public GameView(GameModel model)
    {
        this.model = model;
        this.setSize(800,800);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(false);
    }


    public void updateG(){
        this.repaint();

    }

    @Override
    public void paintComponent(Graphics g)
    {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.red);
                g2d.drawRect(20, 20, 40, 40);


                if(model.getGameObjects() != null) {

                    for (int i = 0; i < model.getGameObjects().size(); i++) {
                        gameObject = model.getGameObjects().get(i);

                        g2d.drawRect(Math.round(gameObject.getX()), Math.round(gameObject.getY()), Math.round(gameObject.getWidth()), Math.round(gameObject.getHeight()));
                    }
                }


                g2d.setColor(Color.green);
                player= model.getPlayer();
                if(model.getPlayer() != null) {

                    g2d.drawRect(Math.round(player.getX()), Math.round(player.getY()), Math.round(player.getWidth()), Math.round(player.getHeight()));
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
        System.out.println(this.isVisible());
    }


    public void onModelUpdate(GameModel model)
    {

        update(this.getGraphics());

    }

}
