package com.example.societyslam.societyslam.GameObjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by James on 26/04/2017.
 */
/**
 * The Coin enum is created as we need to determine who goes first
 */
public enum Coin {
    HEADS, TAILS;

    private static final List<Coin> SIDES =  Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = SIDES.size();
    private static final Random RANDOM = new Random();
    /**
     * The flip method is used to simulate a fair coin toss
     */
    public static Coin flip() {
        return SIDES.get(RANDOM.nextInt(SIZE));
    }
}