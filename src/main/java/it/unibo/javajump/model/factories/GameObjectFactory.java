package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;

public interface GameObjectFactory {
	Character createCharacter(float x, float y);

	Platform createStandardPlatform(float x, float y);

	Platform createRandomPlatform(float x, float y);

	Platform createMovingPlatform(float x, float y, int screenWidth);

	Platform createBreakablePlatform(float x, float y);

	Platform createBouncePlatform(float x, float y, float bounceFactor);

	Coin createCoin(float x, float y);
}
