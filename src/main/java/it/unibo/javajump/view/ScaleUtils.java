package it.unibo.javajump.view;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.*;

public class ScaleUtils {
	public static Rectangle computeScaledRectangle(int virtualWidth, int virtualHeight, Dimension panelSize) {
		float targetAspect = (float) virtualWidth / virtualHeight;
		float panelAspect = (float) panelSize.width / panelSize.height;
		int drawWidth, drawHeight, drawX, drawY;

		if (panelAspect > targetAspect) {
			drawHeight = panelSize.height;
			drawWidth = Math.round(drawHeight * targetAspect);
			drawX = (panelSize.width - drawWidth) / MAINVIEWCENTERDIV;
			drawY = MAINVIEWDRAWYINIT;
		} else {
			drawWidth = panelSize.width;
			drawHeight = Math.round(drawWidth / targetAspect);
			drawX = MAINVIEWDRAWXINIT;
			drawY = (panelSize.height - drawHeight) / MAINVIEWCENTERDIV;
		}
		return new Rectangle(drawX, drawY, drawWidth, drawHeight);
	}
}
