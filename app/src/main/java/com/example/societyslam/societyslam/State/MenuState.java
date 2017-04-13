package com.example.societyslam.societyslam.State;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;


import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.PlayerDetailsActivity;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;
import com.example.societyslam.societyslam.State.PlayState;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class MenuState extends State {

    private Button startButton, howToPlayButton, SettingsButton, scoreButton, onePlayerButton, twoPlayerButton;
    private static boolean isTwoPlayer;
    private boolean isStartPressed;
    public static boolean getIsTwoPlayer(){
        return isTwoPlayer;
    }
    @Override
    public void init() {
        howToPlayButton = new Button(85, 340, 225, 400, Assets.howToPlay);
        startButton = new Button(240, 340, 380, 400, Assets.start);
        scoreButton = new Button(405, 340, 545, 400, Assets.highScoreButton);
        SettingsButton = new Button(560, 340, 680, 400, Assets.SettingsButton);
        onePlayerButton = new Button(156,225, 334, 265,Assets.onePlayerButton);
        twoPlayerButton = new Button(456,225,634,265,Assets.twoPlayerButton);

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
        scoreButton.render(g);
        if(isStartPressed) {
            super.getPainter().drawImage(Assets.welcome,0,0);
            onePlayerButton.render(g);
            twoPlayerButton.render(g);
            g.setFont(Typeface.DEFAULT_BOLD, 30);
            g.drawString("Choose which mode you'd like to play", 120, 140);
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            startButton.onTouchDown(scaledX, scaledY);
            howToPlayButton.onTouchDown(scaledX, scaledY);
            SettingsButton.onTouchDown(scaledX, scaledY);
            scoreButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP && !isStartPressed) {
            if (startButton.isPressed(scaledX, scaledY)) {
                isStartPressed = true;
                startButton.cancel();

            } else if (howToPlayButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                howToPlayButton.cancel();
                //Log.d("MENU STATE", "HOW TO PLAY BUTTON PRESSED");
                setCurrentState(new HowToPlayState());

            } else if (SettingsButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                SettingsButton.cancel();
                setCurrentState(new SettingsState());
            } else if (scoreButton.isPressed(scaledX,scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                scoreButton.cancel();
                setCurrentState(new ScoreState());
            }else {
                startButton.cancel();
                howToPlayButton.cancel();
                SettingsButton.cancel();
            }
        }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            onePlayerButton.onTouchDown(scaledX, scaledY);
            twoPlayerButton.onTouchDown(scaledX, scaledY);
        }
            if (e.getAction() == MotionEvent.ACTION_UP) {
                if (onePlayerButton.isPressed(scaledX, scaledY)) {
                    Assets.playSound(Assets.buttonClickID);
                    isTwoPlayer = false;
                    onePlayerButton.cancel();
                    setCurrentState(new CoinTossState());
                } else if (twoPlayerButton.isPressed(scaledX, scaledY)) {
                    isTwoPlayer = true;
                    System.out.println("ayoooo");
                    Assets.playSound(Assets.buttonClickID);
                    twoPlayerButton.cancel();
                    Intent i = new Intent(this.getContext(), PlayerDetailsActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.getContext().startActivity(i);
                }
            }
        return true;
    }


}
