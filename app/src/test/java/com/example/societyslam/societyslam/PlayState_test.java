package com.example.societyslam.societyslam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.State.PlayState;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by ChloeMullan on 19/04/2017.
 */

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
