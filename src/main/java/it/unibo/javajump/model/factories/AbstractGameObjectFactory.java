package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.collectibles.Coin;


public abstract class AbstractGameObjectFactory {

	public abstract Character createCharacter(float x, float y);

	public abstract Platform createStandardPlatform(float x, float y);

	public abstract Platform createRandomPlatform(float x, float y);

	public abstract Platform createMovingPlatform(float x, float y, int screenWidth);

	public abstract Platform createBreakablePlatform(float x, float y);

	public abstract Platform createBouncePlatform(float x, float y, float bounceFactor);

	public abstract Coin createCoin(float x, float y);


}