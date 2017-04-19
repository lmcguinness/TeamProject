package com.example.societyslam.societyslam.GameObjects;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Animation;

import java.util.Random;

/**
 * Created by James on 09/02/2017.
 */

/**
 * The Coin object is created as we need to determine who goes first
 */

public class Coin {
    Random randomNum = new Random();
    public int result;

    /**
     * Here we set the variable isHeads =0, therefore if the flip method returns 0 the coin flipped heads hence,
     * Heads = 0
     * Tails = 1
     */
    private int isHeads = 0;

    /**
     * The flip method is used to simulate a fair coin toss
     */
    public void flip() {
        result = randomNum.nextInt(2);
        if (result == isHeads)  {
            System.out.println("You flipped Heads!");
        } else {
            System.out.println("You flipped Tails!");
        }
    }
}



