package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;

/**
 * The interface Game object factory.
 */
public interface GameObjectFactory {
    /**
     * Create character character.
     *
     * @param x the x
     * @param y the y
     * @return the character
     */
    Character createCharacter(float x, float y);

    /**
     * Create standard platform platform.
     *
     * @param x the x
     * @param y the y
     * @return the platform
     */
    Platform createStandardPlatform(float x, float y);

    /**
     * Create random platform platform.
     *
     * @param x the x
     * @param y the y
     * @return the platform
     */
    Platform createRandomPlatform(float x, float y);

    /**
     * Create moving platform platform.
     *
     * @param x           the x
     * @param y           the y
     * @param screenWidth the screen width
     * @return the platform
     */
    Platform createMovingPlatform(float x, float y, int screenWidth);

    /**
     * Create breakable platform platform.
     *
     * @param x the x
     * @param y the y
     * @return the platform
     */
    Platform createBreakablePlatform(float x, float y);

    /**
     * Create bounce platform platform.
     *
     * @param x            the x
     * @param y            the y
     * @param bounceFactor the bounce factor
     * @return the platform
     */
    Platform createBouncePlatform(float x, float y, float bounceFactor);

    /**
     * Create coin coin.
     *
     * @param x the x
     * @param y the y
     * @return the coin
     */
    Coin createCoin(float x, float y);
}
