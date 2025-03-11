package it.unibo.javajump.model;

import it.unibo.javajump.model.entities.collectibles.Coin;

import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.Platform;

import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionTest {


    private GameModel model;

    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREENWIDTH, SCREENHEIGHT);
        model.startGame();
        model.setState(new InGameState());

    }

    @Test
    void testCoin() {
        float x = (float) SCREENWIDTH / 2;
        float y = (float) SCREENHEIGHT / 2 ;
        model.getPlayer().setY(y);
        model.getPlayer().setX(x);
        Coin coin = model.getSpawnManager().getFactory().createCoin(x,y);
        model.getGameObjects().add(coin);
        model.update(0);

        assertEquals(CoinState.COLLECTING, coin.getState(), "Coin State should be COLLECTING.");
    }
    @Test
    void testPlatform() {
        float x = (float) SCREENWIDTH / 2;
        float y = (float) SCREENHEIGHT / 2 ;
        model.getPlayer().setY(y-CHARACTERHEIGHT);
        model.getPlayer().setX(x);
        Platform platform = model.getSpawnManager().getFactory().createStandardPlatform(x,y);
        model.getGameObjects().add(platform);
        model.update(0);
        model.update(0);
        assertTrue(platform.consumeTouched(), "Platform Should consume touched .");
    }

}
