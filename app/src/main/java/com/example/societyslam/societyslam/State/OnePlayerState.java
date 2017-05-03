package com.example.societyslam.societyslam.State;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.ChooseCardMenu;
import com.example.societyslam.societyslam.Util.Painter;
import com.example.societyslam.societyslam.Util.PauseMenu;
import com.example.societyslam.societyslam.ai.CPU;
import java.util.ArrayList;
import java.util.Random;
import static com.example.societyslam.societyslam.Game.MainActivity.mediaPlayer;

/**
 * Created by James on 20/04/2017.
 */

public class OnePlayerState extends State {

    private PauseMenu pauseMenu;
    private ChooseCardMenu chooseCardMenu;
    private boolean isStart = true, dealCards = true, retreatError;
    boolean isMenu,attackPlayer1, attackPlayer2,evolvePlayer1,evolvePlayer2, displayWin1 = false,displayWin2 = false;
    private boolean isPause = false,isChooseCard =false,areCardsDrawn = false,isCardRetreated = false, isFirstTurn=true;
    public static boolean player1Winner, player2Winner;

    private Button pauseButton, p1Card0,p1Card1, p1Card2,p1Card3,attackButton, retreatButton,evolveButton,useStudentBehaviourCardButton,
            p1Card4, currentCard1Button, playButton, dealButton;
    private int buttonTop = 385,buttonBottom = 444,buttonLeft = 336,buttonRight = 504;
    private int pauseButtonTop = 385,  pauseButtonBottom = 444, pauseButtonLeft = 266,pauseButtonRight = 326;

    private int menuX = 120, menuY = 50;
    private int chooseCardMenuX = 75, chooseCardMenuY = 95;

    private int player1Cardleft = 20,player1CardRight = 125;
    private int player2Cardleft = 675;

    private int player1Card0Top = 145,  player1Card0Bottom = 190, player1Card1Bottom = 235, player1Card2Bottom = 280, player1Card3Bottom = 325,player1Card4Bottom = 425;
    private int activeCardTop = 175, activeCardBottom = 275;
    private int player1ActiveCardLeft = 280, player1ActiveCardRight = 395;
    private int player2ActiveCardLeft = 410;

    private float nameTextSize = 25;
    private int player1NameX = 303,player2NameX = 425,playerNameY = 20;

    private float scoreTextSize = 20;
    private int player1TextX = 150,player1TextY = 150;
    private int player1WinsX = 200, player1WinsY = 175;
    private int player2TextX = 535,player2TextY = 300;
    private int player2WinsX = 585,  player2WinsY = 317;

    private int player1TurnX = 280,player1TurnY = 140, playerTurnWidth = 125,playerTurnHeight = 130;

    private int cardWidth = 125, cardHeight = 100;

    private int player1ScoreX = 320, player2ScoreX = 440, scoreY = 40;

    private int player1EnergyCardX = 240,  player2EnergyCardX = 440,  energyCardY = 160;
    private ArrayList<Button> player1Bench;

    private boolean prizeCardError = false;
    private static int STARTING_CARD_NUMBER = 5,  whichStatement;
    int dealCardSound =1, cardMove =1;
    private static int player1Wins=0, player2Wins=0;
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
        //Set the players names
        player1Name = "Player 1";
        player2Name = "CPU1";
        player1 = new Player(Assets.myDeck,Assets.currentCardInPlay,Assets.playersCards,Assets.prizeCardDeck1, true,0, player1Name);
        cpu1 = new CPU(Assets.myDeck, Assets.currentCardInPlay2, Assets.player2Cards, Assets.prizeCardDeck2,false,0, player2Name);
        pauseMenu = new PauseMenu(Assets.pauseMenu, menuX, menuY);
        chooseCardMenu = new ChooseCardMenu(Assets.chooseCardMenu, chooseCardMenuX, chooseCardMenuY);
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
        if (areCardsDrawn && Assets.currentCardInPlay != null && isFirstTurn) {
            whichStatement = cpu1.talk(player1, cpu1, 1);
        }
        if(cpu1.isWinner()){
            whichStatement = cpu1.talk(player1, cpu1, 4);
        }else if(player1.isWinner()){
            whichStatement = cpu1.talk(player1, cpu1, 4);
        }
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
        g.drawString(player1.getRoundWins() + " ", player1WinsX,player1WinsY, Color.WHITE);
        g.drawString("Rounds won ", player2TextX,player2TextY, Color.WHITE);
        g.drawString(" "+ cpu1.getRoundWins(), player2WinsX,player2WinsY, Color.WHITE);

