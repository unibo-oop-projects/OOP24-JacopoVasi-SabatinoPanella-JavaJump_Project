package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.platforms.*;

import java.awt.*;

public interface PlatformRenderer {

	void drawPlatform(Graphics2D g2, Platform platform, float cameraOffsetY);

}
