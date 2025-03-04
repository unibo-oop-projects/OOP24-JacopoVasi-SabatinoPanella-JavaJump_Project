package model.level;

import model.GameModel;
import model.factories.AbstractGameObjectFactory;
import model.entities.Coin;
import model.entities.Platform;

import java.util.Random;

public class SpawnManager
{
	private final AbstractGameObjectFactory factory;
	private final Random random;


	private final int numberOfPlatforms;
	private final float minPlatformYSpacing;
	private final float maxPlatformYSpacing;
	private final float coinSpawnChance;

	private float lastSpawnCameraOffset;
	private float topPlatformY;

	public SpawnManager(AbstractGameObjectFactory factory)
	{
		this.factory = factory;
		this.random = new Random();


		this.numberOfPlatforms = 10;
		this.minPlatformYSpacing = 80;
		this.maxPlatformYSpacing = 140;
		this.coinSpawnChance = 0.3f;

		this.lastSpawnCameraOffset = 0;
		this.topPlatformY = 0;
	}

	
	public void generateInitialLevel(GameModel model) {

		Platform p = factory.createStandardPlatform(model.getPlayer().getX(), model.getPlayer().getY() + 60);
		model.getGameObjects().add(p);



		float startY = model.getScreenHeight() - 50;

		topPlatformY = startY;


		spawnBatch(model, startY);




		this.lastSpawnCameraOffset = 0;
	}

	
	public void generateOnTheFly(GameModel model) {

		float cameraOffset = model.getCameraManager().getCurrentOffset();


		if (cameraOffset < (lastSpawnCameraOffset - 300f)) {

			spawnBatch(model, topPlatformY);


			this.lastSpawnCameraOffset -= 300f;
		}
	}

	
	private void spawnBatch(GameModel model, float startY) {
		float currentY = startY;
		float platformWidth = 100;

		for (int i = 0; i < numberOfPlatforms; i++) {

			float gap = randomInRange(minPlatformYSpacing, maxPlatformYSpacing);

			currentY -= gap;


			float x = random.nextFloat() * (model.getScreenWidth() - platformWidth);


			Platform p = spawnAPlatform(x, currentY);
			model.getGameObjects().add(p);


			if (random.nextFloat() < coinSpawnChance) {
				float coinX = x + (platformWidth / 2f) - platformWidth*0.2f;
				float coinY = currentY - 50;
				Coin c = factory.createCoin(coinX, coinY);
				model.getGameObjects().add(c);
			}
		}


		if(currentY < topPlatformY){
			this.topPlatformY = currentY;
		}
	}

	private float randomInRange(float min, float max)
	{
		return min + random.nextFloat() * (max - min);
	}

	private Platform spawnAPlatform(float x, float y) {
		float chance = new Random().nextFloat();
		if (chance < 0.05f) {

			return factory.createBreakablePlatform(x, y);
		} else if (chance < 0.2f) {

			return factory.createMovingPlatform(x, y);
		} else {

			return factory.createRandomPlatform(x, y);
		}
	}

	public void reset() {
		this.lastSpawnCameraOffset = 0;
		this.topPlatformY = 0;
	}

	public AbstractGameObjectFactory getFactory()
	{
		return this.factory;
	}
}
