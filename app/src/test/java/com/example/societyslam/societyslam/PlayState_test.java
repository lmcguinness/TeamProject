package com.example.societyslam.societyslam;
import com.example.societyslam.societyslam.State.PlayState;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ChloeMullan, Aoife Brown, Leanne McGuinness, Chloe McAteer on 19/04/2017.
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

    @Test
    public void setChooseCard_setChooseCardToTrue()  {
        PlayState playstate = new PlayState();
        playstate.setChooseCard(true);
        Assert.assertEquals(true, playstate.isChooseCard());

    }

    @Test
    public void setChooseCard_setChooseCardToFalse()  {
        PlayState playstate = new PlayState();
        playstate.setChooseCard(false);
        Assert.assertEquals(false, playstate.isChooseCard());
    }

    @Test
    public void setEvolvePlayer1_setEvolvePlayer1ToTrue()  {
        PlayState playstate = new PlayState();
        playstate.setEvolvePlayer1(true);
        Assert.assertEquals(true, playstate.isEvolvePlayer1());
    }

    @Test
    public void setEvolvePlayer1_setEvolvePlayer1ToFalse()  {
        PlayState playstate = new PlayState();
        playstate.setEvolvePlayer1(false);
        Assert.assertEquals(false, playstate.isEvolvePlayer1());
    }

    @Test
    public void setEvolvePlayer2_setEvolvePlayer2ToTrue()  {
        PlayState playstate = new PlayState();
        playstate.setEvolvePlayer2(true);
        Assert.assertEquals(true, playstate.isEvolvePlayer2());
    }

    @Test
    public void setEvolvePlayer2_setEvolvePlayer2ToFalse()  {
        PlayState playstate = new PlayState();
        playstate.setEvolvePlayer2(false);
        Assert.assertEquals(false, playstate.isEvolvePlayer2());
    }

    @Test
    public void setCardRetreated_setCardRetreatedToTrue() {
        PlayState playstate = new PlayState();
        playstate.setCardRetreated(true);
        Assert.assertEquals(true, playstate.isCardRetreated());

    }
    @Test
    public void setCardRetreated_setCardRetreatedToFalse() {
        PlayState playstate = new PlayState();
        playstate.setCardRetreated(false);
        Assert.assertEquals(false, playstate.isCardRetreated());

    }

    @Test
    public void setRetreatError_setRetreatErrorToTrue() {
        PlayState playstate = new PlayState();
        playstate.setRetreatError(true);
        Assert.assertEquals(true, playstate.isRetreatError());

    }

    @Test
    public void setRetreatError_setRetreatErrorToFalse() {
        PlayState playstate = new PlayState();
        playstate.setRetreatError(false);
        Assert.assertEquals(false, playstate.isRetreatError());

    }

    @Test
    public void setRenderAnimation_setRenderAnimationToTrue()  {
        PlayState playstate = new PlayState();
        playstate.setRenderAnimation(true);
        Assert.assertEquals(true, playstate.isRenderAnimation());

    }

    @Test
    public void setRenderAnimation_setRenderAnimationToFalse()  {
        PlayState playstate = new PlayState();
        playstate.setRenderAnimation(false);
        Assert.assertEquals(false, playstate.isRenderAnimation());

    }
}
