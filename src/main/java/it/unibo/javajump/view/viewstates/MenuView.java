package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;

import it.unibo.javajump.view.graphics.GameGraphicsImpl;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

public class MenuView implements GameViewState {

	@Override
	public void draw(Graphics g, GameModel model) {

		int width = model.getScreenWidth();
		int height = model.getScreenHeight();

		int centerX = width / MENUVIEWCENTERDIV;
		int centerY = height / MENUVIEWCENTERDIV;

		int bestScore = model.getScoreManager().getBestScore();

		g.setColor(Color.decode("#05051C"));
		g.fillRect(MENUVIEWRECTX, MENUVIEWRECTY, width, height);

		BufferedImage title = GameGraphicsImpl.getTitle();
		g.drawImage(title, (int) (width * MENUVIEWIMGWIDTHSCALAR), height / MENUVIEWIMGHEIGHTSCALAR, (title.getWidth() * MENUVIEWTILEWSCALAR), (title.getHeight() * MENUVIEWTILEHSCALAR), null);

		g.setColor(Color.WHITE);
		g.setFont(GameGraphicsImpl.getGameFont1());
		g.drawString(MENUVIEWSTARTTEXT, centerX / MENUVIEWXCENTER, centerY + MENUVIEWSTARTYOFFSET);
		g.drawString(MENUVIEWQUITTEXT, centerX / MENUVIEWXCENTER, centerY + MENUVIEWQUITYOFFSET);

		g.setColor(Color.decode("#eac10c"));
		Stroke dashed = new BasicStroke(MENUVIEWSTOKEWIDTH, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, MENUVIEWSTROKEMITERLIMIT, new float[]{MENUVIEWSTROKEARRAY}, MENUVIEWSTROKEDASH);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(dashed);
		g2d.drawRoundRect((centerX / MENUVIEWROUNDRECTXDIV) - MENUVIEWROUNDRECTXOFF, height - MENUVIEWROUNDRECTYOFF, MENUVIEWROUNDRECTWIDTH, MENUVIEWROUNDRECTHEIGHT, MENUVIEWROUNDRECTARCW, MENUVIEWROUNDRECTARCH);
		g.setFont(GameGraphicsImpl.getGameFont2());
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
