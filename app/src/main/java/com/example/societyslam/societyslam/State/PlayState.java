package com.example.societyslam.societyslam.State;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.Util.Button;
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
import java.util.Random;

/**
 * The playstate acts as the games play screen for society slam, it displays the board and the cards for the game,
 * it extends state
 */

public class PlayState extends State {

    private boolean isStart = true;
    private boolean dealCards = true;
    private boolean retreatError;
    private SocietyCard currentCardInPlay, currentCardInPlay2;
    private ArrayList<EnergyCard> energyCards = new ArrayList<EnergyCard>();
    private ArrayList<StudentBehaviourCard> prizeCardDeck1 = new ArrayList<StudentBehaviourCard>();
    private ArrayList<StudentBehaviourCard> prizeCardDeck2 = new ArrayList<StudentBehaviourCard>();
    private static int STARTING_CARD_NUMBER = 5;
    private Button playButton;
    private Button dealButton;
    private Button continueButton;
    boolean isMenu;
    boolean attackPlayer1;
    boolean attackPlayer2;
    boolean evolvePlayer1;
    boolean evolvePlayer2;
    int dealCardSound =1;
    int cardMove =1;
    int player1Score;
    int player2Score;
    static int player1Wins=0;
    static int player2Wins=0;
    boolean displayWin1 = false;
    boolean displayWin2 = false;
    private Button attackButton;
    private Button retreatButton;
    private Button evolveButton;
    private Button useStudentBehaviourCardButton;
    private Button pauseButton, restartButton, resumeButton, quitButton, instructionsButton;
    private boolean isPause = false;
    private boolean isChooseCard =false;
    private Button useCardButton, cancelButton;
    private int positionOfCardChosen;
    private Button p1Card0,p1Card1, p1Card2,p1Card3, p1Card4, p2Card0, p2Card1,p2Card2, p2Card3, p2Card4, currentCard1Button, currentCard2Button;
    private boolean areCardsDrawn = false;
    private boolean isCardRetreated = false;

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

    //Student behaviour cards
    private StudentBehaviourCard disruptive1 = new StudentBehaviourCard("Disruptive in class", 0, 0, 3, 2, Assets.disruptve, StudentBehaviourType.stadium, false);
    private StudentBehaviourCard fail1 = new StudentBehaviourCard("Fail Assignment", 0, 0, 3, 2, Assets.fail, StudentBehaviourType.support, false);
    private StudentBehaviourCard freeEntry1 = new StudentBehaviourCard("Free Entry", 0, 0, 3, 2, Assets.freeEntry, StudentBehaviourType.support, true);
    private StudentBehaviourCard freeShots1 = new StudentBehaviourCard("Free Shots", 0, 0, 3, 2, Assets.freeShots, StudentBehaviourType.item, true);
    private StudentBehaviourCard hangover1 = new StudentBehaviourCard("hangover", 0, 0, 3, 2, Assets.hangover, StudentBehaviourType.stadium, false);
    private StudentBehaviourCard late1 = new StudentBehaviourCard("Late to class", 0, 0, 3, 2, Assets.late, StudentBehaviourType.item, false);
    private StudentBehaviourCard lecture1 = new StudentBehaviourCard("Go to a lecture", 0, 0, 3, 2, Assets.lecture, StudentBehaviourType.item, true);
    private StudentBehaviourCard library1 = new StudentBehaviourCard("Go to library", 0, 0, 3, 2, Assets.library, StudentBehaviourType.stadium, true);
    private StudentBehaviourCard redBull1 = new StudentBehaviourCard("Drink Red Bull", 0, 0, 3, 2, Assets.redBull, StudentBehaviourType.support, true);
    private StudentBehaviourCard untidy1 = new StudentBehaviourCard("Untidy Accommodation", 0, 0, 3, 2, Assets.untidy, StudentBehaviourType.stadium, false);
    private StudentBehaviourCard water1 = new StudentBehaviourCard("Litre of water", 0, 0, 3, 2, Assets.water, StudentBehaviourType.support, true);

