package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class StudentBehaviourCard extends Card {

    private StudentBehaviourType type;
    private int cardPoints;
    private boolean positiveEffect;




    public StudentBehaviourCard(String name, float x, float y, float height, float width, Bitmap bitmap, StudentBehaviourType type, boolean positiveEffect) {
        super(name, x, y, height, width, bitmap);

        this.type = type;
        this.positiveEffect = positiveEffect;
    }

    public int getCardPoints() {
        return cardPoints;
    }

    public void setCardPoints(int cardPoints) {
        this.cardPoints = cardPoints;
    }

    public boolean hasPositiveEffect() {
        return positiveEffect;
    }

    public void setPositiveEffect(boolean positiveEffect) {
        this.positiveEffect = positiveEffect;
    }

    public StudentBehaviourType getType() {
        return type;
    }

    public void setType(StudentBehaviourType type) {
        this.type = type;
    }

    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
