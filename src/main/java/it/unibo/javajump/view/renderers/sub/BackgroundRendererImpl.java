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
	private final BufferedImage bgTile;
	private final BufferedImage bgTileEasy;
	private final BufferedImage bgTileMedium;
	private final BufferedImage bgTileHard;
	private final BufferedImage bgTileVeryHard;
	private final BufferedImage bgTileHell;
	private boolean fadeSwitch;
	private DifficultyState currentDifficulty;
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

	/**
	 * Constructor of the BackgroundRendererImpl class.
	 *
	 * @param bgTile          background image tile
	 * @param parallaxFactor  the factor for vertical parallax effect
	 * @param horizontalSpeed the speed for horizontal auto-scrolling effect.
	 *                        If set to 0, the background will not scroll horizontally.
	 */
	public BackgroundRendererImpl(BufferedImage bgTile, float parallaxFactor, float horizontalSpeed) {
		this.bgTile = bgTile;
		this.parallaxFactor = parallaxFactor;
		this.horizontalSpeed = horizontalSpeed;
		this.horizontalOffset = ZERO;
		this.bgTileEasy = bgTile;
		this.bgTileMedium = bgTile;
		this.bgTileHard = bgTile;
		this.bgTileVeryHard = bgTile;
		this.bgTileHell = bgTile;
		this.fadeSwitch = false;
		this.currentDifficulty = DifficultyState.EASY;
	}

	/**
	 * {@inheritDoc}
	 * The implementation uses camera offset (from model) and parallaxFactor to calculate the vertical
	 * offset and then uses the updateBackground method to update the horizontal
	 * offset automatically. It draws the background by looping rows and columns
	 * of the bgTile image, and drawing each tile in the correct position, applying offsets.
	 */
	@Override
	public void drawBackground(Graphics2D g2, GameModel model, float deltaTime) {
		int screenW = model.getScreenWidth();
		int screenH = model.getScreenHeight();

		//Possible way to implement
        /*
        if(currentDifficulty != model.getDifficultyManager().getCurrentDifficulty()) {
            currentDifficulty = model.getDifficultyManager().getCurrentDifficulty();
            switch (currentDifficulty) {
                case EASY:
                    bgTile = bgTileEasy;
                    break;
                case MEDIUM:
                    bgTile = bgTileMedium;
                    break;
                case HARD:
                    bgTile = bgTileHard;
                    break;
                case VERY_HARD:
                    bgTile = bgTileVeryHard;
                    break;
                case HELL:
                    bgTile = bgTileHell;
                    break;
                default:
                    bgTile = bgTileEasy;
                    break;
            }
            fadeSwitch=true;

        }

        if (fadeSwitch) {
            //Do the thing
        }


         */
		float cameraOffset = model.getCameraManager().getCurrentOffset();
		float verticalOffset = cameraOffset * parallaxFactor;

		int tileW = bgTile.getWidth();
		int tileH = bgTile.getHeight();

		updateBackground(deltaTime);

		int shiftY = (int) (verticalOffset) % tileH;
		if (shiftY < ZERO) {
			shiftY += tileH;
		}

		int shiftX = (int) horizontalOffset;
		int verticalTiles = (screenH / tileH) + EXTRATILES;
		int horizontalTiles = (screenW / tileW) + EXTRATILES;

		for (int i = 0; i < verticalTiles; i++) {
			int drawY = -shiftY + i * tileH;
			for (int j = 0; j < horizontalTiles; j++) {
				int drawX = -shiftX + j * tileW;
				g2.drawImage(bgTile, drawX, drawY, null);
			}
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
	private void updateBackground(float deltaTime) {
		if (horizontalSpeed != ZERO) {
			horizontalOffset += horizontalSpeed * deltaTime;
			int tileW = bgTile.getWidth();
			if (horizontalOffset >= tileW) {
				horizontalOffset -= tileW;
			} else if (horizontalOffset < ZERO) {
				horizontalOffset += tileW;
			}
		}
	}
}
