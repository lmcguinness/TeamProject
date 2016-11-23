package com.example.societyslam.societyslam.GameObjects;



/**
 * Created by Aoife Brown on 15/11/2016.
 */

public abstract class Card {
    private String name;
    private float x, y, height, width;
    private int picture;



    public Card(String name, float x, float y, float height, float width, int picture) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.picture = picture;
    }


    public abstract void draw();
    public abstract void discard();

    public int getPicture(){
        return picture;
    }
}

