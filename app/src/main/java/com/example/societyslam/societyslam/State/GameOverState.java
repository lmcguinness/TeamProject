package com.example.societyslam.societyslam.State;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * This class displays a game over state on screen once a player has won the game
 */

public class GameOverState extends State {

    private String player1Name = "";
    private String player2Name = "";
    private Button playAgainButton;
    private Button homeButton;
    private int player1Wins;
    private int player2Wins;
    private String endScore;
    private String winnersName = " ";

    /**
     * This is the constructor for the gameOverState
     * @param playerScore- the score the  winning player has received
     * @param winnerPlayer- the player with the highest score
     */
    public GameOverState(int playerScore, String winnerPlayer){
        endScore = playerScore + ""; //conver int to string
        winnersName = winnerPlayer;
        //check to see if the new score is higher than the saved highest score
        if(playerScore >= MainActivity.getHighScore()){
            MainActivity.setHighScore(playerScore);
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

        playAgainButton = new Button(316, 245, 484, 285, Assets.playAgainButton);
        homeButton = new Button(316, 305, 484, 350, Assets.homeButton);

        player1Wins = PlayState.getPlayer1Wins();
        player2Wins = PlayState.getPlayer2Wins();

        if(PlayState.isPlayer1Winner()) {
            winnersName = player1Name;
        } else if (PlayState.isPlayer2Winner()) {
            winnersName = player2Name;
        }
        endScore = (player1Wins + " - " + player2Wins);



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
        g.setFont(Typeface.DEFAULT_BOLD, 40);
           g.drawString(winnersName + " has won the game!", 210, 140);
           winnersName = player2Name;
       g.drawString(endScore, 350, 225);
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

        }
        if(e.getAction() == MotionEvent.ACTION_UP) {
            if(playAgainButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                playAgainButton.cancel();
                setCurrentState(new CoinTossState());
            } else if (homeButton.isPressed(scaledX, scaledY)) {
                Assets.playSound(Assets.buttonClickID);
                homeButton.cancel();
                setCurrentState(new MenuState());
            }
        }

        return true;
    }
}
