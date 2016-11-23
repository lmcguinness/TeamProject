package com.example.societyslam.societyslam.GameObjects;

import com.example.societyslam.societyslam.Game.Assets;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class SocietyCard extends Card {


    private int hp;
    private String attackName;
    private int attackCost;
    private int attackStrength;
    private Type type;
    private Type weakness;
    private Type resistance;
    private int retreatCost;
    private Level level;

    public SocietyCard(String name, float x, float y, float height, float width, int picture, int hp, String attackName, int attackCost, int attackStrength, Type type, Type weakness, Type resistance, int retreatCost, Level level) {
        super(name, x, y, height, width, picture);
        this.hp = hp;
        this.attackName = attackName;
        this.attackCost = attackCost;
        this.attackStrength = attackStrength;
        this.type = type;
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreatCost = retreatCost;
        this.level = level;

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

    public int getAttackCost() {
        return attackCost;
    }

    public void setAttackCost(int attackCost) {
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

    public int getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(int retreatCost) {
        this.retreatCost = retreatCost;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void attack() {

    }

    public void retreat(int retreatCost) {

    }

    public void evolve() {

    }



    @Override
    public void draw() {

    }

    @Override
    public void discard() {

    }
}
