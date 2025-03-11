package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public class GameOverView implements GameViewState {

    private float fadeAlpha = GAMEOVERALPHAINIT;
    private float elapsedTime = GAMEOVERTIMEINIT;
    private boolean fading = false;
    private float deltaTime = 0;

    private final Font gameFont2;
    private final Font gameFont3;
    private final BufferedImage gameoverImage;

    public GameOverView(GameGraphics gameGraphics) {
        gameFont2 = gameGraphics.getGameFont2();
        gameFont3 = gameGraphics.getGameFont3();
        gameoverImage = gameGraphics.getGameOver();
    }


    public void startFade() {
        this.fadeAlpha = GAMEOVERALPHAINIT;
        this.elapsedTime = GAMEOVERTIMEINIT;
        this.fading = true;
    }


    public void stopFade() {
        this.fadeAlpha = GAMEOVERALPHA;
        this.fading = false;
    }


    public void update() {
        if (fading) {
            elapsedTime += deltaTime;
            fadeAlpha = Math.min(GAMEOVERALPHA, elapsedTime / GAMEOVERDURATIONINIT);
            if (fadeAlpha >= GAMEOVERALPHA) {
                fading = false;
            }
        }
    }


    @Override
    public void draw(Graphics g, GameModel model) {
        this.deltaTime = model.getDeltaTime();
        Graphics2D g2 = (Graphics2D) g;
        Composite oldComposite = g2.getComposite();

        int w = model.getScreenWidth();
        int h = model.getScreenHeight();

        int centerX = w / GAMEOVERCENTERDIV;
        int centerY = h / GAMEOVERCENTERDIV;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
        g2.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
        g2.fillRect(GAMEOVERRECTX, GAMEOVERRECTY, w, h);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, GAMEOVERALPHA));
        g2.drawImage(gameoverImage, (int) (centerX - gameoverImage.getWidth() / GAMEOVERIMGWOFF), (int) (centerY - h * GAMEOVERIMGHOFF), (int) (gameoverImage.getWidth() * GAMEOVERIMGSCALEOFF), (int) (gameoverImage.getHeight() * GAMEOVERIMGSCALEOFF), null);

        if (fadeAlpha >= GAMEOVERALPHA) {
            if (model.getScoreManager().isBestScoreReached()) {
                g.setColor(Color.decode(GOLD_TEXT_COLOR));
                g.setFont(gameFont2);
                g.drawString(GAMEOVERNEWTEXT + model.getScoreManager().getBestScore() + GAMEOVERNEWTEXTESC, (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTNEWYOFF);
            } else {
                g.setColor(Color.WHITE);
                g.setFont(gameFont2);
                g.drawString(GAMEOVERSCORETEXT + model.getScore(), (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTSCOREYOFF);

                g.setColor(Color.decode(RED_TEXT_COLOR));
                g.setFont(gameFont3);
                g.drawString(GAMEOVERBESTTEXT + model.getScoreManager().getBestScore(), (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTBESTYOFF);
            }

            g2.setColor(Color.decode(RED_TEXT_COLOR));
            g2.setFont(gameFont3);
            g2.drawString(GAMEOVERCONTINUETEXT, (int) (centerX * GAMEOVERTEXTXOFF), centerY + GAMEOVERTEXTCONTINUEYOFF);

        }


        g2.setComposite(oldComposite);
    }
}
