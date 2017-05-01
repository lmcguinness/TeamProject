package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Painter;

import java.util.ArrayList;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class SocietyCard extends Card {


    private int hp;
    private String attackName;
    private int attackStrength;
    private Type type;
    private Type weakness;
    private Type resistance;
    private Level level;
    private int identifier;
    private int potentialAttackDamage;
    private int overallPosition;
    private boolean evolvedMax;
    private int textX = 270, textY = 60;
    private float textSize = 20;

   
/*
        @param hp = this is the card value
        @param attackName = this is the name of the attack
        @param attackStrength = this is the strength of attack
        @param type = this is the card type
        @param weakness = this is the card weakness
        @param reistance = this is the card resistance
        @param level = this is the level of the card
        @param evolvedMax = this is set to true if evolve limit of card is reached
 */
    public SocietyCard(String name, float x, float y, float height, float width, Bitmap bitmap,
                       int hp, String attackName, int attackStrength, Type type,
                       Type weakness, Type resistance) {
        super(name, x, y, height, width, bitmap);

        this.hp = hp;
        this.attackName = attackName;
        this.attackStrength = attackStrength;
        this.type = type;
        this.weakness = weakness;
        this.resistance = resistance;
        this.level = Level.Basic;
        this.evolvedMax = false;

    }

/*
        * This method will return the identider of the society card
        * @return identifer
 */
    public int getIdentifier(){
        return identifier;
    }

    /*
        * This method will allow the user to set the width of the card
        * @param identifier1

 */
    public void setIdentifier(int identifier1){
        this.identifier = identifier1;
    }

    /*
        * This method will return the overallPoisition of the society card
        * @return overallPosition
 */

    public int getOverallPosition(){
        return overallPosition;
    }

    /*
      * This method will allow the user to set the overallPosition of the card
      * @param overallPosition1

*/
    public void setOverallPosition(int overallPosition1){
        this.overallPosition = overallPosition1;
    }

    /*
        * This method will return the identider of the potential attack damage card
        * @return potentialAttackDamage
 */
    public int getPotentialAttackDamage(){
        return  potentialAttackDamage;
    }

    /*
      * This method will allow the user to set the potential attack damage of the card
      * @param poteintialAttackDamage1

*/
    public void setPotentialAttackDamage(int potentialAttackDamage1){
        this.potentialAttackDamage = potentialAttackDamage1;
    }

    /*
        * This method will return the hp of the society card
        * @return hp
 */
    public int getHp() {
        return hp;
    }

    /*
      * This method will allow the user to set the hp of the card
      * @param hp

*/
    public void setHp(int hp) {
        this.hp = hp;
    }

    /*
        * This method will return the attackName of the society card
        * @return attackName
 */
    public String getAttackName() {
        return attackName;
    }

    /*
      * This method will allow the user to set the attack name of the card
      * @param attackName

*/
    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    /*
            * This method will return the attack strength of the society card
            * @return attackStrength
     */
    public int getAttackStrength() {
        return attackStrength;
    }

    /*
      * This method will allow the user to set the attack strength of the card
      * @param attackStrength

*/
    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    /*
        * This method will return the type of the society card
        * @return type
 */
    public Type getType() {
        return type;
    }

    /*
      * This method will allow the user to set the type of the card
      * @param type

*/
    public void setType(Type type) {
        this.type = type;
    }

    /*
        * This method will return the weakness of the society card
        * @return weakness
 */
    public Type getWeakness() {
        return weakness;
    }

    /*
      * This method will allow the user to set the weakness of the card
      * @param weakness

*/
    public void setWeakness(Type weakness) {
        this.weakness = weakness;
    }

    /*
        * This method will return the resistence of the society card
        * @return resistance
 */
    public Type getResistance() {
        return resistance;
    }

    /*
      * This method will allow the user to set the resistence of the card
      * @param resistance

*/
    public void setResistance(Type resistance) {
        this.resistance = resistance;
    }

    /*
        * This method will return the level of the society card
        * @return level
 */
    public Level getLevel() {
        return level;
    }

    /*
      * This method will allow the user to set the level of the card
      * @param level

*/
    public void setLevel(Level level) {
        this.level = level;
    }


    /*
    the evolve method will check what level the current society card in play is and if
    the current card in play is at the maximum level possible, when the evolve button is
    clicked by the user, the evolvedMac variable will be set to true.
    else the evolvedMax variable will not be changed and will remain false
     */
    public void evolve() {
        if(this.getLevel().equals(Level.Basic)) {
            this.setLevel(Level.Level1);
            int currentAttackStrength = this.getAttackStrength();
            int newAttackStrength = (currentAttackStrength += 10);
            this.setAttackStrength(newAttackStrength);
            int currentHp = this.getHp();
            int newHp = currentHp -= 5;
            this.setHp(newHp);

        } else if (this.getLevel().equals(Level.Level1)) {
            this.setLevel(Level.Level2);
            int currentAttackStrength = this.getAttackStrength();
            int newAttackStrength = (currentAttackStrength += 20);
            this.setAttackStrength(newAttackStrength);
            int currentHp = this.getHp();
            int newHp = currentHp -= 10;
            this.setHp(newHp);
        } else if(this.getLevel().equals(Level.Level2)) {
            evolvedMax = true;
        }


    }

    /*
    the renderEvolve method displays a string to the screen "this card cannot be evolved anymore" if
    evolvedMax is true
    else it displays a confirmation message that the card in play has been evolved if the
    evolvedMax variable is set to false
    @param g
     */
    public void renderEvolve(Painter g) {
        g.setFont(Typeface.DEFAULT, textSize);
       if(this.evolvedMax) {
           g.drawString("This card cannot be evolved anymore", textX, textY, Color.WHITE);
       } else {
           g.drawString(this.getName() + " evolved to " + this.getLevel(), textX, textY, Color.WHITE);
       }
    }

    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
