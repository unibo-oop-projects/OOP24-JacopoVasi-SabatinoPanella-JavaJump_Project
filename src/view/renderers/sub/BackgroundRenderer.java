package view.renderers.sub;

import model.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundRenderer {
	private final BufferedImage bgTile;
	private final float parallaxFactor;

	public BackgroundRenderer(BufferedImage bgTile, float factor) {
		this.bgTile = bgTile;
		this.parallaxFactor = factor;
	}

	public void drawBackground(Graphics2D g2, GameModel model) {
		int screenW = model.getScreenWidth();
		int screenH = model.getScreenHeight();

		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float bgOffsetY = cameraOffset * parallaxFactor;


		int tileW = bgTile.getWidth();
		int tileH = bgTile.getHeight();

		int shiftY = (int)(bgOffsetY) % tileH;
		if (shiftY < 0) {
			shiftY += tileH;
		}


		int verticalTiles = (screenH / tileH) + 2;


		for (int i=0; i<verticalTiles; i++) {
			int drawY = -shiftY + i * tileH;

			for (int x=0; x<screenW; x+= tileW) {
				g2.drawImage(bgTile, x, drawY, null);
			}
		}
	}
}
