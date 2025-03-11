package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public class MenuView implements GameViewState {
	private final Font font1;
	private final Font font2;
	private final BufferedImage title;

	public MenuView(GameGraphics graphics) {
		font1 = graphics.getGameFont1();
		font2 = graphics.getGameFont2();
		title = graphics.getTitle();
	}

	@Override
	public void draw(Graphics g, GameModel model) {

		int width = model.getScreenWidth();
		int height = model.getScreenHeight();

		int centerX = width / MENUVIEWCENTERDIV;
		int centerY = height / MENUVIEWCENTERDIV;

		int bestScore = model.getScoreManager().getBestScore();

		g.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
		g.fillRect(MENUVIEWRECTX, MENUVIEWRECTY, width, height);


		g.drawImage(title, (int) (width * MENUVIEWIMGWIDTHSCALAR), height / MENUVIEWIMGHEIGHTSCALAR, (title.getWidth() * MENUVIEWTILEWSCALAR), (title.getHeight() * MENUVIEWTILEHSCALAR), null);

		g.setColor(Color.WHITE);
		g.setFont(font1);
		g.drawString(MENUVIEWSTARTTEXT, centerX / MENUVIEWXCENTER, centerY + MENUVIEWSTARTYOFFSET);
		g.drawString(MENUVIEWQUITTEXT, centerX / MENUVIEWXCENTER, centerY + MENUVIEWQUITYOFFSET);

		g.setColor(Color.decode(GOLD_TEXT_COLOR));
		Stroke dashed = new BasicStroke(MENUVIEWSTOKEWIDTH, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, MENUVIEWSTROKEMITERLIMIT, new float[]{MENUVIEWSTROKEARRAY}, MENUVIEWSTROKEDASH);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(dashed);
		g2d.drawRoundRect((centerX / MENUVIEWROUNDRECTXDIV) - MENUVIEWROUNDRECTXOFF, height - MENUVIEWROUNDRECTYOFF, MENUVIEWROUNDRECTWIDTH, MENUVIEWROUNDRECTHEIGHT, MENUVIEWROUNDRECTARCW, MENUVIEWROUNDRECTARCH);
		g.setFont(font2);
		g.drawString(MENUVIEWBESTSCORETEXT + bestScore, centerX / MENUVIEWBESTSCOREXDIV, height - MENUVIEWBESTSCOREYOFF);

	}

	@Override
	public void startFade() {

	}

	@Override
	public void update() {

	}

	@Override
	public void stopFade() {

	}
}
