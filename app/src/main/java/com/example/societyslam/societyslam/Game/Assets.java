package com.example.societyslam.societyslam.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class Assets {

    public static Bitmap welcome, start, startDown, boxingSociety, cardBack, cavingSociety, computerSociety, divingSociety, earthEnergy,
    electricEnergy, engineeringSociety, fencingSociety, fightEngery, friendsOfEarth, gardeningSociety, geographySociety, judoSociety,
    karateSociety, physicsSociety, rowingSociety, surfingSociety, swimmingSociety, waterEnergy, background, ssb;

    public static void load() {
        // Commented ones to be added at a later stage

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
        // fightEngery = loadBitmap("FightEnergy.png", true);
        friendsOfEarth = loadBitmap("FriendsOfEarth.png", true);
        gardeningSociety = loadBitmap("GardeningSociety.png", true);
        geographySociety = loadBitmap("GeographySociety.png", true);
        // judoSociety = loadBitmap("JudoSociety.png", true);
        karateSociety = loadBitmap("KarateSociety.png", true);
        // physicsSociety = loadBitmap("PhysicsSociety.png", true);
        rowingSociety = loadBitmap("RowingSociety.png", true);
        surfingSociety = loadBitmap("SurfingSociety.png", true);
        swimmingSociety = loadBitmap("SwimmingSociety.png", true);
        waterEnergy = loadBitmap("waterenergy.png", true);

        ssb = loadBitmap("ssb.PNG", true);
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
