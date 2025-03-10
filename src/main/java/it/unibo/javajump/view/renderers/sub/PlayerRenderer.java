package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.character.CharacterImpl;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public class PlayerRenderer {

	private final BufferedImage playerSheet;
	private final int frameWidth;
	private final int frameHeight;
	private final float FRAME_DURATION;

	private float animTimer;
	private boolean prevOnPlatform;

	public PlayerRenderer(BufferedImage sheet, int frameWidth, int frameHeight, float FRAME_DURATION) {
		this.playerSheet = sheet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.prevOnPlatform = false;
		this.animTimer = ZERO;
		this.FRAME_DURATION = FRAME_DURATION;
	}

	public void drawPlayer(Graphics2D g2, CharacterImpl player, float offsetY, float deltaTime) {

		if (player.isOnPlatform() != prevOnPlatform) {
			animTimer = ZERO;
			prevOnPlatform = player.isOnPlatform();
		} else {
			animTimer += deltaTime;
		}

		int frameIndex;
		if (player.isOnPlatform()) {

			float cycle = FRAME_DURATION * PLAYERCYCLEDURATION;
			float t = animTimer % cycle;
			frameIndex = (t < FRAME_DURATION) ? PLAYERFIRSTFRAME : PLAYERSECONDFRAME;
		} else {

			frameIndex = (animTimer < FRAME_DURATION) ? PLAYERTHIRDFRAME : PLAYERFOURTHFRAME;
		}

		int sx = frameIndex * frameWidth;
		int sy = ZERO;
		BufferedImage frame = playerSheet.getSubimage(sx, sy, frameWidth, frameHeight);

		float drawX = player.getX();
		float drawY = player.getY() - offsetY;

		AffineTransform old = g2.getTransform();
		if (!player.isFacingRight()) {

			g2.translate(drawX + frameWidth, drawY);
			g2.scale(MINUSONE, ONE);
			g2.drawImage(frame, ZERO, ZERO, null);
		} else {
			g2.drawImage(frame, (int) drawX, (int) drawY, null);
		}
		g2.setTransform(old);
	}
}
