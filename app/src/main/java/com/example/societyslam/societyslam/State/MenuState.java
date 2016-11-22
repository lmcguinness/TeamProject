package com.example.societyslam.societyslam.State;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class MenuState extends State {

    private Rect playRect;
    private boolean playDown = false;

    @Override
    public void init() {
        playRect = new Rect(400,350,484,286);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome,0,0);

        if(playDown) {
            g.drawImage(Assets.startDown, playRect.left, playRect.top);
        } else {
            g.drawImage(Assets.start, playRect.left, playRect.top);
        }

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            //Button has been pressed
            playDown = true;
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            if(playDown && playRect.contains(scaledX,scaledY)){
                //Button has been released
                playDown = false;
                Log.d("MenuState","Play button has been pressed");
            } else {
                playDown = false;
            }
        }
        return true;
    }
}
