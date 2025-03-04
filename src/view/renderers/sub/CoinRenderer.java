package view.renderers.sub;

import model.entities.Coin;
import model.entities.CoinState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CoinRenderer {
	private final BufferedImage coinSheet;
	private final int frameW = 44;
	private final int frameH = 52;

	public CoinRenderer(BufferedImage sheet) {
		this.coinSheet = sheet;
	}

	public void drawCoin(Graphics2D g2, Coin coin, float offsetY) {
		int row = (coin.getState() == CoinState.IDLE) ? 0 : 1;
		int col = coin.getFrameIndex();

		int sx = col * frameW;
		int sy = row * frameH;
		BufferedImage frame = coinSheet.getSubimage(sx, sy, frameW, frameH);

		float drawX = coin.getX();
		float drawY = coin.getY() - offsetY;
		g2.drawImage(frame, (int)drawX, (int)drawY, null);
	}
}
