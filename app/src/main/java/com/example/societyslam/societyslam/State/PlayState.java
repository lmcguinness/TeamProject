package com.example.societyslam.societyslam.State;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
import com.example.societyslam.societyslam.Util.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Card;
import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.EnergyCard;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourType;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.R;
import com.example.societyslam.societyslam.State.State;
import com.example.societyslam.societyslam.Util.Painter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class PlayState extends State implements View.OnClickListener {

    private ImageButton drawButton;
    private ImageView cardImage;
    private boolean isStart = true;
    private boolean dealCards = true;
    private Card currentCardInPlay = null, currentCardInPlay2 = null;

    private static int STARTING_CARD_NUMBER = 5;

    private Button playButton;
    private Button dealButton;
    private Rect playRect;

    //Society cards
    private SocietyCard computerSociety = new SocietyCard("Computer Society", 0, 0, 3, 2, Assets.computerSociety, 100, "Virus Strike", null, 30, Type.electric, Type.water, null, null, Level.Basic, null);
    private SocietyCard artificialInt = new SocietyCard("Artificial Intelligence", 0, 0, 3, 2, Assets.artificialIntel, 150, "Mind Swap", null, 40, Type.electric, Type.water, Type.fighting, null, Level.Basic, null);
    private SocietyCard gamingSociety = new SocietyCard("Gaming Society", 0, 0, 3, 2, Assets.gamingSociety, 80, "Zap Cannon", null, 25, Type.electric, Type.water, Type.fighting, null, Level.Basic, null);
    private SocietyCard physicsSociety = new SocietyCard("Physics Society", 0, 0, 3, 2, Assets.physicsSociety, 120, "Acid Spray", null, 30, Type.electric, null, Type.water, null, Level.Basic, null);
    private SocietyCard engineeringSociety = new SocietyCard("Engineering Society", 0, 0, 3, 2, Assets.engineeringSociety, 90, "Shift Gear", null, 25, Type.electric, null, null, null, Level.Basic, null);
    private SocietyCard roboticsSociety = new SocietyCard("roboticsSociety", 0, 0, 3, 2, Assets.roboticsSociety, 100, "Electric Shock", null, 30, Type.electric, Type.water, Type.fighting, null, Level.Basic, null);
    private SocietyCard boxingSociety = new SocietyCard("Boxing Society", 0, 0, 3, 2, Assets.boxingSociety, 120, "Force Punce", null, 40, Type.fighting, Type.electric, Type.water, null, Level.Basic, null);
    private SocietyCard karateSociety = new SocietyCard("Karate Society", 0, 0, 3, 2, Assets.karateSociety, 100, "Karate Chop", null, 20, Type.fighting, null, null, null, Level.Basic, null);
    private SocietyCard fencingSociety = new SocietyCard("Fencing Society", 0, 0, 3, 2, Assets.fencingSociety, 120, "Low Sweep", null, 25, Type.fighting, null, null, null, Level.Basic, null);
    private SocietyCard judoSociety = new SocietyCard("Judo Society", 0, 0, 3, 2, Assets.judoSociety, 90, "Arm Trust", null, 25, Type.fighting, Type.water, null, null, Level.Basic, null);
    private SocietyCard jujistoSociety = new SocietyCard("jujisto Society", 0, 0, 3, 2, Assets.jujistoSociety, 90, "Shoulder Lock", null, 25, Type.fighting, Type.water, null, null, Level.Basic, null);
    private SocietyCard taekwandoSociety = new SocietyCard("TaekwandoSociety", 0, 0, 3, 2, Assets.taekwando, 60, "Side Kick", null, 25, Type.fighting, Type.water, null, null, Level.Basic, null);
    private SocietyCard rowingSociety = new SocietyCard("Rowing Society", 0, 0, 3, 2, Assets.rowingSociety, 100, "Paddle Pound", null, 20, Type.water, Type.electric, Type.fighting, null, Level.Basic, null);
    private SocietyCard divingSociety = new SocietyCard("Diving Society", 0, 0, 3, 2, Assets.divingSociety, 75, "Dive", null, 10, Type.water, Type.electric, null, null, Level.Basic, null);
    private SocietyCard surfingSociety = new SocietyCard("Surfing Society ", 0, 0, 3, 2, Assets.surfingSociety, 90, "Surf", null, 20, Type.water, null, null, null, Level.Basic, null);
    private SocietyCard swimmingSociety = new SocietyCard("Swimming Society ", 0, 0, 3, 2, Assets.swimmingSociety, 110, "Bubble Bust", null, 30, Type.water, Type.electric, null, null, Level.Basic, null);
    private SocietyCard paddleSociety = new SocietyCard("Paddle Society", 0, 0, 3, 2, Assets.paddle, 65, "Paddle Strike", null, 15, Type.water, null, null, null, Level.Basic, null);
    private SocietyCard sailingSociety = new SocietyCard("SailingSociety", 0, 0, 3, 2, Assets.sailingSociety, 60, "AnchorDrop", null, 10, Type.water, Type.electric, null, null, Level.Basic, null);
    private SocietyCard gardeningSociety = new SocietyCard("Gardening Society", 0, 0, 3, 2, Assets.gardeningSociety, 60, "Magical Leaf", null, 10, Type.earth, null, Type.water, null, Level.Basic, null);
    private SocietyCard geographySociety = new SocietyCard("Geography Society", 0, 0, 3, 2, Assets.geographySociety, 55, "Leaf Storm", null, 15, Type.earth, null, Type.water, null, Level.Basic, null);
    private SocietyCard friendsOfTheEarth = new SocietyCard("Friends of the Earth", 0, 0, 3, 2, Assets.friendsOfEarth, 75, "Cotton Guard", null, 25, Type.earth, Type.fighting, null, null, Level.Basic,null);
    private SocietyCard cavingSociety = new SocietyCard("Caving Society", 0, 0, 3, 2, Assets.cavingSociety, 85, "Drill Run", null, 30, Type.earth, null, Type.fighting, null, Level.Basic, null);
    private SocietyCard environmentalSociety = new SocietyCard("Environmental Society", 0, 0, 3, 2, Assets.environmentalSociety, 70, "Worry Seed", null, 10, Type.earth, null, null, null, Level.Basic, null);
    private SocietyCard greenPeace = new SocietyCard("Green Peace", 0, 0, 3, 2, Assets.greenPeace, 70, "Flower Shield", null, 10, Type.earth, null, null, null, Level.Basic, null);

    //Student behaviour cards
    private StudentBehaviourCard disruptive = new StudentBehaviourCard("Disruptive in class", 0, 0, 3, 2, Assets.disruptve, StudentBehaviourType.stadium, false);
    private StudentBehaviourCard fail = new StudentBehaviourCard("Fail Assignment", 0, 0, 3, 2, Assets.fail, StudentBehaviourType.support, false);
    private StudentBehaviourCard freeEntry = new StudentBehaviourCard("Free Entry", 0, 0, 3, 2, Assets.freeEntry, StudentBehaviourType.support, true);
    private StudentBehaviourCard freeShots = new StudentBehaviourCard("Free Shots", 0, 0, 3, 2, Assets.freeShots, StudentBehaviourType.item, true);
    private StudentBehaviourCard hangover = new StudentBehaviourCard("hangover", 0, 0, 3, 2, Assets.hangover, StudentBehaviourType.stadium, false);
    private StudentBehaviourCard late = new StudentBehaviourCard("Late to class", 0, 0, 3, 2, Assets.late, StudentBehaviourType.item, false);
    private StudentBehaviourCard lecture = new StudentBehaviourCard("Go to a lecture", 0, 0, 3, 2, Assets.lecture, StudentBehaviourType.item, true);
    private StudentBehaviourCard library = new StudentBehaviourCard("Go to library", 0, 0, 3, 2, Assets.library, StudentBehaviourType.stadium, true);
    private StudentBehaviourCard redBull = new StudentBehaviourCard("Drink Red Bull", 0, 0, 3, 2, Assets.redBull, StudentBehaviourType.support, true);
    private StudentBehaviourCard untidy = new StudentBehaviourCard("Untidy Accommodation", 0, 0, 3, 2, Assets.untidy, StudentBehaviourType.stadium, false);
    private StudentBehaviourCard water = new StudentBehaviourCard("Litre of water", 0, 0, 3, 2, Assets.water, StudentBehaviourType.support, true);

    //Engery cards
    private EnergyCard waterEnergy = new EnergyCard("Water", 0, 0, 3, 2, Assets.waterEnergy, Type.water);
    private EnergyCard electricEnergy = new EnergyCard("Electric", 0, 0, 3, 2, Assets.electricEnergy, Type.electric);
    private EnergyCard earthEnergy = new EnergyCard("Earth", 0, 0, 3, 2, Assets.earthEnergy, Type.earth);
    private EnergyCard fightEngery = new EnergyCard("Fight", 0, 0, 3, 2, Assets.fightEngery, Type.fighting);

    //deck of cards
    private ArrayList<Card> deckOfCards = new ArrayList<Card>();
    Deck myDeck = new Deck(deckOfCards);

    Deck playersCards = new Deck(new ArrayList<Card>());
    Deck player2Cards = new Deck(new ArrayList<Card>());

    @Override
    public void init() {
        playButton = new Button(316, 385, 484, 444, Assets.start, Assets.startDown);
        dealButton = new Button(316, 385, 484, 444, Assets.dealButton, Assets.dealButton);

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
        deckOfCards.add(disruptive);
        deckOfCards.add(fail);
        deckOfCards.add(freeEntry);
        deckOfCards.add(freeShots);
        deckOfCards.add(hangover);
        deckOfCards.add(late);
        deckOfCards.add(lecture);
        deckOfCards.add(library);
        deckOfCards.add(redBull);
        deckOfCards.add(untidy);
        deckOfCards.add(water);
        deckOfCards.add(fightEngery);
        deckOfCards.add(waterEnergy);
        deckOfCards.add(electricEnergy);
        deckOfCards.add(earthEnergy);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        //drawing the game board
        g.drawImage(Assets.ssb, 0, 0);
        if (isStart == true) {
           playButton.render(g);
        } else {
            //draw new button called deal
            dealButton.render(g);
            //Now that we have references to the cards that have to be moved, we can change the
            //location of them on the screen. Done here as opposed to update().
            if (currentCardInPlay != null && currentCardInPlay2 != null) {
                super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
            }
        }
        if (playersCards.myDeck.size() > 0) {
            drawCards(g);
        }
    }

    private void drawCards(Painter p) {
        for (int i = 0; i < playersCards.myDeck.size(); i++) {
            p.drawImage(playersCards.myDeck.get(i).getPicture(), 20, 100+ (i +1) * 45 , 125 , 100);
        }

        for (int i = 0; i < player2Cards.myDeck.size(); i++) {
            p.drawImage(player2Cards.myDeck.get(i).getPicture(), 675, (i +1) * 40 , 125 , 100);
        }
        //Attempt to add in the prize cards
        for (int i =0; i< 3; i++) {
            p.drawImage(Assets.cardBack, -65 +(i + 1) * 80, 62, 100, 60);
            p.drawImage(Assets.cardBack, -65 +(i + 1) * 80, 6, 100, 60);
            p.drawImage(Assets.cardBack, 440 +(i +1) * 80, 330, 100, 60);
            p.drawImage(Assets.cardBack, 440 +(i+1) * 80, 380, 100, 60);
        }
    }


    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //If it is the start of the game then this touch event is registered as inital setup.
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
           // if (playButton.isPressed(scaledX, scaledY)) {
                if (isStart == false && dealCards) {
                    //TO DO: move cards currently in the middle of screen to dump pile
                    //move new card of deck into middle of the screen
                    if (!playersCards.myDeck.isEmpty()) {
                        currentCardInPlay = playersCards.myDeck.remove(0);
                    }
                    if (!player2Cards.myDeck.isEmpty()) {
                        currentCardInPlay2 = player2Cards.myDeck.remove(0);
                    }
                    dealCards = false;
                } else {
                    //Button has been pressed
                    isStart = false;
                    for (int i = 0; i < STARTING_CARD_NUMBER; i++) {
                        playersCards.myDeck.add(myDeck.randomCard());
                        player2Cards.myDeck.add(myDeck.randomCard());
                    }
                }
            //}
        }
       // else {
       //     playButton.cancel();

       // }





        return false;
    }


    public void onClick(View v) {
        drawCard();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //seting the view to the activity XML file
//        setContentView(R.layout.activity_main);
//        //setting the draw button to equal an image button set up in the xml file
//        drawButton = (ImageButton) findViewById(R.id.drawButton);
//        cardImage = (ImageView) findViewById(R.id.cardImage);
//        drawButton.setOnClickListener(this);
//
//    }

    //draw a random card from the deck
    private void drawCard() {
        myDeck.randomCard();
    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }
}
