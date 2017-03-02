package com.example.societyslam.societyslam.State;


import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.GameObjects.Coin;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.Util.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.EnergyCard;
import com.example.societyslam.societyslam.GameObjects.Level;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourType;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Painter;
import java.util.ArrayList;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class PlayState extends State {

    private ImageButton drawButton;
    private ImageView cardImage;
    private boolean isStart = true;
    private boolean dealCards = true;
    private boolean retreatError;
    private SocietyCard currentCardInPlay, currentCardInPlay2;
    private ArrayList<EnergyCard> energyCards = new ArrayList<EnergyCard>();

   // private boolean drawCards = false;


    private static int STARTING_CARD_NUMBER = 5;

    private Button playButton;
    private Button dealButton;
    private Button continueButton;
    boolean isMenu;
    boolean attackPlayer1;
    boolean attackPlayer2;
    private Rect playRect;
    int dealCardSound =1;
    int cardMove =1;

    private Button attackButton;
    private Button retreatButton;
    private Button evolveButton;
    private Button useStudentBehaviourCardButton;
    private Button pauseButton, restartButton, resumeButton, quitButton, instructionsButton;
    private boolean isPause = false;

    //Society cards
    private SocietyCard computerSociety = new SocietyCard("Computer Society", 0, 0, 3, 2, Assets.computerSociety, 100, "Virus Strike", energyCards, 30, Type.electric, Type.water, null, energyCards, Level.Basic, energyCards);
    private SocietyCard artificialInt = new SocietyCard("Artificial Intelligence", 0, 0, 3, 2, Assets.artificialIntel, 150, "Mind Swap", energyCards, 40, Type.electric, Type.water, Type.fighting, energyCards, Level.Basic, energyCards);
    private SocietyCard gamingSociety = new SocietyCard("Gaming Society", 0, 0, 3, 2, Assets.gamingSociety, 80, "Zap Cannon",energyCards, 25, Type.electric, Type.water, Type.fighting, energyCards, Level.Basic, energyCards);
    private SocietyCard physicsSociety = new SocietyCard("Physics Society", 0, 0, 3, 2, Assets.physicsSociety, 120, "Acid Spray", energyCards, 30, Type.electric, null, Type.water, energyCards, Level.Basic, energyCards);
    private SocietyCard engineeringSociety = new SocietyCard("Engineering Society", 0, 0, 3, 2, Assets.engineeringSociety, 90, "Shift Gear", energyCards, 25, Type.electric, null, null, energyCards, Level.Basic, energyCards);
    private SocietyCard roboticsSociety = new SocietyCard("roboticsSociety", 0, 0, 3, 2, Assets.roboticsSociety, 100, "Electric Shock", energyCards, 30, Type.electric, Type.water, Type.fighting, energyCards, Level.Basic, energyCards);
    private SocietyCard boxingSociety = new SocietyCard("Boxing Society", 0, 0, 3, 2, Assets.boxingSociety, 120, "Force Punce", energyCards, 40, Type.fighting, Type.electric, Type.water, energyCards, Level.Basic, energyCards);
    private SocietyCard karateSociety = new SocietyCard("Karate Society", 0, 0, 3, 2, Assets.karateSociety, 100, "Karate Chop", energyCards, 20, Type.fighting, null, null, energyCards, Level.Basic, energyCards);
    private SocietyCard fencingSociety = new SocietyCard("Fencing Society", 0, 0, 3, 2, Assets.fencingSociety, 120, "Low Sweep", energyCards, 25, Type.fighting, null, null, energyCards, Level.Basic, energyCards);
    private SocietyCard judoSociety = new SocietyCard("Judo Society", 0, 0, 3, 2, Assets.judoSociety, 90, "Arm Trust", energyCards, 25, Type.fighting, Type.water, null, energyCards, Level.Basic, energyCards);
    private SocietyCard jujistoSociety = new SocietyCard("jujisto Society", 0, 0, 3, 2, Assets.jujistoSociety, 90, "Shoulder Lock", energyCards, 25, Type.fighting, Type.water, null,energyCards, Level.Basic, energyCards);
    private SocietyCard taekwandoSociety = new SocietyCard("TaekwandoSociety", 0, 0, 3, 2, Assets.taekwando, 60, "Side Kick", energyCards, 25, Type.fighting, Type.water, null, energyCards, Level.Basic, energyCards);
    private SocietyCard rowingSociety = new SocietyCard("Rowing Society", 0, 0, 3, 2, Assets.rowingSociety, 100, "Paddle Pound", energyCards, 20, Type.water, Type.electric, Type.fighting, energyCards, Level.Basic, energyCards);
    private SocietyCard divingSociety = new SocietyCard("Diving Society", 0, 0, 3, 2, Assets.divingSociety, 75, "Dive", energyCards, 10, Type.water, Type.electric, null, energyCards, Level.Basic, energyCards);
    private SocietyCard surfingSociety = new SocietyCard("Surfing Society ", 0, 0, 3, 2, Assets.surfingSociety, 90, "Surf", energyCards, 20, Type.water, null, null, energyCards, Level.Basic, energyCards);
    private SocietyCard swimmingSociety = new SocietyCard("Swimming Society ", 0, 0, 3, 2, Assets.swimmingSociety, 110, "Bubble Bust", energyCards, 30, Type.water, Type.electric, null, energyCards, Level.Basic, energyCards);
    private SocietyCard paddleSociety = new SocietyCard("Paddle Society", 0, 0, 3, 2, Assets.paddle, 65, "Paddle Strike", energyCards, 15, Type.water, null, null, energyCards, Level.Basic, energyCards);
    private SocietyCard sailingSociety = new SocietyCard("SailingSociety", 0, 0, 3, 2, Assets.sailingSociety, 60, "AnchorDrop", energyCards, 10, Type.water, Type.electric, null,energyCards, Level.Basic, energyCards);
    private SocietyCard gardeningSociety = new SocietyCard("Gardening Society", 0, 0, 3, 2, Assets.gardeningSociety, 60, "Magical Leaf", energyCards, 10, Type.earth, null, Type.water, energyCards, Level.Basic, energyCards);
    private SocietyCard geographySociety = new SocietyCard("Geography Society", 0, 0, 3, 2, Assets.geographySociety, 55, "Leaf Storm", energyCards, 15, Type.earth, null, Type.water, energyCards, Level.Basic, energyCards);
    private SocietyCard friendsOfTheEarth = new SocietyCard("Friends of the Earth", 0, 0, 3, 2, Assets.friendsOfEarth, 75, "Cotton Guard", energyCards, 25, Type.earth, Type.fighting, null,energyCards, Level.Basic,energyCards);
    private SocietyCard cavingSociety = new SocietyCard("Caving Society", 0, 0, 3, 2, Assets.cavingSociety, 85, "Drill Run", energyCards, 30, Type.earth, null, Type.fighting, energyCards, Level.Basic, energyCards);
    private SocietyCard environmentalSociety = new SocietyCard("Environmental Society", 0, 0, 3, 2, Assets.environmentalSociety, 70, "Worry Seed", energyCards, 10, Type.earth, null, null, energyCards, Level.Basic, energyCards);
    private SocietyCard greenPeace = new SocietyCard("Green Peace", 0, 0, 3, 2, Assets.greenPeace, 70, "Flower Shield", energyCards, 10, Type.earth, null, null, energyCards, Level.Basic, energyCards);

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
    private ArrayList<SocietyCard> deckOfCards = new ArrayList<SocietyCard>();
    Deck myDeck = new Deck(deckOfCards);

    ArrayList<SocietyCard> playersCards = new ArrayList<SocietyCard>();
    ArrayList<SocietyCard> player2Cards = new ArrayList<SocietyCard>();

    ArrayList<StudentBehaviourCard> prizeCards1 = new ArrayList<StudentBehaviourCard>();
    ArrayList<StudentBehaviourCard> prizeCards2 = new ArrayList<StudentBehaviourCard>();


    Player player1 = new Player(myDeck,currentCardInPlay,playersCards,prizeCards1, CoinTossState.getIsPlayer1Turn());
    Player player2 = new Player(myDeck, currentCardInPlay2, player2Cards, prizeCards2, CoinTossState.getIsPlayer2Turn());



    @Override
    public void init() {

        //Set the background music to keep playing
       Assets.playBackground(Assets.backgroundMusicID);

        playButton = new Button(336, 385, 504, 444, Assets.start, Assets.startDown);
        dealButton = new Button(336, 385, 504, 444, Assets.dealButton, Assets.dealButton);
        continueButton = new Button(336, 385, 504, 444, Assets.continueButton, Assets.continueButton);
        attackButton = new Button(316, 115, 484, 155, Assets.attackButton, Assets.attackButton);
        retreatButton = new Button(316, 175, 484, 220, Assets.retreatButton, Assets.retreatButton);
        evolveButton = new Button(316, 235, 484, 285, Assets.evolveButton, Assets.evolveButton);
        useStudentBehaviourCardButton = new Button(316, 295, 484, 350, Assets.societyCardButton, Assets.societyCardButton);
        pauseButton = new Button(266,385,326,444,Assets.pause, Assets.pause);
        resumeButton = new Button(316, 115, 484, 155, Assets.resume, Assets.resume);
        restartButton = new Button(316, 175, 484, 220, Assets.restart, Assets.restart);
        instructionsButton = new Button(316, 235, 484, 285, Assets.instructions, Assets.instructions);
        quitButton = new Button(316, 295, 484, 350, Assets.quit, Assets.quit);

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
       // deckOfCards.add(disruptive);
       // deckOfCards.add(fail);
        //deckOfCards.add(freeEntry);
        //deckOfCards.add(freeShots);
        //deckOfCards.add(hangover);
        //deckOfCards.add(late);
        //deckOfCards.add(lecture);
        //deckOfCards.add(library);
        //deckOfCards.add(redBull);
        //deckOfCards.add(untidy);
        //deckOfCards.add(water);
        //deckOfCards.add(fightEngery);
        //deckOfCards.add(waterEnergy);
        //deckOfCards.add(electricEnergy);
        //deckOfCards.add(earthEnergy);
        energyCards.add(waterEnergy);
        int i =0;
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        //drawing the game board
        g.drawImage(Assets.ssb, 0, 0);

        //Displaying both of the players on the board
        g.setFont(Typeface.DEFAULT_BOLD, 25);
        g.drawString("Player 1", 303, 20);
        g.drawString("Player 2", 425,20);
        pauseButton.render(g);
        if (isStart == true) {
           playButton.render(g);

        } else {
            //draw new button called deal
            if (dealCards) {
                dealButton.render(g);
                //Play sound of the cards being delt only once
               while(dealCardSound ==1) {
                  dealCardSound--;
                    Assets.playSound(Assets.dealingCardsID);
                }
            } else {
                continueButton.render(g);
            }
            //Now that we have references to the cards that have to be moved, we can change the
            //location of them on the screen. Done here as opposed to update();
            if (currentCardInPlay != null && currentCardInPlay2 != null) {
                while (cardMove == 1) {
                    cardMove--;
                    Assets.playSound(Assets.oneCardID);
                }
            }
            // has to be seperate from above as when retreat method is called and it is player 2s turn, both cards are removed
            //from the middle as the above if statement is not satisfied.
                if(currentCardInPlay != null){
                super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                //Set HP levels of the active cards to the players score on the screen
                g.drawString("  "+ player1.getActiveCard().getHp(), 320,40 );
            }
            if (currentCardInPlay2 != null) {
                super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                //Set HP levels of the active cards to the players score on the screen
                g.drawString("  "+ player2.getActiveCard().getHp(),440,40);
            }

        }
        if (player1.getMyCards().getMyDeck().size() > 0) {
            drawCards(g);
        }

        //If player one clicks attack, display their attack and how many points player2 loses on the board
        if(attackPlayer1){
            g.setFont(Typeface.DEFAULT_BOLD,22);
            g.drawString("You attacked with "+ player1.getActiveCard().getAttackName(), 270, 60);
            g.drawString("minus "+ player1.getActiveCard().getAttackStrength()+ " points player2", 315,80);
        }
        //If player two attacks, display their attack and how many points player1 loses
        if(attackPlayer2){
            g.drawString("You attacked with "+ player2.getActiveCard().getAttackName(), 270, 60);
            g.drawString("minus "+ player2.getActiveCard().getAttackStrength()+ " points player2", 315,80);
        }

        if (isMenu) {
            g.drawImage(Assets.menubg, 100, 50);
            attackButton.render(g);
            retreatButton.render(g);
            evolveButton.render(g);
            useStudentBehaviourCardButton.render(g);
        }
        if(retreatError){
            super.getPainter().drawImage(Assets.retreatError, 75, 85, 685 , 65);
        }
        if(isPause){
            super.getPainter().drawImage(Assets.pauseMenu,100,50);
            resumeButton.render(g);
            restartButton.render(g);
            quitButton.render(g);
            instructionsButton.render(g);
        }
    }

    private void drawCards(Painter p) {

        for (int i = 0; i < player1.getBench().size(); i++) {
            p.drawImage(player1.getBench().get(i).getPicture(), 20, 100+ (i +1) * 45 , 125 , 100);
        }

        for (int i = 0; i < player2.getBench().size(); i++) {
            p.drawImage(player2.getBench().get(i).getPicture(), 675, (i +1) * 40 , 125 , 100);
        }
        //prize cards added to board
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

        if(e.getAction() == MotionEvent.ACTION_DOWN) {

            continueButton.onTouchDown(scaledX, scaledY);
            attackButton.onTouchDown(scaledX, scaledY);
            retreatButton.onTouchDown(scaledX, scaledY);
            evolveButton.onTouchDown(scaledX, scaledY);
            useStudentBehaviourCardButton.onTouchDown(scaledX, scaledY);

            if (!isStart && !dealCards) {
                if (continueButton.isPressed(scaledX, scaledY)) {
                    retreatError = false;
                    isMenu = true;
                    attackPlayer1 = false;
                    attackPlayer2=false;
                    continueButton.cancel();

                } else {
                    continueButton.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (attackButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    attackButton.cancel();
                    if (player1.isMyTurn()) {
                        player1.attack(player2);
                        attackPlayer1 = true;
                    } else {
                        player2.attack(player1);
                        attackPlayer2 = true;
                    }
                } else {
                    attackButton.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (retreatButton.isPressed(scaledX, scaledY) && isMenu) {
                    //isMenu = false;
                    //retreatButton.cancel();
                    if (player1.isMyTurn()) {
                        if(currentCardInPlay != null){
                           // move the current card back to bench
                            player1.getActiveCard().retreat(player1.getActiveCard().getEnergyCards(), player1);
                      // remove previous active card from board
                            currentCardInPlay = null;
                            retreatError = false;
                            isMenu = false;
                        }else {
                            // Validation for if the user selects retreat option and no cards are in play
                            System.out.println("Sorry, there must be a card in play in order to retreat");
                            //display to the user that there must be a card in play in order to retreat
                            retreatError = true;
                            //remove menu screen
                            isMenu = false;
                        }
                    } else {

                        if(currentCardInPlay2 != null){
                            player2.getActiveCard().retreat(player2.getActiveCard().getEnergyCards(), player2);
                            currentCardInPlay2 = null;
                            isMenu = false;
                            retreatError = false;
                        }else{
                            // Validation for if the user selects retreat option and no cards are in play
                            System.out.println("Sorry, there must be a card in play in order to retreat");
                            //remove menu screen
                            isMenu = false;
                            //display to the user that there must be a card in play in order to retreat
                            retreatError = true;
                            System.out.println("ayo");
                        }
                    }
                } else {
                    retreatButton.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (evolveButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    evolveButton.cancel();
                    if (player1.isMyTurn()) {
                        player1.getActiveCard().evolve();
                    } else {
                        player2.getActiveCard().evolve();
                    }
                } else {
                    evolveButton.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (useStudentBehaviourCardButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    useStudentBehaviourCardButton.cancel();
                    if (player1.isMyTurn()) {
                        player1.useStudentBehaviourCard(player1.getPrizeCards().get(0), player2);

                    } else {
                        player2.useStudentBehaviourCard(player2.getPrizeCards().get(0), player1);
                    }

                } else {
                    useStudentBehaviourCardButton.cancel();
                }

            }
        }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {

            dealButton.onTouchDown(scaledX,scaledY);
            playButton.onTouchDown(scaledX, scaledY);
            if(dealButton.isPressed(scaledX,scaledY)) {
                dealButton.cancel();
                if (!isStart && dealCards) {
                    //TO DO: move cards currently in the middle of screen to dump pile
                    //move new card of deck into middle of the screen
                    if (!player1.getMyCards().getMyDeck().isEmpty()) {
                        player1.setActiveCard(player1.getBench().remove(0));
                        currentCardInPlay = player1.getActiveCard();


                    }
                    if (!player2.getMyCards().getMyDeck().isEmpty()) {
                        player2.setActiveCard(player2.getBench().remove(0));
                        currentCardInPlay2 = player2.getActiveCard();



                    }
                    dealCards = false;

                } else {

                    if (playButton.isPressed(scaledX, scaledY)) {
                        playButton.cancel();
                        if (dealCards) {
                            for (int i = 0; i < STARTING_CARD_NUMBER; i++) {
                                player1.getBench().add(myDeck.randomCard());
                                player2.getBench().add(myDeck.randomCard());
                                //start game button now shows

                                isStart = false;
                            }
                        }

                    } else {
                        playButton.cancel();
                    }

                }
            } else {
                dealButton.cancel();
            }


        }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            pauseButton.onTouchDown(scaledX, scaledY);
            restartButton.onTouchDown(scaledX, scaledY);
            resumeButton.onTouchDown(scaledX, scaledY);
            quitButton.onTouchDown(scaledX, scaledY);
            instructionsButton.onTouchDown(scaledX, scaledY);
            if (pauseButton.isPressed(scaledX, scaledY)) {
                isPause = true;
                pauseButton.cancel();

            } else {
                pauseButton.cancel();
            }
            if (resumeButton.isPressed(scaledX, scaledY)  && isPause) {
                isPause=false;
            }else{
                resumeButton.cancel();
            }
            if (restartButton.isPressed(scaledX, scaledY)  && isPause) {
                isPause=false;
                setCurrentState(new CoinTossState());
            }else{
                restartButton.cancel();
            }
            if (instructionsButton.isPressed(scaledX, scaledY) && isPause) {
                isPause=false;

            }else{
                instructionsButton.cancel();
            }
            if (quitButton.isPressed(scaledX, scaledY)  && isPause) {
                isPause=false;
                setCurrentState(new MenuState());
            }else{
                quitButton.cancel();
            }
        }
        return false;
    }


//    public void onClick(View v) {
//        drawCard();
//    }
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        //seting the view to the activity XML file
////        setContentView(R.layout.activity_main);
////        //setting the draw button to equal an image button set up in the xml file
////        drawButton = (ImageButton) findViewById(R.id.drawButton);
////        cardImage = (ImageView) findViewById(R.id.cardImage);
////        drawButton.setOnClickListener(this);
////
////    }
//
//    //draw a random card from the deck
//    private void drawCard() {
//        myDeck.randomCard();
//    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }



}

