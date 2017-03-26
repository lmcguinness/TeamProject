package com.example.societyslam.societyslam.GameObjects;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Util.Animation;

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

    public void flip() {
        result = randomNum.nextInt(2);
        if (result == isHeads) {
            System.out.println("You flipped Heads!");
        } else {
            System.out.println("You flipped Tails!");
        }
    }
}

