package com.example.societyslam.societyslam.GameObjects;

import com.example.societyslam.societyslam.Game.Assets;

import java.util.ArrayList;

/**
 *This is a class for a Player object
 *
 */
public class Player {

   private  Deck myCards;
    private SocietyCard activeCard;
    private ArrayList<Card> hand;
    private ArrayList<SocietyCard> bench;
    private ArrayList<StudentBehaviourCard> prizeCards;
    private boolean myTurn;
    private  int roundWins;
    private boolean winner;
    private static int attackDamage = 0;

    /**
     * This constructor creates a player object
     * @param myCards This player's deck of cards
     * @param activeCard This player's active society card
     * @param bench This player's bench, it consists of five society cards
     * @param prizeCards This player's six prize cards, one is flipped over and available to use after the player wins a round
     * @param myTurn Set to true if it is this player's turn, a player's turn ends after they attack
     */


    public Player(Deck myCards, SocietyCard activeCard, ArrayList<SocietyCard> bench, ArrayList<StudentBehaviourCard> prizeCards, boolean myTurn, int roundWins) {
        this.myCards = myCards;
        this.activeCard = activeCard;
        this.bench = bench;
        this.prizeCards = prizeCards;
        this.myTurn = myTurn;
        this.winner = false;
        this.roundWins = roundWins;
    }
    public static int getAttackDamage(){
        return  attackDamage;
    }
    public static void setAttackDamage(int attackDamage1){
        attackDamage = attackDamage1;
    }
    public int getRoundWins(){
        return roundWins;
    }
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
        if (this.getActiveCard().canAttack()) {
            System.out.println(this.getActiveCard().getName() + " used " + this.getActiveCard().getAttackName());
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
            System.out.println(opponent.getActiveCard().getName() + " 's HP is now " + opponent.getActiveCard().getHp());
            if(checkIfWinner(opponent)) {
                System.out.println("End of game");
            } else {
                this.myTurn = false;
                opponent.setMyTurn(true);
            }

        } else {
            System.out.print(this.getActiveCard().getName() + " does not have enough energy cards to use this attack");
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
     * It check if the player has received all its prize cards, if it has this player
     * is the winner.
     * It check if the opponent's deck is empty, if it is then this player is the
     * winner.
     * It check if the opponent's bench is empty, if it is then this player is the winner.
     * @param opponent other player
     * @return true if this player is the winner, false if they are not the winner
     */
    public boolean checkIfWinner(Player opponent) {
        if(this.getPrizeCards().isEmpty()) {
            this.setWinner(true);
            return true;
        } else if(opponent.getMyCards().getMyDeck().isEmpty()) {
            this.setWinner(true);
            return true;
        } else if(opponent.getBench().isEmpty()) {
            this.setWinner(true);
            return true;
        } else {
            return false;
        }
    }
}
