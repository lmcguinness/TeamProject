package com.example.societyslam.societyslam;

import android.provider.Settings;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourType;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.State.CoinTossState;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Player_test {

    Player mockPlayer1 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck1, CoinTossState.getIsPlayer1Turn(),0,"mock name");
    Player mockPlayer2 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck2, CoinTossState.getIsPlayer2Turn(),0,"mock name2");
    SocietyCard mockCard = new SocietyCard("Mock name", 20, 20,34,22, Assets.cardBack, 60, "MockAttackName", 10, Type.water,null,null);
    SocietyCard mockCard2 = new SocietyCard("Mock name2", 20, 20,34,22, Assets.cardBack, 60, "MockAttackName", 10, Type.water,null,null);
    StudentBehaviourCard mockSBC = new StudentBehaviourCard("MockSBC name ", 20, 20 , 34, 22, Assets.cardBack, StudentBehaviourType.item,false );

    @Test
    public void retreatTest(){
        mockPlayer1.retreat();
        Assert.assertEquals(null, mockPlayer1.getActiveCard());
    }

    @Test
    public void checkIfWinnerTest(){
        Assert.assertEquals(false, mockPlayer1.checkIfWinner());
    }

    @Test
    public void attackTest(){
        mockPlayer1.setActiveCard(mockCard);
        mockPlayer2.setActiveCard(mockCard2);
        mockPlayer1.attack(mockPlayer2);
        Assert.assertEquals(50, mockPlayer2.getActiveCard().getHp());
    }

    @Test
    public void checkPlayerFlippedPrizeCardTest(){
        boolean isFlipped = mockPlayer1.checkIfPlayerHasFlippedPrizeCards(mockPlayer1);
        Assert.assertEquals(false, isFlipped);

    }

}