    //Engery cards
    private EnergyCard waterEnergy = new EnergyCard("Water", 0, 0, 3, 2, Assets.waterEnergy, Type.water);
    private EnergyCard electricEnergy = new EnergyCard("Electric", 0, 0, 3, 2, Assets.electricEnergy, Type.electric);
    private EnergyCard earthEnergy = new EnergyCard("Earth", 0, 0, 3, 2, Assets.earthEnergy, Type.earth);
    private EnergyCard fightEngery = new EnergyCard("Fight", 0, 0, 3, 2, Assets.fightEngery, Type.fighting);

    //Deck of cards
    private ArrayList<SocietyCard> deckOfCards = new ArrayList<SocietyCard>();
    Deck myDeck = new Deck(deckOfCards);

    //Separate cards for both players
    ArrayList<SocietyCard> playersCards = new ArrayList<SocietyCard>();
    ArrayList<SocietyCard> player2Cards = new ArrayList<SocietyCard>();

    //players
    private Player player1 = new Player(myDeck,currentCardInPlay,playersCards,prizeCardDeck1, CoinTossState.getIsPlayer1Turn());
    private Player player2 = new Player(myDeck, currentCardInPlay2, player2Cards, prizeCardDeck2, CoinTossState.getIsPlayer2Turn());

    public static boolean player1Winner, player2Winner;

    //The names of the players
    private String player1Name;
    private String player2Name;

    /**
     * This method will be called when we transition into the  playState.
     * It initializes any game objects that will be used throughout the playState
     * e.g. buttons, cards, decks and players
     */
    @Override
    public void init() {
        //Set the players names
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        player1Name = sharedPreferences.getString("player1name", "");
        player2Name = sharedPreferences.getString("player2name", "");

        //Set the background music to keep playing
        Assets.playBackground(Assets.backgroundMusicID);

        //initializing buttons
        playButton = new Button(336, 385, 504, 444, Assets.start);
        dealButton = new Button(336, 385, 504, 444, Assets.dealButton);
        continueButton = new Button(336, 385, 504, 444, Assets.continueButton);
        attackButton = new Button(316, 125, 484, 165, Assets.attackButton);
        retreatButton = new Button(316, 185, 484, 225, Assets.retreatButton);
        evolveButton = new Button(316, 245, 484, 285, Assets.evolveButton);
        useStudentBehaviourCardButton = new Button(316, 305, 484, 350, Assets.societyCardButton);
        pauseButton = new Button(266,385,326,444,Assets.pause);
        resumeButton = new Button(316, 125, 484, 165, Assets.resume);
        restartButton = new Button(316, 185, 484, 225, Assets.restart);
        instructionsButton = new Button(316, 245, 484, 285, Assets.instructions);
        quitButton = new Button(316, 305, 484, 350, Assets.quit);
        useCardButton = new Button(456,165, 634, 205, Assets.useCard);
        cancelButton = new Button(456,225,634,265,Assets.cancel);

        // placing buttons in the position of where player 1's cards are on the bench so they can choose which card to attack with
        p1Card0 = new Button(20, 145, 125, 190, null);
        p1Card1 = new Button(20, 190, 125, 235, null);
        p1Card2 = new Button(20, 235, 125, 280, null);
        p1Card3 = new Button(20, 280, 125, 325, null);
        p1Card4 = new Button(20, 325, 165, 425, null);

        // placing buttons in the position of where player 2's cards are on the bench so they can choose which card to attack with
        p2Card0 = new Button(675, 40, 780 , 80, null);
        p2Card1 = new Button(675, 80, 780 , 120, null);
        p2Card2 = new Button(675, 120, 780 , 160, null);
        p2Card3 = new Button(675, 160, 780 , 200, null);
        p2Card4 = new Button(675, 200, 780 , 300, null);

        //placing buttons in the position of where current card in play is
        currentCard1Button = new Button(280, 175, 395, 275,null);
        currentCard2Button = new Button(410, 175, 525 , 275,null);

        //Adding cards to the main deck
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

        //Adding cards to player 1's deck
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
        setUpPrizeCards();
        int i =0;
    }

