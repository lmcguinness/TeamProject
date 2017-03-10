package com.example.societyslam.societyslam.State;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Leanne on 27/02/2017.
 */

public class HowToPlayState extends State {

    public Button backArrowButton;

    @Override
    public void init() {
        backArrowButton = new Button(-8, -10, 120, 100, Assets.backArrowButton,
                Assets.backArrowButton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.howToPlayBackground, 0,0);
        backArrowButton.render(g);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction()== MotionEvent.ACTION_UP){
            backArrowButton.onTouchDown(scaledX, scaledY);

        } if(backArrowButton.isPressed(scaledX, scaledY)){
            Assets.playSound(Assets.buttonClickID);
            backArrowButton.cancel();
            setCurrentState(new MenuState());

        } else {
            backArrowButton.cancel();
        }

        return true;
    }
}
