package view.view_states;

import model.GameModel;
import model.entities.*;
import model.entities.character.Character;
import model.entities.collectibles.Coin;
import model.entities.platforms.Platform;
import view.renderers.RendererManager;

import java.awt.*;
import java.util.ArrayList;

public class InGameView implements GameViewState {

	private final RendererManager renderer;

	private boolean debugMode = false;

	private boolean isNewHighScore = false;
	private boolean showHighScoreMessage = true;
	private long lastToggleTime = System.currentTimeMillis();

	public InGameView(RendererManager renderer) {
		this.renderer = renderer;
	}

	@Override
	public void draw(Graphics g, GameModel model) {

		ArrayList<GameObject> snapshot;
		synchronized(model.getGameObjects()) {
			snapshot = new ArrayList<>(model.getGameObjects());
			System.out.println(snapshot.size());
		}

		Graphics2D g2 = (Graphics2D) g;


		renderer.drawBackground(g2, model);

		float cameraOffsetY = model.getCameraManager().getCurrentOffset();


		if (debugMode) {
			g2.setColor(Color.RED);
			for (GameObject obj : snapshot) {
				float dx = obj.getX();
				float dy = obj.getY() - cameraOffsetY;
				g2.drawRect((int)dx, (int)dy,
						(int)obj.getWidth(),
						(int)obj.getHeight());
			}
		}


		for (GameObject obj : snapshot) {
			if (obj instanceof Coin c) {
				renderer.drawCoin(g2, c, cameraOffsetY);
			} else if (obj instanceof Platform p) {
				renderer.drawPlatform(g2, p, cameraOffsetY);
			}

		}


		Character player = model.getPlayer();
		renderer.drawPlayer(g2, player, cameraOffsetY);



		long now = System.currentTimeMillis();
		if (now - lastToggleTime > 1700) {
			showHighScoreMessage = !showHighScoreMessage;
			lastToggleTime = now;
		}
		renderer.drawScoreUI(g2, model, isNewHighScore, showHighScoreMessage);
	}

	@Override
	public void startFade() { }
	@Override
	public void update(float deltaTime) { }
	@Override
	public void stopFade() { }
}