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

/**
 * The Coin Toss state acts as the on screen display for the intial coin toss it reads in input from the user and displays the outcome,
 * it first gives player 1 a choice between heads and tails and then displays the coin toss using an animation
 * it extends state
 */

public class CoinTossState extends State {
    private Button flipCoinButton , continueButton, chooseHeadsButton, chooseTailsButton;
    private Coin coin1;
    private boolean isPlayer1Heads, decided =false, isFirstToss = false;
    private static boolean  isPlayer1Turn, isPlayer2Turn;
    private String player1Name = "";
    private String player2Name = "";
    /**
     * This method will be called when we transition into the coinTossState.
     * It initializes any game objects that will be used throughout the coinTossState
     * e.g. buttons, players names, objects
     */
    @Override
    public void init() {
        flipCoinButton = new Button(316, 385, 484, 424, Assets.flipCoin);
        continueButton = new Button(316, 15, 484, 65, Assets.continueButton);
        chooseHeadsButton = new Button(116, 225, 284, 324 , Assets.coin4);
        chooseTailsButton = new Button(516, 225, 684, 324 , Assets.coin3);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(MenuState.getIsTwoPlayer()) {
            player1Name = sharedPreferences.getString("player1name", "");
            player2Name = sharedPreferences.getString("player2name", "");
        }else{
            player1Name = "Player 1";
            player2Name = "CPU1";
        }
/**
 *This method will be called by the game loop on every frame
 * @param delta - time since last position
 */
    }
    @Override
    public void update(float delta) {
        //flip coin animation update
        Assets.coinAnim.update(delta);
    }
    /**
     *This method will be called by in the playstate when creating instances of the Player object in order to determine whos turn it is
     */
    public static boolean getIsPlayer1Turn() {
        return isPlayer1Turn;
    }
    /**
     *This method will be called by in the playstate when creating instances of the Player object in order to determine whos turn it is
     */
    public static boolean getIsPlayer2Turn() {
        return isPlayer2Turn;
    }

    /**
     * The render method draws to the screen
     * @param g- The painter
     */
    @Override
    public void render(Painter g) {

        g.drawImage(Assets.coinTossBackground, 0, 0);
        g.setFont(Typeface.DEFAULT_BOLD, 25);
        g.setColor(Color.WHITE);
        g.drawString(player1Name + ", choose heads or tails", 230, 140, Color.WHITE);
        chooseHeadsButton.render(g);
        chooseTailsButton.render(g);
// when player 1 has decided either heads or tails i.e. if(decided) then display clearly which player is heads/tails
        if (decided) {
            g.drawImage(Assets.coinTossBackground, 0, 0);
            chooseTailsButton.cancel();
            chooseHeadsButton.cancel();
            // display who is assigned to what side of coin
            g.setFont(Typeface.DEFAULT_BOLD, 25);
            if (!isFirstToss) {
                g.drawString(player1Name + " is ", 213, 108, Color.WHITE);
                g.drawString("and  " + player2Name + " is ", 385, 108, Color.WHITE);

                if (isPlayer1Heads) {
                    g.drawString(" heads", 300, 108, Color.WHITE);
                    g.drawString(" tails", 515, 108, Color.WHITE);
                } else {
                    g.drawString(" tails", 300, 108, Color.WHITE);
                    g.drawString(" heads", 515, 108, Color.WHITE);
                }
            }

            if (!isFirstToss) {
                //render button to flip coin
                flipCoinButton.render(g);
                //Coin animation added to the screen
                Assets.coinAnim.render(g, 185, 165, 415, 195);
            } else {
                //Result of Coin Flip: heads
                if(coin1 == Coin.HEADS && isPlayer1Heads) {
                    // if (coin.result == 0 && isPlayer1Heads) {
                   g.drawImage(Assets.coin4, 185, 165, 415, 195);
                    //player 1 is heads, player1s go
                    g.drawString("Heads! " + player1Name + " goes first!", 270, 130, Color.WHITE);
                    //set respective variables to true/false
                    isPlayer1Turn = true;
                    System.out.println("ayo");
                    isPlayer2Turn = false;
                    //Result of Coin Flip: heads
                }else if(coin1 == Coin.HEADS && !isPlayer1Heads){
                   g.drawImage(Assets.coin4,  185, 165, 415, 195);
                    //player 2 is heads, player 2s go
                    g.drawString("Heads! " + player2Name +  " goes first!", 270, 130, Color.WHITE);
                    //set respective variables to true/false
                    isPlayer2Turn =true;
                    System.out.println("ayo");
                    isPlayer1Turn = false;
                }
                //Result of Coin Flip: tails
                if(coin1 == Coin.TAILS && !isPlayer1Heads) {
                    // if (coin.result == 1 && !isPlayer1Heads) {
                    g.drawImage(Assets.coin3, 185, 165, 415, 195);
                    // player 1 is tails, player 1s go
                    g.drawString("Tails! " + player1Name + " goes first!", 270, 130, Color.WHITE);
                    //set respective variables to true/false
                    isPlayer1Turn = true;
                    isPlayer2Turn = false;
                    System.out.println("ayo");
                    //Result of Coin Flip: tails
                }else if(coin1 == Coin.TAILS && isPlayer1Heads){
                   g.drawImage(Assets.coin3,  185, 165, 415, 195);
                   // player 2 is tails, player 2s go
                   g.drawString("Tails! " + player2Name +  " goes first!", 270, 130, Color.WHITE);
                   //set respective variables to true/false
                    System.out.println("ayo");
                   isPlayer2Turn = true;
                   isPlayer1Turn = false;
               }
                //render button to move to PlayState
             continueButton.render(g);
            }
        }
    }
    /**
     * This method checks where the screen has be touched
     * @param e - motion event(object used to report movement)
     * @param scaledX- the scaled x coordinate
     * @param scaledY- the scaled y coordinate
     * @return false
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            flipCoinButton.onTouchDown(scaledX, scaledY);
        }
        // when flip coin button is clicked call flip method from the Coin class
        if (flipCoinButton.isPressed(scaledX, scaledY) && decided) {
            //Play coin flip sound effect
            Assets.playSound(Assets.coinID);
            flipCoinButton.cancel();
            isFirstToss= true;
            coin1 = coin1.flip();
            System.out.println(coin1);
        } else {
            flipCoinButton.cancel();
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            continueButton.onTouchDown(scaledX, scaledY);
        }
        //if continue button is clicked move to new PlayState, determining which playstate by caling getters from MenuState
        if (continueButton.isPressed(scaledX, scaledY) && MenuState.getIsTwoPlayer()) {
            Assets.playSound(Assets.buttonClickID);
            continueButton.cancel();
            setCurrentState(new PlayState());
        } else if(continueButton.isPressed(scaledX, scaledY) && !MenuState.getIsTwoPlayer()) {
            Assets.playSound(Assets.buttonClickID);
            continueButton.cancel();
            //setCurrentState(new OnePlayerState());
        }else{
            continueButton.cancel();
        }
        // chooseHeadsButton and chooseTailsButton give player 1 a choice of which side of coin
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
