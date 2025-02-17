import model.GameModel;
import model.factories.AbstractGameObjectFactory;
import model.factories.GameObjectFactory;

public class Main
{
	public static void main(String[] args)
	{
		int screenWidth = 800;
		int screenHeight = 600;

		AbstractGameObjectFactory factory = new GameObjectFactory();

		GameModel model = new GameModel(screenWidth, screenHeight, factory);

		model.startGame();

	}
}