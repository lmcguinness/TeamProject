package com.example.societyslam.societyslam.GameObjects;

import java.util.ArrayList;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class Deck {

    private ArrayList<Card> myDeck = new ArrayList<Card>();

    public Deck(ArrayList<Card> myDeck) {
        this.myDeck = myDeck;
    }

    public ArrayList<Card> getMyDeck() {
        return myDeck;
    }

    public void setMyDeck(ArrayList<Card> myDeck) {
        this.myDeck = myDeck;
    }
}
