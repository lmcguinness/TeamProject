package com.example.societyslam.societyslam.GameObjects;

import java.util.Random;

/**
 * Created by James on 09/02/2017.
 */

public class Coin {

    Random randomNum = new Random();
    public int result;
    //Heads = 0
    //Tails = 1
    private int isHeads = 0;

    public boolean flip() {
        result = randomNum.nextInt(2);
        if (result == isHeads) {
            System.out.println("You flipped Heads!");
            return true;
        } else {
            System.out.println("You flipped Tails!");
            return false;
        }
    }
}

