package view;
import model.GameModel;
import model.GameModelObserver;
import model.entities.Character;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameModelObserver
{
    private final GameModel model;

    public GameView(GameModel model)
    {
        this.model = model;
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    @Override
    public void update(Graphics g)
    {
                super.update(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.black);
                g2d.drawRect(getX(),getY(), getWidth(), getHeight());

    }


    @Override
    public void onModelUpdate(GameModel model)
    {
        repaint();
    }

}
