package com.example.societyslam.societyslam.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.media.AudioManager;
import android.media.SoundPool;

import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.EnergyCard;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourType;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Animation;
import com.example.societyslam.societyslam.Util.Frame;

/**
 * Created by Aoife Brown on 15/11/2016.
 */

public class Assets {
    public static Bitmap twoPlayerButton, onePlayerButton, yourTurn,chooseCardMenu, useCard, cancel, restart, resume, quit, instructions, pauseMenu, pause, player1T, player2H, headsText, tailsText, choose, retreatError, coinTossBackground, heads, tails, player1, player2, continueDown, continueButton, flipCoin, flipCoinDown, welcome, start, startDown, boxingSociety, cardBack, cavingSociety, computerSociety, divingSociety, earthEnergy,
            electricEnergy, engineeringSociety, fencingSociety, fightEngery, friendsOfEarth, gardeningSociety, geographySociety, judoSociety,
            karateSociety, physicsSociety, rowingSociety, surfingSociety, swimmingSociety, waterEnergy, background, ssb, ssb1, artificialIntel,
            disruptve, environmentalSociety, fail, freeEntry, freeShots, greenPeace, hangover, jujistoSociety, late, lecture, library, howToPlay, howToPlayDown, howToPlayBackground, backArrowButton,
            paddle, redBull, roboticsSociety, sailingSociety, taekwando, untidy, water, gamingSociety, dealButton, menubg, attackButton, retreatButton, evolveButton,
            societyCardButton, SettingsButton, SettingsButton_down, minusButton, plusButton, english_Button, polish_Button, polish_Button_Down, english_Button_down, coin2, coin3, coin4, playAgainButton, homeButton, highScoreButton;

    private static SoundPool soundPool;

    public static int coinID, dealingCardsID, oneCardID, attackID, backgroundMusicID, buttonClickID, prizeID, musicAtBeginning;

    public static Animation coinAnim;


    public static SocietyCard currentCardInPlay, currentCardInPlay2;
    //Deck of cards
    public static ArrayList<SocietyCard> deckOfCards = new ArrayList<SocietyCard>();
    public static Deck myDeck = new Deck(deckOfCards);

    //Separate cards for both players
    public static ArrayList<SocietyCard> playersCards = new ArrayList<SocietyCard>();
    public static  ArrayList<SocietyCard> player2Cards = new ArrayList<SocietyCard>();


    public static ArrayList<EnergyCard> energyCards = new ArrayList<EnergyCard>();
    public static  ArrayList<StudentBehaviourCard> prizeCardDeck1 = new ArrayList<StudentBehaviourCard>();
    public static  ArrayList<StudentBehaviourCard> prizeCardDeck2 = new ArrayList<StudentBehaviourCard>();


