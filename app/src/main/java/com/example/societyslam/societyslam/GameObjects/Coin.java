package com.example.societyslam.societyslam.GameObjects;

import java.util.Random;

/**
 * Created by James on 09/02/2017.
 */

public class Coin {

    Random randomNum = new Random();
    public int result;
    private int heads = 0;
    private int tails = 1;


    public boolean flip() {
        result = randomNum.nextInt(2);
        if (result == heads) {
            System.out.println("You flipped Heads! Player 1's turn");
            return true;
        } else {

            System.out.println("You flipped Tails! Player 2's turn");
            return false;
        }

    }

}

