package com.example.societyslam.societyslam.State;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;
import com.example.societyslam.societyslam.GameObjects.Type;
import com.example.societyslam.societyslam.Util.Button;
import com.example.societyslam.societyslam.Util.ChooseCardMenu;
import com.example.societyslam.societyslam.Util.PauseMenu;
import com.example.societyslam.societyslam.Util.ChooseMoveMenu;
import com.example.societyslam.societyslam.Util.Painter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.example.societyslam.societyslam.Game.MainActivity.mediaPlayer;

/**
 * Created by Leanne McGuinness, Chloe Mc Ateer, Chloe Mullan and Aoife Brown
 * The playstate acts as the games play screen for society slam, it displays the board and the cards for the game,
 * it extends state
 */
public class PlayState extends State {

    private PauseMenu pauseMenu;
    private ChooseMoveMenu chooseMoveMenu;
    private ChooseCardMenu chooseCardMenu;
    private boolean isStart = true, dealCards = true, retreatError;
    boolean isMenu,attackPlayer1, attackPlayer2,evolvePlayer1,evolvePlayer2, displayWin = false;
    private boolean isPause = false,isChooseCard =false,areCardsDrawn = false,isCardRetreated = false;
    public static boolean player1Winner, player2Winner, renderAnimation;

    private Button pauseButton, p1Card0,p1Card1, p1Card2,p1Card3,
                   p1Card4, p2Card0, p2Card1,p2Card2, p2Card3, p2Card4, currentCard1Button, currentCard2Button, playButton, dealButton;

    private ArrayList<Button> player1Bench;
    private ArrayList<Button> player2Bench;

    private int buttonTop = 385,buttonBottom = 444,buttonLeft = 336,buttonRight = 504;
    private int pauseButtonTop = 385,  pauseButtonBottom = 444, pauseButtonLeft = 266,pauseButtonRight = 326;

    private int menuX = 120, menuY = 50;
    private int chooseCardMenuX = 75, chooseCardMenuY = 95;

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


    private boolean prizeCardError = false;

    private static int STARTING_CARD_NUMBER = 5;
    int dealCardSound =1, cardMove =1;
    private static int player1Wins=0, player2Wins=0;
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

        pauseMenu = new PauseMenu(Assets.pauseMenu, menuX, menuY);
        chooseMoveMenu = new ChooseMoveMenu(Assets.menubg, menuX, menuY);
        chooseCardMenu = new ChooseCardMenu(Assets.chooseCardMenu, chooseCardMenuX, chooseCardMenuY);




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
        Assets.attackAnim.update(delta);
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
        g.drawString(" "+ player2.getRoundWins(), player2WinsX,player2WinsY, Color.WHITE);

        pauseButton.render(g);
        // when card has been retreated - render buttons
        if(isCardRetreated) {
           for(int i = 0; i < player1Bench.size(); i++) {
               player1Bench.get(i).render(g);
               player2Bench.get(i).render(g);
           }
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
            }

            if (player1.getActiveCard() != null && player2.getActiveCard() != null) {
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
           renderActiveCard(g, player1, player2, player1ScoreX, scoreY, player1.getRoundWins(), player1ActiveCardLeft);
            renderActiveCard(g, player2, player1, player2ScoreX, scoreY, player2.getRoundWins(), player2ActiveCardLeft);
        }
        if(player1.getMyCards().getMyDeck().size()>0){
            drawCards(g);

        }

        renderAttack(g);
        renderEvolve(g);
        renderMenu(g);
        renderChooseCardMenu(g);

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
            renderAnimation=false;
            currentCard1Button.onTouchDown(scaledX, scaledY);
            currentCard2Button.onTouchDown(scaledX, scaledY);

            //Check to see which button has been pressed
            if (!isStart && !dealCards) {

                if (currentCard1Button.isPressed(scaledX, scaledY) && player1.isMyTurn() || currentCard2Button.isPressed(scaledX, scaledY) && player2.isMyTurn()) {
                    //play sound effect
                    Assets.playSound(Assets.buttonClickID);
                    retreatError = false;
                    isMenu = true;
                    attackPlayer1 = false;
                    attackPlayer2 = false;
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
               chooseMoveMenu.onTouch(scaledX,scaledY,this);
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
            pauseMenu.onTouch(scaledX, scaledY, this);
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN  ) {
            chooseCardMenu.onTouch(scaledX,scaledY,this,positionOfCardChosen);
            chooseCardOnTouch(e, scaledX, scaledY, player1, player1Bench);
            chooseCardOnTouch(e, scaledX, scaledY, player2, player2Bench);
        }

        return false;
    }


