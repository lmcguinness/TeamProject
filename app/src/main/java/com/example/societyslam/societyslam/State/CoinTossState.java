package com.example.societyslam.societyslam.State;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Coin;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by James on 08/02/2017.
 */

public class CoinTossState extends State {
    private Button flipCoinButton , continueButton, chooseHeadsButton, chooseTailsButton;
    private Coin coin;
    private boolean isPlayer1Heads, decided =false, isFirstToss = false;
    private static boolean  isPlayer1Turn, isPlayer2Turn;
    private String player1Name = "";
    private String player2Name = "";

    @Override
    public void init() {
        flipCoinButton = new Button(316, 385, 484, 424, Assets.flipCoin);
        continueButton = new Button(316, 15, 484, 65, Assets.continueButton);
        chooseHeadsButton = new Button(116, 225, 284, 324 , Assets.coin4);
        chooseTailsButton = new Button(516, 225, 684, 324 , Assets.coin3);
        coin = new Coin();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(MenuState.getIsTwoPlayer()) {
            player1Name = sharedPreferences.getString("player1name", "");
            player2Name = sharedPreferences.getString("player2name", "");
        }else{
            player1Name = "Player 1";
            player2Name = "CPU1";
        }
    }
    @Override
    public void update(float delta) {
        //flip coin animation update
        Assets.coinAnim.update(delta);
    }

    public static boolean getIsPlayer1Turn() {
        return isPlayer1Turn;
    }
    public static boolean getIsPlayer2Turn() {
        return isPlayer2Turn;
    }

    @Override
    public void render(Painter g) {

        g.drawImage(Assets.coinTossBackground, 0, 0);
        g.setFont(Typeface.DEFAULT_BOLD, 25);
        g.setColor(Color.WHITE);
        g.drawString(player1Name + ", choose heads or tails", 230, 140);
        chooseHeadsButton.render(g);
        chooseTailsButton.render(g);

        if (decided) {
            g.drawImage(Assets.coinTossBackground, 0, 0);
            chooseTailsButton.cancel();
            chooseHeadsButton.cancel();
            // display who is assigned to what side of coin
            g.setFont(Typeface.DEFAULT_BOLD, 25);
            if (!isFirstToss) {
                g.drawString(player1Name + " is ", 213, 108);
                g.drawString("and  " + player2Name + " is ", 385, 108);

                if (isPlayer1Heads) {
                    g.drawString(" heads", 300, 108);
                    g.drawString(" tails", 515, 108);
                } else {
                    g.drawString(" tails", 300, 108);
                    g.drawString(" heads", 515, 108);
                }
            }

            if (!isFirstToss) {
                flipCoinButton.render(g);
                //Coin animation added to the screen
                Assets.coinAnim.render(g, 185, 165, 415, 195);
            } else {
                //Result of Coin Flip: heads
                if (coin.result == 0 && isPlayer1Heads) {
                    super.getPainter().drawImage(Assets.coin4,  185, 165, 415, 195);
                    //player 1 is heads, player1s go
                    g.drawString("Heads! " + player1Name +  " goes first!", 270, 130);
                    isPlayer1Turn = true;
                    isPlayer2Turn = false;
                }else if(coin.result == 0 && !isPlayer1Heads){
                    super.getPainter().drawImage(Assets.coin4,  185, 165, 415, 195);
                    //player 2 is heads, player 2s go
                    g.drawString("Heads! " + player2Name +  " goes first!", 270, 130);
                    isPlayer2Turn =true;
                    isPlayer1Turn = false;
                }
                //Result of Coin Flip: tails
               if (coin.result == 1 && !isPlayer1Heads) {
                    super.getPainter().drawImage(Assets.coin3,  185, 165, 415, 195);
                    // player 1 is tails, player 1s go
                   g.drawString("Tails! " + player1Name +  " goes first!", 270, 130);
                   isPlayer1Turn = true;
                   isPlayer2Turn = false;
                }else if(coin.result == 1 && isPlayer1Heads){
                   super.getPainter().drawImage(Assets.coin3,  185, 165, 415, 195);
                   // player 2 is tails, player 2s go
                   g.drawString("Tails! " + player2Name +  " goes first!", 270, 130);
                   isPlayer2Turn = true;
                   isPlayer1Turn = false;
               }
             continueButton.render(g);
            }
        }
    }
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            flipCoinButton.onTouchDown(scaledX, scaledY);
        }
        if (flipCoinButton.isPressed(scaledX, scaledY) && decided) {
            //Play coin flip sound effect
            Assets.playSound(Assets.coinID);
            flipCoinButton.cancel();
            isFirstToss= true;
            coin.flip();

        } else {
            flipCoinButton.cancel();
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            continueButton.onTouchDown(scaledX, scaledY);
        }
        if (continueButton.isPressed(scaledX, scaledY) && MenuState.getIsTwoPlayer()) {
            Assets.playSound(Assets.buttonClickID);
            continueButton.cancel();
            setCurrentState(new PlayState());
        } else if(continueButton.isPressed(scaledX, scaledY) && !MenuState.getIsTwoPlayer()) {
            Assets.playSound(Assets.buttonClickID);
            continueButton.cancel();
        }else{
            continueButton.cancel();
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            chooseHeadsButton.onTouchDown(scaledX, scaledY);
        }
        if (chooseHeadsButton.isPressed(scaledX, scaledY)) {
            Assets.playSound(Assets.coinID);
            chooseHeadsButton.cancel();
            isPlayer1Heads = true;
            System.out.println(player1Name + " " +
                    " is heads");
            decided =true;
        } else {
            chooseHeadsButton.cancel();
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            chooseTailsButton.onTouchDown(scaledX, scaledY);
        }
        if (chooseTailsButton.isPressed(scaledX, scaledY)) {
            Assets.playSound(Assets.coinID);
            chooseTailsButton.cancel();
            isPlayer1Heads = false;
            System.out.println("Player 1 is tails");
            decided = true;
        } else {
            chooseTailsButton.cancel();
        }
        return false;
    }






}
