package com.example.societyslam.societyslam.Game;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.AssetManager;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;

import com.example.societyslam.societyslam.R;

//Changed to activity by Leanne McGuinness 26/11/16
public class MainActivity extends Activity {

    public static AssetManager assets;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;

    public static GameView myGame;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        assets = getAssets();
        myGame = new GameView(this,this.GAME_WIDTH,this.GAME_HEIGHT);
        setContentView(myGame);

        //ADDED BY Leanne McGuinness 26/11/2016

        //this will return the window that activity is using
        Window window = getWindow();
        //Keeping the screen on during a game so it doesnt dim
        //We need to ask permission in android manifest
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



    }
}
