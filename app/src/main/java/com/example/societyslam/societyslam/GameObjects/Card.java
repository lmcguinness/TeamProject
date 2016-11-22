package com.example.societyslam.societyslam.GameObjects;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public abstract class Card {
    private String name;
    private float x, y, height, width;


    public Card(String name, float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public abstract void draw();
    public abstract void discard();
}

