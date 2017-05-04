package com.example.societyslam.societyslam.State;

import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Chloe Mullan
 * This class contains the players details
 */
public class PLayerDetailsState extends State {

    /**
     * This method will be called when we transition into the playerDetailsState.
     * It initializes any game objects that will be used throughout this state
     */
    @Override
    public void init() {

    }

    /**
     * This method will be called by the game loop on every frame
     * @param delta - time since last position
     */
    @Override
    public void update(float delta) {

    }

    /**
     * The render method draws to the screen
     * @param g- The painter
     */
    @Override
    public void render(Painter g) {
        g.drawImage(Assets.coinTossBackground,0,0);

    }

    /**
     * This method checks where the screen has be touched
     * @param e - motion event(object used to report movement)
     * @param scaledX - The scaled  x coordinate
     * @param scaledY- The scaled Y coordinate
     * @return- false
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
