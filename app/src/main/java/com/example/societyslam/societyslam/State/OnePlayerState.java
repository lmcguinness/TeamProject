package com.example.societyslam.societyslam.State;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.Painter;
import com.example.societyslam.societyslam.ai.CPU;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by James on 14/04/2017.
 */

public class OnePlayerState extends State {

    private boolean isStart = true, dealCards = true, retreatError;
    boolean isMenu,attackPlayer1, attackPlayer2,evolvePlayer1,evolvePlayer2, displayWin1 = false,displayWin2 = false;
    private boolean isPause = false,isChooseCard =false,areCardsDrawn = false,isCardRetreated = false, isFirstTurn = true;
    public static boolean player1Winner, player2Winner;

    private Button pauseButton, restartButton, resumeButton, quitButton, instructionsButton,useCardButton, cancelButton, p1Card0,p1Card1, p1Card2,p1Card3,
            p1Card4, currentCard1Button, playButton, dealButton, attackButton, retreatButton,
            evolveButton,useStudentBehaviourCardButton;

    private static int STARTING_CARD_NUMBER = 5;
    int dealCardSound =1, cardMove =1,player1Score, player2Score;
    static int player1Wins=0, player2Wins=0;
    private int positionOfCardChosen;

    //players
    private Player player1;
    private CPU cpu1;
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
        player1 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck1, CoinTossState.getIsPlayer1Turn(),0);
        cpu1 = new CPU(Assets.myDeck, Assets.currentCardInPlay2, Assets.player2Cards, Assets.prizeCardDeck2, CoinTossState.getIsPlayer2Turn(),0);

        //Set the players names
        player1Name = "Player 1";
        player2Name = "CPU1";

        //Set the background music to keep playing
        Assets.playBackground(Assets.backgroundMusicID);

        //initializing buttons
        playButton = new Button(336, 385, 504, 444, Assets.start);
        dealButton = new Button(336, 385, 504, 444, Assets.dealButton);
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


        //placing buttons in the position of where current card in play is
        currentCard1Button = new Button(280, 175, 395, 275,null);

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
            if (Assets.currentCardInPlay != null && Assets.currentCardInPlay2 != null) {
                while (cardMove == 1) {
                    cardMove--;
                    Assets.playSound(Assets.oneCardID);
                }
                currentCard1Button.render(g);

                if(player1.isMyTurn()){
                    super.getPainter().drawImage(Assets.yourTurn, 280,140,117,130);
                }
            }
            // has to be separate from above as when retreat method is called and it is player 2s turn, both cards are removed
            //from the middle as the above if statement is not satisfied.
            if(Assets.currentCardInPlay != null){
                super.getPainter().drawImage(Assets.currentCardInPlay.getPicture(), 280, 175, 125 , 100);

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
                    checkPrizeCardState(cpu1, player1);
                    player2Wins++;
                    cpu1.setRoundWins(player2Wins);
                    displayWin1 = true;
                    if(player2Wins >= cpu1.getPrizeCards().size()) {
                        setPlayer2Winner(true);
                        setCurrentState(new GameOverState(player2Wins, player2Name));
                    }
                }
                //will attach matching energyCard to the activeCard
                if (player1.getActiveCard().getType() == Type.earth ){
                    super.getPainter().drawImage(Assets.earthEnergy, 240,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                }//end of new if statement
                if (player1.getActiveCard().getType() == Type.electric ){
                    super.getPainter().drawImage(Assets.electricEnergy, 240,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                }//end of new if statement
                if (player1.getActiveCard().getType() == Type.water ){
                    super.getPainter().drawImage(Assets.waterEnergy, 240,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                }//end of new if statement
                if (player1.getActiveCard().getType() == Type.fighting ){
                    super.getPainter().drawImage(Assets.fightEngery, 240,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay.getPicture(), 280, 175, 125 , 100);
                }//end of new if statement
            }
            if (Assets.currentCardInPlay2 != null) {
                super.getPainter().drawImage(Assets.currentCardInPlay2.getPicture(), 410, 175, 125 , 100);

                //Set HP levels of the active cards to the players score on the screen
                player2Score = cpu1.getActiveCard().getHp();
                g.setFont(Typeface.DEFAULT_BOLD, 25);
                g.drawString("  "+ player2Score,440,40);

                //If player 2 score falls below zero tell them that player 1 has won this round
                //give player 2 a new card in the middle and give player 1 a prize card
                if(player2Score <=0){
                    g.setFont(Typeface.DEFAULT, 25);
                    checkPrizeCardState(player1, cpu1);
                    player1Wins++;
                    player1.setRoundWins(player1Wins);
                    displayWin2 = true;
                    if(player1Wins >= player1.getPrizeCards().size()){
                        setPlayer1Winner(true);
                        setCurrentState(new GameOverState(player1Wins, player1Name));
                    }
                }

                //Attach matching energyCard to player2 active society Card
                if (cpu1.getActiveCard().getType() == Type.earth ){
                    super.getPainter().drawImage(Assets.earthEnergy, 440,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
                if (cpu1.getActiveCard().getType() == Type.electric ){
                    super.getPainter().drawImage(Assets.electricEnergy, 440,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
                if (cpu1.getActiveCard().getType() == Type.water ){
                    super.getPainter().drawImage(Assets.waterEnergy, 440,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
                if (cpu1.getActiveCard().getType() == Type.fighting ){
                    super.getPainter().drawImage(Assets.fightEngery, 440,160,125,100);
                    super.getPainter().drawImage(Assets.currentCardInPlay2.getPicture(), 410, 175, 125 , 100);
                }//end of new if statement
            }
        }
        if(player1.getMyCards().getMyDeck().size()>0){
            drawCards(g);

        }

        //If player one clicks attack, display their attack and how many points player2 loses on the board
        if(attackPlayer1){
            g.setFont(Typeface.DEFAULT_BOLD,22);
            g.drawString("You attacked with "+ player1.getActiveCard().getAttackName(), 270, 60);
            g.drawString("minus "+ player1.getAttackDamage()+ " points " + player2Name, 315,80);
        }
        //If player two attacks, display their attack and how many points player1 loses
        if(attackPlayer2){
            g.setFont(Typeface.DEFAULT_BOLD,22);
            g.drawString("You attacked with "+ cpu1.getActiveCard().getAttackName(), 270, 60);
            g.drawString("minus "+ cpu1.getAttackDamage()+ " points " + player1Name, 315,80);
        }


        if(evolvePlayer1) {
            g.drawString(player1.getActiveCard().getName() + " evolved to " + player1.getActiveCard().getLevel(), 270, 60);
        }

        if(evolvePlayer2) {
            g.drawString(cpu1.getActiveCard().getName() + " evolved to " + cpu1.getActiveCard().getLevel(), 270, 60);
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
                super.getPainter().drawImage(cpu1.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
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
        for (int i = 0; i < cpu1.getBench().size(); i++) {
            p.drawImage(cpu1.getBench().get(i).getPicture(), 675, (i +1) * 40 , 125 , 100);
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
        for (int i =0; i< cpu1.getPrizeCards().size(); i++) {
            StudentBehaviourCard card = cpu1.getPrizeCards().get(i);
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

            //buttons for menu options
            attackButton.onTouchDown(scaledX, scaledY);
            retreatButton.onTouchDown(scaledX, scaledY);
            evolveButton.onTouchDown(scaledX, scaledY);
            useStudentBehaviourCardButton.onTouchDown(scaledX, scaledY);

            //Check to see which button has been pressed
            if (!isStart && !dealCards) {
                if (currentCard1Button.isPressed(scaledX, scaledY) && player1.isMyTurn() ) {
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

                    return true;
                } else {
                    currentCard1Button.cancel();
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (attackButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    attackButton.cancel();
                    if (player1.isMyTurn()) {
                        //checkPrizeCardState(player2, player1);
                        //call the attack method
                        player1.attack(cpu1);
                        //set to true to display the attack which was used and how many points lost
                        attackPlayer1 = true;
                        cpu1.makeMove(cpu1, player1);
                    } else {
                        // checkPrizeCardState(player1, player2);
                        //call the attack method
                        cpu1.attack(player1);
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
                        if (Assets.currentCardInPlay != null) {
                            // move the current card back to bench
                            player1.getActiveCard().retreat(player1.getActiveCard().getEnergyCards(), player1);
                            // remove previous active card from board
                            Assets.currentCardInPlay = null;
                            retreatError = false;
                            isCardRetreated = true;
                            isMenu = false;
                        } else {
                            // Validation for if the user selects retreat option and no cards are in play
                            System.out.println("Sorry, there must be a card in play in order to retreat");
                            //display to the user that there must be a card in play in order to retreat
                            retreatError = true;
                            //remove menu screen
                            isMenu = false;
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
                        //cancel the evolve button
                        evolveButton.cancel();
                    }
                }
                // added "&& isMenu = true" to differentiate between buttons on pause screen and this one
                if (useStudentBehaviourCardButton.isPressed(scaledX, scaledY) && isMenu) {
                    isMenu = false;
                    useStudentBehaviourCardButton.cancel();
                    if (player1.isMyTurn()) {
                        player1.useStudentBehaviourCard(player1.getPrizeCards().get(0), cpu1);
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
                if(CoinTossState.getIsPlayer2Turn() && isFirstTurn){
                    isFirstTurn =false;
                    cpu1.makeMove(cpu1, player1);
                }else{
                    isFirstTurn = false;
                }
                dealButton.cancel();
                if (!isStart && dealCards) {
                    //TO DO: move cards currently in the middle of screen to dump pile
                    //move new card of deck into middle of the screen
                    if (!player1.getMyCards().getMyDeck().isEmpty()) {
                        player1.setActiveCard(player1.getBench().remove(0));
                        Assets.currentCardInPlay = player1.getActiveCard();
                    }
                    if (!cpu1.getMyCards().getMyDeck().isEmpty()) {
                        cpu1.setActiveCard(cpu1.getBench().remove(0));
                        Assets.currentCardInPlay2 = cpu1.getActiveCard();
                    }
                    dealCards = false;
                } else {
                    if (playButton.isPressed(scaledX, scaledY)) {
                        playButton.cancel();
                        if (dealCards) {
                            for (int i = 0; i < STARTING_CARD_NUMBER; i++) {
                                player1.getBench().add(Assets.myDeck.randomCard());
                                cpu1.getBench().add(Assets.myDeck.randomCard());
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
                    Assets.currentCardInPlay = player1.getActiveCard();
                    isCardRetreated = false;
                    isChooseCard = false;
                }else{
                    //give player 2 a new card of their choice and retreat their old card
                    cpu1.setActiveCard(cpu1.getBench().remove(positionOfCardChosen));
                    Assets.currentCardInPlay2 = cpu1.getActiveCard();
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
        return false;
    }
    /**
     * This method gives each player a random set of student behaviour cards as their prize cards
     */
    private void setUpPrizeCards() {
        //created to separate decks for each players prize cards to type student behaviour card
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
            player2PrizeCards.add(Assets.prizeCardDeck2.get(random));
        }
        //set both players prize cards
        player1.setPrizeCards(player1PrizeCards);
        cpu1.setPrizeCards(player2PrizeCards);
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
            Assets.currentCardInPlay = loser.getActiveCard();
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




