package com.example.societyslam.societyslam.GameObjects;

import java.util.ArrayList;

/**
 * Created by Aoife Brown on 24/11/2016.
 */

public class Player {

   private  Deck myCards;
    private SocietyCard activeCard;
    private ArrayList<Card> hand;
    private ArrayList<SocietyCard> bench;
    private ArrayList<StudentBehaviourCard> prizeCards;
    private boolean myTurn;

    public Player(Deck myCards, SocietyCard activeCard, ArrayList<SocietyCard> bench, ArrayList<StudentBehaviourCard> prizeCards, boolean myTurn) {
        this.myCards = myCards;
        this.activeCard = activeCard;
        this.hand = hand;
        this.bench = bench;
        this.prizeCards = prizeCards;
        this.myTurn = myTurn;
    }

    public Deck getMyCards() {
        return myCards;
    }

    public void setMyCards(Deck myCards) {
        this.myCards = myCards;
    }

    public SocietyCard getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(SocietyCard activeCard) {
        this.activeCard = activeCard;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<SocietyCard> getBench() {
        return bench;
    }

    public void setBench(ArrayList<SocietyCard> bench) {
        this.bench = bench;
    }

    public ArrayList<StudentBehaviourCard> getPrizeCards() {
        return prizeCards;
    }

    public void setPrizeCards(ArrayList<StudentBehaviourCard> prizeCards) {
        this.prizeCards = prizeCards;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void attack(Player opponent) {
        if (this.getActiveCard().canAttack()) {
            System.out.println(this.getActiveCard().getName() + " used " + this.getActiveCard().getAttackName());
            int opponentNewHP;
            if (opponent.getActiveCard().getWeakness() == this.getActiveCard().getType()) {
                opponentNewHP = opponent.getActiveCard().getHp() - (2 * this.getActiveCard().getAttackStrength());
            } else if (opponent.getActiveCard().getResistance() == this.getActiveCard().getType()) {
                opponentNewHP = opponent.getActiveCard().getHp() - (this.getActiveCard().getAttackStrength() / 2);
            } else {
                opponentNewHP = (opponent.getActiveCard().getHp() - this.getActiveCard().getAttackStrength());
            }
            opponent.getActiveCard().setHp(opponentNewHP);
            System.out.println(opponent.getActiveCard().getName() + " 's HP is now " + opponent.getActiveCard().getHp());
            this.myTurn = false;

        } else {
            System.out.print(this.getActiveCard().getName() + " does not have enough energy cards to use this attack");
        }

    }

    public void useStudentBehaviourCard(StudentBehaviourCard card, Player opponent) {
        System.out.println("You used " + card.getName());
        int newHP;
        int opponentNewHP;
        if(card.hasPositiveEffect()) {
            newHP = this.getActiveCard().getHp() + card.getCardPoints();
            this.getActiveCard().setHp(newHP);
        } else if(!card.hasPositiveEffect()) {
            opponentNewHP = opponent.getActiveCard().getHp() - card.getCardPoints();
            opponent.getActiveCard().setHp(opponentNewHP);
        }
    }
}
