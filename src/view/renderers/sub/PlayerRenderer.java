package view.renderers.sub;

import model.entities.character.Character;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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
		this.animTimer = 0;
		this.FRAME_DURATION = FRAME_DURATION;
	}

	public void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime) {

		if (player.isOnPlatform() != prevOnPlatform) {
			animTimer = 0;
			prevOnPlatform = player.isOnPlatform();
		} else {
			animTimer += deltaTime;
		}

		int frameIndex;
		if (player.isOnPlatform()) {

			float cycle = FRAME_DURATION * 2;
			float t = animTimer % cycle;
			frameIndex = (t < FRAME_DURATION) ? 0 : 1;
		} else {

			frameIndex = (animTimer < FRAME_DURATION) ? 2 : 3;
		}

		int sx = frameIndex * frameWidth;
		int sy = 0;
		BufferedImage frame = playerSheet.getSubimage(sx, sy, frameWidth, frameHeight);

		float drawX = player.getX();
		float drawY = player.getY() - offsetY;

		AffineTransform old = g2.getTransform();
		if (!player.isFacingRight()) {

			g2.translate(drawX + frameWidth, drawY);
			g2.scale(-1, 1);
			g2.drawImage(frame, 0, 0, null);
		} else {
			g2.drawImage(frame, (int) drawX, (int) drawY, null);
		}
		g2.setTransform(old);
	}
}
