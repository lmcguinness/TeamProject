package com.example.societyslam.societyslam.State;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

import static com.example.societyslam.societyslam.Game.MainActivity.myGame;

/**
 * This class displays a game over state on screen once a player has won the game
 */

public class GameOverState extends State {

    private String player1Name = "";
    private String player2Name = "";
    private Button playAgainButton, homeButton, shareButton;
    private int player1Wins,player2Wins;
    private int buttonLeft = 316, buttonRight = 484, playAgainButtonTop = 245, playAgainButtonBottom = 285,homeButtonTop = 305,homeButtonBottom = 350;
    private int winnerScore;
    private String score;
    private String winnersName = " ";
    private float textSize = 40;
    private int nameX = 210,nameY = 140,scoreX = 350,scoreY = 225;


    /**
     * This is the constructor for the gameOverState
     * @param player1Wins- the number of rounds player 1 won
     * @param player2Wins - the number of rounds player 2 won
     * @param winnerPlayer- the player with the highest score
     */
    public GameOverState(int player1Wins, int player2Wins, String winnerPlayer){

        this.player1Wins = player1Wins;
        this.player2Wins = player2Wins;

        if(player1Wins > player2Wins) {
            winnerScore = player1Wins;
        } else if (player2Wins > player1Wins) {
            winnerScore = player2Wins;
        }
        score = winnerScore + ""; //conver int to string
        winnersName = winnerPlayer;
        //check to see if the new score is higher than the saved highest score
        if(winnerScore >= MainActivity.getHighScore()){
            MainActivity.setHighScore(winnerScore);
            MainActivity.setHighScorePlayer(winnersName);
        }
    }

    /**
     * This method initialises the two players names, the two players' scores, a button to start a new game
     * and a button to return to the main menu
     */
    @Override
    public void init() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        player1Name = sharedPreferences.getString("player1name", "");
        player2Name = sharedPreferences.getString("player2name", "");

        playAgainButton = new Button(buttonLeft, playAgainButtonTop, buttonRight, playAgainButtonBottom, Assets.playAgainButton);
        homeButton = new Button(buttonLeft, homeButtonTop, buttonRight, homeButtonBottom, Assets.homeButton);
        shareButton = new Button(316,365,484,415, Assets.shareButton);




        score = (player1Wins + " - " + player2Wins);



    }

    @Override
    public void update(float delta) {

    }

    /**
     * This method displays the name of the winning player on screen, along with how many rounds they won by
     * and the two buttons
     * @param g
     */
    @Override
    public void render(Painter g) {
        g.drawImage(Assets.coinTossBackground,0,0);
        playAgainButton.render(g);
        homeButton.render(g);
        shareButton.render(g);
        g.setFont(Typeface.DEFAULT_BOLD, textSize);
           g.drawString(winnersName + " has won the game!", nameX, nameY, Color.WHITE);

       g.drawString(score, scoreX, scoreY, Color.WHITE);
    }

    /**
     * This method determines what happens when the user touches the screen
     * @param e the event that is created when the user touches the screen
     * @param scaledX the X coordinate of where the screen was touched
     * @param scaledY the Y coordinate of where the screen was touched
     * @return false
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            playAgainButton.onTouchDown(scaledX, scaledY);
            homeButton.onTouchDown(scaledX,scaledY);
            shareButton.onTouchDown(scaledX,scaledY);
        }
        if(e.getAction() == MotionEvent.ACTION_UP) {
            if(playAgainButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                playAgainButton.cancel();
                myGame.restartGame();
                setCurrentState(new CoinTossState());
            } else if (homeButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                homeButton.cancel();
                myGame.quitGame();
            } else if(shareButton.isPressed(scaledX,scaledY)){
                Assets.playSound(Assets.buttonClickID);
                shareButton.cancel();
            }
        }

        return true;
    }
}
