package com.example.societyslam.societyslam;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.State.CoinTossState;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Player_test {

    Player mockPlayer1 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck1, CoinTossState.getIsPlayer1Turn(),0,"mock name");

    @Test
    public void retreatTest(){
        mockPlayer1.retreat();
        Assert.assertEquals(null, mockPlayer1.getActiveCard());
    }

    @Test
    public void checkIfWinerTest(){
        Assert.assertEquals(false, mockPlayer1.checkIfWinner());
    }


}
