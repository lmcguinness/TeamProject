package com.example.societyslam.societyslam.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class Assets {

    public static Bitmap continueDown, continueButton, flipCoin, flipCoinDown, welcome, start, startDown, boxingSociety, cardBack, cavingSociety, computerSociety, divingSociety, earthEnergy,
    electricEnergy, engineeringSociety, fencingSociety, fightEngery, friendsOfEarth, gardeningSociety, geographySociety, judoSociety,
    karateSociety, physicsSociety, rowingSociety, surfingSociety, swimmingSociety, waterEnergy, background, ssb, ssb1, artificialIntel,
    disruptve, environmentalSociety, fail, freeEntry, freeShots, greenPeace, hangover, jujistoSociety, late, lecture, library,
    paddle, redBull, roboticsSociety, sailingSociety, taekwando, untidy, water, gamingSociety, dealButton;

    public static void load() {
        // Commented ones to be added at a later stage
        continueDown = loadBitmap("continueDown.png", true);
        continueButton = loadBitmap("continueButton.png", true);
        flipCoin = loadBitmap("flipCoin.png", true);
        flipCoinDown = loadBitmap("flipCoinDown.png", true);
        dealButton = loadBitmap("dealButton.png", true);
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
        greenPeace= loadBitmap("greenPeace.png", true);
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

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new BitmapFactory.Options());
        return bitmap;
    }
}
