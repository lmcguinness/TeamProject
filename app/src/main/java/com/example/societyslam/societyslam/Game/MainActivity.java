package com.example.societyslam.societyslam.Game;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.societyslam.societyslam.io.Settings;

//Changed to activity by Leanne McGuinness 26/11/16
public class MainActivity extends Activity {

    //Added by leanne
    public static Settings settings;
    public static float musicVolume = 0;
    public static String language;
    //end

    public static AssetManager assets;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;

    public static GameView myGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        assets = getAssets();
        Intent intent = getIntent();
        boolean isPlayerDetsSet = intent.getBooleanExtra("isPlayerDetsSet", false);
        myGame = new GameView(this, this.GAME_WIDTH, this.GAME_HEIGHT, isPlayerDetsSet);
        setContentView(myGame);

        //ADDED BY Leanne McGuinness 26/11/2016

        //this will return the window that activity is using
        Window window = getWindow();
        //Keeping the screen on during a game so it doesnt dim
        //We need to ask permission in android manifest
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //ADDED BY LEANNE
       settings = new Settings(getApplicationContext());
        language = settings.getLanguage();

    }

}