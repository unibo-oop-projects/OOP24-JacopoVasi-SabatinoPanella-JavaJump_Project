package view.renderers.sub;

import model.entities.BreakablePlatform;
import model.entities.MovingPlatform;
import model.entities.Platform;

import java.awt.*;

public class PlatformRenderer {

	private final float outlineStrokeWidth;
	private final int roundArcW;
	private final int roundArcH;

	public PlatformRenderer(float outlineStrokeWidth, int arcW, int arcH) {
		this.outlineStrokeWidth = outlineStrokeWidth;
		this.roundArcW = arcW;
		this.roundArcH = arcH;
	}

	
	public void drawPlatform(Graphics2D g2, Platform platform,
							 float cameraOffsetY) {

		float drawX = platform.getX();
		float drawY = platform.getY() - cameraOffsetY;
		float w = platform.getWidth();
		float h = platform.getHeight();


		if (platform instanceof BreakablePlatform) {
			drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode("#ea4b1e"), Color.decode("#d4c340"));
		} else if (platform instanceof MovingPlatform) {
			drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode("#276b91"), Color.decode("#d4c340"));
		} else {

			drawPlatformCommon(g2, drawX, drawY, w, h,
					Color.decode("#4d9f50"),  Color.decode("#d4c340"));
		}
	}

	
	private void drawPlatformCommon(Graphics2D g2,
									float x, float y,
									float w, float h,
									Color startColor,
									Color endColor) {

		Paint oldPaint = g2.getPaint();
		Stroke oldStroke = g2.getStroke();


		GradientPaint gp = new GradientPaint(
				x, y, startColor,
				x, y + h, endColor
		);
		g2.setPaint(gp);


		g2.fillRoundRect((int)x, (int)y, (int)w, (int)h,
				roundArcW, roundArcH);


		g2.setStroke(new BasicStroke(outlineStrokeWidth));
		g2.setColor(Color.decode("#0e081e"));
		g2.drawRoundRect((int)x, (int)y, (int)w, (int)h,
				roundArcW, roundArcH);

		g2.setStroke(oldStroke);
		g2.setPaint(oldPaint);
	}
}
