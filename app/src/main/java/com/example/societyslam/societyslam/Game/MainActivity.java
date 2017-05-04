package com.example.societyslam.societyslam.Game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.societyslam.societyslam.State.HowToPlayState;
import com.example.societyslam.societyslam.Util.PauseMenu;
import com.example.societyslam.societyslam.ai.CPU;
import com.example.societyslam.societyslam.io.Settings;
import java.io.IOException;

/**
 * This is the starting point for our game, contains onCreate method which sets our game into motion
 */
public class MainActivity extends Activity {

    public static MediaPlayer mediaPlayer;
    public static Settings settings;
    public static String language;
    public static AssetManager assets;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 450;
    public static GameView myGame;

    private static SharedPreferences prefs;
    private static final String highScoreKey = "highScoreKey",highScorePlayerKey = "highScorePlayerKey";
    private static int highScore;
    private static String highScorePlayer;

    /**
     * method OnCreate is overridden from super class Activity
     * method is implemented to start the activity
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getPreferences(Activity.MODE_PRIVATE);
        highScore = retrieveHighScore();
        highScorePlayer = retrieveHighScorePlayer();
        assets = getAssets();
        Intent intent = getIntent();
        boolean isPlayerDetsSet = intent.getBooleanExtra("isPlayerDetsSet", false);
        myGame = new GameView(this, this.GAME_WIDTH, this.GAME_HEIGHT, isPlayerDetsSet);
        setContentView(myGame);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();

        try{
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("musicAtBeginning.wav");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (IOException e){
            System.out.println("Couldnt load music file " + e.getMessage());
            mediaPlayer = null;
        }

        //ADDED BY Leanne McGuinness 26/11/2016
        //this will return the window that activity is using
        Window window = getWindow();
        //Keeping the screen on during a game so it doesnt dim
        //We need to ask permission in android manifest
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        settings = new Settings(getApplicationContext());
    }

    /**
     * Method called when the players score is greater than the saved high score
     * @param highScore - the new highest score
     */
    public static void setHighScore(int highScore){
        MainActivity.highScore = highScore;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(highScoreKey, highScore);
        editor.commit();
    }

    /**
     * Method is called once when the application is started.
     * Its value is stored into memory as highScore for quicker access
     * @return highscoreKey- the saved highscore
     */
    private int retrieveHighScore(){
        return prefs.getInt(highScoreKey,0);
    }

    /**
     * Allows us to access the high score
     * @return highscore
     */
    public static int getHighScore(){
        return highScore;
    }

    /**
     * Method called when the player scores is greater than the saved high score
     * @param highScorePlayer - The name of player who scored highest
     */
    public static void setHighScorePlayer(String highScorePlayer){
        MainActivity.highScorePlayer = highScorePlayer;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(highScorePlayerKey, highScorePlayer);
        editor.commit();
    }

    /**
     * Method is called once the application is started
     * Its value is stored into the memory as highScorePlayer for quicker access
     * @return highScorePlayer - the name of the player with the highest score
     */
    private String retrieveHighScorePlayer(){
        return  prefs.getString(highScorePlayerKey, " ");
    }

    /**
     * Allows us to access the high score players name
     * @return
     */
    public static String getHighScorePlayer(){
        return highScorePlayer;
    }

    /**
     * Method is implemented from superclass
     * used to restart activity
     */
    @Override
    public void onRestart() {
        super.onRestart();
    }
    /**
     * Method is overridden from superclass
     * used to resume activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        int currentVol = MainActivity.settings.getVolume("musicValue");
        if(mediaPlayer != null){
            if (currentVol == 0) {
                mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
            }
            mediaPlayer.start();
        }
    }
    /**
     * Method is overridden from superclass
     * used to pause activity
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer != null){
            mediaPlayer.pause();
            if(isFinishing()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }
    /**
     * Method is overridden from superclass
     * used to stop activity
     * on stopping cards are cleared for starting a new game
     */
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println();
        Assets.playersCards.clear();
        Assets.deckOfCards.clear();
        Assets.energyCards.clear();
        Assets.player2Cards.clear();
        Assets.prizeCardDeck2.clear();
        Assets.prizeCardDeck1.clear();
        Assets.currentCardInPlay2 = null;
        Assets.currentCardInPlay = null;
        CPU.setIsTalking(false);
        PauseMenu.setLoadGame(false);
    }
}



