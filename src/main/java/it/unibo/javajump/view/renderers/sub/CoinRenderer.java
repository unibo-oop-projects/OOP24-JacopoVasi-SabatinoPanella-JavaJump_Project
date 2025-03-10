package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.collectibles.CoinState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static it.unibo.javajump.utility.Constants.*;


public class CoinRenderer implements Renderer {

	private final BufferedImage coinSheet;
	private final int frameWidth;
	private final int frameHeight;
	private final float frameDuration;

	private final Map<CoinImpl, Float> coinAnimTimers = new HashMap<>();
	private final Map<CoinImpl, CoinState> coinLastStates = new HashMap<>();

	public CoinRenderer(BufferedImage sheet, int frameWidth, int frameHeight, float frameDuration) {
		this.coinSheet = sheet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.frameDuration = frameDuration;
	}


	public void drawCoin(Graphics2D g2, CoinImpl coinImpl, float offsetY, float deltaTime) {
		if (coinImpl.getState() == CoinState.FINISHED) {
			return;
		}

		CoinState prevState = coinLastStates.get(coinImpl);
		if (prevState == null || !prevState.equals(coinImpl.getState())) {
			coinAnimTimers.put(coinImpl, COINANIMTIMERS);
			coinLastStates.put(coinImpl, coinImpl.getState());
		}

		float timer = coinAnimTimers.get(coinImpl);
		timer += deltaTime;
		coinAnimTimers.put(coinImpl, timer);

		int frameIndex;
		int row;
		if (coinImpl.getState() == CoinState.IDLE) {
			float cycle = frameDuration * COINCYCLEDURATION;
			float t = timer % cycle;
			frameIndex = (int) (t / frameDuration);
			row = ZERO;
		} else {
			int idx = (int) (timer / frameDuration);
			if (idx >= COINIDXMAX) {
				frameIndex = COINCYCLEDURATION;
				coinImpl.markAsDone();
				removeCoin(coinImpl);
			} else {
				frameIndex = idx;
			}
			row = 1;
		}

		int sx = frameIndex * frameWidth;
		int sy = row * frameHeight;
		BufferedImage frame = coinSheet.getSubimage(sx, sy, frameWidth, frameHeight);

		float drawX = coinImpl.getX();
		float drawY = coinImpl.getY() - offsetY;
		g2.drawImage(frame, (int) drawX, (int) drawY, null);
	}


	public void removeCoin(CoinImpl coinImpl) {
		coinAnimTimers.remove(coinImpl);
		coinLastStates.remove(coinImpl);
	}
}
