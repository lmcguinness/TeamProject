package com.example.societyslam.societyslam.State;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Coin;
import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by James on 08/02/2017.
 */

public class CoinTossState extends State {
    private Button flipCoinButton , continueButton, chooseHeadsButton, chooseTailsButton;
    private boolean isFirstToss = false;
    private Coin coin;
    private boolean isPlayer1Heads, decided =false;
    private static boolean  isPlayer1Turn, isPlayer2Turn;

    @Override
    public void init() {
        flipCoinButton = new Button(316, 385, 484, 424, Assets.flipCoin, Assets.flipCoinDown);
        continueButton = new Button(316, 15, 484, 65, Assets.continueButton, Assets.continueDown);
        chooseHeadsButton = new Button(116, 225, 284, 274 , Assets.headsText, Assets.headsText);
        chooseTailsButton = new Button(516, 225, 684, 274 , Assets.tailsText, Assets.tailsText);
        coin = new Coin();
    }

    @Override
    public void update(float delta) {

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
        super.getPainter().drawImage(Assets.choose, 135, 85, 565, 65);
        chooseHeadsButton.render(g);
        chooseTailsButton.render(g);

        if (decided) {
            g.drawImage(Assets.coinTossBackground, 0, 0);
            chooseTailsButton.cancel();
            chooseTailsButton.cancel();

            super.getPainter().drawImage(Assets.heads, 155, 185, 525, 195);
            if (isFirstToss == false) {
                flipCoinButton.render(g);
            } else {
                //Result of Coin Flip: heads
                if (coin.result == 0 && isPlayer1Heads) {
                    super.getPainter().drawImage(Assets.heads, 155, 185, 525, 195);
                    //player 1 is heads, player1s go
                    super.getPainter().drawImage(Assets.player1, 135, 85, 565, 65);
                    isPlayer1Turn = true;
                }else if(coin.result == 0 && isPlayer1Heads == false ){
                    super.getPainter().drawImage(Assets.heads, 155, 185, 525, 195);
                    //player 2 is heads, player 2s go
                    super.getPainter().drawImage(Assets.player2H, 135, 85, 565, 65);
                    isPlayer2Turn =true;
                }
                // tails
               if (coin.result == 1 && isPlayer1Heads == false) {
                    super.getPainter().drawImage(Assets.tails, 155, 185, 525, 195);
                    // player 1 is tails, player 1s go
                   super.getPainter().drawImage(Assets.player1T, 135, 85, 565, 65);
                   isPlayer1Turn = true;
                }else if(coin.result == 1 && isPlayer1Heads){
                   super.getPainter().drawImage(Assets.heads, 155, 185, 525, 195);
                   // player 2 is tails, player 2s go
                   super.getPainter().drawImage(Assets.player1, 135, 85, 565, 65);
                   isPlayer2Turn = true;
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
        if (flipCoinButton.isPressed(scaledX, scaledY)) {
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
        if (continueButton.isPressed(scaledX, scaledY)) {
            continueButton.cancel();
            setCurrentState(new PlayState());
        } else {
            continueButton.cancel();
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            chooseHeadsButton.onTouchDown(scaledX, scaledY);
        }
        if (chooseHeadsButton.isPressed(scaledX, scaledY)) {
            chooseHeadsButton.cancel();
            isPlayer1Heads = true;
            System.out.println("Player 1 is heads");
            decided =true;
        } else {
            chooseHeadsButton.cancel();
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            chooseTailsButton.onTouchDown(scaledX, scaledY);
        }
        if (chooseTailsButton.isPressed(scaledX, scaledY)) {
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
