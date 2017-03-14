package com.example.societyslam.societyslam.State;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;


import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;
import com.example.societyslam.societyslam.State.PlayState;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class MenuState extends State {

    private Button startButton, howToPlayButton, SettingsButton;
    //private Rect playRect;
    //private boolean playDown = false;
    //public Button startButton;
    //public Button howToPlayButton;


    @Override
    public void init() {
        startButton = new Button(316, 339, 484, 366, Assets.start,
                Assets.startDown);
        howToPlayButton = new Button (316, 370, 484, 397, Assets.howToPlay,
                Assets.howToPlayDown);
        SettingsButton = new Button(316, 401, 484, 431, Assets.SettingsButton,
                Assets.SettingsButton_down);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome,0,0);

       startButton.render(g);
       howToPlayButton.render(g);
        SettingsButton.render(g);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            startButton.onTouchDown(scaledX, scaledY);
            howToPlayButton.onTouchDown(scaledX, scaledY);
            SettingsButton.onTouchDown(scaledX, scaledY);

        }
        if (e.getAction() == MotionEvent.ACTION_UP) {

            if (startButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                startButton.cancel();
                setCurrentState(new CoinTossState());

            } else if (howToPlayButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                howToPlayButton.cancel();
                //Log.d("MENU STATE", "HOW TO PLAY BUTTON PRESSED");
                setCurrentState(new HowToPlayState());

            } else {
                if (SettingsButton.isPressed(scaledX, scaledY)) {
                    Assets.playSound(Assets.buttonClickID);
                    SettingsButton.cancel();
                    setCurrentState(new SettingsState());

                } else {
                    startButton.cancel();
                    howToPlayButton.cancel();
                    SettingsButton.cancel();
                }
            }
        }
            return true;
        }


}