    /**
     *This method will be called by the game loop on every frame
     * @param delta - time since last position
     */
    @Override
    public void update(float delta) {

    }

    /**
     * The render method draws to the screen
     * @param g- The painter
     */
    @Override
    public void render(Painter g) {
        //drawing the game board
        g.drawImage(Assets.ssb, 0, 0);
        //Displaying both of the players on the board
        g.setFont(Typeface.DEFAULT_BOLD, 25);
        g.drawString(player1Name, 303, 20);
        g.drawString(player2Name, 425,20);

        //Display how many rounds each player has won throughout the game
        g.setFont(Typeface.DEFAULT, 20);
        g.drawString("Rounds won ", 150,150);
        g.drawString(player1Wins + " ", 200,175);
        g.drawString("Rounds won ", 535,300);
        g.drawString(" "+ player2Wins, 585,317);

        pauseButton.render(g);
        // when card has been retreated - render buttons
        if(isCardRetreated) {
            p1Card0.render(g);
            p1Card1.render(g);
            p1Card2.render(g);
            p1Card3.render(g);
            p1Card4.render(g);
            p2Card0.render(g);
            p2Card1.render(g);
            p2Card2.render(g);
            p2Card3.render(g);
            p2Card4.render(g);
        }

        if (isStart == true) {
           playButton.render(g);
        } else {
            //draw new button called deal
            if (dealCards) {
                dealButton.render(g);
                //Play sound of the cards being dealt only once
               while(dealCardSound ==1) {
                  dealCardSound--;
                    Assets.playSound(Assets.dealingCardsID);
                }
            } else {
                //continueButton.render(g);
          }
            //Now that we have references to the cards that have to be moved, we can change the
            //location of them on the screen. Done here as opposed to update();
            if (currentCardInPlay != null && currentCardInPlay2 != null) {
                while (cardMove == 1) {
                    cardMove--;
                    Assets.playSound(Assets.oneCardID);
                }
                currentCard1Button.render(g);
                currentCard2Button.render(g);
                if(player1.isMyTurn()){
                    super.getPainter().drawImage(Assets.yourTurn, 280,140,117,130);
                }else{
                    super.getPainter().drawImage(Assets.yourTurn, 410, 140, 125 , 130);
                }
            }
            // has to be separate from above as when retreat method is called and it is player 2s turn, both cards are removed
            //from the middle as the above if statement is not satisfied.
                if(currentCardInPlay != null){
                super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);

                    // TEST TO FIND OUT THE TYPE OF THE ACTIVE SOCIETY CARD WHEN MOVED TO SCREEN
                    //g.drawString(" " + player1.getActiveCard().getType(), 320, 40);

                //Set HP levels of the active cards to the players score on the screen
                    player1Score = player1.getActiveCard().getHp();
                    g.setFont(Typeface.DEFAULT_BOLD, 25);
                g.drawString("  "+ player1Score, 320,40 );

                    //If player ones score falls below zero tell them that player 2 has won this round
                    //change the card in the middle for player1 and give player 2 a prize card
                    if(player1Score <=0){
                        g.setFont(Typeface.DEFAULT, 25);
                        checkPrizeCardState(player2, player1);
                        player2Wins++;
                        displayWin1 = true;
                        if(player2Wins >= player2.getPrizeCards().size()) {
                            setPlayer2Winner(true);
                            setCurrentState(new GameOverState(player2Wins, player2Name));
                        }
                    }
                    //will attach matching energyCard to the activeCard
                    if (player1.getActiveCard().getType() == Type.earth ){
                        super.getPainter().drawImage(Assets.earthEnergy, 240,160,125,100);
                        super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                    }//end of new if statement
                    if (player1.getActiveCard().getType() == Type.electric ){
                        super.getPainter().drawImage(Assets.electricEnergy, 240,160,125,100);
                        super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                    }//end of new if statement
                    if (player1.getActiveCard().getType() == Type.water ){
                        super.getPainter().drawImage(Assets.waterEnergy, 240,160,125,100);
                        super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                    }//end of new if statement
                    if (player1.getActiveCard().getType() == Type.fighting ){
                        super.getPainter().drawImage(Assets.fightEngery, 240,160,125,100);
                        super.getPainter().drawImage(currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                    }//end of new if statement
            }
            if (currentCardInPlay2 != null) {
                super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);

                //Set HP levels of the active cards to the players score on the screen
                player2Score = player2.getActiveCard().getHp();
                g.setFont(Typeface.DEFAULT_BOLD, 25);
                g.drawString("  "+ player2Score,440,40);

                //If player 2 score falls below zero tell them that player 1 has won this round
                //give player 2 a new card in the middle and give player 1 a prize card
                if(player2Score <=0){
                    g.setFont(Typeface.DEFAULT, 25);
                    checkPrizeCardState(player1, player2);
                    player1Wins++;
                    displayWin2 = true;
                    if(player1Wins >= player1.getPrizeCards().size()){
                        setPlayer1Winner(true);
                        setCurrentState(new GameOverState(player1Wins, player1Name));
                    }
                }

                //Attach matching energyCard to player2 active society Card
                if (player2.getActiveCard().getType() == Type.earth ){
                    super.getPainter().drawImage(Assets.earthEnergy, 440,160,125,100);
                    super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
                if (player2.getActiveCard().getType() == Type.electric ){
                    super.getPainter().drawImage(Assets.electricEnergy, 440,160,125,100);
                    super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
                if (player2.getActiveCard().getType() == Type.water ){
                    super.getPainter().drawImage(Assets.waterEnergy, 440,160,125,100);
                    super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
                if (player2.getActiveCard().getType() == Type.fighting ){
                    super.getPainter().drawImage(Assets.fightEngery, 440,160,125,100);
                    super.getPainter().drawImage(currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
            }
        }
        if (player1.getMyCards().getMyDeck().size() > 0) {
            drawCards(g);

        }

        //If player one clicks attack, display their attack and how many points player2 loses on the board
        if(attackPlayer1){
            g.setFont(Typeface.DEFAULT_BOLD,22);
            g.drawString("You attacked with "+ player1.getActiveCard().getAttackName(), 270, 60);
            g.drawString("minus "+ player1.getActiveCard().getAttackStrength()+ " points " + player2Name, 315,80);
        }
        //If player two attacks, display their attack and how many points player1 loses
        if(attackPlayer2){
            g.setFont(Typeface.DEFAULT_BOLD,22);
            g.drawString("You attacked with "+ player2.getActiveCard().getAttackName(), 270, 60);
            g.drawString("minus "+ player2.getActiveCard().getAttackStrength()+ " points " + player1Name, 315,80);
        }


        if(evolvePlayer1) {
            g.drawString(player1.getActiveCard().getName() + " evolved to " + player1.getActiveCard().getLevel(), 270, 60);
        }

        if(evolvePlayer2) {
            g.drawString(player2.getActiveCard().getName() + " evolved to " + player2.getActiveCard().getLevel(), 270, 60);
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
        //If player ones score falls below zero
        if(displayWin1){
            g.drawString(player2Name + " wins this round!", 300,115);
            g.drawString(player1Name + " you have been given another card ", 245, 130);
        }
        //If player twos score fall below zero
        if(displayWin2){
            g.drawString(player1Name + " wins this round!", 300,115);
            g.drawString(player2Name + " you have been given another card ", 245, 130);
        }
        // screen which pops up to allow player to look at cards in more detail before choosing which one to play with
        if(isChooseCard){
            super.getPainter().drawImage(Assets.chooseCardMenu,75, 95, 685 , 235);
            useCardButton.render(g);
            cancelButton.render(g);
            if(player1.isMyTurn()) {
                super.getPainter().drawImage(player1.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
            }else{
                super.getPainter().drawImage(player2.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
            }
        }
    }

    /**
     * This method gives both players cards from their decks and places them on their bench
     * @param p- the painter
     */
    private void drawCards(Painter p) {
        //gives player 1 society cards on their bench
        for (int i = 0; i < player1.getBench().size(); i++) {
            p.drawImage(player1.getBench().get(i).getPicture(), 20, 100+ (i +1) * 45 , 125 , 100);
        }

        //gives player 2 society cards in their bench
        for (int i = 0; i < player2.getBench().size(); i++) {
            p.drawImage(player2.getBench().get(i).getPicture(), 675, (i +1) * 40 , 125 , 100);
        }

        //draw player 1 prize cards
        for (int i =0; i<player1.getPrizeCards().size(); i++) {
            StudentBehaviourCard card = player1.getPrizeCards().get(i);
            if (i > 2) {
                p.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), -300 +(i + 1) * 80, 62, 100, 60);
            } else {
                p.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), -65 +(i + 1) * 80, 6, 100, 60);
            }
        }
        // draw player 2 prize cards
        for (int i =0; i< player2.getPrizeCards().size(); i++) {
            StudentBehaviourCard card = player2.getPrizeCards().get(i);
            if (i > 2) {
                p.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), 200 +(i +1) * 80, 330, 100, 60);
            } else {
                p.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), 440 +(i+1) * 80, 380, 100, 60);

            }
        }
    }

    /**
     * This method checks where the screen has be touched
     * @param e - motion event(object used to report movement)
     * @param scaledX- the scaled x coordinate
     * @param scaledY- the scaled y coordinate
     * @return false
     */
    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //If it is the start of the game then this touch event is registered as inital setup.
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            //current cards in plays
            currentCard1Button.onTouchDown(scaledX, scaledY);
            currentCard2Button.onTouchDown(scaledX, scaledY);
            //buttons for menu options
            attackButton.onTouchDown(scaledX, scaledY);
            retreatButton.onTouchDown(scaledX, scaledY);
            evolveButton.onTouchDown(scaledX, scaledY);
            useStudentBehaviourCardButton.onTouchDown(scaledX, scaledY);

            //Check to see which button has been pressed
            if (!isStart && !dealCards) {
                if (currentCard1Button.isPressed(scaledX, scaledY) && player1.isMyTurn()  || currentCard2Button.isPressed(scaledX, scaledY) && player2.isMyTurn()) {
                    //play sound effect
                    Assets.playSound(Assets.buttonClickID);
                    displayWin1=false;
                    displayWin2 = false;
                    retreatError = false;
                    isMenu = true;
                    attackPlayer1 = false;
                    attackPlayer2=false;
                    evolvePlayer1 = false;
                    evolvePlayer2 = false;
                    currentCard1Button.cancel();
                    currentCard2Button.cancel();
                    return true;
                } else {
                    currentCard1Button.cancel();
                    currentCard2Button.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (attackButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    attackButton.cancel();
                    if (player1.isMyTurn()) {
                        //checkPrizeCardState(player2, player1);
                        //call the attack method
                        player1.attack(player2);
                        //set to true to display the attack which was used and how many points lost
                        attackPlayer1 = true;
                    } else {
                       // checkPrizeCardState(player1, player2);
                        //call the attack method
                        player2.attack(player1);
                        //set to true to display the attack which was used and how many points lost
                        attackPlayer2 = true;
                    }
                } else {
                    //cancel attack button
                    attackButton.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (retreatButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    //retreatButton.cancel();
                    if (player1.isMyTurn()) {
                        if(currentCardInPlay != null){
                           // move the current card back to bench
                            player1.getActiveCard().retreat(player1.getActiveCard().getEnergyCards(), player1);
                      // remove previous active card from board
                            currentCardInPlay = null;
                            retreatError = false;
                            isCardRetreated = true;
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
                            //call retreat method
                            player2.getActiveCard().retreat(player2.getActiveCard().getEnergyCards(), player2);
                            currentCardInPlay2 = null;
                            isCardRetreated = true;
                            isMenu = false;
                            retreatError = false;
                        }else{
                            // Validation for if the user selects retreat option and no cards are in play
                            System.out.println("Sorry, there must be a card in play in order to retreat");
                            //remove menu screen
                            isMenu = false;
                            //display to the user that there must be a card in play in order to retreat
                            retreatError = true;
                        }
                    }
                } else {
                    //cancel retreat button
                    retreatButton.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (evolveButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    evolveButton.cancel();
                    if (player1.isMyTurn()) {
                        //call evolve method for player 1's card
                        player1.getActiveCard().evolve();
                        evolvePlayer1 = true;
                    } else {
                        //call evolve method for player 2's card
                        player2.getActiveCard().evolve();
                        evolvePlayer2 = true;
                    }
                } else {
                    //cancel the evolve button
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
                                areCardsDrawn = true;
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
                //pause the game
                isPause = true;
                pauseButton.cancel();

            } else {
                pauseButton.cancel();
            }
            if (resumeButton.isPressed(scaledX, scaledY)  && isPause) {
                //resume playing the game
                isPause=false;
            }else{
                resumeButton.cancel();
            }
            if (restartButton.isPressed(scaledX, scaledY)  && isPause) {
                isPause=false;
                //start a new game
                setCurrentState(new CoinTossState());
            }else{
                restartButton.cancel();
            }
            if (instructionsButton.isPressed(scaledX, scaledY) && isPause) {
                isPause=false;
                isChooseCard = true;
            }else{
                instructionsButton.cancel();
            }
            if (quitButton.isPressed(scaledX, scaledY)  && isPause) {
                isPause=false;
                //Takes player back the games start menu
                setCurrentState(new MenuState());
            }else{
                quitButton.cancel();
            }
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN  ) {
            useCardButton.onTouchDown(scaledX, scaledY);
            cancelButton.onTouchDown(scaledX, scaledY);
            if (cancelButton.isPressed(scaledX, scaledY)&& isChooseCard) {
                isChooseCard = false;
                cancelButton.cancel();
            } else {
                cancelButton.cancel();
            }
            if (useCardButton.isPressed(scaledX, scaledY)&& isChooseCard) {
                if (player1.isMyTurn()) {
                    //give player 1 a new card of their choice and retreat their old card
                    player1.setActiveCard(player1.getBench().remove(positionOfCardChosen));
                    currentCardInPlay = player1.getActiveCard();
                    isCardRetreated = false;
                    isChooseCard = false;
                }else{
                    //give player 2 a new card of their choice and retreat their old card
                    player2.setActiveCard(player2.getBench().remove(positionOfCardChosen));
                    currentCardInPlay2 = player2.getActiveCard();
                    isCardRetreated = false;
                    isChooseCard = false;
                }
                isChooseCard = false;
                useCardButton.cancel();

            } else {
                useCardButton.cancel();
            }
        }
        if (e.getAction() == MotionEvent.ACTION_DOWN && player1.isMyTurn() ) {
            //find out which card player 1  has chosen as their new card
            p1Card0.onTouchDown(scaledX, scaledY);
            p1Card1.onTouchDown(scaledX, scaledY);
            p1Card2.onTouchDown(scaledX, scaledY);
            p1Card3.onTouchDown(scaledX, scaledY);
            p1Card4.onTouchDown(scaledX, scaledY);
            if (p1Card0.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 0;
                p1Card0.cancel();
            } else {
                p1Card0.cancel();
            }
            if (p1Card1.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 1;
                p1Card1.cancel();
            } else {
                p1Card1.cancel();
            }
            if (p1Card2.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 2;
                p1Card2.cancel();
            } else {
                p1Card2.cancel();
            }
            if (p1Card3.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 3;
                p1Card3.cancel();
            } else {
                p1Card3.cancel();
            }
            if (p1Card4.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 4;
                p1Card4.cancel();
            } else {
                p1Card4.cancel();
            }

        }
        if (e.getAction() == MotionEvent.ACTION_DOWN && player2.isMyTurn() ) {
            //find out which card player 2 has chosen as their new card
            p2Card0.onTouchDown(scaledX, scaledY);
            p2Card1.onTouchDown(scaledX, scaledY);
            p2Card2.onTouchDown(scaledX, scaledY);
            p2Card3.onTouchDown(scaledX, scaledY);
            p2Card4.onTouchDown(scaledX, scaledY);
            if (p2Card0.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay2 == null &&player2.isMyTurn()) {
                isChooseCard = true;
                positionOfCardChosen = 0;
                p2Card0.cancel();
            } else {
                p2Card0.cancel();
            }
            if (p2Card1.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 1;
                p2Card1.cancel();
            } else {
                p2Card1.cancel();
            }
            if (p2Card2.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 2;
                p2Card2.cancel();
            } else {
                p2Card2.cancel();
            }
            if (p2Card3.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 3;
                p2Card3.cancel();
            } else {
                p2Card3.cancel();
            }
            if (p2Card4.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 4;
                p2Card4.cancel();
            } else {
                p2Card4.cancel();
            }
        }
        return false;
    }

    /**
     * This method gives each player a random set of student behaviour cards as their proze cards
     */
    private void setUpPrizeCards() {
        //cretes to separate decks for each players prize cards to type student behaviour card
        ArrayList<StudentBehaviourCard> player1PrizeCards = new ArrayList<StudentBehaviourCard>();
        ArrayList<StudentBehaviourCard> player2PrizeCards = new ArrayList<StudentBehaviourCard>();

        //Each player should be given 6 random student behaviour cards
        for (int i = 0; i< 6; i++) {
            Random generator = new Random();
            int random = generator.nextInt(10) + 1;
            player1PrizeCards.add(prizeCardDeck1.get(random));
        }
        for (int i = 0; i< 6; i++) {
            Random generator = new Random();
            int random = generator.nextInt(10) + 1;
            player2PrizeCards.add(prizeCardDeck2.get(random));
        }
        //set both players prize cards
        player1.setPrizeCards(player1PrizeCards);
        player2.setPrizeCards(player2PrizeCards);
    }

    /**
     * This method checks to see if a player should be awarded a prize card
     * @param winner - the winning player of the round
     * @param loser- the losing player of the round
     */
    public void checkPrizeCardState(Player winner, Player loser) {
        if (loser.getActiveCard().getHp() <= 0) {
            // If the hp of the active card gets below zero, retreat it
            loser.getActiveCard().retreat(loser.getActiveCard().getEnergyCards(), loser);
            //move another card from the bench to replace it
            loser.setActiveCard(loser.getBench().remove(0));
            currentCardInPlay = loser.getActiveCard();
            //give a prize card to the winner of the round
            flipPrizeCard(winner);
        }
    }

    /**
     * This method flips over a prize card for the winning player each time that player wins a round
     * @param p - the player who will receieve the prize card
     */
    public void flipPrizeCard(Player p) {
        for (int i= 0; i <p.getPrizeCards().size(); i++) {
            if (!p.getPrizeCards().get(i).isFlipped()) {
                p.getPrizeCards().get(i).flipCard();
                //play sound of card being flipped over
                Assets.playSound(Assets.prizeID);
                break;
            }
        }
    }

    /**
     * Checks to see if player 1 has won the game
     * @return- true if they have won
     */
    public static boolean isPlayer1Winner() {
        return player1Winner;
    }

    /**
     * Checks to see if player 2 has won the game
     * @return - true if the have won
     */
    public static boolean isPlayer2Winner() {
        return player2Winner;
    }

    /**
     * sets Player 1 as the games winner
     * @param player1Winner - boolean value ture = winner
     */
    public static void setPlayer1Winner(boolean player1Winner) {
        PlayState.player1Winner = player1Winner;
    }

    /**
     * sets Player 2 as the games winner
     * @param player2Winner - boolean value ture = winner
     */
    public static void setPlayer2Winner(boolean player2Winner) {
        PlayState.player2Winner = player2Winner;
    }

    /**
     * This method is called when player 1 wins
     * @return player1Wins- the number of rounds that player 1 has won
     */
    public static int getPlayer1Wins() {
        return player1Wins;
    }

    /**
     * This method is called when player 2 wins the game
     * @return player2Wins - the number of rounds player 2 has won
     */
    public static int getPlayer2Wins() {
        return player2Wins;
    }

}

