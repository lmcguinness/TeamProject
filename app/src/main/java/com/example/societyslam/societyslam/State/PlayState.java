package com.example.societyslam.societyslam.State;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.Game.GameView;
import com.example.societyslam.societyslam.Game.MainActivity;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;
import java.util.ArrayList;
import java.util.Random;

import static com.example.societyslam.societyslam.Game.MainActivity.mediaPlayer;
import static com.example.societyslam.societyslam.Game.MainActivity.myGame;

/**
 * The playstate acts as the games play screen for society slam, it displays the board and the cards for the game,
 * it extends state
 */
public class PlayState extends State {

    private boolean isStart = true, dealCards = true, retreatError;
    boolean isMenu,attackPlayer1, attackPlayer2,evolvePlayer1,evolvePlayer2, displayWin1 = false,displayWin2 = false;
    private boolean isPause = false,isChooseCard =false,areCardsDrawn = false,isCardRetreated = false;
    public static boolean player1Winner, player2Winner;

    private Button pauseButton, continueButton, restartButton, resumeButton, quitButton, instructionsButton,useCardButton, cancelButton, p1Card0,p1Card1, p1Card2,p1Card3,
                   p1Card4, p2Card0, p2Card1,p2Card2, p2Card3, p2Card4, currentCard1Button, currentCard2Button, playButton, dealButton, attackButton, retreatButton,
                    evolveButton,useStudentBehaviourCardButton;

    private int buttonTop = 385,buttonBottom = 444,buttonLeft = 336,buttonRight = 504;
    private int menuButtonLeft = 316,menuButtonRight = 484;
    private int menuButton1Top = 125,  menuButton1Bottom = 165;
    private int menuButton2Top = 185,menuButton2Bottom = 225;
    private int menuButton3Top = 245,menuButton3Bottom = 285;
    private int menuButton4Top = 305, menuButton4Bottom = 350;
    private int pauseButtonTop = 385,  pauseButtonBottom = 444, pauseButtonLeft = 266,pauseButtonRight = 326;


    private int player1Cardleft = 20,player1CardRight = 125;
    private int player2Cardleft = 675,player2CardRight = 780;

    private int player1Card0Top = 145,  player1Card0Bottom = 190, player1Card1Bottom = 235, player1Card2Bottom = 280, player1Card3Bottom = 325,player1Card4Bottom = 425;
    private int player2Card0Top = 40, player2Card0Bottom = 80, player2Card1Bottom = 120,player2Card2Bottom = 160,player2Card3Bottom = 200,player2Card4Bottom = 300;

    private int activeCardTop = 175, activeCardBottom = 275;
    private int player1ActiveCardLeft = 280, player1ActiveCardRight = 395;
    private int player2ActiveCardLeft = 410, player2ActiveCardRight = 525;

    private float nameTextSize = 25;
    private int player1NameX = 303,player2NameX = 425,playerNameY = 20;

    private float scoreTextSize = 20;
    private int player1TextX = 150,player1TextY = 150;
    private int player1WinsX = 200, player1WinsY = 175;
    private int player2TextX = 535,player2TextY = 300;
    private int player2WinsX = 585,  player2WinsY = 317;

    private int player1TurnX = 280,player1TurnY = 140,player2TurnX = 410, player2TurnY = 140, playerTurnWidth = 125,playerTurnHeight = 130;

    private int cardWidth = 125, cardHeight = 100;

    private int player1ScoreX = 320, player2ScoreX = 440, scoreY = 40;

    private int player1EnergyCardX = 240,  player2EnergyCardX = 440,  energyCardY = 160;

    private int attackTextX = 270, attackTextY = 60,attackText2X = 315,attackText2Y = 80  ;

    private int roundWonTextX = 300, roundWonTextY = 115, roundWonText2X = 245, roundWonText2Y = 130;

    private boolean prizeCardError = false;

    private static int STARTING_CARD_NUMBER = 5;
    int dealCardSound =1, cardMove =1,player1Score, player2Score;
    static int player1Wins=0, player2Wins=0;
    private int positionOfCardChosen;

    //players
    private Player player1,player2;

    //The names of the players

    private String player1Name, player2Name;


