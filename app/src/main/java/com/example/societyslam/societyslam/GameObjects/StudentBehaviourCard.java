package com.example.societyslam.societyslam.GameObjects;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class StudentBehaviourCard extends Card {

    private StudentBehaviourType type;
    private int cardPoints;




    public StudentBehaviourCard(String name, float x, float y, float height, float width, StudentBehaviourType type) {
        super(name, x, y, height, width);

        this.type = type;
    }

    public StudentBehaviourType getType() {
        return type;
    }

    public void setType(StudentBehaviourType type) {
        this.type = type;
    }

    public void useCard(int cardPoints) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
