package com.example.societyslam.societyslam.State;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    //Society cards
    private SocietyCard computerSociety = new SocietyCard("Computer Society", 0, 0, 3, 2, Assets.computerSociety, 100, "Virus Strike", 1, 30, Type.electric, Type.water, null, 2, Level.Basic);
    private SocietyCard artificialInt = new SocietyCard("Artificial Intelligence", 0, 0, 3, 2, Assets.artificialIntel, 150, "Mind Swap", 1, 40, Type.electric, Type.water, Type.fighting, 2, Level.Basic);
    private SocietyCard gamingSociety = new SocietyCard("Gaming Society", 0, 0, 3, 2, Assets.gamingSociety, 80, "Zap Cannon", 1, 25, Type.electric, Type.water, Type.fighting, 2, Level.Basic);
    private SocietyCard physicsSociety = new SocietyCard("Physics Society", 0, 0, 3, 2, Assets.physicsSociety, 120, "Acid Spray", 1, 30, Type.electric, null, Type.water, 2, Level.Basic);
    private SocietyCard engineeringSociety = new SocietyCard("Engineering Society", 0, 0, 3, 2, Assets.engineeringSociety, 90, "Shift Gear", 1, 25, Type.electric, null, null, 1, Level.Basic);
    private SocietyCard roboticsSociety = new SocietyCard("roboticsSociety", 0, 0, 3, 2, Assets.roboticsSociety, 100, "Electric Shock", 1, 30, Type.electric, Type.water, Type.fighting, 2, Level.Basic);
    private SocietyCard boxingSociety = new SocietyCard("Boxing Society", 0, 0, 3, 2, Assets.boxingSociety, 120, "Force Punce", 1, 40, Type.fighting, Type.electric, Type.water, 2, Level.Basic);
    private SocietyCard karateSociety = new SocietyCard("Karate Society", 0, 0, 3, 2, Assets.karateSociety, 100, "Karate Chop", 1, 20, Type.fighting, null, null, 2, Level.Basic);
    private SocietyCard fencingSociety = new SocietyCard("Fencing Society", 0, 0, 3, 2, Assets.fencingSociety, 120, "Low Sweep", 1, 25, Type.fighting, null, null, 0, Level.Basic);
    private SocietyCard judoSociety = new SocietyCard("Judo Society", 0, 0, 3, 2, Assets.judoSociety, 90, "Arm Trust", 1, 25, Type.fighting, Type.water, null, 1, Level.Basic);
    private SocietyCard jujistoSociety = new SocietyCard("jujisto Society", 0, 0, 3, 2, Assets.jujistoSociety, 90, "Shoulder Lock", 1, 25, Type.fighting, Type.water, null, 1, Level.Basic);
    private SocietyCard taekwandoSociety = new SocietyCard("TaekwandoSociety", 0, 0, 3, 2, Assets.taekwando, 60, "Side Kick", 1, 25, Type.fighting, Type.water, null, 1, Level.Basic);
    private SocietyCard rowingSociety = new SocietyCard("Rowing Society", 0, 0, 3, 2, Assets.rowingSociety, 100, "Paddle Pound", 1, 20, Type.water, Type.electric, Type.fighting, 1, Level.Basic);
    private SocietyCard divingSociety = new SocietyCard("Diving Society", 0, 0, 3, 2, Assets.divingSociety, 75, "Dive", 1, 10, Type.water, Type.electric, null, 1, Level.Basic);
    private SocietyCard surfingSociety = new SocietyCard("Surfing Society ", 0, 0, 3, 2, Assets.surfingSociety, 90, "Surf", 1, 20, Type.water, null, null, 1, Level.Basic);
    private SocietyCard swimmingSociety = new SocietyCard("Swimming Society ", 0, 0, 3, 2, Assets.swimmingSociety, 110, "Bubble Bust", 1, 30, Type.water, Type.electric, null, 1, Level.Basic);
    private SocietyCard paddleSociety = new SocietyCard("Paddle Society", 0, 0, 3, 2, Assets.paddle, 65, "Paddle Strike", 1, 15, Type.water, null, null, 1, Level.Basic);
    private SocietyCard sailingSociety = new SocietyCard("SailingSociety", 0, 0, 3, 2, Assets.sailingSociety, 60, "AnchorDrop", 1, 10, Type.water, Type.electric, null, 1, Level.Basic);
    private SocietyCard gardeningSociety = new SocietyCard("Gardening Society", 0, 0, 3, 2, Assets.gardeningSociety, 60, "Magical Leaf", 1, 10, Type.earth, null, Type.water, 1, Level.Basic);
    private SocietyCard geographySociety = new SocietyCard("Geography Society", 0, 0, 3, 2, Assets.geographySociety, 55, "Leaf Storm", 1, 15, Type.earth, null, Type.water, 1, Level.Basic);
    private SocietyCard friendsOfTheEarth = new SocietyCard("Friends of the Earth", 0, 0, 3, 2, Assets.friendsOfEarth, 75, "Cotton Guard", 1, 25, Type.earth, Type.fighting, null, 1, Level.Basic);
    private SocietyCard cavingSociety = new SocietyCard("Caving Society", 0, 0, 3, 2, Assets.cavingSociety, 85, "Drill Run", 1, 30, Type.earth, null, Type.fighting, 1, Level.Basic);
    private SocietyCard environmentalSociety = new SocietyCard("Environmental Society", 0, 0, 3, 2, Assets.environmentalSociety, 70, "Worry Seed", 1, 10, Type.earth, null, null, 1, Level.Basic);
    private SocietyCard greenPeace = new SocietyCard("Green Peace", 0, 0, 3, 2, Assets.greenPeace, 70, "Flower Shield", 1, 10, Type.earth, null, null, 1, Level.Basic);

    //Student behaviour cards
    private StudentBehaviourCard disruptive = new StudentBehaviourCard("Disruptive in class", 0, 0, 3, 2, Assets.disruptve, StudentBehaviourType.stadium);
    private StudentBehaviourCard fail = new StudentBehaviourCard("Fail Assignment", 0, 0, 3, 2, Assets.fail, StudentBehaviourType.support);
    private StudentBehaviourCard freeEntry = new StudentBehaviourCard("Free Entry", 0, 0, 3, 2, Assets.freeEntry, StudentBehaviourType.support);
    private StudentBehaviourCard freeShots = new StudentBehaviourCard("Free Shots", 0, 0, 3, 2, Assets.freeShots, StudentBehaviourType.item);
    private StudentBehaviourCard hangover = new StudentBehaviourCard("hangover", 0, 0, 3, 2, Assets.hangover, StudentBehaviourType.stadium);
    private StudentBehaviourCard late = new StudentBehaviourCard("Late to class", 0, 0, 3, 2, Assets.late, StudentBehaviourType.item);
    private StudentBehaviourCard lecture = new StudentBehaviourCard("Go to a lecture", 0, 0, 3, 2, Assets.lecture, StudentBehaviourType.item);
    private StudentBehaviourCard library = new StudentBehaviourCard("Go to library", 0, 0, 3, 2, Assets.library, StudentBehaviourType.stadium);
    private StudentBehaviourCard redBull = new StudentBehaviourCard("Drink Red Bull", 0, 0, 3, 2, Assets.redBull, StudentBehaviourType.support);
    private StudentBehaviourCard untidy = new StudentBehaviourCard("Untity Accommodation", 0, 0, 3, 2, Assets.untidy, StudentBehaviourType.stadium);
    private StudentBehaviourCard water = new StudentBehaviourCard("Litre of water", 0, 0, 3, 2, Assets.water, StudentBehaviourType.support);

    //Engery cards 
    private EnergyCard waterEnergy = new EnergyCard("Water", 0, 0, 3, 2, Assets.waterEnergy, Type.water);
    private EnergyCard electricEnergy = new EnergyCard("Electric", 0, 0, 3, 2, Assets.electricEnergy, Type.electric);
    private EnergyCard earthEnergy = new EnergyCard("Earth", 0, 0, 3, 2, Assets.earthEnergy, Type.earth);
    private EnergyCard fightEngery = new EnergyCard("Fight", 0, 0, 3, 2, Assets.fightEngery, Type.fighting);

    //deck of cards
    private ArrayList<Card> deckOfCards = new ArrayList<Card>();
    Deck myDeck = new Deck(deckOfCards);

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        //drawing the game board
        g.drawImage(Assets.ssb, 0, 0);

        //draws the back of the card and changes its size and position
       g.drawImage(Assets.cardBack, 225, 265, 90, 95);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    public void onClick(View v) {
        drawCard();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //seting the view to the activity XML file
        setContentView(R.layout.activity_main);
        //setting the draw button to equal an image button set up in the xml file
        drawButton = (ImageButton) findViewById(R.id.drawButton);
        cardImage = (ImageView) findViewById(R.id.cardImage);
        drawButton.setOnClickListener(this);

        //add cards into the deck
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

