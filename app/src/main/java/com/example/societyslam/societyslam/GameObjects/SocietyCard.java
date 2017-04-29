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


    public int getIdentifier(){
        return identifier;
    }
    public void setIdentifier(int identifier1){
        this.identifier = identifier1;
    }

    public int getOverallPosition(){
        return overallPosition;
    }
    public void setOverallPosition(int overallPosition1){
        this.overallPosition = overallPosition1;
    }
    public int getPotentialAttackDamage(){
        return  potentialAttackDamage;
    }
    public void setPotentialAttackDamage(int potentialAttackDamage1){
        this.potentialAttackDamage = potentialAttackDamage1;
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }


    public int getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getWeakness() {
        return weakness;
    }

    public void setWeakness(Type weakness) {
        this.weakness = weakness;
    }

    public Type getResistance() {
        return resistance;
    }

    public void setResistance(Type resistance) {
        this.resistance = resistance;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


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
