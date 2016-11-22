package com.example.societyslam.societyslam.State;

import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public abstract class State {

    public void setCurrentState(State newState)
    {
        MainActivity.myGame.setCurrentState(newState);
    }

    public abstract void init();
    public abstract void update(float delta);
    public abstract void render(Painter g);
    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);


}