        pauseButton.render(g);
        // when card has been retreated - render buttons
        if(isCardRetreated) {
           for(int i =0;i<player1Bench.size();i++){
               player1Bench.get(i).render(g);
           }
        }
        if(CPU.getIsTalking()) {
            super.getPainter().drawImage(Assets.robot, 535, 100, 125, 100);
            if (whichStatement == 6) {
                super.getPainter().drawImage(Assets.speechBubble1, 330, 50, 355, 100);
            } else if (whichStatement == 0) {
                super.getPainter().drawImage(Assets.speechBubble2, 430, 50, 200, 100);
            } else if (whichStatement == 1) {
                super.getPainter().drawImage(Assets.speechBubble3, 430, 50, 200, 100);
            } else if (whichStatement == 2) {
                super.getPainter().drawImage(Assets.speechBubble4, 330, 50, 355, 100);
            } else if (whichStatement == 3) {
                super.getPainter().drawImage(Assets.speechBubble5, 430, 50, 200, 100);
            } else if (whichStatement == 4) {
                super.getPainter().drawImage(Assets.speechBubble6, 430, 50, 200, 100);
            } else if (whichStatement == 5) {
                super.getPainter().drawImage(Assets.speechBubble7, 430, 50, 200, 100);
            } else if (whichStatement == 7) {
                super.getPainter().drawImage(Assets.speechBubble8, 330, 50, 355, 100);
            } else if (whichStatement == 8) {
                super.getPainter().drawImage(Assets.speechBubble9, 330, 50, 355, 100);
            }
        }

