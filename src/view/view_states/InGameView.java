package view.view_states;

import model.GameModel;
import model.entities.*;
import model.entities.Character;
import view.graphics.GameGraphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InGameView implements GameViewState {

	private boolean debugMode = false;

	private static final float PARALLAX_FACTOR = 0.2f;

	private boolean isNewHighScore = false;
	private boolean showHighScoreMessage = true;
	private long lastToggleTime = System.currentTimeMillis();

	@Override
	public void draw(Graphics g, GameModel model) {

		ArrayList<GameObject> snapshot;

		synchronized (model.getGameObjects()) {
			snapshot = new ArrayList<>(model.getGameObjects());
		}

		drawParallaxBackground(g, model);


		float cameraOffsetY = model.getCameraManager().getCurrentOffset();


		if (debugMode) {
			g.setColor(Color.RED);
			for (GameObject obj : snapshot) {
				int drawX = (int) obj.getX();
				int drawY = (int) (obj.getY() - cameraOffsetY);
				g.drawRect(drawX, drawY, (int) obj.getWidth(), (int) obj.getHeight());
			}
		}


		for (GameObject obj : snapshot) {
			if (obj instanceof Coin c) {
				drawCoin(g, c, cameraOffsetY);
			}
			else if (obj instanceof Platform p) {
				drawPlatform(g, p, cameraOffsetY);
			}
			if (obj instanceof MovingPlatform p) {
				drawMovingPlatform(g, p, cameraOffsetY);
			}
			else if (obj instanceof BreakablePlatform bp) {
				drawBreakablePlatform(g, bp, cameraOffsetY);
			}

		}

		drawPlayer(g, model, cameraOffsetY);

		drawScoreAndUI(g, model);






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

	private void drawCoin(Graphics g, Coin c, float cameraOffsetY) {

		int row = (c.getState() == CoinState.IDLE) ? 0 : 1;
		int col = c.getFrameIndex();

		int sx = col * 44;
		int sy = row * 52;
		BufferedImage coinSheet = GameGraphics.getCoinSheet();
		BufferedImage frame = coinSheet.getSubimage(sx, sy, 44, 52);

		float drawY = c.getY() - cameraOffsetY;
		g.drawImage(frame, (int)c.getX(), (int)drawY, null);
	}

	private void drawPlatform(Graphics g, Platform platform, float cameraOffsetY) {

		int drawX = (int) platform.getX();
		int drawY = (int) (platform.getY() - cameraOffsetY);



		g.setColor(Color.GRAY);
		g.fillRoundRect(drawX, drawY, (int)platform.getWidth(), (int)platform.getHeight(), 10, 10);
	}

	private void drawMovingPlatform(Graphics g, Platform platform, float cameraOffsetY) {

		int drawX = (int) platform.getX();
		int drawY = (int) (platform.getY() - cameraOffsetY);



		g.setColor(Color.BLUE);
		g.fillRoundRect(drawX, drawY, (int)platform.getWidth(), (int)platform.getHeight(), 10, 10);
	}

	private void drawBreakablePlatform(Graphics g, Platform platform, float cameraOffsetY) {

		int drawX = (int) platform.getX();
		int drawY = (int) (platform.getY() - cameraOffsetY);



		g.setColor(Color.RED);
		g.fillRoundRect(drawX, drawY, (int)platform.getWidth(), (int)platform.getHeight(), 10, 10);
	}


	private void drawPlayer(Graphics g, GameModel model, float cameraOffsetY) {
		Character player = model.getPlayer();

		int frameIndex = player.getFrameIndex();
		BufferedImage sheet = GameGraphics.getPlayerSheet();
		int frameWidth = 48;
		int frameHeight = 50;

		int sx = frameIndex * frameWidth;
		int sy = 0;

		BufferedImage frame = sheet.getSubimage(sx, sy, frameWidth, frameHeight);

		float drawX = player.getX();
		float drawY = player.getY() - cameraOffsetY;

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform oldTransform = g2.getTransform();

		if (!player.isFacingRight()) {

			g2.translate(drawX + frameWidth, drawY);
			g2.scale(-1, 1);
			g2.drawImage(frame, 0, 0, null);
		} else {

			g2.drawImage(frame, (int)drawX, (int)drawY, null);
		}

		g2.setTransform(oldTransform);
	}

	private void drawScoreAndUI(Graphics g, GameModel model) {

		BufferedImage scoreContainer = GameGraphics.getScoreContainer();
		g.drawImage(scoreContainer, 0, 0, null);

		if (model.getScore() <  model.getScoreManager().getBestScore()) {
			g.setColor(Color.WHITE);
			g.setFont(GameGraphics.getGameFont2());
			String scoreStr = "Score:    " + model.getScore();
			g.drawString(scoreStr, 10, 20);
			isNewHighScore = false;
		} else {
			g.setColor(Color.decode("#eac10c"));
			g.setFont(GameGraphics.getGameFont2());
			String scoreStr = "Score:    " + model.getScore();
			g.drawString(scoreStr, 10, 20);
			isNewHighScore = true;
		}


		long currentTime = System.currentTimeMillis();
		if (currentTime - lastToggleTime > 1700) {
			showHighScoreMessage = !showHighScoreMessage;
			lastToggleTime = currentTime;
		}

		if (isNewHighScore && showHighScoreMessage) {
			g.setColor(Color.decode("#D83426"));
			g.setFont(GameGraphics.getGameFont3());
			String message = "New High Score !!";
			g.drawString(message, 10, 43);
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