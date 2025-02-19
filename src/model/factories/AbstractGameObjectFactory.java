package model.factories;

import model.entities.Character;
import model.entities.Platform;
import model.entities.Coin;


public abstract class AbstractGameObjectFactory
{

	public abstract Character createCharacter(float x, float y);

	public abstract Platform createStandardPlatform(float x, float y);

	public abstract Platform createRandomPlatform(float x, float y);


	public abstract Coin createCoin(float x, float y);

}