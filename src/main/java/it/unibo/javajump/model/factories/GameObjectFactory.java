package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.BouncePlatform;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.Platform;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.*;

public class GameObjectFactory extends AbstractGameObjectFactory {

	@Override
	public Character createCharacter(float x, float y) {

		float width = CHARACTERWIDTH;
		float height = CHARACTERHEIGHT;
		float jumpForce = CHARACTERJUMPFORCE;

		return new Character(x, y, width, height, jumpForce);
	}

	@Override
	public Platform createStandardPlatform(float x, float y) {

		float width = STANDARDPLATFORMWIDTH;
		float height = PLATFORMHEIGHT;

		return new Platform(x, y, width, height);
	}

	@Override
	public Platform createRandomPlatform(float x, float y) {
		float width = RANDOMPLATFORMWIDTH + new Random().nextInt(RANDOMPLATFORMRNGFACTOR);
		float height = PLATFORMHEIGHT;

		return new Platform(x, y, width, height);
	}

	@Override
	public Platform createMovingPlatform(float x, float y, int screenWidth) {
		float width = MOVINGPLATFORMWIDTH + new Random().nextInt(MOVINGPLATFORMWIDTHRNGFACTOR);
		float height = PLATFORMHEIGHT;
		float range = MOVINGPLATFORMRANGE + new Random().nextFloat() * MOVINGPLATFORMRANGERNGFACTOR;
		float speed = MOVINGPLATFORMSPEED + new Random().nextInt(MOVINGPLATFORMSPEEDRNGFACTOR);
		float deceleration = MOVINGPLATFORMSPEEDCHANGE;
		float acceleration = MOVINGPLATFORMSPEEDCHANGE;

		return new MovingPlatform(x, y, width, height, range, screenWidth, speed, acceleration, deceleration);
	}

	@Override
	public Platform createBreakablePlatform(float x, float y) {
		float width = BREAKABLEPLATFORMWIDTH + new Random().nextInt(BREAKABLEPLATFORMRNGFACTOR);
		float height = PLATFORMHEIGHT;

		return new BreakablePlatform(x, y, width, height);
	}

	@Override
	public Platform createBouncePlatform(float x, float y, float bounceFactor) {
		float width = BOUNCEPLATFORMWIDTH + new Random().nextInt(BOUNCEPLATFORMRNGFACTOR);
		float height = PLATFORMHEIGHT;

		return new BouncePlatform(x, y, width, height, bounceFactor);
	}

	@Override
	public Coin createCoin(float x, float y) {

		float width = COINWIDTH;
		float height = COINHEIGHT;

		return new Coin(x, y, width, height);
	}
}
