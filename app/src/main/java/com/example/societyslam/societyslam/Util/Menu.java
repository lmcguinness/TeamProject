package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;

/**
 * Created by Aoife Brown
 * This abstract class is for a menu object
 */

public abstract class Menu {

    private Bitmap menuBackground;
    private int xPos;
    private int yPos;

    /**
     * This method constructs a menu object
     * @param menuBackground - the bitmap that is the menu background
     * @param xPos - the X coordinate where the menu will be displayed on screen
     * @param yPos - the Y coordinate where the menu will be displayed on screen
     */
    public Menu(Bitmap menuBackground, int xPos, int yPos) {
        this.menuBackground = menuBackground;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * This method sets up the buttons for the menu
     */

    public abstract void initialiseButtons();

    /**
     * This method renders the menu and its buttons
     * @param g - the painter
     */
    public abstract void render(Painter g);

    /**
     * This method returns the bitmap that is the menu's background
     * @return - menu background bitmap
     */
    public Bitmap getMenuBackground() {
        return menuBackground;
    }

    /**
     * This method returns the X coordinate of the menu
     * @return the X coordinate
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * This method returns the Y coordinate of the menu
     * @return the Y coordinate
     */
    public int getyPos() {
        return yPos;
    }
}