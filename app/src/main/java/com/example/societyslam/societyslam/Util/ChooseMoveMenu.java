package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.State.PlayState;

/**
 * This class is for a choose move menu object
 */

public class ChooseMoveMenu extends Menu {

    private Button attackButton;
    private Button retreatButton;
    private Button evolveButton;
    private Button useStudentBehaviourCardButton;
    private int menuButtonLeft = 316,menuButtonRight = 484;
    private int attackButtonTop = 125,  attackButtonBottom = 165;
    private int retreatButtonTop = 185, retreatButtonBottom = 225;
    private int evolveButtonTop = 245,evolveButtonBottom = 285;
    private int useStudentBehaviourCardButtonTop = 305, useStudentBehaviourCardButtonBottom = 350;


    /**
     * This method constructs a  choose move menu object
     *
     * @param menuBackground - the image that is the menu background
     * @param xPos           - the X coordinate where the menu will be displayed on screen
     * @param yPos           - the Y coordinate where the menu will be displayed on screen
     */
    public ChooseMoveMenu(Bitmap menuBackground, int xPos, int yPos) {
        super(menuBackground, xPos, yPos);
        initialiseButtons();
    }

    /**
     * This method initialises the buttons needed for the choose move menu
     */
    @Override
    public void initialiseButtons() {
        attackButton = new Button(menuButtonLeft, attackButtonTop, menuButtonRight, attackButtonBottom, Assets.attackButton);
        retreatButton = new Button(menuButtonLeft, retreatButtonTop, menuButtonRight,retreatButtonBottom, Assets.retreatButton);
        evolveButton = new Button(menuButtonLeft,evolveButtonTop, menuButtonRight,evolveButtonBottom, Assets.evolveButton);
        useStudentBehaviourCardButton = new Button(menuButtonLeft, useStudentBehaviourCardButtonTop, menuButtonRight,useStudentBehaviourCardButtonBottom, Assets.societyCardButton);

    }

    /**
     * This method renders the choose move menu background and the choose move menu buttons
     * @param g - the painter
     */
    @Override
    public void render(Painter g) {
        g.drawImage(this.getMenuBackground(),this.getxPos(), this.getyPos());
        attackButton.render(g);
        retreatButton.render(g);
        evolveButton.render(g);
        useStudentBehaviourCardButton.render(g);

    }

    /**
     *This method determines what happens when the buttons are pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void onTouch(int scaledX, int scaledY, PlayState playState) {

        attackButton.onTouchDown(scaledX,scaledY);
        retreatButton.onTouchDown(scaledX,scaledY);
        evolveButton.onTouchDown(scaledX,scaledY);
        useStudentBehaviourCardButton.onTouchDown(scaledX,scaledY);

        attackButtonOnTouch(scaledX,scaledY,playState);
        retreatButtonOnTouch(scaledX,scaledY,playState);
        evolveButtonOnTouch(scaledX,scaledY,playState);
        useStudentBehaviourCardButtonOnTouch(scaledX,scaledY,playState);




    }

    /**
     * This method attacks the player whose turn it is not when the attack button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void attackButtonOnTouch(int scaledX, int scaledY, PlayState playState) {

        Player player1 = playState.getPlayer1();
        Player player2 = playState.getPlayer2();
        if (attackButton.isPressed(scaledX, scaledY) && playState.isMenu()) {
            playState.setPrizeCardError(false);
            playState.setMenu(false);
            attackButton.cancel();
            if (player1.isMyTurn()) {

                //call the attack method
                player1.attack(player2);
                //set to true to display the attack which was used and how many points lost

                playState.setAttackPlayer1(true);
            } else {

                //call the attack method
                player2.attack(player1);
                //set to true to display the attack which was used and how many points lost
                playState.setAttackPlayer2(true);

            }
        } else {
            //cancel attack button
            attackButton.cancel();
        }
    }


    /**
     * This method retreats the active card of the player whose turn it is when the retreat button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void retreatButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        Player player1 = playState.getPlayer1();
        Player player2 = playState.getPlayer2();
        if (retreatButton.isPressed(scaledX, scaledY) && playState.isMenu()) {

            playState.setPrizeCardError(false);
            playState.setMenu(false);
            //retreatButton.cancel();
            if (player1.isMyTurn()) {
                if(Assets.currentCardInPlay != null){
                    // move the current card back to bench
                    player1.retreat();
                    // remove previous active card from board
                    Assets.currentCardInPlay = null;

                    playState.setRetreatError(false);
                    playState.setCardRetreated(true);
                    playState.setMenu(false);
                }else {
                    // Validation for if the user selects retreat option and no cards are in play
                    System.out.println("Sorry, there must be a card in play in order to retreat");
                    //display to the user that there must be a card in play in order to retreat

                    playState.setRetreatError(true);
                    //remove menu screen
                    playState.setMenu(false);
                }
            } else {
                if(Assets.currentCardInPlay2 != null){
                    //call retreat method
                    player2.retreat();
                    Assets.currentCardInPlay2 = null;

                    playState.setCardRetreated(true);
                    playState.setMenu(false);
                    playState.setRetreatError(false);
                }else{
                    // Validation for if the user selects retreat option and no cards are in play
                    System.out.println("Sorry, there must be a card in play in order to retreat");
                    //remove menu screen
                    playState.setMenu(false);
                    //display to the user that there must be a card in play in order to retreat
                    playState.setRetreatError(true);
                }
            }
        } else {
            //cancel retreat button
            retreatButton.cancel();
        }
    }

    /**
     *This method evolves the active card of the player whose turn it is when the evolve button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void evolveButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        Player player1 = playState.getPlayer1();
        Player player2 = playState.getPlayer2();
        if (evolveButton.isPressed(scaledX, scaledY) && playState.isMenu()) {

            playState.setPrizeCardError(false);
            playState.setMenu(false);

            evolveButton.cancel();
            if (player1.isMyTurn()) {
                //call evolve method for player 1's card
                player1.getActiveCard().evolve();
                playState.setEvolvePlayer1(true);
            } else {
                //call evolve method for player 2's card
                player2.getActiveCard().evolve();
                playState.setEvolvePlayer2(true);
            }
        } else {
            //cancel the evolve button
            evolveButton.cancel();
        }

    }

    /**
     *This method uses one of the prize cards that has been won by the player whose turn it is when the use student behaviour button is pressed button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */

    public void useStudentBehaviourCardButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        Player player1 = playState.getPlayer1();
        Player player2 = playState.getPlayer2();
        if (useStudentBehaviourCardButton.isPressed(scaledX, scaledY) && playState.isMenu()) {
            if (player1.isMyTurn()) {
                if (player1.checkIfPlayerHasFlippedPrizeCards(player1)) {
                    player1.useStudentBehaviourCard(player1.getPrizeCards().get(0), player2);
                    playState.setMenu(false);
                    playState.setPrizeCardError(false);
                    useStudentBehaviourCardButton.cancel();
                } else {

                    playState.setPrizeCardError(true);
                }
            } else {
                if (player2.checkIfPlayerHasFlippedPrizeCards(player2)) {
                    player2.useStudentBehaviourCard(player2.getPrizeCards().get(0), player1);

                    playState.setMenu(false);
                    playState.setPrizeCardError(false);
                    useStudentBehaviourCardButton.cancel();
                }
                else{
                    playState.setPrizeCardError(true);
                }
            }
        } else {
            useStudentBehaviourCardButton.cancel();
        }

    }


}
