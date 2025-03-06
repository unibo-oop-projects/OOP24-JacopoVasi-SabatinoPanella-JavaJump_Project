package model.factories;

import model.entities.character.Character;
import model.entities.collectibles.Coin;
import model.entities.platforms.BouncePlatform;
import model.entities.platforms.BreakablePlatform;
import model.entities.platforms.MovingPlatform;
import model.entities.platforms.Platform;

import java.util.Random;

public class GameObjectFactory extends AbstractGameObjectFactory
{
	@Override
	public Character createCharacter(float x, float y)
	{

		float width = 48;
		float height = 50;
		float jumpForce = 700;

		return new Character(x, y, width, height, jumpForce);
	}

	@Override
	public Platform createStandardPlatform(float x, float y)
	{

		float width = 100;
		float height = 10;

		return new Platform(x, y, width, height);
	}

	@Override
	public Platform createRandomPlatform(float x, float y)
	{
		float width = 80 + new Random().nextInt(41);
		float height = 10;

		return new Platform(x, y, width, height);
	}

	@Override
	public Platform createMovingPlatform(float x, float y, int screenWidth)
	{
		float width = 70 + new Random().nextInt(45);
		float height = 10;
		float range = 50 + new Random().nextFloat()* 100;
		float speed = 30f + new Random().nextInt(40);

		return new MovingPlatform(x, y, width, height, range, screenWidth, speed);
	}

	@Override
	public Platform createBreakablePlatform(float x, float y)
	{
		float width = 80 + new Random().nextInt(25);
		float height = 10;

		return new BreakablePlatform(x, y, width, height);
	}

	@Override
	public Platform createBouncePlatform(float x, float y, float bounceFactor)
	{
		float width = 80 + new Random().nextInt(25);
		float height = 10;

		return new BouncePlatform(x, y, width, height, bounceFactor);
	}

	@Override
	public Coin createCoin(float x, float y)
	{

		float width = 44;
		float height = 52;

		return new Coin(x, y, width, height);
	}
}
