package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;
import android.graphics.Rect;
/**
 * Created by aoife on 08/02/2017.
 */

public class Button {

    private Rect buttonRect;
    private boolean buttonDown = false;
    private Bitmap buttonImage, buttonDownImage;

    public Button(int left, int top, int right, int bottom,
                    Bitmap buttonImage, Bitmap buttonPressedImage) {
        buttonRect = new Rect(left, top, right, bottom);
        this.buttonImage = buttonImage;
        this.buttonDownImage = buttonPressedImage;
    }

    public void render(Painter g) {
        Bitmap currentButtonImage = buttonDown ? buttonDownImage : buttonImage;
        g.drawImage(currentButtonImage, buttonRect.left, buttonRect.top,
                buttonRect.width(), buttonRect.height());
    }

    public void onTouchDown(int touchX, int touchY) {
        if (buttonRect.contains(touchX, touchY)) {
            buttonDown = true;
        } else {
            buttonDown = false;
        }
    }

    public void cancel() {
        buttonDown = false;
    }

    public boolean isPressed(int touchX, int touchY) {
        return buttonDown && buttonRect.contains(touchX, touchY);
    }

    }




