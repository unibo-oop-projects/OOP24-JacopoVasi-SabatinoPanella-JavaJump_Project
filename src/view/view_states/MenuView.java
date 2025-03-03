package view.view_states;

import model.GameModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuView implements GameViewState {

    @Override
    public void draw(Graphics g, GameModel model) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, model.getScreenWidth(), model.getScreenHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("JAVA JUMP - MENU", 100, 100);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("1) Start Game (ENTER)", 100, 160);
        g.drawString("2) Quit (ESC)", 100, 190);
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
