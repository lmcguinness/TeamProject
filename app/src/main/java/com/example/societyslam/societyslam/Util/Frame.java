package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;

/**
 * Created by Chloe McAteer
 * This is a class for a frame object
 */
public class Frame {

    private Bitmap image;
    private double duration;

    /**
     * This constructor creates a frame object
     * @param image - the image to be used in the animation, it must be a bitmap
     * @param duration- the time the image should be displayed for
     */
    public Frame(Bitmap image, double duration){
        this.image = image;
        this.duration = duration;
    }

    /**
     * This method gets the duration time for the image
     * @return duration - the time the image should be displayed for
    */
    public double getDuration(){
        return duration;
    }


    /**
     * This method gets the image to be animated
     * @return image- the image to be used in the animation
     */
    public Bitmap getImage(){
        return image;
    }
}

