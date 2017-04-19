package com.example.societyslam.societyslam.State;
import android.graphics.Paint;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;

/**
 * This class displays a How to play state on screen
 * Once the user clicks the how to play button on the menu screen
 * Created by Leanne on 27/02/2017.
 */

public class HowToPlayState extends State {
    public Button backArrowButton;

    private String[] instructions;
    protected Paint paint;


    /**
     * This method initialises a button to return to the main menu
     */
    @Override
    public void init() {
        backArrowButton = new Button(-8, -10, 120, 100, Assets.backArrowButton);
    }

    @Override
    public void update(float delta) {

    }

    /**
     * The render method draws to the screen
     * This method displays the button on screen
     * @param g - the painter
     */
    @Override
    public void render(Painter g) {
        g.drawImage(Assets.howToPlayBackground, 0,0);
        backArrowButton.render(g);


    }

    /**
     * This method checks where the screen has been touched
     * @param e - motion event(object used to report movement)
     * @param scaledX - The scaled x co-ord
     * @param scaledY - The scaled y co-ord
     * @return false
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction()== MotionEvent.ACTION_UP){
            backArrowButton.onTouchDown(scaledX, scaledY);

        } if(backArrowButton.isPressed(scaledX, scaledY)){
            Assets.playSound(Assets.buttonClickID);
            backArrowButton.cancel();
            setCurrentState(new MenuState());
        } else {
            backArrowButton.cancel();
        }

        return true;
    }


}
