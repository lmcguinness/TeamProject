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

    public ArrayList<SocietyCard> myDeck = new ArrayList<SocietyCard>();

    public Deck(ArrayList<SocietyCard> myDeck) {
        this.myDeck = myDeck;
    }


    public ArrayList<SocietyCard> getMyDeck() {
        return myDeck;
    }

    public void setMyDeck(ArrayList<SocietyCard> myDeck) {
        this.myDeck = myDeck;
    }

    public SocietyCard randomCard(){
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(myDeck.size());
        SocietyCard card =  myDeck.get(index);
        return card;

    }


}



