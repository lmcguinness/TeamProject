package com.example.societyslam.societyslam.ai;


import com.example.societyslam.societyslam.GameObjects.Deck;
import com.example.societyslam.societyslam.GameObjects.Player;
import com.example.societyslam.societyslam.GameObjects.SocietyCard;
import com.example.societyslam.societyslam.GameObjects.StudentBehaviourCard;



import java.util.ArrayList;

/**
 * Created by James on 09/04/2017.
 */
/**
 *This is a class for a CPU object
 *this class extends Player
 *an instance of this object is created in OnePlayerState
 * contains additional methods which allow for one player games
 */
public class CPU extends Player {
    public static ArrayList<SocietyCard> inOrderOfHpLevel = new ArrayList<SocietyCard>();
    public static ArrayList<SocietyCard> inOrderOfAttackDamage = new ArrayList<SocietyCard>();
    public static ArrayList<SocietyCard> inOrderOfOverallPosition = new ArrayList<SocietyCard>();
    SocietyCard cardOfChoice;
    private static boolean isTalking;
    private  static int whichStatement;
    /**
     * This constructor creates a player object
     * @param myCards This player's deck of cards
     * @param activeCard This player's active society card
     * @param bench This player's bench, it consists of five society cards
     * @param prizeCards This player's six prize cards, one is flipped over and available to use after the player wins a round
     * @param myTurn Set to true if it is this player's turn, a player's turn ends after they attack
     * @param roundWins This players number of rounds won
     */
    public CPU(Deck myCards, SocietyCard activeCard, ArrayList<SocietyCard> bench, ArrayList<StudentBehaviourCard> prizeCards, boolean myTurn, int roundWins) {
        super(myCards, activeCard, bench, prizeCards, myTurn, roundWins);
    }
    /**
     *This method is implemented in to return the current state of the isTalking variable
     * @return  isTalking
     * */
    public static boolean getIsTalking(){
        return isTalking;
    }
    /**
     *This method is implemented in to set a new value to the variable in the OnePlayerState
     *  @param isTalking1
     *  @return  isTalking
     * */
    public static void setIsTalking(boolean isTalking1){
        isTalking = isTalking1;
    }
    /**
     *This method is implemented in to return the current state of the whichStatement variable
     * @return  isTalking
     * */
    public static int getWhichStatement(){
        return whichStatement;
    }
    /**
     * implemented in superclass
     * */
    @Override
    public int getRoundWins() {
        return super.getRoundWins();
    }
    /**
     * implemented in superclass
     */
    @Override
    public void setRoundWins(int wins) {
        super.setRoundWins(wins);
    }
    /**
     * implemented in superclass
     */
    @Override
    public Deck getMyCards() {
        return super.getMyCards();
    }
    /**
     * implemented and called from super class
     */
    @Override
    public void setMyCards(Deck myCards) {
        super.setMyCards(myCards);
    }
    /**
     * implemented and called from super class
     */
    @Override
    public SocietyCard getActiveCard() {
        return super.getActiveCard();
    }
    /**
     * implemented and called from super class
     */
    @Override
    public void setActiveCard(SocietyCard activeCard) {
        super.setActiveCard(activeCard);
    }
    /**
     * implemented and called from super class
     */
    @Override
    public boolean isWinner() {
        return super.isWinner();
    }
    /**
     * implemented and called from super class
     */
    @Override
    public void setWinner(boolean winner) {
        super.setWinner(winner);
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public boolean checkIfWinner(Player opponent) {
        return super.checkIfWinner(opponent);
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public ArrayList<SocietyCard> getBench() {
        return super.getBench();
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public void setBench(ArrayList<SocietyCard> bench) {
        super.setBench(bench);
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public ArrayList<StudentBehaviourCard> getPrizeCards() {
        return super.getPrizeCards();
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public void setPrizeCards(ArrayList<StudentBehaviourCard> prizeCards) {
        super.setPrizeCards(prizeCards);
    }
    /**
     * implemented and called from super class
     */
    @Override
    public boolean isMyTurn() {
        return super.isMyTurn();
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public void setMyTurn(boolean myTurn) {
        super.setMyTurn(myTurn);
    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public void attack(Player opponent) {
        super.attack(opponent);
    }
    /**
     * This method is implemented to allow the CPU to retreat a chosen card
     * @param cpu1
     */
    public void retreat(CPU cpu1){
        cpu1.getActiveCard().retreat(cpu1.getActiveCard().getEnergyCards(), cpu1);
    }
    /**
     * This method  is implemented to allow the CPU to move a card from the bench to the active slot
     * @param cpu1
     * @param card
     */
    public void moveCard(CPU cpu1, SocietyCard card){
        int position=0 ;
        for(int i = 0; i< 5;i++){
            if(card.getIdentifier() == cpu1.getBench().get(i).getIdentifier()){
                position = i;
            }
        }
        cpu1.setActiveCard(cpu1.getBench().remove(position));
       // PlayState.setCurrentCardInPlay(cpu1.getActiveCard());

    }
    /**
     * this method is implemented and called from super class
     */
    @Override
    public void useStudentBehaviourCard(StudentBehaviourCard card, Player opponent) {
        super.useStudentBehaviourCard(card, opponent);
    }
    /**
     * This method  is implemented to allow the CPU to evolve a card
     * @param cpu1
     * @param Card
     */
    public  void evolve(CPU cpu1, SocietyCard Card){
        cpu1.getActiveCard().evolve();
    }
    /**
     * This method  is implemented to allow the CPU to stall before an action has taken place
     * Generates a wait time to make the game more interactive as the user has the impression the
     * computer is taking tim to think about next action
     * @param timeMilliseconds
     */
    public void stall(int timeMilliseconds){
          try{
              Thread.sleep(timeMilliseconds);
          }catch(InterruptedException e){
              e.printStackTrace();
          }
    }
    /**
     * This method is implemented to allow the CPU to give feedback to the opponent
     */
    public void talk(Player opponent, CPU cpu1) {
        isTalking = true;
        if (opponent.getAttackDamage() < 20) {
            whichStatement=0;
            System.out.println("Is that all you got?");
        } else if (opponent.getAttackDamage() >= 20 && opponent.getAttackDamage() <=40) {
            whichStatement=1;
            System.out.println("Good Move");
        }else{
            whichStatement=2;
            System.out.println("Well Done, great move");
        }
        if(cpu1.getActiveCard().getAttackStrength() > 20){
            whichStatement=3;
            System.out.println("Are you ready for this?");
        }
        if(opponent.isWinner()){
            whichStatement=4;
            System.out.println("Well done, good game!");
        }else{
            whichStatement=5;
            System.out.println("Better luck next time");
        }
    }
    /**
     * This method is implemented and allows the CPU to scan their current cards and sort them in order to make a
     * rational choice as to what card is best to play
     * @param cpu1
     * @param opponent
     */
    public void scanCards(CPU cpu1, Player opponent){

        //initialise arrays
      // add current active card to array
            inOrderOfHpLevel.add(cpu1.getActiveCard());
            inOrderOfAttackDamage.add( cpu1.getActiveCard());
            inOrderOfOverallPosition.add( cpu1.getActiveCard());
        //add cards in bench to array
        for(int i =1; i < 5 ; i++) {
            inOrderOfHpLevel.add(cpu1.getBench().get(i-1));
            inOrderOfAttackDamage.add(cpu1.getBench().get(i-1));
            inOrderOfOverallPosition.add(cpu1.getBench().get(i-1));
        }
        //calculate each cards current attack damage against opponents current card in play
        // i.e. what would the attack damage be if CPU used this card to attack
        for(int i =0; i<5;i++){
            if(inOrderOfAttackDamage.get(i).getType() == opponent.getActiveCard().getResistance()){
               inOrderOfAttackDamage.get(i).setPotentialAttackDamage(inOrderOfAttackDamage.get(i).getAttackStrength()/2);
            }else if(inOrderOfAttackDamage.get(i).getType() == opponent.getActiveCard().getWeakness()){
                inOrderOfAttackDamage.get(i).setPotentialAttackDamage(inOrderOfAttackDamage.get(i).getAttackStrength()*2);
            }else{
                inOrderOfAttackDamage.get(i).setPotentialAttackDamage(inOrderOfAttackDamage.get(i).getAttackStrength());
            }
        }

        //sort respective arrays in ascending order of respective characteristics
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfAttackDamage.get(i).getPotentialAttackDamage() > inOrderOfAttackDamage.get(j).getPotentialAttackDamage()){
                    temp = inOrderOfAttackDamage.get(i);
                    inOrderOfAttackDamage.set(j,inOrderOfAttackDamage.get(i));
                    inOrderOfAttackDamage.set(i, temp);
                }
            }
        }
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfHpLevel.get(i).getHp() > inOrderOfHpLevel.get(j).getHp()){
                    temp = inOrderOfHpLevel.get(i);
                    inOrderOfHpLevel.set(j,inOrderOfHpLevel.get(i));
                    inOrderOfHpLevel.set(i, temp);
                }
            }
        }
    }
    /**
     * This method  implements which card the CPU chooses to play in which scenario
     * @param cpu1
     * @param opponent
     * @return society card
     */
    public SocietyCard chooseCard(Player opponent, CPU cpu1){
        //work out each cards best overall standing giving a weighting of two to attack damage and a weighting of one to besthp
        for(int i =0; i < 5; i++){
            for(int j =i+1; j<5;j++){
                if(inOrderOfAttackDamage.get(i).getIdentifier() == inOrderOfHpLevel.get(j).getIdentifier()) {
                    int AttackDamage=i;
                    int BestHp = j;
                    // add one to each variable to take into account first position zero
                    // therefore a 'score' out of 15 is generated for each card
                    AttackDamage = (AttackDamage +1)*2;
                    BestHp = BestHp +1;
                    inOrderOfAttackDamage.get(i).setOverallPosition(AttackDamage + BestHp);
                }
                }
            }
        //sort cards now, in ascending order of overall standing
        for(int i =0; i < 5; i++){
            SocietyCard temp;
            for(int j =i+1; j<5;j++){
                if(inOrderOfOverallPosition.get(i).getOverallPosition() > inOrderOfOverallPosition.get(j).getOverallPosition()){
                    temp = inOrderOfOverallPosition.get(i);
                    inOrderOfOverallPosition.set(j,inOrderOfOverallPosition.get(i));
                    inOrderOfOverallPosition.set(i, temp);
                }
            }
        }
        // if opponent is in last round or
        // if opponent is winning by three or more rounds
        // if opponent has an active card with a better attack damage than that of the lat card in our array
        // use card with best attack damage
        if(opponent.getRoundWins() >= 5||(opponent.getRoundWins()-cpu1.getRoundWins())>=3 || opponent.getActiveCard().getAttackStrength() >= inOrderOfOverallPosition.get(inOrderOfAttackDamage.size()-1).getPotentialAttackDamage() ){
            return inOrderOfAttackDamage.get(inOrderOfAttackDamage.size()-1);
        }else {
            //if the card chosen has a weakness to the opponents current card in play choose the second best card
            if (opponent.getActiveCard().getType() != inOrderOfOverallPosition.get(inOrderOfOverallPosition.size()-1).getWeakness()) {
                return inOrderOfOverallPosition.get(inOrderOfOverallPosition.size() -1);
            } else {
                return inOrderOfOverallPosition.get(inOrderOfOverallPosition.size() - 2);
            }
        }
    }
    /**
     * This method  implements which move and in what order the CPU makes
     * @param opponent
     * @param cpu1
     */
     public void makeMove(CPU cpu1, Player opponent){
       //  talk(opponent, cpu1);
        scanCards(cpu1, opponent);
        cardOfChoice = chooseCard(opponent, cpu1);
        if(cardOfChoice == cpu1.getActiveCard()){
            cpu1.stall(3000);
            attack(opponent);
        }else{
            cpu1.stall(3000);
            retreat(cpu1);
            cpu1.stall(3000);
            moveCard(cpu1, cardOfChoice);
            cpu1.stall(3000);
            attack(opponent);
        }
    }

}
