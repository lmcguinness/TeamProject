package com.example.societyslam.societyslam.GameObjects;

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.societyslam.societyslam.Game.Assets;
import com.example.societyslam.societyslam.State.GameOverState;
import com.example.societyslam.societyslam.State.PlayState;
import com.example.societyslam.societyslam.Util.Painter;

import java.util.ArrayList;

/**
 *This is a class for a Player object
 *
 */
public class Player {
   // public static PlayState playState;

   private  Deck myCards;
    private SocietyCard activeCard;
    private ArrayList<Card> hand;
    private ArrayList<SocietyCard> bench;
    private ArrayList<StudentBehaviourCard> prizeCards;
    private boolean myTurn;
    private  int roundWins;
    private boolean winner;
    private static int attackDamage = 0;
    private String name;
    private float textSize = 20;
    private int textX = 270, textY = 60, text2X = 314, text2Y = 80;
    private int winRoundTextX = 300, winRoundTextY = 115, winRoundText2X = 245, winRoundText2Y = 130;
    private boolean retreatError;

    /**
     * This constructor creates a player object
     * @param myCards This player's deck of cards
     * @param activeCard This player's active society card
     * @param bench This player's bench, it consists of five society cards
     * @param prizeCards This player's six prize cards, one is flipped over and available to use after the player wins a round
     * @param myTurn Set to true if it is this player's turn, a player's turn ends after they attack
     * @param roundWins This players number of rounds won
     */


    public Player(Deck myCards, SocietyCard activeCard, ArrayList<SocietyCard> bench, ArrayList<StudentBehaviourCard> prizeCards, boolean myTurn, int roundWins, String name) {
        this.myCards = myCards;
        this.activeCard = activeCard;
        this.bench = bench;
        this.prizeCards = prizeCards;
        this.myTurn = myTurn;
        this.winner = false;
        this.roundWins = roundWins;
        this.name = name;
        this.retreatError = false;
    }
    /**
     * This method returns the player's attack damage
     * this is called in playstates to show on screen what players have attacked with
     * @return the attack damage
     */
    public static int getAttackDamage(){
        return  attackDamage;
    }
    /**
     * This method sets new information into the attackDamage variable
     * @param attackDamage1
     * @return attackDamage
     */
    public static void setAttackDamage(int attackDamage1){
        attackDamage = attackDamage1;
    }
    /**
     * This method returns the number of rounds each player has won
     * @return roundWins
     */
    public int getRoundWins(){
        return roundWins;
    }
    /**
     * This method sets new information into the roundWins variable
     * @param wins
     */
    public void setRoundWins(int wins){
        roundWins = wins;
    }
    /**
     * This method returns the player's deck of cards
     * @return the deck of cards
     */
    public Deck getMyCards() {
        return myCards;
    }

    /**
     * This method sets new information into the player's deck of cards
     * @param myCards new deck of cards
     */
    public void setMyCards(Deck myCards) {
        this.myCards = myCards;
    }

    /**
     * This method returns the player's active society card
     * @return active society card
     */
    public SocietyCard getActiveCard() {
        return activeCard;
    }

    /**
     * This method sets a new society card as the player's active card
     * @param activeCard new active society card
     */
    public void setActiveCard(SocietyCard activeCard) {
        this.activeCard = activeCard;
    }

    /**
     * This method returns the array list of cards that is the player's bench
     * @return player's bench
     */
    public ArrayList<SocietyCard> getBench() {
        return bench;
    }

    /**
     * This method sets a new array list of cards as the player's bench
     * @param bench new bench
     */
    public void setBench(ArrayList<SocietyCard> bench) {
        this.bench = bench;
    }


    /**
     * This method returns the array list of prize cards
     * @return prize cards
     */
    public ArrayList<StudentBehaviourCard> getPrizeCards() {
        return prizeCards;
    }

    /**
     * This method sets a new array list of cards as the player's prize cards
     * @param prizeCards new prize cards
     */
    public void setPrizeCards(ArrayList<StudentBehaviourCard> prizeCards) {
        this.prizeCards = prizeCards;
    }

    /**
     * This method sets whether or not this player is the winner
     * @return winner
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * This method sets winner to true if this player is the winner
     * @param winner
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    /**
     * This method returns true if it is this player's turn
     * @return myTurn
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * This method sets the player's turn to true, if it is their turn
     * @param myTurn
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method checks if the player's active card can attack, it then checks if the opponent's card has
     * a weakness or resistance to this player's active card's type, if it does it adjusts the attack strength accordingly.
     * It then subtracts this player's active card's attack strength from the opponent's active card's HP and sets this
     * player's turn to false.
     * @param opponent the player whose card is being attacked
     */
    public void attack(Player opponent) {
        //Play attack sound effect
        Assets.playSound(Assets.attackID);
        int opponentNewHP;
            if (opponent.getActiveCard().getWeakness() == this.getActiveCard().getType()) {
                attackDamage = (2 * this.getActiveCard().getAttackStrength());
                opponentNewHP = opponent.getActiveCard().getHp() - attackDamage;
            } else if (opponent.getActiveCard().getResistance() == this.getActiveCard().getType()) {
                attackDamage = (this.getActiveCard().getAttackStrength() / 2);
                opponentNewHP = opponent.getActiveCard().getHp() -attackDamage;
            } else {
                attackDamage = this.getActiveCard().getAttackStrength();
                opponentNewHP = (opponent.getActiveCard().getHp() - attackDamage);
            }
            opponent.getActiveCard().setHp(opponentNewHP);


        this.myTurn = false;
        opponent.setMyTurn(true);


    }

