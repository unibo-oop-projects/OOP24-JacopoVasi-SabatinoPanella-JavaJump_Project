package view.view_states;

import model.GameModel;
import model.entities.GameObject;
import model.entities.Character;
import view.graphics.GameGraphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class InGameView implements GameViewState {

	private static final float PARALLAX_FACTOR = 0.2f;
	private boolean debugMode = true;


	@Override
	public void draw(Graphics g, GameModel model) {

		drawParallaxBackground(g, model);


		float cameraOffsetY = model.getCameraManager().getCurrentOffset();


		g.setColor(Color.RED);
		for (GameObject obj : model.getGameObjects()) {
			if(debugMode) {
				if (obj instanceof Character) {
					continue;
				}
				int drawX = (int)obj.getX();
				int drawY = (int)(obj.getY() - cameraOffsetY);

				g.drawRect(drawX, drawY, (int)obj.getWidth(), (int)obj.getHeight());
			}
		}


		Character player = model.getPlayer();
		float drawX = player.getX();
		float drawY = player.getY() - cameraOffsetY;

		int frameIndex = player.getFrameIndex();
		BufferedImage sheet = GameGraphics.getPlayerSheet();

		int frameWidth = 48;
		int frameHeight = 50;
		int sx = frameIndex * frameWidth;
		int sy = 0;

		BufferedImage frame = sheet.getSubimage(sx, sy, frameWidth, frameHeight);

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform oldTransform = g2.getTransform();


		boolean facingRight = player.isFacingRight();
		if (!facingRight) {



			g2.translate(drawX + frameWidth, drawY);
			g2.scale(-1, 1);

			g2.drawImage(frame, 0, 0, null);
		} else {

			g2.drawImage(frame, (int)drawX, (int)drawY, null);
		}


		g2.setTransform(oldTransform);



		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		String scoreStr = "Score: " + model.getScore();
		g.drawString(scoreStr, 10, 20);
	}


	private void drawParallaxBackground(Graphics g, GameModel model) {
		BufferedImage bgTile = GameGraphics.getBackground();
		if (bgTile == null) {

			g.setColor(Color.BLACK);
			g.fillRect(0,0, model.getScreenWidth(), model.getScreenHeight());
			return;
		}

		int screenW = model.getScreenWidth();
		int screenH = model.getScreenHeight();


		float cameraOffset = model.getCameraManager().getCurrentOffset();

		float bgOffsetY = cameraOffset * PARALLAX_FACTOR;


		int tileW = bgTile.getWidth();
		int tileH = bgTile.getHeight();




		Graphics2D g2 = (Graphics2D) g;



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