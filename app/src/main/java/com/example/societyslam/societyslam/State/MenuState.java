package com.example.societyslam.societyslam.State;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.PlayerDetailsActivity;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class MenuState extends State {

    /*
        * buttons that have been declared in the MenuState class
     */
    private Button startButton, howToPlayButton, SettingsButton, scoreButton, onePlayerButton,
                   twoPlayerButton, hardButton, easyButton, startButtonPolish,
                   howToPlayButtonPolish, settingsButtonPolish, scoreButtonPolish,  backArrowButton;

    /*
            * variables outlining different co-ordinates for each button on the screen
     */
    private int buttonTop = 360;
    private int buttonBottom = 420;
    private int howToPlayButtonLeft = 85;
    private int howToPlayButtonRight = 225;
    private int startButtonLeft = 240;
    private int startButtonRight = 380;
    private int scoreButtonLeft = 405;
    private int scoreButtonRight = 545;
    private int settingsButtonLeft = 560;
    private int settingButtonRight = 680;
    private int chooseModeButtonTop = 380;
    private int chooseModeButtonBottom = 440;

    /*
    variables outlining the different textsizes for the buttons on screen
     */
    private float textSize = 30;
    private float textSize2 = 20;
    private int textX = 120;
    private int textY = 375;

    private static boolean isTwoPlayer , hard, easy;
    private boolean isStartPressed = false, isModeChosen = false;
    public static boolean getIsTwoPlayer(){
        return isTwoPlayer;
    }
    public static boolean isHard(){
        return hard;
    }

    /**
     * This method will be called when we transition into the MenuState
     * It initializes any game objects that will be used throughout the MenuState
     * this method is loading the buttons that are to be displayed
     * */
    @Override
    public void init() {
        howToPlayButton = new Button(howToPlayButtonLeft, buttonTop, howToPlayButtonRight, buttonBottom, Assets.howToPlay);
        startButton = new Button(startButtonLeft, buttonTop, startButtonRight, buttonBottom, Assets.start);
        scoreButton = new Button(scoreButtonLeft, buttonTop, scoreButtonRight, buttonBottom, Assets.highScoreButton);
        SettingsButton = new Button(settingsButtonLeft, buttonTop, settingButtonRight, buttonBottom, Assets.SettingsButton);
        howToPlayButtonPolish = new Button(howToPlayButtonLeft,buttonTop,howToPlayButtonRight,buttonBottom, Assets.HowToPlayButton_Polish);
        startButtonPolish = new Button(startButtonLeft,buttonTop,startButtonRight,buttonBottom, Assets.startButton_Polish);
        scoreButtonPolish = new Button(scoreButtonLeft,buttonTop,scoreButtonRight,buttonBottom, Assets.highScoresButton_Polish);
        settingsButtonPolish = new Button(settingsButtonLeft,buttonTop,settingButtonRight,buttonBottom, Assets.SettingsButton_Polish);
        onePlayerButton = new Button(startButtonLeft,chooseModeButtonTop, startButtonRight, chooseModeButtonBottom,Assets.onePlayerButton);
        twoPlayerButton = new Button(scoreButtonLeft,chooseModeButtonTop,scoreButtonRight,chooseModeButtonBottom,Assets.twoPlayerButton);
        easyButton = new Button(startButtonLeft,chooseModeButtonTop, startButtonRight, chooseModeButtonBottom,Assets.easyButton);
        hardButton = new Button(scoreButtonLeft,chooseModeButtonTop,scoreButtonRight,chooseModeButtonBottom,Assets.hardButton);
        backArrowButton = new Button(-8, -10, 120, 100, Assets.backArrowButton);

    }
    /**
     *
     *This method will be called by the game loop on every frame
     * @param delta - time since last position
     *
     */
    @Override
    public void update(float delta) {
    }


    /*
    * This render method draws to the screen
    * this method is drawing objects, graphics, buttons and text to the screen
    * @param g - draws to the screen
     */
    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome,0,0);

        //depending on language, drawing buttons in english or buttons in polish
        if(SettingsState.getCurrentLanguage() == "English"){
            startButton.render(g);
            howToPlayButton.render(g);
            SettingsButton.render(g);
            scoreButton.render(g);
        } else if(SettingsState.getCurrentLanguage() == "Polish"){
            startButtonPolish.render(g);
            howToPlayButtonPolish.render(g);
            settingsButtonPolish.render(g);
            scoreButtonPolish.render(g);
        }

        /*
            * if isStartPressed is true then different objects will display for the user to
            * choose one player or two player
         */

        if(isStartPressed) {
            super.getPainter().drawImage(Assets.welcome,0,0);
            onePlayerButton.render(g);
            twoPlayerButton.render(g);
            g.setFont(Typeface.DEFAULT_BOLD, textSize);
            g.drawString("Choose which mode you would like to play", textX, textY, Color.WHITE);
            backArrowButton.render(g);
        }
        if(isModeChosen){
            super.getPainter().drawImage(Assets.welcome,0,0);
            g.setFont(Typeface.DEFAULT_BOLD, textSize2);
            g.drawString("Choose which difficulty level you would like to play against: ", textX, textY, Color.WHITE);
            easyButton.render(g);
            hardButton.render(g);
            backArrowButton.render(g);
        }
    }

    /**
     * This method checks where the screen has be touched
     * @param e - motion event(object used to report movement)
     * @param scaledX- the scaled x coordinate
     * @param scaledY- the scaled y coordinate
     * @return true when object is pressed
     */
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
                //setCurrentState(new HowToPlayState(this));
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
            backArrowButton.onTouchDown(scaledX, scaledY);
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
                }else if (backArrowButton.isPressed(scaledX, scaledY) && isStartPressed) {
                    isStartPressed = false;
                    Assets.playSound(Assets.buttonClickID);
                    backArrowButton.cancel();
                }
            }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            easyButton.onTouchDown(scaledX, scaledY);
            hardButton.onTouchDown(scaledX, scaledY);
            backArrowButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (easyButton.isPressed(scaledX, scaledY) && isModeChosen && !isTwoPlayer) {
                Assets.playSound(Assets.buttonClickID);
                easy = true;
                easyButton.cancel();
                setCurrentState(new OnePlayerState());
            } else if (hardButton.isPressed(scaledX, scaledY) && isModeChosen &&!isTwoPlayer ) {
                Assets.playSound(Assets.buttonClickID);
                hard = true;
                hardButton.cancel();
                setCurrentState(new OnePlayerState());
            }else if (backArrowButton.isPressed(scaledX, scaledY) && isModeChosen) {
                Assets.playSound(Assets.buttonClickID);
                backArrowButton.cancel();
                isStartPressed = true;
                isModeChosen = false;
                return  true;
            }
        }
        return true;
    }
}
