package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.*;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class GameObjectFactoryImpl extends AbstractGameObjectFactoryImpl implements GameObjectFactory {

	@Override
	public CharacterImpl createCharacter(float x, float y) {

		return new CharacterImpl(x, y, CHARACTERWIDTH, CHARACTERHEIGHT, CHARACTERJUMPFORCE);
	}

	@Override
	public PlatformImpl createStandardPlatform(float x, float y) {

		return new PlatformImpl(x, y, STANDARDPLATFORMWIDTH, PLATFORMHEIGHT);
	}

	@Override
	public PlatformImpl createRandomPlatform(float x, float y) {
		float width = RANDOMPLATFORMWIDTH + new Random().nextInt(RANDOMPLATFORMRNGFACTOR);

		return new PlatformImpl(x, y, width, PLATFORMHEIGHT);
	}

	@Override
	public PlatformImpl createMovingPlatform(float x, float y, int screenWidth) {
		float width = MOVINGPLATFORMWIDTH + new Random().nextInt(MOVINGPLATFORMWIDTHRNGFACTOR);
		float range = MOVINGPLATFORMRANGE + new Random().nextFloat() * MOVINGPLATFORMRANGERNGFACTOR;
		float speed = MOVINGPLATFORMSPEED + new Random().nextInt(MOVINGPLATFORMSPEEDRNGFACTOR);

		return new MovingPlatformImpl(x, y, width, PLATFORMHEIGHT, range, screenWidth, speed, MOVINGPLATFORMSPEEDCHANGE, MOVINGPLATFORMSPEEDCHANGE);
	}

	@Override
	public PlatformImpl createBreakablePlatform(float x, float y) {
		float width = BREAKABLEPLATFORMWIDTH + new Random().nextInt(BREAKABLEPLATFORMRNGFACTOR);

		return new BreakablePlatformImpl(x, y, width, PLATFORMHEIGHT);
	}

	@Override
	public PlatformImpl createBouncePlatform(float x, float y, float bounceFactor) {
		float width = BOUNCEPLATFORMWIDTH + new Random().nextInt(BOUNCEPLATFORMRNGFACTOR);

		return new BouncePlatformImpl(x, y, width, PLATFORMHEIGHT, bounceFactor);
	}

	@Override
	public CoinImpl createCoin(float x, float y) {


		return new CoinImpl(x, y, COINWIDTH, COINHEIGHT);
	}
}