    /**
     * This method will be called when we transition into the  playState.
     * It initializes any game objects that will be used throughout the playState
     * e.g. buttons, cards, decks and players
     */
    @Override
    public void init() {
        Assets.InitialiseCards();

        //Set the players names
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        player1Name = sharedPreferences.getString("player1name", "");
        player2Name = sharedPreferences.getString("player2name", "");

        player1 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck1, CoinTossState.getIsPlayer1Turn(),0, player1Name);
        player2 = new Player(Assets.myDeck, Assets.currentCardInPlay2, Assets.player2Cards, Assets.prizeCardDeck2, CoinTossState.getIsPlayer2Turn(),0, player2Name);


        //Set the background music to keep playing
        //Assets.playBackground(Assets.backgroundMusicID);

        //initialising buttons
        initialiseButtons();
        initaliseCardButtons();
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
        g.setFont(Typeface.DEFAULT_BOLD, nameTextSize);
        g.drawString(player1Name, player1NameX, playerNameY, Color.WHITE);
        g.drawString(player2Name, player2NameX,playerNameY, Color.WHITE);

        //Display how many rounds each player has won throughout the game
        g.setFont(Typeface.DEFAULT, scoreTextSize);
        g.drawString("Rounds won ", player1TextX,player1TextY, Color.WHITE);
        g.drawString(player1Wins + " ", player1WinsX,player1WinsY, Color.WHITE);
        g.drawString("Rounds won ", player2TextX,player2TextY, Color.WHITE);
        g.drawString(" "+ player2Wins, player2WinsX,player2WinsY, Color.WHITE);

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
            } else if (!dealCards && !isStart) {
                continueButton.render(g);

            }



            //Now that we have references to the cards that have to be moved, we can change the
            //location of them on the screen. Done here as opposed to update();
            if (Assets.currentCardInPlay != null && Assets.currentCardInPlay2 != null) {
                while (cardMove == 1) {
                    cardMove--;
                    Assets.playSound(Assets.oneCardID);
                }
                currentCard1Button.render(g);
                currentCard2Button.render(g);
                if(player1.isMyTurn()){
                    g.drawImage(Assets.yourTurn, player1TurnX,player1TurnY,playerTurnWidth,playerTurnHeight);
                }else{
                    g.drawImage(Assets.yourTurn, player2TurnX, player2TurnY, playerTurnWidth,playerTurnHeight);
                }
            }
            // has to be separate from above as when retreat method is called and it is player 2s turn, both cards are removed
            //from the middle as the above if statement is not satisfied.
            if(Assets.currentCardInPlay != null){
                g.drawImage(Assets.currentCardInPlay.getPicture(), player1ActiveCardLeft, activeCardTop, cardWidth , cardHeight);

                // TEST TO FIND OUT THE TYPE OF THE ACTIVE SOCIETY CARD WHEN MOVED TO SCREEN
                //g.drawString(" " + player1.getActiveCard().getType(), 320, 40);

                //Set HP levels of the active cards to the players score on the screen
                player1Score = player1.getActiveCard().getHp();
                g.setFont(Typeface.DEFAULT_BOLD, nameTextSize);
                g.drawString("  "+ player1Score, player1ScoreX, scoreY, Color.WHITE);

                //If player ones score falls below zero tell them that player 2 has won this round
                //change the card in the middle for player1 and give player 2 a prize card
                if(player1Score <=0){

                    player2.winRound(player1);

                    player2Wins++;
                    displayWin1 = true;
                    if(player2.checkIfWinner()) {
                        setPlayer2Winner(true);
                        setCurrentState(new GameOverState(player1Wins, player2Wins, player2Name));
                    }
                }
                //will attach matching energyCard to the activeCard
                attachEnergyCard(player1,g);
                g.drawImage(Assets.currentCardInPlay.getPicture(), player1ActiveCardLeft, activeCardTop, cardWidth , cardHeight);
            }
            if (Assets.currentCardInPlay2 != null) {
                g.drawImage(Assets.currentCardInPlay2.getPicture(), player2ActiveCardLeft, activeCardTop, cardWidth , cardHeight);

                //Set HP levels of the active cards to the players score on the screen
                player2Score = player2.getActiveCard().getHp();
                g.setFont(Typeface.DEFAULT_BOLD, nameTextSize);
                g.drawString("  "+ player2Score, player2ScoreX, scoreY, Color.WHITE);

                //If player 2 score falls below zero tell them that player 1 has won this round
                //give player 2 a new card in the middle and give player 1 a prize card
                if(player2Score <=0){

                    player1.winRound(player2);
                    player1Wins ++;
                    displayWin2 = true;
                    if(player1.checkIfWinner()){
                        setPlayer1Winner(true);
                        setCurrentState(new GameOverState(player1Wins, player2Wins, player1Name));
                    }
                }

                //Attach matching energyCard to player2 active society Card
                attachEnergyCard(player2,g);
                g.drawImage(Assets.currentCardInPlay2.getPicture(), player2ActiveCardLeft, activeCardTop, cardWidth , cardHeight);
            }
        }
        if(player1.getMyCards().getMyDeck().size()>0){
            drawCards(g);

        }

        renderAttack(g);
        renderEvolve(g);
        renderMenu(g);
        renderWinRound(g);

        if(retreatError){
            g.drawImage(Assets.retreatError, 75, 85, 685 , 65);
        }
        if(isPause){
            //pause the background music when the game is paused
            if(mediaPlayer != null){
                mediaPlayer.pause();
                if(isFinishing()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }

            renderPauseMenu(g);
        }

        // screen which pops up to allow player to look at cards in more detail before choosing which one to play with
        if(isChooseCard){
            g.drawImage(Assets.chooseCardMenu,75, 95, 685 , 235);
            useCardButton.render(g);
            cancelButton.render(g);
            if(player1.isMyTurn()) {
                g.drawImage(player1.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
            }else{
                g.drawImage(player2.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
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
        if(e.getAction() == MotionEvent.ACTION_DOWN && !isPause) {
            //current cards in plays
            currentCard1Button.onTouchDown(scaledX, scaledY);
            currentCard2Button.onTouchDown(scaledX, scaledY);
            //buttons for menu options
            attackButton.onTouchDown(scaledX, scaledY);
            retreatButton.onTouchDown(scaledX, scaledY);
            evolveButton.onTouchDown(scaledX, scaledY);
            useStudentBehaviourCardButton.onTouchDown(scaledX, scaledY);
            continueButton.onTouchDown(scaledX, scaledY);

            //Check to see which button has been pressed
            if (!isStart && !dealCards) {
                isMenu = true;
                if (currentCard1Button.isPressed(scaledX, scaledY) && player1.isMyTurn()  || currentCard2Button.isPressed(scaledX, scaledY) && player2.isMyTurn()) {
                    //play sound effect
                    Assets.playSound(Assets.buttonClickID);
                    displayWin1=false;
                    displayWin2 = false;
                    retreatError = false;
                    //isMenu = true;
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
                    prizeCardError = false;
                    isMenu = false;
                    attackButton.cancel();
                    if (player1.isMyTurn()) {
                        //call the attack method
                        player1.attack(player2);
                        //set to true to display the attack which was used and how many points lost
                        attackPlayer1 = true;
                    } else {

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
                    prizeCardError = false;
                    isMenu = false;
                    //retreatButton.cancel();
                    if (player1.isMyTurn()) {
                        if(Assets.currentCardInPlay != null){
                            // move the current card back to bench
                            player1.retreat();
                            // remove previous active card from board
                            Assets.currentCardInPlay = null;
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
                        if(Assets.currentCardInPlay2 != null){
                            //call retreat method
                            player2.retreat();
                            Assets.currentCardInPlay2 = null;
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
                    prizeCardError = false;
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

                    if (player1.isMyTurn()) {
                        if (player1.checkIfPlayerHasFlippedPrizeCards(player1)) {
                            player1.useStudentBehaviourCard(player1.getPrizeCards().get(0), player2);
                            isMenu = false;
                            prizeCardError = false;
                            useStudentBehaviourCardButton.cancel();
                        } else {
                                prizeCardError = true;
                        }
                    } else {
                        if (player2.checkIfPlayerHasFlippedPrizeCards(player2)) {
                            player2.useStudentBehaviourCard(player2.getPrizeCards().get(0), player1);
                            isMenu = false;
                            prizeCardError = false;
                            useStudentBehaviourCardButton.cancel();
                        }
                        else{
                            prizeCardError = true;
                        }
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
                        Assets.currentCardInPlay = player1.getActiveCard();
                    }
                    if (!player2.getMyCards().getMyDeck().isEmpty()) {
                        player2.setActiveCard(player2.getBench().remove(0));
                        Assets.currentCardInPlay2 = player2.getActiveCard();
                    }
                    dealCards = false;
                } else {
                    if (playButton.isPressed(scaledX, scaledY)) {
                        playButton.cancel();
                        if (dealCards) {
                            for (int i = 0; i < STARTING_CARD_NUMBER; i++) {
                                player1.getBench().add(Assets.myDeck.randomCard());
                                player2.getBench().add(Assets.myDeck.randomCard());
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
                //resume the back ground music
                int currentVol = MainActivity.settings.getVolume("musicValue");
                if(mediaPlayer != null){
                    if (currentVol == 0) {
                        mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
                    }
                    mediaPlayer.start();
                }
            }else{
                resumeButton.cancel();
            }
            if (restartButton.isPressed(scaledX, scaledY)  && isPause) {
                isPause=false;
                //start a new game
                myGame.restartGame();
                super.onRestart();
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
                //Takes player back the games start menu

                //resume the back ground music
                int currentVol = MainActivity.settings.getVolume("musicValue");
                if(mediaPlayer != null){
                    if (currentVol == 0) {
                        mediaPlayer.setVolume(currentVol/10.0f, currentVol/10.0f);
                    }
                    mediaPlayer.start();
                }
                myGame.quitGame();
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
                    Assets.currentCardInPlay = player1.getActiveCard();
                    isCardRetreated = false;
                    isChooseCard = false;
                }else{
                    //give player 2 a new card of their choice and retreat their old card
                    player2.setActiveCard(player2.getBench().remove(positionOfCardChosen));
                    Assets.currentCardInPlay2 = player2.getActiveCard();
                    isCardRetreated = false;
                    isChooseCard = false;
                }
                isChooseCard = false;
                useCardButton.cancel();

            } else {
                useCardButton.cancel();
            }
        }
        if (e.getAction() == MotionEvent.ACTION_DOWN && player1.isMyTurn() && isCardRetreated ) {
            //find out which card player 1  has chosen as their new card
            p1Card0.onTouchDown(scaledX, scaledY);
            p1Card1.onTouchDown(scaledX, scaledY);
            p1Card2.onTouchDown(scaledX, scaledY);
            p1Card3.onTouchDown(scaledX, scaledY);
            p1Card4.onTouchDown(scaledX, scaledY);
            if (p1Card0.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 0;
                p1Card0.cancel();
            } else {
                p1Card0.cancel();
            }
            if (p1Card1.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 1;
                p1Card1.cancel();
            } else {
                p1Card1.cancel();
            }
            if (p1Card2.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 2;
                p1Card2.cancel();
            } else {
                p1Card2.cancel();
            }
            if (p1Card3.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 3;
                p1Card3.cancel();
            } else {
                p1Card3.cancel();
            }
            if (p1Card4.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay == null) {
                isChooseCard = true;
                positionOfCardChosen = 4;
                p1Card4.cancel();
            } else {
                p1Card4.cancel();
            }

        }
        if (e.getAction() == MotionEvent.ACTION_DOWN && player2.isMyTurn() &&isCardRetreated ) {
            //find out which card player 2 has chosen as their new card
            p2Card0.onTouchDown(scaledX, scaledY);
            p2Card1.onTouchDown(scaledX, scaledY);
            p2Card2.onTouchDown(scaledX, scaledY);
            p2Card3.onTouchDown(scaledX, scaledY);
            p2Card4.onTouchDown(scaledX, scaledY);
            if (p2Card0.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay2 == null &&player2.isMyTurn()) {
                isChooseCard = true;
                positionOfCardChosen = 0;
                p2Card0.cancel();
            } else {
                p2Card0.cancel();
            }
            if (p2Card1.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 1;
                p2Card1.cancel();
            } else {
                p2Card1.cancel();
            }
            if (p2Card2.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 2;
                p2Card2.cancel();
            } else {
                p2Card2.cancel();
            }
            if (p2Card3.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 3;
                p2Card3.cancel();
            } else {
                p2Card3.cancel();
            }
            if (p2Card4.isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && Assets.currentCardInPlay2 == null) {
                isChooseCard = true;
                positionOfCardChosen = 4;
                p2Card4.cancel();
            } else {
                p2Card4.cancel();
            }
        }
        return false;
    }

    public void initialiseButtons() {
        playButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.start);
        continueButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.continueButton);
        dealButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.dealButton);
        attackButton = new Button(menuButtonLeft, menuButton1Top, menuButtonRight, menuButton1Bottom, Assets.attackButton);
        retreatButton = new Button(menuButtonLeft, menuButton2Top, menuButtonRight, menuButton2Bottom, Assets.retreatButton);
        evolveButton = new Button(menuButtonLeft, menuButton3Top, menuButtonRight, menuButton3Bottom, Assets.evolveButton);
        useStudentBehaviourCardButton = new Button(menuButtonLeft, menuButton4Top, menuButtonRight, menuButton4Bottom, Assets.societyCardButton);
        pauseButton = new Button(pauseButtonLeft,pauseButtonTop,pauseButtonRight,pauseButtonBottom,Assets.pause);
        resumeButton = new Button(menuButtonLeft, menuButton1Top, menuButtonRight, menuButton1Bottom, Assets.resume);
        restartButton = new Button(menuButtonLeft, menuButton2Top, menuButtonRight, menuButton2Bottom, Assets.restart);
        instructionsButton = new Button(menuButtonLeft, menuButton3Top, menuButtonRight, menuButton3Bottom, Assets.instructions);
        quitButton = new Button(menuButtonLeft, menuButton4Top, menuButtonRight, menuButton4Bottom, Assets.quit);
        useCardButton = new Button(456,165, 634, 205, Assets.useCard);
        cancelButton = new Button(456,225,634,265,Assets.cancel);
    }

    public void initaliseCardButtons() {
        // placing buttons in the position of where player 1's cards are on the bench so they can choose which card to attack with
        p1Card0 = new Button(player1Cardleft, player1Card0Top, player1CardRight, player1Card0Bottom, null);
        p1Card1 = new Button(player1Cardleft, player1Card0Bottom, player1CardRight, player1Card1Bottom, null);
        p1Card2 = new Button(player1Cardleft, player1Card1Bottom, player1CardRight, player1Card2Bottom, null);
        p1Card3 = new Button(player1Cardleft, player1Card2Bottom, player1CardRight, player1Card3Bottom, null);
        p1Card4 = new Button(player1Cardleft, player1Card3Bottom, player1CardRight,player1Card4Bottom, null);

        // placing buttons in the position of where player 2's cards are on the bench so they can choose which card to attack with
        p2Card0 = new Button(player2Cardleft, player2Card0Top, player2CardRight , player2Card0Bottom, null);
        p2Card1 = new Button(player2Cardleft, player2Card0Bottom, player2CardRight , player2Card1Bottom, null);
        p2Card2 = new Button(player2Cardleft, player2Card1Bottom, player2CardRight , player2Card2Bottom, null);
        p2Card3 = new Button(player2Cardleft, player2Card2Bottom, player2CardRight , player2Card3Bottom, null);
        p2Card4 = new Button(player2Cardleft, player2Card3Bottom, player2CardRight , player2Card4Bottom, null);

        //placing buttons in the position of where current card in play is
        currentCard1Button = new Button(player1ActiveCardLeft, activeCardTop, player1ActiveCardRight, activeCardBottom,null);
        currentCard2Button = new Button(player2ActiveCardLeft, activeCardTop, player2ActiveCardRight , activeCardBottom,null);
    }

    /**
     * This method gives both players cards from their decks and places them on their bench
     * @param g- the painter
     */
    private void drawCards(Painter g) {
        //gives player 1 society cards on their bench
        for (int i = 0; i < player1.getBench().size(); i++) {
            int player1CardY = 100 + (i+1) * 45;
            g.drawImage(player1.getBench().get(i).getPicture(), player1Cardleft,player1CardY , cardWidth , cardHeight);
        }

        //gives player 2 society cards in their bench
        for (int i = 0; i < player2.getBench().size(); i++) {
            int player2CardY = (i+1) *40;
            g.drawImage(player2.getBench().get(i).getPicture(), player2Cardleft, player2CardY , cardWidth , cardHeight);
        }

        //draw player 1 prize cards
        for (int i =0; i<player1.getPrizeCards().size(); i++) {
            StudentBehaviourCard card = player1.getPrizeCards().get(i);
            if (i > 2) {
                g.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), -300 +(i + 1) * 80, 62, 100, 60);
            } else {
                g.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), -65 +(i + 1) * 80, 6, 100, 60);
            }
        }
        // draw player 2 prize cards
        for (int i =0; i< player2.getPrizeCards().size(); i++) {
            StudentBehaviourCard card = player2.getPrizeCards().get(i);
            if (i > 2) {
                g.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), 200 +(i +1) * 80, 330, 100, 60);
            } else {
                g.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), 440 +(i+1) * 80, 380, 100, 60);

            }
        }
    }


    public void renderMenu(Painter g) {
        if (isMenu) {
            g.drawImage(Assets.menubg, 100, 50);
            attackButton.render(g);
            retreatButton.render(g);
            evolveButton.render(g);
            useStudentBehaviourCardButton.render(g);

            if (prizeCardError) {
                g.drawString("You Have No PrizeCards Yet!", 235, 400, Color.RED);
            }
        }




    }

    public void renderPauseMenu(Painter g) {
        g.drawImage(Assets.pauseMenu,100,50);
        resumeButton.render(g);
        restartButton.render(g);
        quitButton.render(g);
        instructionsButton.render(g);
    }

    /**
     * This method gives each player a random set of student behaviour cards as their prize cards
     */
    private void setUpPrizeCards() {
        //creates two separate decks for each players prize cards to type student behaviour card
        ArrayList<StudentBehaviourCard> player1PrizeCards = new ArrayList<StudentBehaviourCard>();
        ArrayList<StudentBehaviourCard> player2PrizeCards = new ArrayList<StudentBehaviourCard>();

        //Each player should be given 6 random student behaviour cards
        for (int i = 0; i< 6; i++) {
            Random generator = new Random();
            int random = generator.nextInt(10) + 1;
            player1PrizeCards.add(Assets.prizeCardDeck1.get(random));
        }
        for (int i = 0; i< 6; i++) {
            Random generator = new Random();
            int random = generator.nextInt(10) + 1;
            player2PrizeCards.add(Assets.prizeCardDeck1.get(random));
        }
        //set both players prize cards
        player1.setPrizeCards(player1PrizeCards);
        player2.setPrizeCards(player2PrizeCards);
    }


    /**
     * This method attaches the appropriate energy card to the player's active card
     * @param p - the player whose active card needs an energy card
     * @param g - the painter
     */

    public void attachEnergyCard(Player p, Painter g) {
        Type type = p.getActiveCard().getType();
       if(p.equals(player1)) {
           switch(type){
               case earth: g.drawImage(Assets.earthEnergy, player1EnergyCardX, energyCardY, cardWidth, cardHeight);
                   break;
               case electric: g.drawImage(Assets.electricEnergy, player1EnergyCardX, energyCardY,cardWidth,cardHeight);
                   break;
               case water: g.drawImage(Assets.waterEnergy, player1EnergyCardX, energyCardY, cardWidth, cardHeight);
                   break;
               case fighting: g.drawImage(Assets.fightEngery, player1EnergyCardX, energyCardY, cardWidth, cardHeight);
                   break;

           }
       } else if(p.equals(player2)) {
           switch(type){
               case earth: g.drawImage(Assets.earthEnergy, player2EnergyCardX, energyCardY, cardWidth, cardHeight);
                   break;
               case electric: g.drawImage(Assets.electricEnergy, player2EnergyCardX, energyCardY,cardWidth,cardHeight);
                   break;
               case water: g.drawImage(Assets.waterEnergy, player2EnergyCardX, energyCardY, cardWidth, cardHeight);
                   break;
               case fighting: g.drawImage(Assets.fightEngery, player2EnergyCardX, energyCardY, cardWidth, cardHeight);
                   break;

           }
       }
    }


    public void renderAttack(Painter g) {
        //If player one clicks attack, display their attack and how many points player2 loses on the board
        //If player two attacks, display their attack and how many points player1 loses
        if(attackPlayer1){
            player1.renderAttack(g, player2);
        } else if(attackPlayer2){
            player2.renderAttack(g, player1);
        }
    }

    public void renderEvolve(Painter g) {
        if(evolvePlayer1) {
            player1.getActiveCard().renderEvolve(g);
        }
        if(evolvePlayer2) {
            player2.getActiveCard().renderEvolve(g);
        }
    }

    public void renderWinRound(Painter g) {
        //If player ones score falls below zero
        if(displayWin1){
            player2.renderWinRound(g, player1);

        }
        //If player twos score fall below zero
         else if(displayWin2){
            player1.renderWinRound(g, player2);

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
    public static void setPlayer1Wins(int player1W){
        player1Wins = player1W;
    }
    public static void setPlayer2Wins(int player2W){
        player1Wins = player2W;
    }
    /**
     * This method is called when player 2 wins the game
     * @return player2Wins - the number of rounds player 2 has won
     */
    public static int getPlayer2Wins() {
        return player2Wins;
    }


}


