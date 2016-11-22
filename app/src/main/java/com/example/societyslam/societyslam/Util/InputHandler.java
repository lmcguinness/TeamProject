package com.example.societyslam.societyslam.Util;

import android.view.MotionEvent;
import android.view.View;

import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.State.State;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class InputHandler implements View.OnTouchListener{

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int)((event.getX()/ v.getWidth()) * MainActivity.GAME_WIDTH);
        int scaledY = (int)((event.getY()/v.getHeight()) * MainActivity.GAME_HEIGHT);
        return currentState.onTouch(event, scaledX, scaledY);
    }
}
