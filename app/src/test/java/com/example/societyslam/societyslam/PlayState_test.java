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

    @Test
    public void setPause_SetPauseToTrue() {
        PlayState playState = new PlayState();
        playState.setPause(true);
        Assert.assertEquals(true, playState.isPause());
    }

    @Test
    public void setPause_SetPauseToFalse() {
        PlayState playState = new PlayState();
        playState.setPause(false);
        Assert.assertEquals(false, playState.isPause());
    }

    @Test
    public void setMenu_SetMenuToTrue() {
        PlayState playState = new PlayState();
        playState.setMenu(true);
        Assert.assertEquals(true, playState.isMenu());
    }

    @Test
    public void setMenu_SetMenuToFalse() {
        PlayState playState = new PlayState();
        playState.setMenu(false);
        Assert.assertEquals(false, playState.isMenu());
    }

    @Test
    public void setPrizeCardError_SetPrizeCardErrorToTrue() {
        PlayState playState = new PlayState();
        playState.setPrizeCardError(true);
        Assert.assertEquals(true, playState.isPrizeCardError());
    }

    @Test
    public void setPrizeCardError_SetPrizeCardErrorToFalse() {
        PlayState playState = new PlayState();
        playState.setPrizeCardError(false);
        Assert.assertEquals(false, playState.isPrizeCardError());
    }

    @Test
    public void setAttackPlayer1_SetAttackPlayer1ToTrue() {
        PlayState playState = new PlayState();
        playState.setAttackPlayer1(true);
        Assert.assertEquals(true, playState.isAttackPlayer1());
    }

    @Test
    public void setAttackPlayer1_SetAttackPlayer1ToFalse() {
        PlayState playState = new PlayState();
        playState.setAttackPlayer1(false);
        Assert.assertEquals(false, playState.isAttackPlayer1());
    }

    @Test
    public void setAttackPlayer2_SetAttackPlayer2ToTrue() {
        PlayState playState = new PlayState();
        playState.setAttackPlayer2(true);
        Assert.assertEquals(true, playState.isAttackPlayer2());
    }

    @Test
    public void setAttackPlayer2_SetAttackPlayer2ToFalse() {
        PlayState playState = new PlayState();
        playState.setAttackPlayer2(false);
        Assert.assertEquals(false, playState.isAttackPlayer2());
    }

}
