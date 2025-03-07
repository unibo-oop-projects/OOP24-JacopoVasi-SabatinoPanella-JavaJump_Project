package model.collision;

import model.entities.*;
import model.GameModel;
import model.entities.character.Character;
import model.entities.collectibles.Coin;
import model.entities.collectibles.CoinState;
import model.entities.platforms.BouncePlatform;
import model.entities.platforms.BreakablePlatform;
import model.entities.platforms.Platform;

import java.util.ArrayList;
import java.util.List;


public class CollisionManager
{
	
	public void checkCollisions(GameModel model)
	{
		Character player = model.getPlayer();
		boolean foundPlatformCollision = false;

		List<GameObject> objects = model.getGameObjects();


		for (int i = 0; i < objects.size(); i++) {

			GameObject a = objects.get(i);
			for (int j = i + 1; j < objects.size(); j++) {

				GameObject b = objects.get(j);
				if (isColliding(a, b)) {

					a.onCollision(b);
					b.onCollision(a);


					if (a instanceof Character && b instanceof Coin) {
						handleCharacterCoinCollision((Character) a, (Coin) b, model);
					} else if (b instanceof Character && a instanceof Coin) {
						handleCharacterCoinCollision((Character) b, (Coin) a, model);
					}


					if (a instanceof Character && b instanceof Platform) {
						if(handleCharacterPlatformCollision((Character) a, (Platform) b, model)){
							foundPlatformCollision = true;
						}
					} else if (b instanceof Character && a instanceof Platform) {
						if(handleCharacterPlatformCollision((Character) b, (Platform) a, model)){
							foundPlatformCollision = true;
						}
					}
				}
			}
		}

		if(!foundPlatformCollision){
			player.goInAir();
		}
	}

	
	private boolean isColliding(GameObject a, GameObject b) {
		return a.getX() < b.getX() + b.getWidth()
			&& a.getX() + a.getWidth() > b.getX()
			&& a.getY() < b.getY() + b.getHeight()
			&& a.getY() + a.getHeight() > b.getY();
	}

	private void handleCharacterCoinCollision(Character character, Coin coin, GameModel model) {
		if(coin.getState() == CoinState.IDLE) {
			coin.collect();
			model.addPointsToScore(50);
		}
	}

	private boolean handleCharacterPlatformCollision(Character player, Platform platform, GameModel model) {
		if (player.getVelocityY() > 0) {
			float playerOldBottom = player.getOldY() + player.getHeight();
			float platformTop = platform.getY();

			if (playerOldBottom <= platformTop) {
				float jumpForce = player.getJumpForce();

				if (platform instanceof BouncePlatform bp) {
					jumpForce *= bp.getBounceFactor();
				}

				player.setVelocityY(-jumpForce);
				player.setY(platformTop - player.getHeight());
				player.landOnPlatform();

				if (platform instanceof BreakablePlatform) {
					((BreakablePlatform)platform).breakPlatform();
				}
			}
			return true;
		}
		return false;
	}
}