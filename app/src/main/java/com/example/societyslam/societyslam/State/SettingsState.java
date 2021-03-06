package com.example.societyslam.societyslam.State;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

import java.util.ArrayList;

/**
 * This class displays a Settings state on screen
 * Once the user clicks the Settings button on the main menu
 * Created by Leanne on 03/03/2017.
 */

public class SettingsState extends State {

    private int buttonTop = 120, buttonTop2 = 260, buttonBottom = 219, buttonButtom2= 428;
    private int minusButtonLeft = 260, minusButtonRight = 364, plusButtonLeft = 430, plusButtonRight = 534, englishButtonRight = 264, englishButtonLeft = 220, polishButtonRight = 319, polishButtonLeft = 275;

    private float textSize = 25;
    private int textX = 120, textY = 170, textY2 = 250, textX2 = 385, textY3= 178;

    public Button backArrowButton, minusButton, plusButton, englishButton, polishButton;
    public int musicVol;
    private boolean minusImage = true;
    private boolean plusImage = true;
    public static String currentLanguage = "English";


    /**
     * This method initialises the music volume, a button to return to the main menu
     * a button to decrease the volume
     * a button to increase the volume
     * a button to change the language to polish
     * a button to change the language back to english
     */
    @Override
    public void init() {
        backArrowButton = new Button(-8, -10, buttonTop, 100, Assets.backArrowButton);
        minusButton = new Button(minusButtonLeft, buttonTop, minusButtonRight, buttonBottom, Assets.minusButton);
        plusButton = new Button(plusButtonLeft, buttonTop, plusButtonRight, buttonBottom, Assets.plusButton);
        englishButton = new Button(buttonTop2, englishButtonLeft, buttonButtom2, englishButtonRight, Assets.english_Button);
        polishButton = new Button(buttonTop2, polishButtonLeft, buttonButtom2, polishButtonRight, Assets.polish_Button);
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

        if(currentLanguage == "English"){
            g.drawImage(Assets.coinTossBackground, 0,0);
            backArrowButton.render(g);
            minusButton.render(g);
            plusButton.render(g);
            englishButton.render(g);
            polishButton.render(g);
            g.setFont(Typeface.DEFAULT_BOLD, textSize);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(musicVol),textX2,textY3, Color.WHITE);
            g.drawString("Volume: ", textX,textY, Color.WHITE);
            g.drawString("Language: ", textX,textY2, Color.WHITE);

        } else if (currentLanguage == "Polish") {
            g.drawImage(Assets.coinTossBackground, 0,0);
            backArrowButton.render(g);
            minusButton.render(g);
            plusButton.render(g);
            englishButton.render(g);
            polishButton.render(g);
            g.setFont(Typeface.DEFAULT_BOLD, textSize);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(musicVol),textX2,textY3, Color.WHITE);
            g.drawString("głośność: ", textX,textY, Color.WHITE);
            g.drawString("Język: ", textX,textY2, Color.WHITE);
        }


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
                currentLanguage = "English";

            } else if(polishButton.isPressed(scaledX,scaledY)){
                currentLanguage = "Polish";
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

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(String currentLanguage) {
        SettingsState.currentLanguage = currentLanguage;
    }
}