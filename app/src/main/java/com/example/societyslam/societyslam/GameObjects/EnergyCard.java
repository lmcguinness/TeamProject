package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;

import com.example.societyslam.societyslam.Game.Assets;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class EnergyCard extends Card {

    private Type type;

    public EnergyCard(String name, float x, float y, float height, float width, Bitmap bitmap, Type type) {
        super(name, x, y, height, width, bitmap);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
