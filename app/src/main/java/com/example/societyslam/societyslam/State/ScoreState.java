package com.example.societyslam.societyslam.State;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * This class displays the name and score of the highest player ever
 */

public class ScoreState extends State {
    public Button backArrowButton;
    private String highScore;
    private String winnersName;

    /**
     * This method will be called when we transition into the  ScoreState.
     * It initializes any game objects that will be used throughout the scoreState
     */
    @Override
    public void init() {
        backArrowButton = new Button(-8, -10, 120, 100, Assets.backArrowButton);
        highScore = MainActivity.getHighScore() + " ";
        winnersName = MainActivity.getHighScorePlayer();

    }

    /**
     * *This method will be called by the game loop on every frame
     * @param delta- time since last position
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
        if(SettingsState.getCurrentLanguage() == "English"){
            g.drawImage(Assets.coinTossBackground, 0, 0);
            backArrowButton.render(g);
            g.setFont(Typeface.DEFAULT, 40);
            g.setColor(Color.WHITE);
            g.drawString("Highest game score ", 200, 35);
            g.setFont(Typeface.DEFAULT, 30);
            g.drawString("Player ", 200, 100);
            g.drawString(winnersName, 200, 140);
            g.drawString("Score ", 400, 100);
            g.drawString(highScore, 430, 140);
        } else if(SettingsState.getCurrentLanguage() == "Polish"){
            g.drawImage(Assets.coinTossBackground, 0, 0);
            backArrowButton.render(g);
            g.setFont(Typeface.DEFAULT, 40);
            g.setColor(Color.WHITE);
            g.drawString("Najwyższy wynik gry ", 200, 35);
            g.setFont(Typeface.DEFAULT, 30);
            g.drawString("Gracz ", 200, 100);
            g.drawString(winnersName, 200, 140);
            g.drawString("Wynik", 400, 100);
            g.drawString(highScore, 430, 140);
        }


    }


    /**
     * This method checks where the screen has be touched
     * @param e - motion event(object used to report movement)
     * @param scaledX- the scaled x coordinate
     * @param scaledY- the scaled y coordinate
     * @return false
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            backArrowButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (backArrowButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                backArrowButton.cancel();
                setCurrentState(new MenuState());
            } else {
                backArrowButton.cancel();
            }
        }
        return true;
    }
}

