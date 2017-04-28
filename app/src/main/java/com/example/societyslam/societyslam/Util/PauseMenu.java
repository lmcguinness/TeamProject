package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.State.OnePlayerState;
import com.example.societyslam.societyslam.State.PlayState;
import com.example.societyslam.societyslam.State.SettingsState;

import static com.example.societyslam.societyslam.Game.MainActivity.mediaPlayer;
import static com.example.societyslam.societyslam.Game.MainActivity.myGame;

/**
 * This class is for a pause menu object
 */

public class PauseMenu extends Menu {

    private boolean instructionsScreen = false;
    private Button resumeButton;
    private Button restartButton;
    private Button instructionsButton;
    private Button quitButton;
    private Button backArrowButton;
    private int buttonLeft = 316,buttonRight = 484;
    private int resumeButtonTop = 125,  resumeButtonBottom = 165;
    private int restartButtonTop = 185, restartButtonBottom = 225;
    private int instructionsButtonTop = 245,instructionsButtonBottom = 285;

    private int quitButtonTop = 305, quitButtonBottom = 350;


    /**
     * This method creates a pause menu object and calls the initalise buttons method
     * @param menuBackground - the bitmap that is the menu background
     * @param xPos - the X coordinate of the menu
     * @param yPos - the Y coordinate of the menu
     */
    public PauseMenu(Bitmap menuBackground, int xPos, int yPos) {
        super(menuBackground, xPos, yPos);
        initialiseButtons();
    }

    /**
     * This method initialised the buttons needed for the pause menu
     */

    @Override
    public void initialiseButtons() {
        resumeButton = new Button(buttonLeft, resumeButtonTop, buttonRight, resumeButtonBottom, Assets.resume);
        restartButton = new Button(buttonLeft, restartButtonTop, buttonRight, restartButtonBottom, Assets.restart);
        instructionsButton = new Button(buttonLeft, instructionsButtonTop, buttonRight, instructionsButtonBottom, Assets.instructions);
        quitButton = new Button(buttonLeft, quitButtonTop, buttonRight, quitButtonBottom, Assets.quit);
        backArrowButton = new Button(-8,-10,120,100, Assets.backArrowButton);
    }

    /**
     * This method renders the pause menu background and the pause menu buttons
     * @param g - the painter
     */
    @Override
    public void render(Painter g) {
        g.drawImage(this.getMenuBackground(),this.getxPos(), this.getyPos());
        resumeButton.render(g);
        restartButton.render(g);
        instructionsButton.render(g);
        quitButton.render(g);
        if(SettingsState.getCurrentLanguage() == "English" && instructionsScreen){
            g.drawImage(Assets.howToPlayBackground, 0, 0);
            backArrowButton.render(g);
        } else if(SettingsState.getCurrentLanguage() == "Polish" && instructionsScreen){
            g.drawImage(Assets.howToPlayBackground_Polish,0,0);
            backArrowButton.render(g);
        }

    }


    /**
     * This method determines what happens when the buttons are pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void onTouch(int scaledX, int scaledY, PlayState playState) {


        resumeButton.onTouchDown(scaledX, scaledY);
        restartButton.onTouchDown(scaledX, scaledY);
        instructionsButton.onTouchDown(scaledX, scaledY);
        quitButton.onTouchDown(scaledX, scaledY);
        backArrowButton.onTouchDown(scaledX,scaledY);

        resumeButtonOnTouch(scaledX, scaledY, playState);
        restartButtonOnTouch(scaledX, scaledY, playState);
        instructionsButtonOnTouch(scaledX, scaledY, playState);
        quitButtonOnTouch(scaledX, scaledY, playState);
        backArrowButtonOnTouch(scaledX, scaledY, playState);

    }

    /**
     * This method will take the user back to the play state once they have read the instructions
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */

    public void backArrowButtonOnTouch(int scaledX, int scaledY, PlayState playState){
        if(backArrowButton.isPressed(scaledX, scaledY)){
            instructionsScreen = false;
        }
    }

