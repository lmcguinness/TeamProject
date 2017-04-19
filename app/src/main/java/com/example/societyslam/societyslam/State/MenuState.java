package com.example.societyslam.societyslam.State;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.PlayerDetailsActivity;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class MenuState extends State {
    private Button startButton, howToPlayButton, SettingsButton, scoreButton, onePlayerButton, twoPlayerButton, hardButton, easyButton;
    private static boolean isTwoPlayer , hard, easy;
    private boolean isStartPressed = false, isModeChosen = false;
    public static boolean getIsTwoPlayer(){
        return isTwoPlayer;
    }
    public static boolean isHard(){
        return hard;
    }
    public static boolean isEasy(){
        return easy;
    }
    @Override
    public void init() {
        howToPlayButton = new Button(85, 360, 225, 420, Assets.howToPlay);
        startButton = new Button(240, 360, 380, 420, Assets.start);
        scoreButton = new Button(405, 360, 545, 420, Assets.highScoreButton);
        SettingsButton = new Button(560, 360, 680, 420, Assets.SettingsButton);
        onePlayerButton = new Button(240,380, 380, 440,Assets.onePlayerButton);
        twoPlayerButton = new Button(405,380,545,440,Assets.twoPlayerButton);
        easyButton = new Button(240,380, 380, 440,Assets.easyButton);
        hardButton = new Button(405,380,545,440,Assets.hardButton);
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
            g.drawString("Choose which mode you would like to play", 120, 375);
        }
        if(isModeChosen){
            super.getPainter().drawImage(Assets.welcome,0,0);
            g.setFont(Typeface.DEFAULT_BOLD, 20);
            g.drawString("Choose which difficulty level you would like to play against: ", 120, 375);
            easyButton.render(g);
            hardButton.render(g);
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
                return true;
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
            if (e.getAction() == MotionEvent.ACTION_UP && !isModeChosen) {
                if (onePlayerButton.isPressed(scaledX, scaledY) && isStartPressed) {
                    Assets.playSound(Assets.buttonClickID);
                    isTwoPlayer = false;
                    isModeChosen =true;
                    onePlayerButton.cancel();
                    return true;
                } else if (twoPlayerButton.isPressed(scaledX, scaledY) && isStartPressed) {
                    isTwoPlayer = true;
                    Assets.playSound(Assets.buttonClickID);
                    twoPlayerButton.cancel();
                    Intent i = new Intent(this.getContext(), PlayerDetailsActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.getContext().startActivity(i);
                }
            }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            easyButton.onTouchDown(scaledX, scaledY);
            hardButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (easyButton.isPressed(scaledX, scaledY) && isModeChosen && !isTwoPlayer) {
                Assets.playSound(Assets.buttonClickID);
                easy = true;
                easyButton.cancel();
               // setCurrentState(new CoinTossState());
            } else if (hardButton.isPressed(scaledX, scaledY) && isModeChosen &&!isTwoPlayer ) {
                Assets.playSound(Assets.buttonClickID);
                hard = true;
                hardButton.cancel();
               // setCurrentState(new CoinTossState());
            }
        }
        return true;
    }
}
