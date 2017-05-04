package com.example.societyslam.societyslam;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.State.CoinTossState;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Chloe McAteer
 */

@RunWith(MockitoJUnitRunner.class)
public class Card_test {

    SocietyCard mockCard = new SocietyCard("Mock name", 20, 20, Assets.cardBack, 60, "MockAttackName", 10, Type.water,Type.fighting,Type.earth);

    @Test
    public void setWidthTest(){
        int mockWidth = 5;
        mockCard.setWidth(mockWidth);
        Assert.assertEquals(mockWidth, mockCard.getWidth());
    }

    @Test
    public void setHeightTest(){
        int mockHeight = 9;
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
    public void evolveTest() {
        mockCard.evolve();
        Assert.assertEquals(Level.Level1, mockCard.getLevel());
    }
}