        if (isStart) {
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
            }
            //Now that we have references to the cards that have to be moved, we can change the
            //location of them on the screen. Done here as opposed to update();
            if (player1.getActiveCard() != null && cpu1.getActiveCard() != null) {
                while (cardMove == 1) {
                    cardMove--;
                    Assets.playSound(Assets.oneCardID);
                }
                currentCard1Button.render(g);
                if(player1.isMyTurn()){
                    g.drawImage(Assets.yourTurn, player1TurnX,player1TurnY,playerTurnWidth,playerTurnHeight);
                }
            }
            // has to be separate from above as when retreat method is called and it is player 2s turn, both cards are removed
            //from the middle as the above if statement is not satisfied.
            renderActiveCard(g, player1, cpu1, player1ScoreX, scoreY, player1.getRoundWins(), player1ActiveCardLeft);
            renderActiveCard(g, cpu1, player1, player2ScoreX, scoreY, cpu1.getRoundWins(), player2ActiveCardLeft);
        }
        if(player1.getMyCards().getMyDeck().size()>0){
            drawCards(g);

        }
        if (isMenu) {
            g.drawImage(Assets.menubg, 100, 50);
            attackButton.render(g);
            retreatButton.render(g);
            evolveButton.render(g);
            useStudentBehaviourCardButton.render(g);
        }
        renderChooseCardMenu(g);

        if(retreatError){
            super.getPainter().drawImage(Assets.retreatError, 75, 85, 685 , 65);
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
            pauseMenu.render(g);
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
                    isFirstTurn = false;
                    cpu1.setIsTalking(false);
                    attackButton.cancel();
                    if (player1.isMyTurn()) {
                        //checkPrizeCardState(player2, player1);
                        //call the attack method
                        player1.attack(cpu1);
                        whichStatement = cpu1.talk(player1,cpu1, 2);
                        //set to true to display the attack which was used and how many points lost
                        attackPlayer1 = true;
                        cpu1.makeMove(cpu1, player1);
                        whichStatement = cpu1.talk(player1, cpu1, 5);
                    } else {
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
                            player1.retreat();
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
            dealButtonOnTouch(scaledX, scaledY);
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            pauseButton.onTouchDown(scaledX, scaledY);
            pauseButtonOnTouch(scaledX, scaledY);
            pauseMenu.onTouch1(scaledX, scaledY, this);

        }

        if (e.getAction() == MotionEvent.ACTION_DOWN  ) {
            chooseCardMenu.onTouch1(scaledX,scaledY,this,positionOfCardChosen);
            chooseCardOnTouch(e, scaledX, scaledY, player1, player1Bench);
        }
        return false;
    }

    public void initialiseButtons() {
        playButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.start);
        dealButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.dealButton);
        pauseButton = new Button(pauseButtonLeft,pauseButtonTop,pauseButtonRight,pauseButtonBottom,Assets.pause);
        attackButton = new Button(316, 125, 484, 165, Assets.attackButton);
        retreatButton = new Button(316, 185, 484, 225, Assets.retreatButton);
        evolveButton = new Button(316, 245, 484, 285, Assets.evolveButton);
        useStudentBehaviourCardButton = new Button(316, 305, 484, 350, Assets.societyCardButton);
    }

    public void initaliseCardButtons() {
        // placing buttons in the position of where player 1's cards are on the bench so they can choose which card to attack with
        p1Card0 = new Button(player1Cardleft, player1Card0Top, player1CardRight, player1Card0Bottom, null);
        p1Card1 = new Button(player1Cardleft, player1Card0Bottom, player1CardRight, player1Card1Bottom, null);
        p1Card2 = new Button(player1Cardleft, player1Card1Bottom, player1CardRight, player1Card2Bottom, null);
        p1Card3 = new Button(player1Cardleft, player1Card2Bottom, player1CardRight, player1Card3Bottom, null);
        p1Card4 = new Button(player1Cardleft, player1Card3Bottom, player1CardRight,player1Card4Bottom, null);

        player1Bench = new ArrayList<Button>();
        player1Bench.add(p1Card0);
        player1Bench.add(p1Card1);
        player1Bench.add(p1Card2);
        player1Bench.add(p1Card3);
        player1Bench.add(p1Card4);


        //placing button in the position of where current card in play is
        currentCard1Button = new Button(player1ActiveCardLeft, activeCardTop, player1ActiveCardRight, activeCardBottom,null);
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
        for (int i = 0; i < cpu1.getBench().size(); i++) {
            int player2CardY = (i+1) *40;
            g.drawImage(cpu1.getBench().get(i).getPicture(), player2Cardleft, player2CardY , cardWidth , cardHeight);
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
        for (int i =0; i< cpu1.getPrizeCards().size(); i++) {
            StudentBehaviourCard card = cpu1.getPrizeCards().get(i);
            if (i > 2) {
                g.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), 200 +(i +1) * 80, 330, 100, 60);
            } else {
                g.drawImage((card.isFlipped() ? card.getBitmap() : Assets.cardBack), 440 +(i+1) * 80, 380, 100, 60);

            }
        }
    }

    public void renderActiveCard(Painter g, Player p, Player opponent, int scoreX, int scoreY, int playerWins, int activeCardX ) {
        int playerScore;
        if(p.getActiveCard() != null){
            //g.drawImage(player1.getActiveCard().getBitmap(), player1ActiveCardLeft, activeCardTop, cardWidth , cardHeight);

            // TEST TO FIND OUT THE TYPE OF THE ACTIVE SOCIETY CARD WHEN MOVED TO SCREEN
            //g.drawString(" " + player1.getActiveCard().getType(), 320, 40);

            //Set HP levels of the active cards to the players score on the screen
            playerScore = p.getActiveCard().getHp();
            g.setFont(Typeface.DEFAULT_BOLD, nameTextSize);
            g.drawString("  "+ playerScore, scoreX, scoreY, Color.WHITE);

            //If player ones score falls below zero tell them that player 2 has won this round
            //change the card in the middle for player1 and give player 2 a prize card
            if(playerScore <=0){

                opponent.winRound(p);
                playerWins++;

                displayWin1 = true;
                if(p.checkIfWinner()) {
                    p.setWinner(true);
                    setCurrentState(new GameOverState(player1.getRoundWins(), cpu1.getRoundWins(),p.getName()));
                }
            }
            //will attach matching energyCard to the activeCard
            attachEnergyCard(p,g);
            g.drawImage(p.getActiveCard().getBitmap(),activeCardX, activeCardTop, cardWidth , cardHeight);

        }
    }
    public void renderChooseCardMenu(Painter g) {
        // screen which pops up to allow player to look at cards in more detail before choosing which one to play with
        if(isChooseCard){
            chooseCardMenu.render(g);
            if(player1.isMyTurn()) {
                g.drawImage(player1.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
            }
        }
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
        cpu1.setPrizeCards(player2PrizeCards);
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
        } else if(p.equals(cpu1)) {
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
    public void dealButtonOnTouch(int scaledX, int scaledY) {
        if(dealButton.isPressed(scaledX,scaledY)) {
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
                playButtonOnTouch(scaledX, scaledY);
            }
        } else {
            dealButton.cancel();
        }
    }
    public void playButtonOnTouch(int scaledX, int scaledY) {
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
    public boolean pauseButtonOnTouch(int scaledX, int scaledY) {
        if (pauseButton.isPressed(scaledX, scaledY)) {
            //pause the game
            isPause = true;
            pauseButton.cancel();
            return  true;
        } else {
            pauseButton.cancel();
        }
        return  false;
    }
    public void chooseCardOnTouch(MotionEvent e, int scaledX, int scaledY, Player p, ArrayList<Button> playerBench) {
        if (e.getAction() == MotionEvent.ACTION_DOWN && p.isMyTurn() && isCardRetreated ) {
            //find out which card player 1  has chosen as their new card
            for(int i = 0; i <playerBench.size(); i++) {
                playerBench.get(i).onTouchDown(scaledX, scaledY);
            }
            for(int i = 0; i < playerBench.size(); i++) {
                if(playerBench.get(i).isPressed(scaledX, scaledY) && !isChooseCard && areCardsDrawn && p.getActiveCard() == null) {
                    isChooseCard = true;
                    positionOfCardChosen = i;
                    playerBench.get(i).cancel();
                } else {
                    playerBench.get(i).cancel();
                }
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


    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPrizeCardError(boolean prizeCardError) {
        this.prizeCardError = prizeCardError;
    }

    public Player getPlayer1() {
        return player1;
    }

    public CPU getPlayer2() {
        return cpu1;
    }

    public void setAttackPlayer1(boolean attackPlayer1) {
        this.attackPlayer1 = attackPlayer1;
    }

    public void setAttackPlayer2(boolean attackPlayer2) {
        this.attackPlayer2 = attackPlayer2;
    }

    public void setRetreatError(boolean retreatError) {
        this.retreatError = retreatError;
    }

    public void setCardRetreated(boolean cardRetreated) {
        isCardRetreated = cardRetreated;
    }

    public void setEvolvePlayer1(boolean evolvePlayer1) {
        this.evolvePlayer1 = evolvePlayer1;
    }

    public void setEvolvePlayer2(boolean evolvePlayer2) {
        this.evolvePlayer2 = evolvePlayer2;
    }

    public void setChooseCard(boolean chooseCard) {
        isChooseCard = chooseCard;
    }

    public boolean isChooseCard() {
        return isChooseCard;
    }
}