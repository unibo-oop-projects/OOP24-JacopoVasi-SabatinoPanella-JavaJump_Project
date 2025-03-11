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

import static it.unibo.javajump.utility.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

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
        int counter = 0;
        int maxcount = 200;
        List<GameObject> toRemove = new ArrayList<>();
        for (GameObject go : model.getGameObjects()) {
            if (go instanceof Platform c) {
                    toRemove.add(c);
                }
            }
        model.getGameObjects().removeAll(toRemove);
        Platform platform = model.getSpawnManager().getFactory().createStandardPlatform(model.getPlayer().getX(),model.getPlayer().getY()+100);
        model.getGameObjects().add(platform);
        while(!model.getPlayer().isOnPlatform() && counter<maxcount){
            model.update(0.1f);
            counter++;
        }
        assertTrue(platform.consumeTouched(), "Platform Should consume touched .");
    }

}
