package com.example.societyslam.societyslam.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.State.CoinTossState;
import com.example.societyslam.societyslam.State.LoadState;
import com.example.societyslam.societyslam.State.MenuState;
import com.example.societyslam.societyslam.State.OnePlayerState;
import com.example.societyslam.societyslam.State.PlayState;
import com.example.societyslam.societyslam.State.State;
import com.example.societyslam.societyslam.Util.InputHandler;
import com.example.societyslam.societyslam.Util.Painter;
import com.example.societyslam.societyslam.Util.PauseMenu;
import com.example.societyslam.societyslam.ai.CPU;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

/**
 * This is the central class for our game, this class hosts our game loop, and has methods to start
 * and exit out of the game
 */

public class GameView extends SurfaceView implements Runnable {

    private Bitmap gameImage;
    private Rect gameImageSrc, gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics;
    private Context context;
    private Thread gameThread;
    private volatile boolean running = false;
    private volatile State currentState, temp;
    private InputHandler inputHandler;
    private boolean isPlayerDetailsSet;


    public GameView(Context context, int gameWidth, int gameHeight, final boolean isPlayerDetailsSet) {
        super(context);
        this.context = context;
        this.isPlayerDetailsSet = isPlayerDetailsSet;
        gameImage = Bitmap.createBitmap(gameWidth,gameHeight, Bitmap.Config.RGB_565);
        gameImageSrc = new Rect(0,0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        graphics = new Painter(gameCanvas);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initInput();
                if (currentState == null) {
                    if (!isPlayerDetailsSet) {
                        setCurrentState(new LoadState());
                    } else {
                        setCurrentState(new CoinTossState());
                    }
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                pauseGame();

            }
        });
    }

    /**
     * Run method implemented to allow the game to run
     */
    public void run() {
        Looper.prepare();
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;
        while(running) {
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            updateAndRender(deltaMillis);
            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) /1000000L;
            sleepDurationMillis = Math.max(2,17-updateDurationMillis);
            try {
                Thread.sleep(sleepDurationMillis);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * change states method implemented in order to be able to change states whilst storing an instance of the previous state
     * @param previousState
     * @param newState
     */
    public void changeStates(State previousState, State newState){
        System.gc();
        temp = previousState;
        newState.setContext(this.context);
        newState.init();
        currentState = newState;
        currentState.setPainter(graphics);
        inputHandler.setCurrentState(currentState);
    }

    /**
     * method implemented to go back to a previous state
     */
    public void changeBack(){
        System.gc();
        currentState=temp;
        currentState.setContext(this.context);
        currentState.setPainter(graphics);
        inputHandler.setCurrentState(currentState);
    }
    /**
     * method implemented to set current state to a new state
     * @param newState
     */
    public void setCurrentState(State newState) {
        System.gc();
        resetCards();
        newState.setContext(this.context);
        newState.init();
        currentState = newState;
        currentState.setPainter(graphics);
        inputHandler.setCurrentState(currentState);
    }

    /**
     * method used to initialise input
     */
    private void initInput() {
        if (inputHandler == null) {
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    /**
     * method used to initialise the game thread and start the game loop
     */
    private void initGame() {
        running = true;
        gameThread = new Thread(this,"Game Thread");
        gameThread.start();
    }

    /**
     * method used to restart the game
     */
    public void restartGame(){
       resetCards();
        if(MenuState.getIsTwoPlayer()){
            PlayState.setPlayer1Wins(0);
            PlayState.setPlayer2Wins(0);
        setCurrentState(new CoinTossState());
    }else {
            OnePlayerState.setPlayer1Wins(0);
            OnePlayerState.setPlayer2Wins(0);
            setCurrentState(new OnePlayerState());
        }
    }

    /**
     * method used to quit game
     */
    public void quitGame(){
       resetCards();
         if(MenuState.getIsTwoPlayer()){
             PlayState.setPlayer1Wins(0);
             PlayState.setPlayer2Wins(0);
             setCurrentState(new MenuState());
         }else {
             OnePlayerState.setPlayer1Wins(0);
             OnePlayerState.setPlayer2Wins(0);
             setCurrentState(new MenuState());
    }
}

    /**
     * method implemented to reset all cards and their arrays
     * used when game is quit or restarted
     */
    public void resetCards(){
        Assets.playersCards.clear();
        Assets.deckOfCards.clear();
        Assets.energyCards.clear();
        Assets.player2Cards.clear();
        Assets.prizeCardDeck2.clear();
        Assets.prizeCardDeck1.clear();
        Assets.currentCardInPlay2 = null;
        Assets.currentCardInPlay = null;
        CPU.setIsTalking(false);
        PauseMenu.setLoadGame(false);
    }

    /**
     * method implemented to pause game
     */
    public void pauseGame() {
        running = false;
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method implemented to resume game
     */
    public void resumeGame(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public boolean isRunning() {
        return running;
    }

    private void updateAndRender(long delta) {
        currentState.update(delta / 1000f);
        currentState.render(graphics);
        renderGameImage();
    }

    private void renderGameImage() {
        Canvas screen = getHolder().lockCanvas();

        if(screen != null) {
            screen.getClipBounds(gameImageDst);
            screen.drawBitmap(gameImage,gameImageSrc,gameImageDst, null);
            getHolder().unlockCanvasAndPost(screen);
        }
    }
}