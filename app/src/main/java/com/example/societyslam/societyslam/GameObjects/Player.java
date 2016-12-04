package com.example.societyslam.societyslam.GameObjects;

import java.util.ArrayList;

/**
 * Created by Aoife Brown on 24/11/2016.
 */

public class Player {

   private  Deck myCards;
    private Card activeCard;
    private ArrayList<Card> hand;
    private ArrayList<Card> bench;
    private ArrayList<Card> prizeCards;
    private boolean myTurn;

    public Player(Deck myCards, Card activeCard, ArrayList<Card> hand, ArrayList<Card> bench, ArrayList<Card> prizeCards, boolean myTurn) {
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

    public Card getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(Card activeCard) {
        this.activeCard = activeCard;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getBench() {
        return bench;
    }

    public void setBench(ArrayList<Card> bench) {
        this.bench = bench;
    }

    public ArrayList<Card> getPrizeCards() {
        return prizeCards;
    }

    public void setPrizeCards(ArrayList<Card> prizeCards) {
        this.prizeCards = prizeCards;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
}
