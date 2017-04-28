package com.example.societyslam.societyslam.Util;

import android.graphics.Bitmap;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.State.OnePlayerState;
import com.example.societyslam.societyslam.State.PlayState;
import com.example.societyslam.societyslam.ai.CPU;

/**
 * This class is for a choose card menu object
 */

public class ChooseCardMenu extends Menu {


    private Button useCardButton;
    private Button cancelButton;
    private int buttonLeft = 456, buttonRight = 634;
    private int useCardButtonTop = 165, useCardButtonBottom = 205;
    private int cancelButtonTop = 225, cancelButtonBottom = 265;




    /**
     * This method constructs a menu object and calls the initalise buttons method
     * @param menuBackground - the bitmap that is the menu background
     * @param xPos           - the X coordinate where the menu will be displayed on screen
     * @param yPos           - the Y coordinate where the menu will be displayed on screen
     */
    public ChooseCardMenu(Bitmap menuBackground, int xPos, int yPos) {
        super(menuBackground, xPos, yPos);

        initialiseButtons();
    }

    /**
     * This method initialises the buttons needed for the choose card menu
     */

    @Override
    public void initialiseButtons() {
        useCardButton = new Button(buttonLeft,useCardButtonTop, buttonRight, useCardButtonBottom, Assets.useCard);
        cancelButton = new Button(buttonLeft,cancelButtonTop,buttonRight,cancelButtonBottom,Assets.cancel);

    }


    /**
     * This method renders the choose card menu background and the choose card menu buttons
     * @param g - the painter
     */
    @Override
    public void render(Painter g) {
        g.drawImage(this.getMenuBackground(), this.getxPos(), this.getyPos());
        useCardButton.render(g);
        cancelButton.render(g);

    }


    /**
     *
     * This method determines what happens when the buttons are pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     * @param positionOfCardChosen - position in bench of card chosen
     */
    public void onTouch(int scaledX, int scaledY, PlayState playState, int positionOfCardChosen) {
        useCardButton.onTouchDown(scaledX, scaledY);
        cancelButton.onTouchDown(scaledX, scaledY);

        useCardButtonOnTouch(scaledX, scaledY, playState, positionOfCardChosen);
        cancelButtonOnTouch(scaledX, scaledY, playState);

    }

    /**
     * This method assigns the selected card as the players active card when the use card button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     * @param positionOfCardChosen - position in bench of card chosen
     */
    public void useCardButtonOnTouch(int scaledX, int scaledY, PlayState playState, int positionOfCardChosen) {
        Player player1 = playState.getPlayer1();
        Player player2 = playState.getPlayer2();
        if (useCardButton.isPressed(scaledX, scaledY)&& playState.isChooseCard()) {
            if (player1.isMyTurn()) {
                //give player 1 a new card of their choice and retreat their old card
                player1.setActiveCard(player1.getBench().remove(positionOfCardChosen));
                Assets.currentCardInPlay = player1.getActiveCard();
                playState.setCardRetreated(false);

                playState.setChooseCard(false);
            }else{
                //give player 2 a new card of their choice and retreat their old card
                player2.setActiveCard(player2.getBench().remove(positionOfCardChosen));
                Assets.currentCardInPlay2 = player2.getActiveCard();
                playState.setCardRetreated(false);
                playState.setChooseCard(false);
            }
            playState.setChooseCard(false);
            useCardButton.cancel();

        } else {
            useCardButton.cancel();
        }
    }


    /**
     * This button cancels selection of the card allowing another card to be chosed as the active card
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param playState - the play state where the menu is displayed
     */
    public void cancelButtonOnTouch(int scaledX, int scaledY, PlayState playState) {
        if (cancelButton.isPressed(scaledX, scaledY)&& playState.isChooseCard()) {

            playState.setChooseCard(false);
            cancelButton.cancel();
        } else {
            cancelButton.cancel();
        }
    }


    public void onTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate, int positionOfCardChosen) {
        useCardButton.onTouchDown(scaledX, scaledY);
        cancelButton.onTouchDown(scaledX, scaledY);

        useCardButtonOnTouch1(scaledX, scaledY, oneplayerstate, positionOfCardChosen);
        cancelButtonOnTouch1(scaledX, scaledY, oneplayerstate);

    }

    /**
     * This method assigns the selected card as the players active card when the use card button is pressed
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     * @param positionOfCardChosen - position in bench of card chosen
     */
    public void useCardButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate, int positionOfCardChosen) {
        Player player1 = oneplayerstate.getPlayer1();
        CPU cpu1 = oneplayerstate.getPlayer2();
        if (useCardButton.isPressed(scaledX, scaledY)&& oneplayerstate.isChooseCard()) {
            if (player1.isMyTurn()) {
                //give player 1 a new card of their choice and retreat their old card
                player1.setActiveCard(player1.getBench().remove(positionOfCardChosen));
                Assets.currentCardInPlay = player1.getActiveCard();
                oneplayerstate.setCardRetreated(false);

                oneplayerstate.setChooseCard(false);
            }else{
                //give player 2 a new card of their choice and retreat their old card
                cpu1.setActiveCard(cpu1.getBench().remove(positionOfCardChosen));
                Assets.currentCardInPlay2 = cpu1.getActiveCard();
                oneplayerstate.setCardRetreated(false);
                oneplayerstate.setChooseCard(false);
            }
            oneplayerstate.setChooseCard(false);
            useCardButton.cancel();

        } else {
            useCardButton.cancel();
        }
    }


    /**
     * This button cancels selection of the card allowing another card to be chosed as the active card
     * @param scaledX - the X coordinate of the touch event
     * @param scaledY - the Y coordinate of the touch event
     * @param oneplayerstate - the play state where the menu is displayed
     */
    public void cancelButtonOnTouch1(int scaledX, int scaledY, OnePlayerState oneplayerstate) {
        if (cancelButton.isPressed(scaledX, scaledY)&& oneplayerstate.isChooseCard()) {

            oneplayerstate.setChooseCard(false);
            cancelButton.cancel();
        } else {
            cancelButton.cancel();
        }
    }
}
