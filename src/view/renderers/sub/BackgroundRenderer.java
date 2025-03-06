package view.renderers.sub;

import model.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundRenderer {
	private final BufferedImage bgTile;
	private final float parallaxFactor;

	private final float horizontalSpeed;
	private float horizontalOffset;

	
	public BackgroundRenderer(BufferedImage bgTile, float parallaxFactor, float horizontalSpeed) {
		this.bgTile = bgTile;
		this.parallaxFactor = parallaxFactor;
		this.horizontalSpeed = horizontalSpeed;
		this.horizontalOffset = 0;
	}

	
	public void updateBackground(float deltaTime) {


		if (horizontalSpeed != 0) {
			horizontalOffset += horizontalSpeed * deltaTime;

			int tileW = bgTile.getWidth();

			if (horizontalOffset >= tileW) {
				horizontalOffset -= tileW;
			} else if (horizontalOffset < 0) {
				horizontalOffset += tileW;
			}
		}
	}

	
	public void drawBackground(Graphics2D g2, GameModel model, float deltaTime) {
		int screenW = model.getScreenWidth();
		int screenH = model.getScreenHeight();


		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float verticalOffset = cameraOffset * parallaxFactor;

		int tileW = bgTile.getWidth();
		int tileH = bgTile.getHeight();

		updateBackground(deltaTime);


		int shiftY = (int) (verticalOffset) % tileH;
		if (shiftY < 0) {
			shiftY += tileH;
		}


		int shiftX = (int) horizontalOffset;


		int verticalTiles = (screenH / tileH) + 2;
		int horizontalTiles = (screenW / tileW) + 2;


		for (int i = 0; i < verticalTiles; i++) {
			int drawY = -shiftY + i * tileH;
			for (int j = 0; j < horizontalTiles; j++) {
				int drawX = -shiftX + j * tileW;
				g2.drawImage(bgTile, drawX, drawY, null);
			}
		}
	}
}
