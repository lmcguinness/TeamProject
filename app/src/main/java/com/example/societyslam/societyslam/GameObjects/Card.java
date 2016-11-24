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
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.bitmap = bitmap;
    }


    public abstract void draw();
    public abstract void discard();

    public Bitmap getPicture(){
        return bitmap;
    }
}

