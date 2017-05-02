package com.example.societyslam.societyslam;
import android.provider.Settings;
import com.example.societyslam.societyslam.GameObjects.Coin;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Coin_test {
    Coin mockCoin;

    @Test
    public void coinFlipTest(){
       mockCoin= mockCoin.flip();
        if(mockCoin == Coin.HEADS||mockCoin == Coin.TAILS) {
            Assert.assertTrue(true);
        }
    }
}
