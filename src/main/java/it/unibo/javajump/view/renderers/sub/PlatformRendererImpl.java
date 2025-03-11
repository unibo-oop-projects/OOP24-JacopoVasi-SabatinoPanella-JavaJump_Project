package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.platforms.*;
import it.unibo.javajump.view.sound.sfx.*;

import java.awt.*;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Class implementing the PlatformRenderer interface, used to draw a platform.
 */
public class PlatformRendererImpl implements PlatformRenderer {
	/**
	 * The width of the outline stroke.
	 */
	private final float outlineStrokeWidth;
	/**
	 * the width of the rounded corners
	 */
	private final int roundArcW;
	/**
	 * the height of the rounded corners
	 */
	private final int roundArcH;

	private final SoundEffectsManager soundEffectsManager;

	/**
	 * Class constructor for the PlatformRendererImpl, that initializes the fields for Platform rendering.
	 *
	 * @param outlineStrokeWidth the width of the outline stroke
	 * @param arcW               the width of the rounded corners
	 * @param arcH               the height of the rounded corners
	 */
	public PlatformRendererImpl(float outlineStrokeWidth, int arcW, int arcH, SoundEffectsManager soundEffectsManager) {
		this.outlineStrokeWidth = outlineStrokeWidth;
		this.roundArcW = arcW;
		this.roundArcH = arcH;
		this.soundEffectsManager = soundEffectsManager;
	}

	/**
	 * {@inheritDoc}
	 * The platforms are drawn using the drawPlatformCommon method,
	 * the base color of the platform is based on the type of platform,
	 * and the highlight color is always the same for all platforms.
	 */
	@Override
	public void drawPlatform(Graphics2D g2, Platform platform, float cameraOffsetY) {
		float drawX = platform.getX();
		float drawY = platform.getY() - cameraOffsetY;
		float w = platform.getWidth();
		float h = platform.getHeight();

		switch (platform) {
			case BouncePlatform ignored -> drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode(BOUNCE_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
			case BreakablePlatform ignored -> drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode(BREAKABLE_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
			case MovingPlatform ignored -> drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode(MOVING_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
			default -> drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode(STANDARD_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
		}

		if (platform.consumeTouched()) {
			if (platform instanceof BouncePlatform) {
				soundEffectsManager.playSound(SFXType.BOUNCE);
				System.out.println("bounce platform touched");
			} else if (platform instanceof BreakablePlatform bp) {
				soundEffectsManager.playSound(SFXType.BREAK);
				System.out.println("breakable platform broken");
				bp.setFinished();
			} else {
				soundEffectsManager.playSound(SFXType.DEFAULT);
				System.out.println("platform touched");
			}
		}
	}

	/**
	 * Common drawing method for all Platform type objects.
	 * Uses an outline stroke & a vertical gradient for inner color.
	 *
	 * @param g2         the Graphics2D context
	 * @param x          Platform's X position
	 * @param y          Platform's Y position
	 * @param w          Platform's width
	 * @param h          Platform's height
	 * @param startColor the starting color for the gradient
	 * @param endColor   the ending color for the gradient
	 */
	private void drawPlatformCommon(Graphics2D g2, float x, float y, float w, float h, Color startColor, Color endColor) {
		Paint oldPaint = g2.getPaint();
		Stroke oldStroke = g2.getStroke();

		GradientPaint gp = new GradientPaint(x, y, startColor, x, y + h, endColor);
		g2.setPaint(gp);
		g2.fillRoundRect((int) x, (int) y, (int) w, (int) h, roundArcW, roundArcH);

		g2.setStroke(new BasicStroke(outlineStrokeWidth));
		g2.setColor(Color.decode(OUTLINE_COLOR));
		g2.drawRoundRect((int) x, (int) y, (int) w, (int) h, roundArcW, roundArcH);

		g2.setStroke(oldStroke);
		g2.setPaint(oldPaint);
	}
}
