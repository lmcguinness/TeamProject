package com.example.societyslam.societyslam.State;

import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class LoadState extends State {

/**
 * This method will be called when we transition into the loadState
 * It initializes any game objects that will be used throughout the loadState
 * this method is loading the graphics and animations from Assets that are declared the load() method
 * */
    @Override
    public void init() {
        Assets.load();
    }

    /**
     *This method will be called by the game loop on every frame
     * this update method is setting the new state to MenuState
     * @param delta - time since last position
     *
     */
    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    /*
    * This render method draws to the screen
    * @param g - draws to the screen
     */
    @Override
    public void render(Painter g) {

    }

    /**
     * This method determines what happens when the user touches the screen
     * @param e  - motion event(object used to report movement)
     * @param scaledX- the scaled x coordinate
     * @param scaledY- the scaled y coordinate
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}