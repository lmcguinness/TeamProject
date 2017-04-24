package com.example.societyslam.societyslam.Game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.societyslam.societyslam.State.HowToPlayState;
import com.example.societyslam.societyslam.io.Settings;
import java.io.IOException;

//Changed to activity by Leanne McGuinness 26/11/16
public class MainActivity extends Activity {

    //HowToPlayState startingEmailIntent;

    public static MediaPlayer mediaPlayer;
    public static Settings settings;
    public static String language;

    public static AssetManager assets;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    public static GameView myGame;
    private static SharedPreferences prefs;
    private static final String highScoreKey = "highScoreKey";
    private static int highScore;
    private static final String highScorePlayerKey = "highScorePlayerKey";
    private static String highScorePlayer;


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

        //startingEmailIntent = new HowToPlayState(MainActivity.this);
        //startingEmailIntent.sendHelpEmail();
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



}



