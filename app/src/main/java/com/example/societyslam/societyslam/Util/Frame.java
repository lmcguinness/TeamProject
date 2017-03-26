package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;
import android.media.Image;

public class Frame {

    private Bitmap image;
    private double duration;

    public Frame(Bitmap image, double duration){
        this.image = image;
        this.duration = duration;

    }
    public double getDuration(){
        return duration;
    }
    public Bitmap getImage(){
        return image;
    }
}

