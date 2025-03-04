package view.view_states;

import model.GameModel;
import view.graphics.GameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuView implements GameViewState {

    @Override
    public void draw(Graphics g, GameModel model) {

        int width = model.getScreenWidth();
        int height = model.getScreenHeight();

        int centerX = width / 2;
        int centerY = height / 2;

        int bestScore = model.getScoreManager().getBestScore();

        g.setColor(Color.decode("#05051C"));
        g.fillRect(0, 0, width, height);

        BufferedImage title = GameGraphics.getTitle();
        g.drawImage(title, (int) (width*0.03), (int) height/5, (int) (title.getWidth() * 1.3), (int) (title.getHeight() * 1.3),null);

        g.setColor(Color.WHITE);
        g.setFont(GameGraphics.getGameFont1());
        g.drawString("- Start Game (ENTER)", centerX/4, centerY + 50);
        g.drawString("- Quit (ESC)", centerX/4, centerY + 100);

        g.setColor(Color.decode("#eac10c"));
        Stroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(dashed);
        g2d.drawRoundRect((centerX / 3)-20, height - 120, 400, 40, 10, 10);
        g.setFont(GameGraphics.getGameFont2());
        g.drawString("Best Score:   " + bestScore, centerX / 3, height - 95);



    }

    @Override
    public void startFade()
    {

    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void stopFade()
    {

    }
}
