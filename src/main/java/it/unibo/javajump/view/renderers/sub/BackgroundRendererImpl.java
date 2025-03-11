package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.*;

/**
 * Class that implements the BackgroundRenderer interface, used for graphical rendering of
 * the background.
 */
public class BackgroundRendererImpl implements BackgroundRenderer {
	/**
	 * Image tile of the background
	 */
	private final BufferedImage bgTileEasy;
	private final BufferedImage bgTileMedium;
	private final BufferedImage bgTileHard;
	/**
	 * Factor for vertical parallax effect
	 */
	private final float parallaxFactor;
	/**
	 * Factor for horizontal auto-scrolling effect velocity
	 */
	private final float horizontalSpeed;
	/**
	 * Current horizontal offset, used for auto-scrolling
	 */
	private float horizontalOffset;

	private DifficultyState currentDifficulty;

	private BufferedImage currentBg;
	private BufferedImage targetBg;
	private boolean inTransition;
	private float transitionTimer;
	private final float transitionDuration;

	/**
	 * Constructor of the BackgroundRendererImpl class.
	 *
	 * @param bgTileEasy      background image tile
	 * @param parallaxFactor  the factor for vertical parallax effect
	 * @param horizontalSpeed the speed for horizontal auto-scrolling effect.
	 *                        If set to 0, the background will not scroll horizontally.
	 */
	public BackgroundRendererImpl(BufferedImage bgTileEasy, BufferedImage bgTileMedium, BufferedImage bgTileHard,
								  float parallaxFactor, float horizontalSpeed, float transitionDuration) {
		this.bgTileEasy = bgTileEasy;
		this.bgTileMedium = bgTileMedium;
		this.bgTileHard = bgTileHard;
		this.parallaxFactor = parallaxFactor;
		this.horizontalSpeed = horizontalSpeed;
		this.horizontalOffset = BGHORIZONTALOFFSETINIT;
		this.currentDifficulty = DifficultyState.EASY;
		this.transitionDuration = transitionDuration;
		this.currentBg = bgTileEasy;
		this.targetBg = bgTileEasy;
		this.inTransition = false;
		this.transitionTimer = BGTRANSITIONTIMERINIT;
	}

	private BufferedImage selectBackground(DifficultyState diff) {
		return switch (diff) {
			case EASY, MEDIUM -> bgTileEasy;
			case HARD, VERY_HARD -> bgTileMedium;
			case HELL -> bgTileHard;
		};
	}

	private void updateTransition(DifficultyState currentDiff) {
		BufferedImage newBg = selectBackground(currentDiff);
		if (newBg != currentBg && !inTransition) {
			targetBg = newBg;
			inTransition = true;
			transitionTimer = 0;
		}
	}

	/**
	 * Private method that updates the horizontal offset automatically, independent
	 * of the camera offset. Should be called every frame with the deltaTime.
	 * If horizontalSpeed is 0, this method does nothing.
	 * If horizontalSpeed is not 0, the horizontal offset is updated based on the
	 * horizontalSpeed and the deltaTime, and the offset is kept within the
	 * range [0, tileWidth].
	 *
	 * @param deltaTime time passed since the last update (in seconds)
	 */
	private void updateHorizontalOffset(float deltaTime) {
		if (horizontalSpeed != BGHORIZONTALNULLSPEED) {
			horizontalOffset += horizontalSpeed * deltaTime;
			int tileW = currentBg.getWidth();
			if (horizontalOffset >= tileW) {
				horizontalOffset -= tileW;
			} else if (horizontalOffset < BGHORIZONTALOFFSETINIT) {
				horizontalOffset += tileW;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * The implementation uses camera offset (from model) and parallaxFactor to calculate the vertical
	 * offset and then uses the updateHorizontalOffset method to update the horizontal
	 * offset automatically. It draws the background by looping rows and columns
	 * of the bgTile image, and drawing each tile in the correct position, applying offsets.
	 */
	@Override
	public void drawBackground(Graphics2D g2, GameModel model, float deltaTime) {
		updateHorizontalOffset(deltaTime);
		updateTransition(model.getDifficultyManager().getCurrentDifficulty());

		int screenW = model.getScreenWidth();
		int screenH = model.getScreenHeight();
		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float verticalOffset = cameraOffset * parallaxFactor;

		int tileW = currentBg.getWidth();
		int tileH = currentBg.getHeight();

		int shiftY = (int) (verticalOffset) % tileH;
		if (shiftY < SCREENLEFTMARGIN) {
			shiftY += tileH;
		}
		int shiftX = (int) horizontalOffset;

		int verticalTiles = (screenH / tileH) + EXTRATILES;
		int horizontalTiles = (screenW / tileW) + EXTRATILES;

		if (inTransition) {
			transitionTimer += deltaTime;
			float alpha = Math.min(transitionTimer / transitionDuration, 1.0f);

			Composite originalComposite = g2.getComposite();

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha));
			for (int i = 0; i < verticalTiles; i++) {
				int drawY = -shiftY + i * tileH;
				for (int j = 0; j < horizontalTiles; j++) {
					int drawX = -shiftX + j * tileW;
					g2.drawImage(currentBg, drawX, drawY, null);
				}
			}

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			for (int i = 0; i < verticalTiles; i++) {
				int drawY = -shiftY + i * tileH;
				for (int j = 0; j < horizontalTiles; j++) {
					int drawX = -shiftX + j * tileW;
					g2.drawImage(targetBg, drawX, drawY, null);
				}
			}

			g2.setComposite(originalComposite);

			if (alpha >= 1.0f) {
				currentBg = targetBg;
				inTransition = false;
			}
		} else {
			for (int i = 0; i < verticalTiles; i++) {
				int drawY = -shiftY + i * tileH;
				for (int j = 0; j < horizontalTiles; j++) {
					int drawX = -shiftX + j * tileW;
					g2.drawImage(currentBg, drawX, drawY, null);
				}
			}
		}
	}
}
