package it.unibo.javajump.model.collision;

import it.unibo.javajump.model.entities.*;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.BouncePlatformImpl;
import it.unibo.javajump.model.entities.platforms.BreakablePlatformImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import java.util.List;

import static it.unibo.javajump.utility.Constants.*;


public class CollisionManagerImpl implements CollisionManager {
	@Override
	public void checkCollisions(GameModel model) {
		CharacterImpl player = model.getPlayer();
		boolean foundPlatformCollision = false;
		List<GameObjectImpl> objects = model.getGameObjects();

		for (int i = 0; i < objects.size(); i++) {
			GameObjectImpl a = objects.get(i);
			for (int j = i + 1; j < objects.size(); j++) {

				GameObjectImpl b = objects.get(j);
				if (isColliding(a, b)) {
					a.onCollision(b);
					b.onCollision(a);

					if (a instanceof CharacterImpl && b instanceof CoinImpl) {
						handleCharacterCoinCollision((CharacterImpl) a, (CoinImpl) b, model);
					} else if (b instanceof CharacterImpl && a instanceof CoinImpl) {
						handleCharacterCoinCollision((CharacterImpl) b, (CoinImpl) a, model);
					}

					if (a instanceof CharacterImpl && b instanceof PlatformImpl) {
						if (handleCharacterPlatformCollision((CharacterImpl) a, (PlatformImpl) b, model)) {
							foundPlatformCollision = true;
						}
					} else if (b instanceof CharacterImpl && a instanceof PlatformImpl) {
						if (handleCharacterPlatformCollision((CharacterImpl) b, (PlatformImpl) a, model)) {
							foundPlatformCollision = true;
						}
					}
				}
			}
		}
		if (!foundPlatformCollision) {
			player.goInAir();
		}
	}

	private boolean isColliding(GameObjectImpl a, GameObjectImpl b) {
		return a.getX() < b.getX() + b.getWidth()
				&& a.getX() + a.getWidth() > b.getX()
				&& a.getY() < b.getY() + b.getHeight()
				&& a.getY() + a.getHeight() > b.getY();
	}


	private void handleCharacterCoinCollision(CharacterImpl character, CoinImpl coin, GameModel model) {
		if (coin.getState() == CoinState.IDLE) {
			coin.collect();
			model.addPointsToScore(COINSCOREVALUE);
		}
	}


	private boolean handleCharacterPlatformCollision(CharacterImpl player, PlatformImpl platform, GameModel model) {
		if (player.getVelocityY() > NULLDIRECTION) {
			float playerOldBottom = player.getOldY() + player.getHeight();
			float platformTop = platform.getY();

			if (playerOldBottom <= platformTop) {
				float jumpForce = player.getJumpForce();

				if (platform instanceof BouncePlatformImpl bp) {
					jumpForce *= bp.getBounceFactor();
				}

				player.setVelocityY(-jumpForce);
				player.setY(platformTop - player.getHeight());
				player.landOnPlatform();

				if (platform instanceof BreakablePlatformImpl breakablePlatform) {
					breakablePlatform.breakPlatform();
				}
			}
			return true;
		}
		return false;
	}
}
