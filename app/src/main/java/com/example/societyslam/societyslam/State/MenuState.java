package com.example.societyslam.societyslam.State;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.Util.Painter;
import com.example.societyslam.societyslam.State.PlayState;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class MenuState extends State {

    private Rect playRect;
    private boolean playDown = false;
    public Button startButton;

    @Override
    public void init() {
        playRect = new Rect(310,350,484,286);
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
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            //Button has been pressed
            playDown = true;
        }

        if (playDown) {
            //Button has been released
            playDown = false;
            setCurrentState(new PlayState());
        }
        return true;
    }
}
