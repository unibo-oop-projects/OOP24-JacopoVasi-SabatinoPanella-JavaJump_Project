package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;


public abstract class AbstractGameObjectFactoryImpl {

	public abstract CharacterImpl createCharacter(float x, float y);

	public abstract PlatformImpl createStandardPlatform(float x, float y);

	public abstract PlatformImpl createRandomPlatform(float x, float y);

	public abstract PlatformImpl createMovingPlatform(float x, float y, int screenWidth);

	public abstract PlatformImpl createBreakablePlatform(float x, float y);

	public abstract PlatformImpl createBouncePlatform(float x, float y, float bounceFactor);

	public abstract CoinImpl createCoin(float x, float y);


}