    public static void load() {
        twoPlayerButton = loadBitmap("twoPlayerButton.png", true);
        onePlayerButton = loadBitmap("onePlayerButton.png", true);
        homeButton = loadBitmap("home-button.png", true);
        playAgainButton = loadBitmap("play-again-button.png",true);
        polish_Button = loadBitmap("polish-button.png", true);
        english_Button = loadBitmap("english-button.png", true);
        plusButton = loadBitmap("plusButton.png", true);
        minusButton = loadBitmap("minusButton.png", true);
        SettingsButton = loadBitmap("settings-button.png", true);
        highScoreButton = loadBitmap("highScore.png", true);
        yourTurn = loadBitmap("YourTurn.png", true);
        chooseCardMenu = loadBitmap("chooseCardMenu.png", true);
        useCard = loadBitmap("useCard.png", true);
        cancel = loadBitmap("cancel.png", true);
        coinTossBackground = loadBitmap("CoinTossBackground.png", true);
        heads = loadBitmap("heads.png", true);
        tails = loadBitmap("tails.png", true);
        player1 = loadBitmap("player1.png", true);
        player2 = loadBitmap("player2.png", true);
        continueButton = loadBitmap("continue-button.png", true);
        flipCoin = loadBitmap("flip-coin-button.png", true);
        backArrowButton = loadBitmap("backArrowButton.png", true);
        howToPlayBackground = loadBitmap("howToPlayBackground.png", true);
        howToPlay = loadBitmap("how-to-play-button.png", true);
        headsText = loadBitmap("headsText.png", true);
        tailsText = loadBitmap("tailsText.png", true);
        choose = loadBitmap("choose.png" ,true);
        player1T = loadBitmap("player1T.png",true);
        player2H = loadBitmap("player2H.png",true);
        pause = loadBitmap("pause.png",true);
        dealButton = loadBitmap("deal-cards-button.png", true);
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
        start = loadBitmap("start-button.png", true);
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
        musicAtBeginning = loadSound("musicAtBeginning.wav");

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
   public static void InitialiseCards(){

       SocietyCard computerSociety = new SocietyCard("Computer Society", 0, 0, 3, 2, Assets.computerSociety, 100, "Virus Strike", energyCards, 30, Type.electric, Type.water, null, energyCards, Level.Basic, energyCards);
       SocietyCard artificialInt = new SocietyCard("Artificial Intelligence", 0, 0, 3, 2, Assets.artificialIntel, 150, "Mind Swap", energyCards, 40, Type.electric, Type.water, Type.fighting, energyCards, Level.Basic, energyCards);
       SocietyCard gamingSociety = new SocietyCard("Gaming Society", 0, 0, 3, 2, Assets.gamingSociety, 80, "Zap Cannon", energyCards, 25, Type.electric, Type.water, Type.fighting, energyCards, Level.Basic, energyCards);
       SocietyCard physicsSociety = new SocietyCard("Physics Society", 0, 0, 3, 2, Assets.physicsSociety, 120, "Acid Spray", energyCards, 30, Type.electric, null, Type.water, energyCards, Level.Basic, energyCards);
       SocietyCard engineeringSociety = new SocietyCard("Engineering Society", 0, 0, 3, 2, Assets.engineeringSociety, 90, "Shift Gear", energyCards, 25, Type.electric, null, null, energyCards, Level.Basic, energyCards);
       SocietyCard roboticsSociety = new SocietyCard("roboticsSociety", 0, 0, 3, 2, Assets.roboticsSociety, 100, "Electric Shock", energyCards, 30, Type.electric, Type.water, Type.fighting, energyCards, Level.Basic, energyCards);
       SocietyCard boxingSociety = new SocietyCard("Boxing Society", 0, 0, 3, 2, Assets.boxingSociety, 120, "Force Punce", energyCards, 40, Type.fighting, Type.electric, Type.water, energyCards, Level.Basic, energyCards);
       SocietyCard karateSociety = new SocietyCard("Karate Society", 0, 0, 3, 2, Assets.karateSociety, 100, "Karate Chop", energyCards, 20, Type.fighting, null, null, energyCards, Level.Basic, energyCards);
       SocietyCard fencingSociety = new SocietyCard("Fencing Society", 0, 0, 3, 2, Assets.fencingSociety, 120, "Low Sweep", energyCards, 25, Type.fighting, null, null, energyCards, Level.Basic, energyCards);
       SocietyCard judoSociety = new SocietyCard("Judo Society", 0, 0, 3, 2, Assets.judoSociety, 90, "Arm Trust", energyCards, 25, Type.fighting, Type.water, null, energyCards, Level.Basic, energyCards);
       SocietyCard jujistoSociety = new SocietyCard("jujisto Society", 0, 0, 3, 2, Assets.jujistoSociety, 90, "Shoulder Lock", energyCards, 25, Type.fighting, Type.water, null, energyCards, Level.Basic, energyCards);
       SocietyCard taekwandoSociety = new SocietyCard("TaekwandoSociety", 0, 0, 3, 2, Assets.taekwando, 60, "Side Kick", energyCards, 25, Type.fighting, Type.water, null, energyCards, Level.Basic, energyCards);
       SocietyCard rowingSociety = new SocietyCard("Rowing Society", 0, 0, 3, 2, Assets.rowingSociety, 100, "Paddle Pound", energyCards, 20, Type.water, Type.electric, Type.fighting, energyCards, Level.Basic, energyCards);
       SocietyCard divingSociety = new SocietyCard("Diving Society", 0, 0, 3, 2, Assets.divingSociety, 75, "Dive", energyCards, 10, Type.water, Type.electric, null, energyCards, Level.Basic, energyCards);
       SocietyCard surfingSociety = new SocietyCard("Surfing Society ", 0, 0, 3, 2, Assets.surfingSociety, 90, "Surf", energyCards, 20, Type.water, null, null, energyCards, Level.Basic, energyCards);
       SocietyCard swimmingSociety = new SocietyCard("Swimming Society ", 0, 0, 3, 2, Assets.swimmingSociety, 110, "Bubble Bust", energyCards, 30, Type.water, Type.electric, null, energyCards, Level.Basic, energyCards);
       SocietyCard paddleSociety = new SocietyCard("Paddle Society", 0, 0, 3, 2, Assets.paddle, 65, "Paddle Strike", energyCards, 15, Type.water, null, null, energyCards, Level.Basic, energyCards);
       SocietyCard sailingSociety = new SocietyCard("SailingSociety", 0, 0, 3, 2, Assets.sailingSociety, 60, "AnchorDrop", energyCards, 10, Type.water, Type.electric, null, energyCards, Level.Basic, energyCards);
       SocietyCard gardeningSociety = new SocietyCard("Gardening Society", 0, 0, 3, 2, Assets.gardeningSociety, 60, "Magical Leaf", energyCards, 10, Type.earth, null, Type.water, energyCards, Level.Basic, energyCards);
       SocietyCard geographySociety = new SocietyCard("Geography Society", 0, 0, 3, 2, Assets.geographySociety, 55, "Leaf Storm", energyCards, 15, Type.earth, null, Type.water, energyCards, Level.Basic, energyCards);
       SocietyCard friendsOfTheEarth = new SocietyCard("Friends of the Earth", 0, 0, 3, 2, Assets.friendsOfEarth, 75, "Cotton Guard", energyCards, 25, Type.earth, Type.fighting, null, energyCards, Level.Basic, energyCards);
       SocietyCard cavingSociety = new SocietyCard("Caving Society", 0, 0, 3, 2, Assets.cavingSociety, 85, "Drill Run", energyCards, 30, Type.earth, null, Type.fighting, energyCards, Level.Basic, energyCards);
       SocietyCard environmentalSociety = new SocietyCard("Environmental Society", 0, 0, 3, 2, Assets.environmentalSociety, 70, "Worry Seed", energyCards, 10, Type.earth, null, null, energyCards, Level.Basic, energyCards);
       SocietyCard greenPeace = new SocietyCard("Green Peace", 0, 0, 3, 2, Assets.greenPeace, 70, "Flower Shield", energyCards, 10, Type.earth, null, null, energyCards, Level.Basic, energyCards);

       //Student behaviour cards
       StudentBehaviourCard disruptive = new StudentBehaviourCard("Disruptive in class", 0, 0, 3, 2, Assets.disruptve, StudentBehaviourType.stadium, false);
       StudentBehaviourCard fail = new StudentBehaviourCard("Fail Assignment", 0, 0, 3, 2, Assets.fail, StudentBehaviourType.support, false);
       StudentBehaviourCard freeEntry = new StudentBehaviourCard("Free Entry", 0, 0, 3, 2, Assets.freeEntry, StudentBehaviourType.support, true);
       StudentBehaviourCard freeShots = new StudentBehaviourCard("Free Shots", 0, 0, 3, 2, Assets.freeShots, StudentBehaviourType.item, true);
       StudentBehaviourCard hangover = new StudentBehaviourCard("hangover", 0, 0, 3, 2, Assets.hangover, StudentBehaviourType.stadium, false);
       StudentBehaviourCard late = new StudentBehaviourCard("Late to class", 0, 0, 3, 2, Assets.late, StudentBehaviourType.item, false);
       StudentBehaviourCard lecture = new StudentBehaviourCard("Go to a lecture", 0, 0, 3, 2, Assets.lecture, StudentBehaviourType.item, true);
       StudentBehaviourCard library = new StudentBehaviourCard("Go to library", 0, 0, 3, 2, Assets.library, StudentBehaviourType.stadium, true);
       StudentBehaviourCard redBull = new StudentBehaviourCard("Drink Red Bull", 0, 0, 3, 2, Assets.redBull, StudentBehaviourType.support, true);
       StudentBehaviourCard untidy = new StudentBehaviourCard("Untidy Accommodation", 0, 0, 3, 2, Assets.untidy, StudentBehaviourType.stadium, false);
       StudentBehaviourCard water = new StudentBehaviourCard("Litre of water", 0, 0, 3, 2, Assets.water, StudentBehaviourType.support, true);

       //Student behaviour cards
       StudentBehaviourCard disruptive1 = new StudentBehaviourCard("Disruptive in class", 0, 0, 3, 2, Assets.disruptve, StudentBehaviourType.stadium, false);
       StudentBehaviourCard fail1 = new StudentBehaviourCard("Fail Assignment", 0, 0, 3, 2, Assets.fail, StudentBehaviourType.support, false);
       StudentBehaviourCard freeEntry1 = new StudentBehaviourCard("Free Entry", 0, 0, 3, 2, Assets.freeEntry, StudentBehaviourType.support, true);
       StudentBehaviourCard freeShots1 = new StudentBehaviourCard("Free Shots", 0, 0, 3, 2, Assets.freeShots, StudentBehaviourType.item, true);
       StudentBehaviourCard hangover1 = new StudentBehaviourCard("hangover", 0, 0, 3, 2, Assets.hangover, StudentBehaviourType.stadium, false);
       StudentBehaviourCard late1 = new StudentBehaviourCard("Late to class", 0, 0, 3, 2, Assets.late, StudentBehaviourType.item, false);
       StudentBehaviourCard lecture1 = new StudentBehaviourCard("Go to a lecture", 0, 0, 3, 2, Assets.lecture, StudentBehaviourType.item, true);
       StudentBehaviourCard library1 = new StudentBehaviourCard("Go to library", 0, 0, 3, 2, Assets.library, StudentBehaviourType.stadium, true);
       StudentBehaviourCard redBull1 = new StudentBehaviourCard("Drink Red Bull", 0, 0, 3, 2, Assets.redBull, StudentBehaviourType.support, true);
       StudentBehaviourCard untidy1 = new StudentBehaviourCard("Untidy Accommodation", 0, 0, 3, 2, Assets.untidy, StudentBehaviourType.stadium, false);
       StudentBehaviourCard water1 = new StudentBehaviourCard("Litre of water", 0, 0, 3, 2, Assets.water, StudentBehaviourType.support, true);

       //Engery cards
       EnergyCard waterEnergy = new EnergyCard("Water", 0, 0, 3, 2, Assets.waterEnergy, Type.water);
       EnergyCard electricEnergy = new EnergyCard("Electric", 0, 0, 3, 2, Assets.electricEnergy, Type.electric);
       EnergyCard earthEnergy = new EnergyCard("Earth", 0, 0, 3, 2, Assets.earthEnergy, Type.earth);
       EnergyCard fightEngery = new EnergyCard("Fight", 0, 0, 3, 2, Assets.fightEngery, Type.fighting);
       deckOfCards.add(computerSociety);
       deckOfCards.add(artificialInt);
       deckOfCards.add(gamingSociety);
       deckOfCards.add(physicsSociety);
       deckOfCards.add(engineeringSociety);
       deckOfCards.add(roboticsSociety);
       deckOfCards.add(karateSociety);
       deckOfCards.add(boxingSociety);
       deckOfCards.add(fencingSociety);
       deckOfCards.add(judoSociety);
       deckOfCards.add(jujistoSociety);
       deckOfCards.add(taekwandoSociety);
       deckOfCards.add(divingSociety);
       deckOfCards.add(rowingSociety);
       deckOfCards.add(surfingSociety);
       deckOfCards.add(swimmingSociety);
       deckOfCards.add(paddleSociety);
       deckOfCards.add(sailingSociety);
       deckOfCards.add(gardeningSociety);
       deckOfCards.add(geographySociety);
       deckOfCards.add(friendsOfTheEarth);
       deckOfCards.add(cavingSociety);
       deckOfCards.add(environmentalSociety);
       deckOfCards.add(greenPeace);

       prizeCardDeck1.add(disruptive);
       prizeCardDeck1.add(fail);
       prizeCardDeck1.add(freeEntry);
       prizeCardDeck1.add(freeShots);
       prizeCardDeck1.add(hangover);
       prizeCardDeck1.add(late);
       prizeCardDeck1.add(lecture);
       prizeCardDeck1.add(library);
       prizeCardDeck1.add(redBull);
       prizeCardDeck1.add(untidy);
       prizeCardDeck1.add(water);

       //Adding cards to player 2's deck
       prizeCardDeck2.add(disruptive1);
       prizeCardDeck2.add(fail1);
       prizeCardDeck2.add(freeEntry1);
       prizeCardDeck2.add(freeShots1);
       prizeCardDeck2.add(hangover1);
       prizeCardDeck2.add(late1);
       prizeCardDeck2.add(lecture1);
       prizeCardDeck2.add(library1);
       prizeCardDeck2.add(redBull1);
       prizeCardDeck2.add(untidy1);
       prizeCardDeck2.add(water1);

       energyCards.add(waterEnergy);
   }


}








