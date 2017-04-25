package com.example.societyslam.societyslam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.GameObjects.Card;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.State.CoinTossState;
import com.example.societyslam.societyslam.State.PlayState;

import junit.framework.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.SharedPreferences;

@RunWith(MockitoJUnitRunner.class)
public class Card_test {

    SocietyCard mockCard = new SocietyCard("Mock name", 20, 20,34,22, Assets.cardBack, 60, "MockAttackName", 10, Type.water,Type.fighting,Type.earth);
    Player mockPlayer1 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck1, CoinTossState.getIsPlayer1Turn(),0);

    @Test
    public void setWidthTest(){
        float mockWidth = 5;
        mockCard.setWidth(mockWidth);
        Assert.assertEquals(mockWidth, mockCard.getWidth());
    }

    @Test
    public void setHeightTest(){
        float mockHeight = 9;
        mockCard.setHeight(mockHeight);
        Assert.assertEquals(mockHeight, mockCard.getHeight());
    }

    @Test
    public void setHpTest() {
        int mockHp = 75;
        mockCard.setHp(mockHp);
        Assert.assertEquals(mockHp, mockCard.getHp());
    }

    @Test
    public void setAttackStrengthTest(){
        int mockAttackStrength = 30;
        mockCard.setAttackStrength(mockAttackStrength);
        Assert.assertEquals(mockAttackStrength, mockCard.getAttackStrength());

    }

    @Test
    public void retreatTest(){
        mockCard.retreat(mockPlayer1);
        Assert.assertEquals(null, mockPlayer1.getActiveCard());
    }

}
