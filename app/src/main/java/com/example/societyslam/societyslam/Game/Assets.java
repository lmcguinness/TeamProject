package com.example.societyslam.societyslam.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.societyslam.societyslam.Util.Animation;
import com.example.societyslam.societyslam.Util.Frame;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class Assets {
    public static Bitmap yourTurn,chooseCardMenu, useCard, cancel, restart, resume, quit, instructions, pauseMenu, pause, player1T, player2H, headsText, tailsText, choose, retreatError, coinTossBackground, heads, tails, player1, player2, continueDown, continueButton, flipCoin, flipCoinDown, welcome, start, startDown, boxingSociety, cardBack, cavingSociety, computerSociety, divingSociety, earthEnergy,
            electricEnergy, engineeringSociety, fencingSociety, fightEngery, friendsOfEarth, gardeningSociety, geographySociety, judoSociety,
            karateSociety, physicsSociety, rowingSociety, surfingSociety, swimmingSociety, waterEnergy, background, ssb, ssb1, artificialIntel,
            disruptve, environmentalSociety, fail, freeEntry, freeShots, greenPeace, hangover, jujistoSociety, late, lecture, library, howToPlay, howToPlayDown, howToPlayBackground, backArrowButton,
            paddle, redBull, roboticsSociety, sailingSociety, taekwando, untidy, water, gamingSociety, dealButton, menubg, attackButton, retreatButton, evolveButton,
            societyCardButton, SettingsButton, SettingsButton_down, minusButton, plusButton, english_Button, polish_Button, polish_Button_Down, english_Button_down, coin2, coin3, coin4;

    private static SoundPool soundPool;

    public static int coinID, dealingCardsID, oneCardID, attackID, backgroundMusicID, buttonClickID, prizeID;

    public static Animation coinAnim;

    public static void load() {


        english_Button_down = loadBitmap("english_Button_down.png", true);
        polish_Button_Down = loadBitmap("polish_Button_Down.png", true);
        polish_Button = loadBitmap("polish_Button.png", true);
        english_Button = loadBitmap("english_Button.png", true);
        plusButton = loadBitmap("plusButton.png", true);
        minusButton = loadBitmap("minusButton.png", true);
        SettingsButton = loadBitmap("SettingsButton.png", true);
        SettingsButton_down = loadBitmap("SettingsButton_down.png", true);

        // Commented ones to be added at a later stage
        yourTurn = loadBitmap("YourTurn.png", true);
        chooseCardMenu = loadBitmap("chooseCardMenu.png", true);
        useCard = loadBitmap("useCard.png", true);
        cancel = loadBitmap("cancel.png", true);
        coinTossBackground = loadBitmap("CoinTossBackground.png", true);
        heads = loadBitmap("heads.png", true);
        tails = loadBitmap("tails.png", true);
        player1 = loadBitmap("player1.png", true);
        player2 = loadBitmap("player2.png", true);
        continueDown = loadBitmap("continueDown.png", true);
        continueButton = loadBitmap("continueButton.png", true);
        flipCoin = loadBitmap("flipCoin.png", true);
        flipCoinDown = loadBitmap("flipCoinDown.png", true);
        backArrowButton = loadBitmap("backArrowButton.png", true);
        howToPlayBackground = loadBitmap("howToPlayBackground.png", true);
        howToPlay = loadBitmap("howToPlay.png", true);
        howToPlayDown = loadBitmap("howToPlayDown.png", true);
        headsText = loadBitmap("headsText.png", true);
        tailsText = loadBitmap("tailsText.png", true);
        choose = loadBitmap("choose.png" ,true);
        player1T = loadBitmap("player1T.png",true);
        player2H = loadBitmap("player2H.png",true);
        pause = loadBitmap("pause.png",true);
        dealButton = loadBitmap("dealButton.png", true);
        pauseMenu = loadBitmap("pauseMenu.png",true);
        menubg = loadBitmap("pikachuMenu.png", true);
        attackButton = loadBitmap("attack.png", true);
        retreatButton = loadBitmap("retreat.png", true);
        evolveButton = loadBitmap("Evolve.png", true);
        societyCardButton = loadBitmap("societyCard.png", true);
        retreatError = loadBitmap("retreatError.png", true);
        restart =loadBitmap("restart.png", true);
        resume = loadBitmap("resume.png", true);
        quit = loadBitmap("quit.png", true);
        instructions = loadBitmap("instructions.png", true);

        welcome = loadBitmap("welcome.jpg", false);
        start = loadBitmap("start_button.png", true);
        startDown = loadBitmap("start_button_down.png",true);
        boxingSociety = loadBitmap("boxingsociety.png", true);
        cardBack = loadBitmap("CardBack.png", true);
        cavingSociety = loadBitmap("CavingSociety.png", true);
        computerSociety = loadBitmap("computersociety.png", true);
        divingSociety = loadBitmap("divingsociety.png", true);
        earthEnergy = loadBitmap("EarthEnergy.png", true);
        electricEnergy = loadBitmap("ElectricEnergy.png", true);
        engineeringSociety = loadBitmap("EngineeringSociety.png", true);
        fencingSociety = loadBitmap("FencingSociety.png", true);
        fightEngery = loadBitmap("FightEnergy.png", true);
        friendsOfEarth = loadBitmap("FriendsOfEarth.png", true);
        gardeningSociety = loadBitmap("GardeningSociety.png", true);
        geographySociety = loadBitmap("GeographySociety.png", true);
        judoSociety = loadBitmap("JudoSociety.png", true);
        karateSociety = loadBitmap("KarateSociety.png", true);
        physicsSociety = loadBitmap("PhysicsSociety.png", true);
        rowingSociety = loadBitmap("RowingSociety.png", true);
        surfingSociety = loadBitmap("SurfingSociety.png", true);
        swimmingSociety = loadBitmap("SwimmingSociety.png", true);
        waterEnergy = loadBitmap("waterenergy.png", true);
        artificialIntel = loadBitmap("ArtificialIntel.png", true);
        disruptve = loadBitmap("Disruptive.png", true);
        environmentalSociety = loadBitmap("environmentalSociety.png", true);
        fail = loadBitmap("fail.png", true);
        freeEntry= loadBitmap("freeEntry.png", true);
        freeShots=loadBitmap("freeShots.png", true);
        greenPeace= loadBitmap("GreenPeace.png", true);
        hangover = loadBitmap("hangover.png", true);
        jujistoSociety= loadBitmap("jujistoSociety.png", true);
        late = loadBitmap("late.png" ,true);
        lecture = loadBitmap("lecture.png", true);
        library = loadBitmap("library.png", true);
        paddle = loadBitmap("Paddle.png", true);
        redBull = loadBitmap("redBull.png", true);
        roboticsSociety = loadBitmap("roboticsSociety.png", true);
        sailingSociety = loadBitmap("sailingSociety.png", true);
        taekwando = loadBitmap("Taekwando.png", true);
        untidy = loadBitmap("untidy.png", true);
        water = loadBitmap("water.png", true);
        gamingSociety = loadBitmap("gamingSociety.png", true);

        ssb = loadBitmap("ssb (1).png", false);

        //load sound files
        coinID = loadSound("CoinFlip.mp3");
        dealingCardsID = loadSound("DealingCards.wav");
        oneCardID = loadSound("OneCard.wav");
        attackID = loadSound("Attack.wav");
        backgroundMusicID = loadSound("backgroundMusic.mp3");
        buttonClickID = loadSound("ButtonClick.wav");
        prizeID = loadSound("Prize.wav");

        //Animation of coin
        coin2 = loadBitmap("coin2.png", true);
        coin3 = loadBitmap("coin3.png", true);
        coin4 = loadBitmap("coin4.png", true);

        Frame f1 = new Frame(coin2, .1f);
        Frame f2 = new Frame(coin3, .1f);
        Frame f3 = new Frame(coin4, .1f);

        coinAnim = new Animation(f1,f2,f3,f2,f1);


    }


    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = MainActivity.assets.open(filename);
        } catch(IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        if(transparency) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inScaled = false;

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new BitmapFactory.Options());
        return bitmap;
    }

    private static int loadSound(String filename){
        int soundID =0;
        if(soundPool == null){
            soundPool = new SoundPool(25,AudioManager.STREAM_MUSIC,0);
        }
        try{
            soundID = soundPool.load(MainActivity.assets.openFd(filename),1);
        }catch(IOException e){
            e.printStackTrace();
        }
        return soundID;
    }


    //play sounds that dont need to be looped
    public static void playSound(int soundID){
        soundPool.play(soundID,1,1,1,0,1);

    }

    //Set the background music to loop forever
    public static void playBackground(int soundID) {
        soundPool.play(soundID, 1, 1, 1, -1, 1);


    }



}








