package it.unibo.javajump.model;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class CollisionTest {


    private GameModel model;

    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT);
        model.startGame();
        model.setState(new InGameState());

    }

    @Test
    void testCoin() {
        final float x = (float) SCREEN_WIDTH / 2;
        final float y = (float) SCREEN_HEIGHT / 2;
        model.getPlayer().setY(y);
        model.getPlayer().setX(x);
        final Coin coin = model.getSpawnManager().getFactory().createCoin(x, y);
        model.getGameObjects().add(coin);
        model.update(0);

        assertEquals(CoinState.COLLECTING, coin.getState(), "Coin State should be COLLECTING.");
    }

    @Test
    void testPlatform() {
        int counter = 0;
        final int maxcount = 200;
        final List<GameObject> toRemove = new ArrayList<>();
        for (final GameObject go : model.getGameObjects()) {
            if (go instanceof Platform c) {
                toRemove.add(c);
            }
        }
        model.getGameObjects().removeAll(toRemove);
        final Platform platform = model.getSpawnManager().getFactory().createStandardPlatform(model.getPlayer().getX(), model.getPlayer().getY() + 100);
        model.getGameObjects().add(platform);
        while (!model.getPlayer().isOnPlatform() && counter < maxcount) {
            model.update(0.1f);
            counter++;
        }
        assertTrue(platform.consumeTouched(), "Platform Should consume touched .");
    }

}
