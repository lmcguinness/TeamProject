package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;

import com.example.societyslam.societyslam.Game.Assets;

/**
 * This class creates an EnergyCard which is a type of Card within the game
 * Created by Aoife Brown on 15/11/2016.
 */


public class EnergyCard extends Card {

    private Type type;

    /**
     *
     * @param name This is the name of the energy cards
     * @param x This is the x co-ord of the energy card
     * @param y This is the y co-ord of the energy card
     * @param height This is the height of the energy card
     * @param width This is the width of the energy card
     * @param bitmap This is image of the energy card
     * @param type This is the specfic type of the energy card. eg water, earth, electric, fighting
     */
    public EnergyCard(String name, float x, float y, float height, float width, Bitmap bitmap, Type type) {
        super(name, x, y, height, width, bitmap);
        this.type = type;
    }

    /**
     * This method will return the specfic type of the energy card
     * @return type- Type is a an enum containing the 4 different types of energies
     */
    public Type getType() {
        return type;
    }

    /**
     * This method allows the programmer to set the type of energy
     * @param type - the type of energy the specific card is
     */
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

