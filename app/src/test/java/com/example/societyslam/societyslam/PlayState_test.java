package com.example.societyslam.societyslam;
import com.example.societyslam.societyslam.State.PlayState;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ChloeMullan on 19/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayState_test {

    @Test
    public void setPlayer1Winner_SetPlayer1WinnerToTrue() {
        PlayState playState = new PlayState();
        playState.setPlayer1Winner(true);
        Assert.assertEquals(true, PlayState.player1Winner);
    }

    @Test
    public void setPlayer1Winner_SetPlayer1WinnerToFalse() {
        PlayState playState = new PlayState();
        playState.setPlayer1Winner(false);
        Assert.assertEquals(false, PlayState.player1Winner);

    }

    @Test
    public void setPlayer2Winner_SetPlayer2WinnerToTrue() {
        PlayState playState = new PlayState();
        playState.setPlayer2Winner(true);
        Assert.assertEquals(true, PlayState.player2Winner);

    }

    @Test
    public void setPlayer2Winner_SetPlayer2WinnerToFalse() {
        PlayState playState = new PlayState();
        playState.setPlayer2Winner(false);
        Assert.assertEquals(false, PlayState.player2Winner);

    }

}
