package com.example.societyslam.societyslam.Util;

import android.view.MotionEvent;
import android.view.View;

import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.State.State;

/**
 * This is the Input Handler class
 */

public class InputHandler implements View.OnTouchListener{

    private State currentState;

    /**
     * This method changes the current state
     * @param currentState - the state to be set to
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    @Override

    /**
     * This method creates a touch event whenever the screen is touched
     */
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int)((event.getX()/ v.getWidth()) * MainActivity.GAME_WIDTH);
        int scaledY = (int)((event.getY()/v.getHeight()) * MainActivity.GAME_HEIGHT);
        return currentState.onTouch(event, scaledX, scaledY);
    }
}
