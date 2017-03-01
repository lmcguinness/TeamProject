package com.example.societyslam.societyslam.State;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Coin;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * Created by James on 08/02/2017.
 */

public class CoinTossState extends State {

    public Button flipCoin , continueButton;
    private boolean isFirstToss = false;
    private Coin coin;
    private boolean flipResult;

    @Override
    public void init() {
        flipCoin = new Button(316, 385, 484, 424, Assets.flipCoin, Assets.flipCoinDown);
        continueButton = new Button(316, 15, 484, 65, Assets.continueButton, Assets.continueDown);
        coin = new Coin();
    }

    @Override
    public void update(float delta) {

    }

    public boolean getFlipResult() {
        return flipResult;
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.coinTossBackground, 0, 0);
        super.getPainter().drawImage(Assets.heads, 155, 185, 525 , 195);
        if (isFirstToss == false) {
            flipCoin.render(g);
        } else {
            if(coin.result == 0){
                super.getPainter().drawImage(Assets.heads, 155, 185, 525 , 195);
                super.getPainter().drawImage(Assets.player1, 135, 85, 565 , 65);
            }else if(coin.result == 1){
                super.getPainter().drawImage(Assets.tails, 155, 185, 525 , 195);
                super.getPainter().drawImage(Assets.player2, 135, 85, 565 , 65);
            }
            continueButton.render(g);
        }
    }
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            flipCoin.onTouchDown(scaledX, scaledY);
        }
        if (flipCoin.isPressed(scaledX, scaledY)) {
            //Play coin flip sound effect
            Assets.playSound(Assets.coinID);
            flipCoin.cancel();
            isFirstToss= true;
            coin.flip();


        } else {
            flipCoin.cancel();

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
        return false;
    }
}
