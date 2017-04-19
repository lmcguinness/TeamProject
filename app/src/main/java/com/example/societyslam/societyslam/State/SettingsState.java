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
 * This class displays a Settings state on screen
 * Once the user clicks the Settings button on the main menu
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

    /**
     * This method initialises the music volume, a button to return to the main menu
     * a button to decrease the volume
     * a button to increase the volume
     * a button to change the language to polish
     * a button to change the language back to english
     */


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

    /**
     * This render method draws to the screen
     * The method displays the current volume on screen
     * Displays the language options
     * and the five buttons
     * @param g - the painter
     */

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

    /**
     * This method determins what happens when the user touches the screen
     * @param e - the event that is created when the user touches the screen
     * @param scaledX - the scaledX co-ord of where the screen was touched
     * @param scaledY - the scaledY co-ord of where the screen was touched
     * @return false
     */

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
                    musicVol = MainActivity.settings.decreaseVolume("musicValue");
                    MainActivity.mediaPlayer.setVolume(musicVol  / 10.0f, musicVol  / 10.0f);
                }
            } else if (plusButton.isPressed(scaledX, scaledY)) {
                if (plusImage && musicVol < 10) {
                    plusButton.cancel();
                    musicVol = MainActivity.settings.increaseVolume("musicValue");
                    MainActivity.mediaPlayer.setVolume(musicVol / 10.0f, musicVol  / 10.0f);
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
