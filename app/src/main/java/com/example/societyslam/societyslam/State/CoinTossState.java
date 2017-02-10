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

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome, 0, 0);
        if (isFirstToss == false) {
            flipCoin.render(g);
        } else {
            if(coin.result == 0){

                super.getPainter().drawImage(Assets.player1, 155, 135, 525 , 125);
            }else if(coin.result == 1){

                super.getPainter().drawImage(Assets.player2, 155, 135, 525 , 125);
            }
            continueButton.render(g);
        }
    }
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            //Button has been pressed
            flipCoin.onTouchDown(scaledX, scaledY);

        }
        if (flipCoin.isPressed(scaledX, scaledY)) {
            flipCoin.cancel();
            isFirstToss= true;
            coin.flip();


        } else {
            flipCoin.cancel();

        }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            //Button has been pressed
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
