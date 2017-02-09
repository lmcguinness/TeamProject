package com.example.societyslam.societyslam.GameObjects;


import android.graphics.Bitmap;

;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public abstract class Card {
    private String name;
    private float x, y, height, width;
    private Bitmap bitmap;

    public Card(String name, float x, float y, float height, float width, Bitmap bitmap) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.bitmap = bitmap;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void draw();
    public abstract void discard();

    public Bitmap getPicture(){
        return this.bitmap;
    }
    public String getName() {
        return this.name;
    }
}

