package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class StudentBehaviourCard extends Card {

    private StudentBehaviourType type;
    private int cardPoints;
    private boolean positiveEffect; //if the card is going to work to add points to the players socre (positive) or take away points from the oppenents score (negitive)
    private boolean flipped;

    /* @param type = the type of student behaviour card
      @param cardPoints = the number of points the card has
      @param positiveEffect = if the card has a positive effect or not
      @param flipped = if the card has been flipped or not
    */

    public StudentBehaviourCard(String name, float x, float y, float height, float width, Bitmap bitmap, StudentBehaviourType type, boolean positiveEffect) {
        super(name, x, y, height, width, bitmap);

        this.type = type;
        this.positiveEffect = positiveEffect;
    }


    /*
        * This method will return the card points of the card
        * @return cardPoints
 */
    public int getCardPoints() {
        return cardPoints;
    }

    /*
    * This method will allow the user to set the point level of the card
    * @param cardPoints

*/
    public void setCardPoints(int cardPoints) {
        this.cardPoints = cardPoints;
    }

    /*
        * This method will return the if the card has a positive effect or not of the card
        * @return positiveEffect
 */
    public boolean hasPositiveEffect() {
        return positiveEffect;
    }

    /*
    * This method will allow the user to set if the card has a positive effect or not
    * @param positiveEffect

*/
    public void setPositiveEffect(boolean positiveEffect) {
        this.positiveEffect = positiveEffect;
    }

    /*
        * This method will return the type of the card
        * @return type
 */
    public StudentBehaviourType getType() {
        return type;
    }

    /*
    * This method will allow the user to set the type of the card
    * @param type

*/
    public void setType(StudentBehaviourType type) {
        this.type = type;
    }

    /*
        * This method will return if the card is flipped
        * @return flipped
 */
    public boolean isFlipped() {
        return flipped;
    }

    /*
    * This method will allow the user to set if the card has been flipped or not
    * @param level

*/
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /*
    flipCard will set the flipped variable to true
     */
    public void flipCard() {
        flipped = true;
    }

    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
