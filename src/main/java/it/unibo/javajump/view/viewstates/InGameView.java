package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.entities.*;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.view.MainGameViewImpl;
import it.unibo.javajump.view.renderers.RenderManager;
import it.unibo.javajump.view.renderers.RendererManagerImpl;

import java.awt.*;
import java.util.ArrayList;

import static it.unibo.javajump.utility.Constants.*;

public class InGameView implements GameViewState {

	private final RenderManager renderer;

	private boolean debugMode = false;

	private boolean isNewHighScore = false;
	private boolean showHighScoreMessage = true;
	private long lastToggleTime = System.currentTimeMillis();
	private float deltaTime;

	public InGameView(RenderManager renderer) {
		this.renderer = renderer;
	}

	@Override
	public void draw(Graphics g, GameModel model) {

		ArrayList<GameObject> snapshot;
		synchronized (model.getGameObjects()) {
			snapshot = new ArrayList<>(model.getGameObjects());
			System.out.println(snapshot.size());
		}

		Graphics2D g2 = (Graphics2D) g;


		float deltaTime = MainGameViewImpl.getCurrentDeltaTime();

		renderer.drawBackground1(g2, model, deltaTime);
		renderer.drawBackground2(g2, model, deltaTime);

		float cameraOffsetY = model.getCameraManager().getCurrentOffset();


		if (debugMode) {
			g2.setColor(Color.RED);
			for (GameObject obj : snapshot) {
				float dx = obj.getX();
				float dy = obj.getY() - cameraOffsetY;
				g2.drawRect((int) dx, (int) dy,
						(int) obj.getWidth(),
						(int) obj.getHeight());
			}
		}


		for (GameObject obj : snapshot) {
			if (obj instanceof Coin c) {
				renderer.drawCoin(g2, c, cameraOffsetY, deltaTime);
			} else if (obj instanceof Platform p) {
				renderer.drawPlatform(g2, p, cameraOffsetY);
			}

		}


		renderer.drawPlayer(g2, model.getPlayer(), cameraOffsetY, deltaTime);


		long now = System.currentTimeMillis();
		if (now - lastToggleTime > INGAMETIMETOGGLE) {
			showHighScoreMessage = !showHighScoreMessage;
			lastToggleTime = now;
		}
		renderer.drawScoreUI(g2, model, isNewHighScore, showHighScoreMessage);
	}

	@Override
	public void startFade() {
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void stopFade() {
	}
}