    /**
     * This method inititalises the buttons
     */
    public void initialiseButtons() {
        playButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.start);
        dealButton = new Button(buttonLeft, buttonTop, buttonRight, buttonBottom, Assets.dealButton);
        pauseButton = new Button(pauseButtonLeft,pauseButtonTop,pauseButtonRight,pauseButtonBottom,Assets.pause);
    }

    /**
     * This method initalises the card positions as buttons
     */
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

        // placing buttons in the position of where player 2's cards are on the bench so they can choose which card to attack with
        p2Card0 = new Button(player2Cardleft, player2Card0Top, player2CardRight , player2Card0Bottom, null);
        p2Card1 = new Button(player2Cardleft, player2Card0Bottom, player2CardRight , player2Card1Bottom, null);
        p2Card2 = new Button(player2Cardleft, player2Card1Bottom, player2CardRight , player2Card2Bottom, null);
        p2Card3 = new Button(player2Cardleft, player2Card2Bottom, player2CardRight , player2Card3Bottom, null);
        p2Card4 = new Button(player2Cardleft, player2Card3Bottom, player2CardRight , player2Card4Bottom, null);

        player2Bench = new ArrayList<Button>();
        player2Bench.add(p2Card0);
        player2Bench.add(p2Card1);
        player2Bench.add(p2Card2);
        player2Bench.add(p2Card3);
        player2Bench.add(p2Card4);

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

    /**
     * This method renders a players active card in the allocated space on the screen
     * @param g - the painter
     * @param p - the player
     * @param opponent - the other player
     * @param scoreX - the X coordinate of the player's score
     * @param scoreY - the Y coordinate of the player's score
     * @param playerWins - how many rounds the player has won
     * @param activeCardX - the X coordinate of the players active card
     */
    public void renderActiveCard(Painter g, Player p, Player opponent, int scoreX, int scoreY, int playerWins, int activeCardX ) {
        int playerScore;
        if(p.getActiveCard() != null){

            //Set HP levels of the active cards to the players score on the screen
            playerScore = p.getActiveCard().getHp();
            g.setFont(Typeface.DEFAULT_BOLD, nameTextSize);
            g.drawString("  "+ playerScore, scoreX, scoreY, Color.WHITE);

            //If player ones score falls below zero tell them that player 2 has won this round
            //change the card in the middle for player1 and give player 2 a prize card
            if(playerScore <=0){
                opponent.winRound(p);
                displayWin = true;
                renderWinRound(g,opponent, p);

                if(opponent.checkIfWinner()) {
                    opponent.setWinner(true);
                    setCurrentState(new GameOverState(player1.getRoundWins(), player2.getRoundWins(),opponent.getName()));
                }
            }
            //will attach matching energyCard to the activeCard
            attachEnergyCard(p,g);
            g.drawImage(p.getActiveCard().getBitmap(),activeCardX, activeCardTop, cardWidth , cardHeight);

        }
    }

    /**
     * This method renders the menu
     * @param g - the painter
     */
    public void renderMenu(Painter g) {
        if (isMenu) {
            displayWin = false;
            chooseMoveMenu.render(g);
            if (prizeCardError) {
                g.drawString("You Have No PrizeCards in Play!", 225, 400, Color.RED);
            }
        }
    }

    /**
     * This method renders the choose card menu
     * @param g - the painter
     */
    public void renderChooseCardMenu(Painter g) {
        // screen which pops up to allow player to look at cards in more detail before choosing which one to play with
        if(isChooseCard){
            chooseCardMenu.render(g);
            if(player1.isMyTurn()) {
                g.drawImage(player1.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
            }else{
                g.drawImage(player2.getBench().get(positionOfCardChosen).getPicture(), 116, 105, 284, 224);
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
        ArrayList<Integer> randoms1 = new ArrayList<Integer>();
        ArrayList<Integer> randoms2 = new ArrayList<Integer>();

        //Each player should be given 6 random student behaviour cards
        for (int i = 0; i< 6; i++) {
            Random generator = new Random();
            int random = generator.nextInt(10) + 1;
            if (!checkDuplicates(randoms1, random)) {
                randoms1.add(random);
                player1PrizeCards.add(Assets.prizeCardDeck1.get(random));
            } else {
                do {
                    random = generator.nextInt(10) + 1;
                } while (checkDuplicates(randoms1, random));
                randoms1.add(random);
                player1PrizeCards.add(Assets.prizeCardDeck1.get(random));
            }
        }
        for (int i = 0; i< 6; i++) {
            Random generator = new Random();
            int random = generator.nextInt(10) + 1;
            if (!checkDuplicates(randoms2, random)) {
                randoms2.add(random);
                player2PrizeCards.add(Assets.prizeCardDeck2.get(random));
            } else {
                do {
                    random = generator.nextInt(10) + 1;
                } while (checkDuplicates(randoms2, random));
                randoms2.add(random);
                player2PrizeCards.add(Assets.prizeCardDeck2.get(random));
            }
        }
        //set both players prize cards
        player1.setPrizeCards(player1PrizeCards);
        player2.setPrizeCards(player2PrizeCards);
    }

    private boolean checkDuplicates(List<Integer> randoms, Integer num) {
        for (Integer i: randoms) {
            if (num == i) {
                return true;
            }
        }
        return false;
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


    /**
     * This method renders the text when a player is attacked
     * @param g - the painter
     */
    public void renderAttack(Painter g) {
        //If player one clicks attack, display their attack and how many points player2 loses on the board
        //If player two attacks, display their attack and how many points player1 loses
        if(renderAnimation) {
            if (attackPlayer1) {
                player1.renderAttack(g, player2);
                Assets.attackAnim.render(g, player2ActiveCardLeft, activeCardTop, player2ActiveCardRight-player2ActiveCardLeft, activeCardBottom-activeCardTop);
            } else if (attackPlayer2) {
                player2.renderAttack(g, player1);
                Assets.attackAnim.render(g, player1ActiveCardLeft, activeCardTop, player1ActiveCardRight-player1ActiveCardLeft, activeCardBottom-activeCardTop);
            }
        }
    }

    /**
     * This method render the text when a player evolves their card
     * @param g - the painter
     */
    public void renderEvolve(Painter g) {
        if(evolvePlayer1) {
            player1.getActiveCard().renderEvolve(g);
        }
        if(evolvePlayer2) {
            player2.getActiveCard().renderEvolve(g);
        }
    }

    /**
     * This method renders the text when a player wins a round
     * @param g - the painter
     */
    public void renderWinRound(Painter g, Player p, Player opponent) {
        //If player ones score falls below zero
        if(displayWin){
            p.renderWinRound(g, opponent);
        }
    }

    /**
     * This method determines what happens when the deal button is pressed
     * @param scaledX - the X coordinate of where the screen is pressed
     * @param scaledY - the Y coordinate of where the screen is pressed
     */
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
                if (!player2.getMyCards().getMyDeck().isEmpty()) {
                    player2.setActiveCard(player2.getBench().remove(0));
                    Assets.currentCardInPlay2 = player2.getActiveCard();
                }
                dealCards = false;
            } else {
                playButtonOnTouch(scaledX, scaledY);
            }
        } else {
            dealButton.cancel();
        }
    }

    /**
     * This method determines what happens when the play button is pressed
     * @param scaledX - the X coordinate of where the screen is pressed
     * @param scaledY - the Y coordinate of where the screen is pressed
     */
    public void playButtonOnTouch(int scaledX, int scaledY) {
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

    /**
     * This method determines what happens when the play button is pressed
     * @param scaledX - the X coordinate of where the screen is pressed
     * @param scaledY - the Y coordinate of where the screen is pressed
     */
    public void pauseButtonOnTouch(int scaledX, int scaledY) {
        if (pauseButton.isPressed(scaledX, scaledY)) {
            //pause the game
            isPause = true;
            pauseButton.cancel();
        } else {
            pauseButton.cancel();
        }
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
     * This method sets if player 1 has won the game
     * @param player1Winner - true if they have won
     */
    public static void setPlayer1Winner(boolean player1Winner) {
        PlayState.player1Winner = player1Winner;
    }

    /**
     * This method sets if player 1 has won the game
     * @param player2Winner - true if they have won
     */
    public static void setPlayer2Winner(boolean player2Winner) {
        PlayState.player2Winner = player2Winner;
    }

    public static void setPlayer1Wins(int player1W){
        player1Wins = player1W;
    }
    public static void setPlayer2Wins(int player2W){
        player2Wins = player2W;
    }

    /**
     * This method sets if the game is paused
     * @param pause - true if the game is paused
     */
    public void setPause(boolean pause) {
        isPause = pause;
    }

    /**
     * This menu returns whether the game is paused or not
     * @return - true if the game is paused
     */
    public boolean isPause() {
        return isPause;
    }

    /**
     * This method returns whether the menu is displayed or not
     * @return true if menu is displayed
     */
    public boolean isMenu() {
        return isMenu;
    }

    /**
     * This method sets whether the menu is to be displayed or not
     * @param menu - true if menu is to be displayed
     */
    public void setMenu(boolean menu) {
        isMenu = menu;
    }


    /**
     * This method returns whether or not there is a prize card error
     * @return - true if there is a prize card error
     */
    public boolean isPrizeCardError() {
        return prizeCardError;
    }
    /**
     * This method sets whether there is a prize card error or not
     * @param prizeCardError - true if the player has not won any prize cards
     */
    public void setPrizeCardError(boolean prizeCardError) {
        this.prizeCardError = prizeCardError;
    }

    /**
     * This method gets player 1
     * @return - player 1
     */

    public Player getPlayer1() {
        return player1;
    }

    /**
     * This method gets player 2
     * @return - player 2
     */
    public  Player getPlayer2() {
        return player2;
    }


    /**
     * This method returns if player 1 has attacked
     * @return - true if player 1 has attacked
     */
    public boolean isAttackPlayer1() {
        return attackPlayer1;
    }
    /**
     * This method sets if player 1 has attacked
     * @param attackPlayer1 - true if player one has attacked
     */
    public void setAttackPlayer1(boolean attackPlayer1) {
        this.attackPlayer1 = attackPlayer1;
    }

    /**
     * This method returns if player 2 has attacked
     * @return - true if player 2 has attacked
     */
    public boolean isAttackPlayer2() {
        return attackPlayer2;
    }

    /**
     * This method sets if player 1 has attacked
     * @param attackPlayer2 - true if player one has attacked
     */
    public void setAttackPlayer2(boolean attackPlayer2) {
        this.attackPlayer2 = attackPlayer2;
    }

    /**
     * This method sets if there is a retreat error
     * @param retreatError - true if there is no active card to retreat
     */
    public void setRetreatError(boolean retreatError) {
        this.retreatError = retreatError;
    }

    /**
     * This method returns whether or not there is a retreat error
     * @return true if there is a retreatError
     */
    public boolean isRetreatError() {
        return retreatError;
    }

    /**
     * This method sets if a card has retreated
     * @param cardRetreated - true if card has retreated
     */
    public void setCardRetreated(boolean cardRetreated) {
        isCardRetreated = cardRetreated;
    }

    /**
     * This method returns whether the card has been retreated or not
     * @return true if card has been retreated
     */
    public boolean isCardRetreated() {
        return isCardRetreated;
    }

    /**
     * This method sets if player 1 has evolved their active card
     * @param evolvePlayer1 - true if player 1 has evolved
     */
    public void setEvolvePlayer1(boolean evolvePlayer1) {
        this.evolvePlayer1 = evolvePlayer1;
    }

    /**
     * This method returns whether or not player 1 has evolved their card
     * @return true if card has been evolved
     */
    public boolean isEvolvePlayer1() {
        return evolvePlayer1;
    }

    /**
     * This method returns whether or not player 2 has evolved their card
     * @return true if card has been evolved
     */
    public boolean isEvolvePlayer2() {
        return evolvePlayer2;
    }

    /**
     * This method sets if player 2 has evolved their active card
     * @param evolvePlayer2 - true if player 2 has evolved
     */
    public void setEvolvePlayer2(boolean evolvePlayer2) {
        this.evolvePlayer2 = evolvePlayer2;
    }

    /**
     * This  method sets if it is time to choose a card
     * @param chooseCard - true if it is time to choose a card
     */
    public void setChooseCard(boolean chooseCard) {
        isChooseCard = chooseCard;
    }

    /**
     * This method returns if it is time to choose a card
     * @return - true if it is time to choose a card
     */
    public boolean isChooseCard() {
        return isChooseCard;
    }

    /**
     * This method sets if the animation has to be rendered
     * @param renderAnimation1 true if the animation is to be rendered
     */
    public static void setRenderAnimation(boolean renderAnimation1){
        renderAnimation= renderAnimation1;
    }

    /**
     * This method returns if the animation has to be rendered
     * @return true if the animation is to be rendered
     */
    public static boolean isRenderAnimation() {
        return renderAnimation;
    }
}