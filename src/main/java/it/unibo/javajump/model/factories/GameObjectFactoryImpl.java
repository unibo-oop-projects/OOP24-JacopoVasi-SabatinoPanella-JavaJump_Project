package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.BouncePlatform;
import it.unibo.javajump.model.entities.platforms.BouncePlatformImpl;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.platforms.BreakablePlatformImpl;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.BOUNCE_PLATFORM_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.BOUNCE_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.BREAKABLE_PLATFORM_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.BREAKABLE_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.CHARACTER_HEIGHT;
import static it.unibo.javajump.utility.Constants.CHARACTER_JUMP_FORCE;
import static it.unibo.javajump.utility.Constants.CHARACTER_WIDTH;
import static it.unibo.javajump.utility.Constants.COIN_HEIGHT;
import static it.unibo.javajump.utility.Constants.COIN_WIDTH;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_RANGE;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_RANGE_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_SPEED;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_SPEED_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_WIDTH_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.PLATFORM_HEIGHT;
import static it.unibo.javajump.utility.Constants.RANDOM_PLATFORM_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.RANDOM_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.STANDARD_PLATFORM_WIDTH;

public class GameObjectFactoryImpl extends AbstractGameObjectFactory {
    private final Random rand = new Random();

    @Override
    public Character createCharacter(float x, float y) {
        return new CharacterImpl(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT, CHARACTER_JUMP_FORCE);
    }

    @Override
    public Platform createStandardPlatform(float x, float y) {
        return new PlatformImpl(x, y, STANDARD_PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    @Override
    public Platform createRandomPlatform(float x, float y) {
        float width = RANDOM_PLATFORM_WIDTH + rand.nextInt(RANDOM_PLATFORM_RNG_FACTOR);
        return new PlatformImpl(x, y, width, PLATFORM_HEIGHT);
    }

    @Override
    public MovingPlatform createMovingPlatform(float x, float y, int screenWidth) {
        float width = MOVING_PLATFORM_WIDTH + rand.nextInt(MOVING_PLATFORM_WIDTH_RNG_FACTOR);
        float range = MOVING_PLATFORM_RANGE + rand.nextFloat() * MOVING_PLATFORM_RANGE_RNG_FACTOR;
        float speed = MOVING_PLATFORM_SPEED + rand.nextInt(MOVING_PLATFORM_SPEED_RNG_FACTOR);
        return new MovingPlatformImpl(x, y, width, PLATFORM_HEIGHT, range, screenWidth, speed);
    }

    @Override
    public BreakablePlatform createBreakablePlatform(float x, float y) {
        float width = BREAKABLE_PLATFORM_WIDTH + rand.nextInt(BREAKABLE_PLATFORM_RNG_FACTOR);
        return new BreakablePlatformImpl(x, y, width, PLATFORM_HEIGHT);
    }

    @Override
    public BouncePlatform createBouncePlatform(float x, float y, float bounceFactor) {
        float width = BOUNCE_PLATFORM_WIDTH + rand.nextInt(BOUNCE_PLATFORM_RNG_FACTOR);
        return new BouncePlatformImpl(x, y, width, PLATFORM_HEIGHT, bounceFactor);
    }

    @Override
    public Coin createCoin(float x, float y) {
        return new CoinImpl(x, y, COIN_WIDTH, COIN_HEIGHT);
    }
}
