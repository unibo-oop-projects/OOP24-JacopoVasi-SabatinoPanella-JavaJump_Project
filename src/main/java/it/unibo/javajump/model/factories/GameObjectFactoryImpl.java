package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.*;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class GameObjectFactoryImpl extends AbstractGameObjectFactory {
	private final Random rand = new Random();

	@Override
	public Character createCharacter(float x, float y) {
		return new CharacterImpl(x, y, CHARACTERWIDTH, CHARACTERHEIGHT, CHARACTERJUMPFORCE);
	}

	@Override
	public Platform createStandardPlatform(float x, float y) {
		return new PlatformImpl(x, y, STANDARDPLATFORMWIDTH, PLATFORMHEIGHT);
	}

	@Override
	public Platform createRandomPlatform(float x, float y) {
		float width = RANDOMPLATFORMWIDTH + rand.nextInt(RANDOMPLATFORMRNGFACTOR);
		return new PlatformImpl(x, y, width, PLATFORMHEIGHT);
	}

	@Override
	public MovingPlatform createMovingPlatform(float x, float y, int screenWidth) {
		float width = MOVINGPLATFORMWIDTH + rand.nextInt(MOVINGPLATFORMWIDTHRNGFACTOR);
		float range = MOVINGPLATFORMRANGE + rand.nextFloat() * MOVINGPLATFORMRANGERNGFACTOR;
		float speed = MOVINGPLATFORMSPEED + rand.nextInt(MOVINGPLATFORMSPEEDRNGFACTOR);
		return new MovingPlatformImpl(x, y, width, PLATFORMHEIGHT, range, screenWidth, speed);
	}

	@Override
	public BreakablePlatform createBreakablePlatform(float x, float y) {
		float width = BREAKABLEPLATFORMWIDTH + rand.nextInt(BREAKABLEPLATFORMRNGFACTOR);
		return new BreakablePlatformImpl(x, y, width, PLATFORMHEIGHT);
	}

	@Override
	public BouncePlatform createBouncePlatform(float x, float y, float bounceFactor) {
		float width = BOUNCEPLATFORMWIDTH + rand.nextInt(BOUNCEPLATFORMRNGFACTOR);
		return new BouncePlatformImpl(x, y, width, PLATFORMHEIGHT, bounceFactor);
	}

	@Override
	public Coin createCoin(float x, float y) {
		return new CoinImpl(x, y, COINWIDTH, COINHEIGHT);
	}
}
