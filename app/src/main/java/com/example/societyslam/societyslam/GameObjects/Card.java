package com.example.societyslam.societyslam.GameObjects;


import android.graphics.Bitmap;

;

/**
 * Created by Aoife Brown on 15/11/2016.
 * This is a class for a card object
 */


public abstract class Card {
    private String name;
    private float x, y, height, width;
    private Bitmap bitmap;

    /**
     * This constructor creates a card object
     * @param name This is the name of the card
     * @param x This is the x co-ordinates for the card
     * @param y This is the y co-ordinates for the card
     * @param height This is the height of the card
     * @param width This is the width of the card
     * @param bitmap This is the image of the card itself
     */

    public Card(String name, float x, float y, float height, float width, Bitmap bitmap) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.bitmap = bitmap;
    }
    /**
     * This method returns the x co-ord of the card
     * @return x
     */

    /**
     * This method returns the x co-ord of the card
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * This method allows the programmer to set the x co-ord of the card
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * This method returns the y co-ord of the card
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * This method allows the programmer to set the y co-ord of the card
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * This method will return the width of the card
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * This method will allow the user to set the width of the card
     * @param width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * This method will return the height of the card
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * This method will allow the user to set the height of the card
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * This method returns the bitmap itself
     * @return image of card
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * This method allows the user to set the bitmap
     * @param bitmap - image of the card to set equal to the bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * This method allows the programmer to give the card a specific name
     * @param name - name will depend on type of card
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to draw the bitmap to the screen
     */
    public abstract void draw();

    /**
     *
     */
    public abstract void discard();

    /**
     * This method will return the bitmap
     * @return bitmap- the image of the card
     */
    public Bitmap getPicture(){
        return this.bitmap;
    }

    /**
     * This method will return the name of the card
     * @return name of card
     */
    public String getName() {
        return this.name;
    }
}



