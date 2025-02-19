import model.GameModel;
import model.collision.CollisionManager;
import model.factories.AbstractGameObjectFactory;
import model.factories.GameObjectFactory;

public class Main
{
	public static void main(String[] args)
	{
		int screenWidth = 800;
		int screenHeight = 600;

		AbstractGameObjectFactory factory = new GameObjectFactory();

		CollisionManager collisionManager = new CollisionManager();

		GameModel model = new GameModel(screenWidth, screenHeight, factory, collisionManager);


		model.startGame();
	}
}