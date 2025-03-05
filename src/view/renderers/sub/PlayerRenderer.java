package view.renderers.sub;

import model.entities.character.Character;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class PlayerRenderer {
	private final BufferedImage sheet;
	private final int frameWidth;
	private final int frameHeight;

	public PlayerRenderer(BufferedImage sheet, int frameWidth, int frameHeight) {
		this.sheet = sheet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
	}

	public void drawPlayer(Graphics2D g2, Character player, float offsetY) {
		int frameIndex = player.getFrameIndex();

		int sx = frameIndex * frameWidth;
		int sy = 0;

		BufferedImage frame = sheet.getSubimage(sx, sy, frameWidth, frameHeight);

		float drawX = player.getX();
		float drawY = player.getY() - offsetY;

		AffineTransform old = g2.getTransform();

		if (!player.isFacingRight()) {

			g2.translate(drawX + frameWidth, drawY);
			g2.scale(-1, 1);
			g2.drawImage(frame, 0, 0, null);
		} else {

			g2.drawImage(frame, (int)drawX, (int)drawY, null);
		}

		g2.setTransform(old);
	}
}
