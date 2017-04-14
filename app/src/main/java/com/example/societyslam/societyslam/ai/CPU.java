package com.example.societyslam.societyslam.ai;

import android.graphics.Bitmap;

import com.example.societyslam.societyslam.GameObjects.Card;
import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.State.PlayState;
import com.example.societyslam.societyslam.Util.Painter;

import java.util.ArrayList;

/**
 * Created by James on 09/04/2017.
 */

public class CPU extends Player {
    public CPU(Deck myCards, SocietyCard activeCard, ArrayList<SocietyCard> bench, ArrayList<StudentBehaviourCard> prizeCards, boolean myTurn, int roundWins) {
        super(myCards, activeCard, bench, prizeCards, myTurn, roundWins);
    }
    @Override
    public int getRoundWins() {
        return super.getRoundWins();
    }

    @Override
    public void setRoundWins(int wins) {
        super.setRoundWins(wins);
    }

    @Override
    public Deck getMyCards() {
        return super.getMyCards();
    }
    @Override
    public void setMyCards(Deck myCards) {
        super.setMyCards(myCards);
    }
    @Override
    public SocietyCard getActiveCard() {
        return super.getActiveCard();
    }

    @Override
    public void setActiveCard(SocietyCard activeCard) {
        super.setActiveCard(activeCard);
    }

    @Override
    public boolean isWinner() {
        return super.isWinner();
    }

    @Override
    public void setWinner(boolean winner) {
        super.setWinner(winner);
    }

    @Override
    public boolean checkIfWinner(Player opponent) {
        return super.checkIfWinner(opponent);
    }

    @Override
    public ArrayList<SocietyCard> getBench() {
        return super.getBench();
    }

    @Override
    public void setBench(ArrayList<SocietyCard> bench) {
        super.setBench(bench);
    }

    @Override
    public ArrayList<StudentBehaviourCard> getPrizeCards() {
        return super.getPrizeCards();
    }

    @Override
    public void setPrizeCards(ArrayList<StudentBehaviourCard> prizeCards) {
        super.setPrizeCards(prizeCards);
    }

    @Override
    public boolean isMyTurn() {
        return super.isMyTurn();
    }

    @Override
    public void setMyTurn(boolean myTurn) {
        super.setMyTurn(myTurn);
    }

    @Override
    public void attack(Player opponent) {
        super.attack(opponent);
    }
    public void retreat(CPU cpu1){
        cpu1.getActiveCard().retreat(cpu1.getActiveCard().getEnergyCards(), cpu1);
    }
    public void chooseCard(CPU cpu1, int position){
        cpu1.setActiveCard(cpu1.getBench().remove(position));
        //    PlayState.setCurrentCardInPlay(cpu1.getActiveCard());

    }
    @Override
    public void useStudentBehaviourCard(StudentBehaviourCard card, Player opponent) {
        super.useStudentBehaviourCard(card, opponent);
    }
    public  void evolve(CPU cpu1, SocietyCard Card){

        cpu1.getActiveCard().evolve();
    }
    public void talk(Player opponent, CPU cpu1) {
        if (opponent.getAttackDamage() < 20) {
            System.out.println("Is that all you got?");
        } else if (opponent.getAttackDamage() >= 20 && opponent.getAttackDamage() <=40) {
            System.out.println("Good Move");
        }else{
            System.out.println("Well Done, great move");
        }
        if(cpu1.getActiveCard().getAttackStrength() > 20){
            System.out.println("Are you ready for this?");
        }
    }
    public void scanBench(CPU cpu1){
        SocietyCard[] inOrderOfAttackStrength = new SocietyCard[5];
        SocietyCard[] inOrderOfHpLevel = new SocietyCard[5];

        //initialise arrays
        for(int i =0; i < 5 ; i++) {
            inOrderOfAttackStrength[i] = cpu1.getBench().get(i);
            inOrderOfHpLevel[i] = cpu1.getBench().get(i);
        }
        //sort arrays
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfAttackStrength[i].getAttackStrength() > inOrderOfAttackStrength[j].getAttackStrength()){
                    temp = inOrderOfAttackStrength[i];
                    inOrderOfAttackStrength[j] = inOrderOfAttackStrength[i];
                    inOrderOfAttackStrength[i] = temp;
                }
            }
        }
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfHpLevel[i].getHp() > inOrderOfHpLevel[j].getHp()){
                    temp = inOrderOfHpLevel[i];
                    inOrderOfHpLevel[j] = inOrderOfHpLevel[i];
                    inOrderOfHpLevel[i] = temp;
                }
            }
        }
        int bestAttackStrength = 0;
        int bestHp = 0;
        for(int i =0; i < 5 ; i++) {
            if(cpu1.getBench().get(i).getAttackStrength()  > bestAttackStrength){
                bestAttackStrength = cpu1.getBench().get(i).getAttackStrength();
            }
        }
        for(int i =0; i < 5 ; i++) {
            if (cpu1.getBench().get(i).getHp() > bestHp) {
                bestHp = cpu1.getBench().get(i).getHp();
            }
        }
    }
    public void analayseOpponent(Player opponent){
        Type oppositionResistance = opponent.getActiveCard().getResistance();
        Type oppositionWeakness = opponent.getActiveCard().getWeakness();
        // int opponentsNumberOfWins = PlayState.getPlayer1Wins();
        Level opponentsLevel = opponent.getActiveCard().getLevel();
        int opponentsAttackStrength = opponent.getActiveCard().getAttackStrength();
        int opponentNumberOfWins = opponent.getRoundWins();
    }

    public void makeDecision(CPU cpu1, Player opponent){
        cpu1.talk(opponent, cpu1);

    }

}
