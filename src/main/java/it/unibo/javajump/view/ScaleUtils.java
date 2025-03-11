package it.unibo.javajump.view;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.MAIN_VIEW_CENTER_DIV;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_DRAW_X_INIT;
import static it.unibo.javajump.utility.Constants.MAIN_VIEW_DRAW_Y_INIT;

public class ScaleUtils {
    public static Rectangle computeScaledRectangle(final int virtualWidth, final int virtualHeight, final Dimension panelSize) {
        final float targetAspect = (float) virtualWidth / virtualHeight;
        final float panelAspect = (float) panelSize.width / panelSize.height;
        final int drawWidth, drawHeight, drawX, drawY;

        if (panelAspect > targetAspect) {
            drawHeight = panelSize.height;
            drawWidth = Math.round(drawHeight * targetAspect);
            drawX = (panelSize.width - drawWidth) / MAIN_VIEW_CENTER_DIV;
            drawY = MAIN_VIEW_DRAW_Y_INIT;
        } else {
            drawWidth = panelSize.width;
            drawHeight = Math.round(drawWidth / targetAspect);
            drawX = MAIN_VIEW_DRAW_X_INIT;
            drawY = (panelSize.height - drawHeight) / MAIN_VIEW_CENTER_DIV;
        }
        return new Rectangle(drawX, drawY, drawWidth, drawHeight);
    }
}
