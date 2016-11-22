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
    karateSociety, physicsSociety, rowingSociety, surfingSociety, swimmingSociety, waterEnergy, background;

    public static void load() {
        welcome = loadBitmap("welcome.jpg", false);
        start = loadBitmap("start_button.png", true);
        startDown = loadBitmap("start_button_down.png",true);
        boxingSociety = loadBitmap("BoxingSociety.PNG", true);
        cardBack = loadBitmap("CardBack.PNG", true);
        cavingSociety = loadBitmap("CavingSociety.PNG", true);
        computerSociety = loadBitmap("ComputerSociety.PNG", true);
        divingSociety = loadBitmap("DivingSociety.PNG", true);
        earthEnergy = loadBitmap("EarthEnergy.PNG", true);
        electricEnergy = loadBitmap("ElectricEnergy.PNG", true);
        engineeringSociety = loadBitmap("EngineeringSociety.PNG", true);
        fencingSociety = loadBitmap("FencingSociety.PNG", true);
        fightEngery = loadBitmap("FightEnergy.PNG", true);
        friendsOfEarth = loadBitmap("FriendsOfEarth.PNG", true);
        gardeningSociety = loadBitmap("GardeningSociety.PNG", true);
        geographySociety = loadBitmap("GeographySociety.PNG", true);
        judoSociety = loadBitmap("JudoSociety.PNG", true);
        karateSociety = loadBitmap("KarateSociety.PNG", true);
        physicsSociety = loadBitmap("PhysicsSociety.PNG", true);
        rowingSociety = loadBitmap("RowingSociety.PNG", true);
        surfingSociety = loadBitmap("SurfingSociety.PNG", true);
        swimmingSociety = loadBitmap("SwimmingSociety.PNG", true);
        waterEnergy = loadBitmap("WaterEnergy.PNG", true);
        background = loadBitmap("pokemonboard.jpg", false);

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
