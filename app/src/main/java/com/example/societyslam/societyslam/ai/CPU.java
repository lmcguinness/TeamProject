package com.example.societyslam.societyslam.ai;


import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import java.util.ArrayList;

/**
 * Created by James on 09/04/2017.
 */

public class CPU extends Player {
    public static ArrayList<SocietyCard> inOrderOfHpLevel = new ArrayList<SocietyCard>();
    public static ArrayList<SocietyCard> inOrderOfAttackDamage = new ArrayList<SocietyCard>();
    public static ArrayList<SocietyCard> inOrderOfOverallPosition = new ArrayList<SocietyCard>();
    SocietyCard cardOfChoice;

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
    public void moveCard(CPU cpu1, SocietyCard card){
        int position=0 ;
        for(int i = 0; i< 5;i++){
            if(card == cpu1.getBench().get(i)){
                position = i;
            }
        }
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

    public void stall(){

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
    public void scanCards(CPU cpu1, Player opponent){

        //initialise arrays
        for(int i =1; i < 5 ; i++) {
            inOrderOfHpLevel.add(cpu1.getActiveCard());
            inOrderOfAttackDamage.add( cpu1.getActiveCard());
            inOrderOfOverallPosition.add( cpu1.getActiveCard());

            inOrderOfHpLevel.add(cpu1.getBench().get(i-1));
            inOrderOfAttackDamage.add(cpu1.getBench().get(i-1));
            inOrderOfOverallPosition.add(cpu1.getBench().get(i-1));
        }
        //calculate each cards current attack damage against opponents current card in play
        for(int i =0; i<5;i++){
            if(inOrderOfAttackDamage.get(i).getType() == opponent.getActiveCard().getResistance()){
               inOrderOfAttackDamage.get(i).setPotentialAttackDamage(inOrderOfAttackDamage.get(i).getAttackStrength()/2);
            }else if(inOrderOfAttackDamage.get(i).getType() == opponent.getActiveCard().getWeakness()){
                inOrderOfAttackDamage.get(i).setPotentialAttackDamage(inOrderOfAttackDamage.get(i).getAttackStrength()*2);
            }else{
                inOrderOfAttackDamage.get(i).setPotentialAttackDamage(inOrderOfAttackDamage.get(i).getAttackStrength());
            }
        }

        //sort arrays in ascending order of respective characteristics
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfAttackDamage.get(i).getPotentialAttackDamage() > inOrderOfAttackDamage.get(j).getPotentialAttackDamage()){
                    temp = inOrderOfAttackDamage.get(i);
                    inOrderOfAttackDamage.set(j,inOrderOfAttackDamage.get(i));
                    inOrderOfAttackDamage.set(i, temp);
                }
            }
        }
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfHpLevel.get(i).getHp() > inOrderOfHpLevel.get(j).getHp()){
                    temp = inOrderOfHpLevel.get(i);
                    inOrderOfHpLevel.set(j,inOrderOfHpLevel.get(i));
                    inOrderOfHpLevel.set(i, temp);
                }
            }
        }
    }
    public SocietyCard chooseCard(Player opponent){
        //work out each cards best overall standing giving a weighting of two to attack damage and a weighting of one to besthp
        for(int i =0; i < 5; i++){
            for(int j =i+1; j<5;j++){
                if(inOrderOfAttackDamage.get(i).getIdentifier() == inOrderOfHpLevel.get(j).getIdentifier()) {
                    int AttackDamage=i;
                    int BestHp = j;
                    AttackDamage = (AttackDamage +1)*2;
                    BestHp = BestHp +1;
                    inOrderOfAttackDamage.get(i).setOverallPosition(AttackDamage + BestHp);
                }
                }
            }
        //sort cards in ascending order now of overall standing
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfOverallPosition.get(i).getOverallPosition() > inOrderOfOverallPosition.get(j).getOverallPosition()){
                    temp = inOrderOfOverallPosition.get(i);
                    inOrderOfOverallPosition.set(j,inOrderOfOverallPosition.get(i));
                    inOrderOfOverallPosition.set(i, temp);
                }
            }
        }
        // if opponent is in last round use card with best attack damage
        if(opponent.getRoundWins() >= 5){
            return inOrderOfAttackDamage.get(inOrderOfAttackDamage.size()-1);
        }else {
            //if the card chosen has a weakness to the opponents current card in play choose the second best card
            if (opponent.getActiveCard().getType() != inOrderOfOverallPosition.get(inOrderOfOverallPosition.size()-1).getWeakness()) {
                return inOrderOfOverallPosition.get(inOrderOfOverallPosition.size() -1);
            } else {
                return inOrderOfOverallPosition.get(inOrderOfOverallPosition.size() - 1);
            }
        }
    }
     public void makeMove(CPU cpu1, Player opponent){
       //  talk(opponent, cpu1);
        scanCards(cpu1, opponent);
        cardOfChoice = chooseCard(opponent);
        if(cardOfChoice == cpu1.getActiveCard()){
            attack(opponent);
        }else{
            retreat(cpu1);
            moveCard(cpu1, cardOfChoice);
            attack(opponent);
        }
    }

}
