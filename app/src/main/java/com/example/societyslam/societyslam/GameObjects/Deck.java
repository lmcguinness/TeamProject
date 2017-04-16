package com.example.societyslam.societyslam.GameObjects;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Deck object is a collection of cards
 */
public class Deck {

    private Random randomGenerator;
    public ArrayList<SocietyCard> myDeck = new ArrayList<SocietyCard>();

    /**
     * This constructor creates a Deck object
     * @param myDeck- An arraylist that contains the society cards to be used in the game
     */
    public Deck(ArrayList<SocietyCard> myDeck) {
        this.myDeck = myDeck;
    }

    /**
     * This method returns the Deck
     * @return- return the arraylist containing the games society cards
     */
    public ArrayList<SocietyCard> getMyDeck() {
        return myDeck;
    }

    /**
     * This method sets up the deck to be used in the game
     * @param myDeck- An arraylist of society cards
     */
    public void setMyDeck(ArrayList<SocietyCard> myDeck) {
        this.myDeck = myDeck;
    }

    /**
     * This method generates a random card from the deck
     * @return card - a random society card taken from the deck
     */
    public SocietyCard randomCard(){
        int identifier = 0;
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(myDeck.size());
        SocietyCard card =  myDeck.get(index);
        myDeck.get(index).setIdentifier(identifier);
        identifier++;
        return card;

    }
}



