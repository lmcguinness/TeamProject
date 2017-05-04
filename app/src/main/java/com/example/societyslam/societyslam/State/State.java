package com.example.societyslam.societyslam.State;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Aoife Brown on 21/11/2016.
 *
 */

/**
 * The State acts as the games screen for society slam, it is used to display output and read user input
 * it extends AppCompatActivity
 */

public abstract class State extends AppCompatActivity {

    private Painter painter;
    private Context context;
    /**
     * This method is used to move between States, sets current state to a new one .
     * @param newState - state you want to move into
     */

    public void setCurrentState(State newState)
    {
        MainActivity.myGame.setCurrentState(newState);
    }
    public void setContext(Context context) { this.context = context; }
    /**
     * This method will be called when we transition into State.
     * It initializes any game objects that will be used throughout the coinTossState
     * e.g. buttons, players names, objects
     */
    public abstract void init();
    /**
     *This method will be called by the game loop on every frame
     * @param delta - time since last position
     */
    public abstract void update(float delta);
    /**
     * The render method draws to the screen
     * @param g- The painter
     */
    public abstract void render(Painter g);
    /**
     * This method checks where the screen has be touched
     * @param e - motion event(object used to report movement)
     * @param scaledX- the scaled x coordinate
     * @param scaledY- the scaled y coordinate
     * @return false
     */
    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
    public Context getContext() { return this.context;}

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }


}
