package com.example.societyslam.societyslam.Game;

import android.app.FragmentManager;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.societyslam.societyslam.R;


public class MainActivity extends AppCompatActivity {

    public static AssetManager assets;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    public static GameView myGame;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // hello world
        super.onCreate(savedInstanceState);
        assets = getAssets();
        myGame = new GameView(this,GAME_WIDTH,GAME_HEIGHT);
        setContentView(myGame);

    }
}
