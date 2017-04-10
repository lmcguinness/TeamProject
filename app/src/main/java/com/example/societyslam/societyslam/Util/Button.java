package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;
import android.graphics.Rect;
/**
 * This is a class for a button object
 */

public class Button {

    private Rect buttonRect;
    private boolean buttonDown = false;
    private Bitmap buttonImage;

    /**
     * This constructor creates a button object
     * @param left the left coordinate of the button
     * @param top the top coordinate of the button
     * @param right the right coordinate of the button
     * @param bottom the bottom coordinate of the button
     * @param buttonImage a bitmap which is the image for this button
     */
    public Button(int left, int top, int right, int bottom,
                    Bitmap buttonImage) {
        buttonRect = new Rect(left, top, right, bottom);
        this.buttonImage = buttonImage;

    }

    /**
     * This method draws the button to the screen
     * @param g
     */

    public void render(Painter g) {

        g.drawImage(buttonImage, buttonRect.left, buttonRect.top,
                buttonRect.width(), buttonRect.height());
    }


    /**
     * This method checks if where the screen has been touched is on the button.
     * If it is buttonDown is set to true
     * @param touchX X coordinate of where the screen was touched
     * @param touchY Y coordinate of where the screen was touched
     */
    public void onTouchDown(int touchX, int touchY) {
        if (buttonRect.contains(touchX, touchY)) {
            buttonDown = true;
        } else {
            buttonDown = false;
        }
    }


    /**
     * This cancels anything that happened whilst the button was pressed.
     * buttonDown is set to false
     */
    public void cancel() {
        buttonDown = false;
    }

    /**
     * @param touchX X coordinate of where the screen was touched
     * @param touchY Y coordinate of where the screen was touched
     * @return true if the button has been pressed, false if it has not
     */
    public boolean isPressed(int touchX, int touchY) {
        return buttonDown && buttonRect.contains(touchX, touchY);
    }

    }




