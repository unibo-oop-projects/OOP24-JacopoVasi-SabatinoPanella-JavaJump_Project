package view.renderers.sub;

import model.entities.collectibles.Coin;
import model.entities.collectibles.CoinState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static Utility.Constants.*;


public class CoinRenderer {
	private final BufferedImage coinSheet;
	private final int frameWidth;
	private final int frameHeight;
	private final float frameDuration;

	private final Map<Coin, Float> coinAnimTimers = new HashMap<>();
	private final Map<Coin, CoinState> coinLastStates = new HashMap<>();

	public CoinRenderer(BufferedImage sheet, int frameWidth, int frameHeight, float frameDuration) {
		this.coinSheet = sheet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.frameDuration = frameDuration;
	}

	public void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime) {
		if (coin.getState() == CoinState.FINISHED) {
			return;
		}

		CoinState prevState = coinLastStates.get(coin);
		if (prevState == null || !prevState.equals(coin.getState())) {
			coinAnimTimers.put(coin, COINANIMTIMERS);
			coinLastStates.put(coin, coin.getState());
		}

		float timer = coinAnimTimers.get(coin);
		timer += deltaTime;
		coinAnimTimers.put(coin, timer);

		int frameIndex;
		int row;
		if (coin.getState() == CoinState.IDLE) {
			float cycle = frameDuration * COINCYCLEDURATION;
			float t = timer % cycle;
			frameIndex = (int)(t / frameDuration);
			row = ZERO;
		} else {
			int idx = (int)(timer / frameDuration);
			if (idx >= COINIDXMAX) {
				frameIndex = COINCYCLEDURATION;
				coin.markAsDone();
				removeCoin(coin);
			} else {
				frameIndex = idx;
			}
			row = 1;
		}

		int sx = frameIndex * frameWidth;
		int sy = row * frameHeight;
		BufferedImage frame = coinSheet.getSubimage(sx, sy, frameWidth, frameHeight);

		float drawX = coin.getX();
		float drawY = coin.getY() - offsetY;
		g2.drawImage(frame, (int) drawX, (int) drawY, null);
	}

	
	public void removeCoin(Coin coin) {
		coinAnimTimers.remove(coin);
		coinLastStates.remove(coin);
	}
}