    /**
     * The method resumes the game when the resume button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void resumeButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        if (resumeButton.isPressed(scaledX, scaledY)  && playState.isPause()) {
            //resume playing the game
            playState.setPause(false);
            //isPause=false;
            //resume the back ground music
            int currentVol = MainActivity.settings.getVolume("musicValue");
            if(mediaPlayer != null){
                if (currentVol == 0) {
                    mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
                }
                mediaPlayer.start();
            }
        }else{
            resumeButton.cancel();
        }


    }

    /**
     * This method determines what happens when the restart button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void restartButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        if (restartButton.isPressed(scaledX, scaledY)  && playState.isPause()) {
            playState.setPause(false);
            //start a new game
            myGame.restartGame();
            //super.onRestart();
        }else{
            restartButton.cancel();
        }
    }

    /**
     * This method determines what happens when the instructions button is pressed
     * @@param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void instructionsButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        if (instructionsButton.isPressed(scaledX, scaledY) && playState.isPause()) {
            instructionsScreen = true;

        }else{
            instructionsButton.cancel();
        }
    }

    /**
     * This method detemines what happens when the quit button is pressed
     * @@param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     */

    public void quitButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        if (quitButton.isPressed(scaledX, scaledY)  && playState.isPause()) {
            playState.setPause(false);
            //Takes player back the games start menu

            //resume the back ground music
            int currentVol = MainActivity.settings.getVolume("musicValue");
            if(mediaPlayer != null){
                if (currentVol == 0) {
                    mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
                }
                mediaPlayer.start();
            }
            myGame.quitGame();
        }else{
            quitButton.cancel();
        }
    }

    /**
     * This method determines what happens when the buttons are pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     */
    public void onTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate) {


        resumeButton.onTouchDown(scaledX, scaledY);
        restartButton.onTouchDown(scaledX, scaledY);
        instructionsButton.onTouchDown(scaledX, scaledY);
        quitButton.onTouchDown(scaledX, scaledY);
        backArrowButton.onTouchDown(scaledX,scaledY);

        resumeButtonOnTouch1(scaledX, scaledY, oneplayerstate);
        restartButtonOnTouch1(scaledX, scaledY, oneplayerstate);
        instructionsButtonOnTouch1(scaledX, scaledY, oneplayerstate);
        quitButtonOnTouch1(scaledX, scaledY, oneplayerstate);
        backArrowButtonOnTouch1(scaledX, scaledY, oneplayerstate);

    }

    /**
     * This method will take the user back to the play state once they have read the instructions
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     */

    public void backArrowButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate){
        if(backArrowButton.isPressed(scaledX, scaledY)){
            instructionsScreen = false;
        }
    }

    /**
     * The method resumes the game when the resume button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     */
    public void resumeButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate) {
        if (resumeButton.isPressed(scaledX, scaledY)  && oneplayerstate.isPause()) {
            //resume playing the game
            oneplayerstate.setPause(false);
            //isPause=false;
            //resume the back ground music
            int currentVol = MainActivity.settings.getVolume("musicValue");
            if(mediaPlayer != null){
                if (currentVol == 0) {
                    mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
                }
                mediaPlayer.start();
            }
        }else{
            resumeButton.cancel();
        }


    }

    /**
     * This method determines what happens when the restart button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     */
    public void restartButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate) {
        if (restartButton.isPressed(scaledX, scaledY)  && oneplayerstate.isPause()) {
            oneplayerstate.setPause(false);
            //start a new game
            myGame.restartGame();
            //super.onRestart();
        }else{
            restartButton.cancel();
        }
    }

    /**
     * This method determines what happens when the instructions button is pressed
     * @@param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     */
    public void instructionsButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate) {
        if (instructionsButton.isPressed(scaledX, scaledY) && oneplayerstate.isPause()) {
            instructionsScreen = true;

        }else{
            instructionsButton.cancel();
        }
    }

    /**
     * This method detemines what happens when the quit button is pressed
     * @@param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     */

    public void quitButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate) {
        if (quitButton.isPressed(scaledX, scaledY)  && oneplayerstate.isPause()) {
            oneplayerstate.setPause(false);
            //Takes player back the games start menu

            //resume the back ground music
            int currentVol = MainActivity.settings.getVolume("musicValue");
            if(mediaPlayer != null){
                if (currentVol == 0) {
                    mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
                }
                mediaPlayer.start();
            }
            myGame.quitGame();
        }else{
            quitButton.cancel();
        }
    }


}
