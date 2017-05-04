package com.example.societyslam.societyslam.Util;

/**
 * Chloe Mc Ateer
 * The Animation class is a collection of associated frames
 * In implementation we create an Animation object for each desired animation
 */
public class Animation {
    private Frame[] frames;
    private double[] frameEndTimes;
    private int currentFrameIndex = 0;
    private double totalDuration =0;
    private double currentTime =0;

    /**
    This constructor creates an Animation object
    @param frames- it takes in an array of frame objects, (...) means can take any number of frames
    as each animation holds multiple frames(images and corresponding durations), we can determine,
    when to switch from ont frame to another
     */
    public Animation(Frame...frames){
        this.frames = frames;
        //create a new array parallel to the frames array(identical lengths)
        frameEndTimes = new double[frames.length];
        //Loop to determine the end time for each frame and the total duration of all of the frames
        for(int i =0; i< frames.length;i++){
            Frame f = frames[i];
            totalDuration += f.getDuration();
            frameEndTimes[i] = totalDuration;
        }
    }

    /**
     * Synchronized- ensures that the animations will update accurarely in a multitreaded environment
     * This is the update method which has two main responsibilities
     * Firstly it keeps track of the current timeand handles irregularities
     * Secondly it determines the current frame index
     * @param increment- This will be the delta value from the game loop,
     *                 the current time is incremented with this value
     */
    public synchronized void update(float increment ){
        currentTime += increment;
        //if the current time is greater than that of total duration, we know animation is complete
        if(currentTime > totalDuration){
            wrapAnimation();
        }
        while(currentTime > frameEndTimes[currentFrameIndex]){
            currentFrameIndex++;
        }
    }

    /**
     * The wrapAnimation method is called when current time has exceeded the total duration,
     * it resets the current frame index at zero and calculates a new current time
     */
    private synchronized void wrapAnimation(){
        currentFrameIndex=0;
        //% has the effect of calculating how many seconds past the end of the animation we were at before resetting the animation
        currentTime %= totalDuration; //equal to CT = CT% td
    }

    /**
     * The render method draws the animation to the screen
     * @param g- The painter
     * @param x- X coordinate for the animations position on the screen
     * @param y- Y coordinate for the animations position on the screen
     */
    public synchronized void render(Painter g, int x, int y){
      g.drawImage(frames[currentFrameIndex].getImage(), x,y);
    }

    /**
     * Overloads the other render method
     * still draws the image to the screen but takes in its height and width
     * @param g - The painter
     * @param x- The X coordinate for the animations position on the screen
     * @param y- The Y coordinate for the animations position on the screen
     * @param width- The width of the animation
     * @param height- The height of the animation
     */
    public synchronized void render(Painter g, int x, int y , int width, int height){
        g.drawImage(frames[currentFrameIndex].getImage(),x,y, width,height);
    }
}