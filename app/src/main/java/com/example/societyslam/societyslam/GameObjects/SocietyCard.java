package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.societyslam.societyslam.Game.Assets;

import java.util.ArrayList;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class SocietyCard extends Card {


    private int hp;
    private String attackName;
    private ArrayList<EnergyCard> attackCost;
    private int attackStrength;
    private Type type;
    private Type weakness;
    private Type resistance;
    private ArrayList<EnergyCard> retreatCost;
    private Level level;
    private ArrayList<EnergyCard> energyCards;

   

    public SocietyCard(String name, float x, float y, float height, float width, Bitmap bitmap,
                       int hp, String attackName, ArrayList<EnergyCard> attackCost, int attackStrength, Type type,
                       Type weakness, Type resistance, ArrayList<EnergyCard> retreatCost, Level level, ArrayList<EnergyCard> energyCards) {
        super(name, x, y, height, width, bitmap);

        this.hp = hp;
        this.attackName = attackName;
        this.attackCost = attackCost;
        this.attackStrength = attackStrength;
        this.type = type;
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreatCost = retreatCost;
        this.level = level;
        this.energyCards = energyCards;


    }

    public ArrayList<EnergyCard> getEnergyCards() {
        return energyCards;
    }

    public void setEnergyCards(ArrayList<EnergyCard> energyCards) {
        this.energyCards = energyCards;
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

    public ArrayList<EnergyCard> getAttackCost() {
        return attackCost;
    }

    public void setAttackCost(ArrayList<EnergyCard> attackCost) {
        this.attackCost = attackCost;
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

    public ArrayList<EnergyCard> getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(ArrayList<EnergyCard> retreatCost) {
        this.retreatCost = retreatCost;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    public void retreat(ArrayList<EnergyCard> retreatCost, Player player) {
        if(this.canRetreat()) {
            System.out.println(this.getName() + " has retreated");
            player.getBench().add(this);
            player.setActiveCard(null);
        } else {
            System.out.println("Not enough energy cards to retreat " + this.getName());
        }

    }

    public void evolve() {
        if(this.getLevel().equals(Level.Basic)) {
            this.setLevel(Level.Level1);
            int currentAttackStrength = this.getAttackStrength();
            int newAttackStrength = (currentAttackStrength += 10);
            this.setAttackStrength(newAttackStrength);
            System.out.println(this.getName() + " evolved to Level 1");
        } else if (this.getLevel().equals(Level.Level1)) {
            this.setLevel(Level.Level2);
            int currentAttackStrength = this.getAttackStrength();
            int newAttackStrength = (currentAttackStrength += 20);
            this.setAttackStrength(newAttackStrength);
            System.out.println(this.getName() + " evolved to level 2");
        } else if(this.getLevel().equals(Level.Level2)) {
            System.out.println("This card cannot be evolved anymore");
        }


    }

    public boolean canAttack() {
        if(this.getEnergyCards().size() >= this.getAttackCost().size()) {
           for(EnergyCard eachEnergyCard : attackCost) {
               if(this.getEnergyCards().contains(eachEnergyCard)) {
                   return true;
               }
           }
        }
        return false;
    }

    public boolean canRetreat() {
        if(this.getEnergyCards().size() >= this.getRetreatCost().size()) {
            for(EnergyCard eachEnergyCard : retreatCost) {
                if(this.getEnergyCards().contains(eachEnergyCard)) {
                    return true;
                }
            }
        }
        return false;

    }


    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
