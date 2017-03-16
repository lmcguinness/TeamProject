package com.example.societyslam.societyslam.State;

import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by ChloeMullan on 12/03/2017.
 */

public class PLayerDetailsState extends State {


    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.coinTossBackground,0,0);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
