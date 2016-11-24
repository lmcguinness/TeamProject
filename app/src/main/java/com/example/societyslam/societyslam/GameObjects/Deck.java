package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Bitmap;
import android.widget.EditText;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class Deck {
    private Random randomGenerator;

    public ArrayList<Card> myDeck = new ArrayList<Card>();

    public Deck(ArrayList<Card> myDeck) {
        this.myDeck = myDeck;
    }


    public ArrayList<Card> getMyDeck() {
        return myDeck;
    }

    public void setMyDeck(ArrayList<Card> myDeck) {
        this.myDeck = myDeck;
    }

    public Bitmap randomCard(){
        int index = randomGenerator.nextInt(myDeck.size());
        Card card =  myDeck.get(index);
        return card.getPicture();

    }


}



