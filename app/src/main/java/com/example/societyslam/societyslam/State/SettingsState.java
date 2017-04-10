package com.example.societyslam.societyslam.State;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Leanne on 03/03/2017.
 */

public class SettingsState extends State {

    public Button backArrowButton;
    public Button minusButton;
    public Button plusButton;
    public Button englishButton;
    public Button polishButton;
    public int musicVol;
    private boolean minusImage = true;
    private boolean plusImage = true;

    @Override
    public void init() {
        backArrowButton = new Button(-8, -10, 120, 100, Assets.backArrowButton);
        minusButton = new Button(260, 120, 364, 219, Assets.minusButton);
        plusButton = new Button(430, 120, 534, 219, Assets.plusButton);
        englishButton = new Button(260, 220, 428, 264, Assets.english_Button);
        polishButton = new Button(260, 275, 428, 319, Assets.polish_Button);
        musicVol = MainActivity.settings.getVolume("musicValue");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.coinTossBackground, 0,0);
        backArrowButton.render(g);
        minusButton.render(g);
        plusButton.render(g);
        englishButton.render(g);
        polishButton.render(g);
        g.setFont(Typeface.DEFAULT_BOLD, 25);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(musicVol),385,178);
        g.drawString("Volume: ", 120,170);
        g.drawString("Language: ", 120,250);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            backArrowButton.onTouchDown(scaledX, scaledY);
            minusButton.onTouchDown(scaledX, scaledY);
            plusButton.onTouchDown(scaledX, scaledY);
            englishButton.onTouchDown(scaledX, scaledY);
            polishButton.onTouchDown(scaledX,scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (backArrowButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                backArrowButton.cancel();
                setCurrentState(new MenuState());
            } else if (minusButton.isPressed(scaledX, scaledY)) {
                if (minusImage && musicVol > 0) {
                    minusButton.cancel();
                    musicVol--;
                } else{
                    minusImage = false;
                }

            } else if (plusButton.isPressed(scaledX, scaledY)) {
                if (plusImage && musicVol < 10) {
                    plusButton.cancel();
                    musicVol++;

                } else {
                    plusImage = false;
                }

            } else if (englishButton.isPressed(scaledX, scaledY)) {
                englishButton.cancel();

            } else if(polishButton.isPressed(scaledX,scaledY)){
                polishButton.cancel();

            } else {
                backArrowButton.cancel();
                minusButton.cancel();
                plusButton.cancel();
                englishButton.cancel();
                polishButton.cancel();
            }
        }

    return true;

    }
}
