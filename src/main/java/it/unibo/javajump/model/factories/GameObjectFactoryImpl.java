package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.BouncePlatformImpl;
import it.unibo.javajump.model.entities.platforms.BreakablePlatformImpl;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class GameObjectFactoryImpl extends AbstractGameObjectFactoryImpl {

	@Override
	public CharacterImpl createCharacter(float x, float y) {

		float width = CHARACTERWIDTH;
		float height = CHARACTERHEIGHT;
		float jumpForce = CHARACTERJUMPFORCE;

		return new CharacterImpl(x, y, width, height, jumpForce);
	}

	@Override
	public PlatformImpl createStandardPlatform(float x, float y) {

		float width = STANDARDPLATFORMWIDTH;
		float height = PLATFORMHEIGHT;

		return new PlatformImpl(x, y, width, height);
	}

	@Override
	public PlatformImpl createRandomPlatform(float x, float y) {
		float width = RANDOMPLATFORMWIDTH + new Random().nextInt(RANDOMPLATFORMRNGFACTOR);
		float height = PLATFORMHEIGHT;

		return new PlatformImpl(x, y, width, height);
	}

	@Override
	public PlatformImpl createMovingPlatform(float x, float y, int screenWidth) {
		float width = MOVINGPLATFORMWIDTH + new Random().nextInt(MOVINGPLATFORMWIDTHRNGFACTOR);
		float height = PLATFORMHEIGHT;
		float range = MOVINGPLATFORMRANGE + new Random().nextFloat() * MOVINGPLATFORMRANGERNGFACTOR;
		float speed = MOVINGPLATFORMSPEED + new Random().nextInt(MOVINGPLATFORMSPEEDRNGFACTOR);
		float deceleration = MOVINGPLATFORMSPEEDCHANGE;
		float acceleration = MOVINGPLATFORMSPEEDCHANGE;

		return new MovingPlatformImpl(x, y, width, height, range, screenWidth, speed, acceleration, deceleration);
	}

	@Override
	public PlatformImpl createBreakablePlatform(float x, float y) {
		float width = BREAKABLEPLATFORMWIDTH + new Random().nextInt(BREAKABLEPLATFORMRNGFACTOR);
		float height = PLATFORMHEIGHT;

		return new BreakablePlatformImpl(x, y, width, height);
	}

	@Override
	public PlatformImpl createBouncePlatform(float x, float y, float bounceFactor) {
		float width = BOUNCEPLATFORMWIDTH + new Random().nextInt(BOUNCEPLATFORMRNGFACTOR);
		float height = PLATFORMHEIGHT;

		return new BouncePlatformImpl(x, y, width, height, bounceFactor);
	}

	@Override
	public CoinImpl createCoin(float x, float y) {

		float width = COINWIDTH;
		float height = COINHEIGHT;

		return new CoinImpl(x, y, width, height);
	}
}