    public void renderAttack(Painter g, Player opponent) {
        g.setFont(Typeface.DEFAULT, textSize);
        g.drawString("You attacked with " + this.getActiveCard().getAttackName(), textX, textY, Color.WHITE);
        g.drawString("minus " + this.getActiveCard().getAttackStrength() + " points " + opponent.getName(), text2X, text2Y, Color.WHITE );
    }

    public void retreat() {
        if(this.getActiveCard() == null) {
            this.retreatError = true;
        } else {
            Assets.playSound(Assets.oneCardID);
            this.getBench().add(this.getActiveCard());
            this.setActiveCard(null);
        }
    }

    public void renderRetreat(Painter g) {
        if (this.retreatError) {
            g.drawString("Sorry, there must be a card in play in order to retreat!", textX, textY, Color.WHITE);
        } else {
            g.drawString(this.getActiveCard().getName() + " has retreated", textX, textY, Color.WHITE);
        }
    }


    /**
     * This method checks if the student behaviour card to be used has a positive or negative effect on
     * a society card's HP. If it has a positive effect the student behaviour card's points are added to
     * this player's active card's HP.
     * If it has a negative effect the student behaviour card's points are subtracted from the opponent's
     * active card's HP
     *
     * @param card the student behaviour card to be used
     * @param opponent the other player
     */
    public void useStudentBehaviourCard(StudentBehaviourCard card, Player opponent) {

        System.out.println("You used " + card.getName());
        int newHP;
        int opponentNewHP;
        if(card.hasPositiveEffect()) {
            newHP = this.getActiveCard().getHp() + card.getCardPoints();
            this.getActiveCard().setHp(newHP);
        } else if(!card.hasPositiveEffect()) {
            opponentNewHP = opponent.getActiveCard().getHp() - card.getCardPoints();
            opponent.getActiveCard().setHp(opponentNewHP);
        }
    }



    /**
      * This method checks if anyone has won the game after a player finishes their turn
     * It checks if the player has received all its prize cards, if it has this player
     * is the winner.
     * @return true if this player is the winner, false if they are not the winner
     */
    public boolean checkIfWinner() {
        if(this.getRoundWins() == this.getPrizeCards().size()-1) {
            this.winner = true;
        }
        return this.winner;
    }

    public void winRound(Player opponent) {
        if(opponent.getActiveCard().getHp() <= 0) {
            this.roundWins ++;
            checkPrizeCardState(opponent);


        }
    }

    public void renderWinRound(Painter g, Player opponent) {
        g.drawString(this.getName() + " wins the round!", winRoundTextX, winRoundTextY, Color.WHITE);
        g.drawString(opponent.getName() + " has been given another card ", winRoundText2X, winRoundText2Y, Color.WHITE);
    }

    /**
     * This method checks to see if a player should be awarded a prize card
     * @param opponent- the losing player of the round
     */
    public void checkPrizeCardState(Player opponent) {
        if (opponent.getActiveCard().getHp() <= 0) {
            // If the hp of the active card gets below zero, retreat it
            opponent.setActiveCard(null);
            //move another card from the bench to replace it
            opponent.setActiveCard(opponent.getBench().remove(0));
            Assets.currentCardInPlay = opponent.getActiveCard();
           // playState.attachEnergyCard(opponent, Painter g);
            //give a prize card to the winner of the round
            flipPrizeCard();
            //add a new card to bench
            opponent.getBench().add(opponent.getMyCards().randomCard());
        }
    }


    /**
     * This method flips over a prize card for the winning player each time that player wins a round
     */
    public void flipPrizeCard() {
        for (int i= 0; i <this.getPrizeCards().size(); i++) {
            if (!this.getPrizeCards().get(i).isFlipped()) {
                this.getPrizeCards().get(i).flipCard();
                //play sound of card being flipped over
                Assets.playSound(Assets.prizeID);
                break;
            }
        }
    }

    public boolean checkIfPlayerHasFlippedPrizeCards(Player player) {
        boolean isFlipped = false;
        for (StudentBehaviourCard s : player.getPrizeCards()) {
            if (s.isFlipped()) {
                isFlipped = true;
                return isFlipped;
            } else {
                isFlipped = false;
            }
        }

        return isFlipped;
    }
}
