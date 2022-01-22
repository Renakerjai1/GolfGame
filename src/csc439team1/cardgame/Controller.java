package csc439team1.cardgame;

import java.lang.reflect.Array;
import java.util.*;

/**
 * This is the controller class that communicates with the CLIView to talk to the command line for
 * user output. Holds all of the logic for how the game works
 * @version 4.0
 */
public class Controller {
    private View view;//make this a view to allow both CLI & Test
    private int response,numberOfPlayers,gameTurnNumber,playersTurn, numberOfHoles,currentHole;
    private boolean gameOver = false;
    private Shoe playingshoe;
    private Stack<Card>discardpile= new Stack<>();
    private ArrayList<Player> currentPlayers= new ArrayList<>();

    /**
     * This method generates an object comparator for player scores so we
     * can sort the Arraylist from low to high
     */
    public static Comparator<Player> playerScoreComparator = new Comparator<Player>() {
        public int compare(Player s1, Player s2)
        {
            return s1.getScore()-s2.getScore();
        }
    };

    public Controller(View givenview) {
        view = givenview;playersTurn=0;gameOver=false;
        numberOfHoles=1;
        currentHole=1;
    }

    /**
     * The method which starts the game, a response of 3 should kill the game. The controller should prompt the view
     * for an input then appropriately process said input.
     */
    public void playGolf() throws Exception {
        Hand tempHand;
        view.titleScreen();

        numberOfPlayers=view.getNumPlayers();
        numberOfHoles= view.getNumHoles();
        dealToPlayers();//make two cards dealt faceup

        while (playingshoe.size()>0&& !gameOver && currentHole<=numberOfHoles) {//currently a redundancy
            gameTurnNumber++;
                //, which would display all input options a player can take, whose turn it is, the player's cards, and the card on top of the discard pile.
            response=view.drawTurn(currentPlayers.get(playersTurn),gameTurnNumber,discardpile,playingshoe.size());

            try {
                switch (response) {
                    case 1://
                        draw(1);
                        break;
                    case 2:
                        draw(2);
                        break;
                    case 3://quit game
                        gameOver = view.quitGame();
                        break;
                    }
            } catch (Exception e) {
                throw e;
            }

            if(currentPlayers.get(playersTurn).getHeldHand().isAllFaceUp()) {
                //if one person's is face up, check all, then either skip their turn or
                checkhole();
            } else {
                incrementplayersTurn();
                view.scoreBoard(currentHole, numberOfHoles, currentPlayers, gameOver);
            }
        }
}


    /**
     * @param response is the player's decision
     * @throws Exception throws an exception if there are no cards left in the playing shoe or discard pile
     *
     */
    public void draw(int response) {
        Card tempCard,tempCard2;
        int index;
        try {
            if (response == 1) {//draw from draw pile
                tempCard = new Card(playingshoe.pickCard());
                index = view.chooseCard(currentPlayers.get(playersTurn), tempCard, false);
                if (index == 6) {
                    tempCard2 = tempCard;//throwaway from draw pile
                } else {
                    tempCard2 = currentPlayers.get(playersTurn).getHeldHand().replace(tempCard, index);
                }
            } else {//draw from discard
                tempCard = discardpile.pop();
                tempCard2 = currentPlayers.get(playersTurn).getHeldHand().replace(tempCard, view.chooseCard(currentPlayers.get(playersTurn), tempCard, true));
            }
            addToDiscard(tempCard2);
        } catch (Exception e){
            System.out.println(e.getMessage()+" thrown in controller's draw");
        }
    }

    /**
     * tracks the player whose turn it is
     */
    public void incrementplayersTurn(){
        if(playersTurn>=numberOfPlayers-1) {
            playersTurn=0;
        } else{playersTurn++;}
    }

    /**
     * @param tempCard takes a card, flips is up, and adds it to the discard pile
     */
    public void addToDiscard(Card tempCard) {
        tempCard.flipUp();
        discardpile.push(tempCard);
    }

    /**
     *This method will deal 6 cards to each player with the 1st and 3rd card being turned face up
     */
    public void dealToPlayers() {
        currentPlayers.clear();//problem
        Card chosenCard;
        try {
            if (numberOfPlayers > 4) {
                playingshoe = new Shoe(2);
            } else {
                playingshoe = new Shoe(1);
            }
            for (int i = 0; i < numberOfPlayers; i++) {
                currentPlayers.add(new Player(playingshoe.dealCards(), String.format("Player %d", i + 1)));
            }
            chosenCard = new Card(playingshoe.pickCard());
            addToDiscard(chosenCard);
        }catch (Exception e){
            System.out.println(e.getMessage()+" thrown in dealToPlayers");
        }
        playersTurn=0;
    }

    /**
     * reshuffles all cards and save's score and name
     */
    public void reshuffle(){
        if (numberOfPlayers > 4) {
            playingshoe = new Shoe(2);
        } else {
            playingshoe = new Shoe(1);
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            currentPlayers.get(i).getHeldHand().setHand(playingshoe.dealCards());
        }
        Card chosenCard;
        try{
            chosenCard = new Card(playingshoe.pickCard());
            addToDiscard(chosenCard);
        }catch (Exception e){
            System.out.println(e.getMessage()+" thrown in reshuffle");
        }
        playersTurn=0;
    }

    /**
     * Checkhole method sees if all the player's cards
     * are currently turned up. if so count up the values, tally the points,
     * assign them to each player, and display each player's score. after a hole is finished
     * if the number of holes is equal to 18, kill the game and present the winner.
     **/
    public boolean checkhole() {
        currentHole = currentHole + 1;
        for(Player iplayer: currentPlayers){
            iplayer.getHeldHand().flipAllCardsUp();
        }
        tallyScore();
        if(currentHole>numberOfHoles)gameOver=true;
        sortPlayers();
        view.scoreBoard(currentHole,numberOfHoles,currentPlayers,gameOver);
        reshuffle();
        return true;
    }

    /**
     * This method sorts the list of players by score using the
     * Arraylist and a score comparator defined at the top of this class
     */
    public void sortPlayers() {
        Collections.sort(currentPlayers,playerScoreComparator);
    }

    /**
     * Iterates through each player and totals the score of their hands.
     * Score is saved to a variable within player class.
     */
    public void tallyScore() {

        for (Player player : currentPlayers) {
            ArrayList<Card> playerCards = player.getHeldHand().getCards();

            if (playerCards.get(0).getValue() == playerCards.get(3).getValue()
                    && !playerCards.get(0).isFaceDown() && !playerCards.get(3).isFaceDown()) {
                playerCards.get(0).setScore(0);
                playerCards.get(3).setScore(0);
            }
            else if (playerCards.get(1).getValue() == playerCards.get(4).getValue()
                    && !playerCards.get(1).isFaceDown() && !playerCards.get(4).isFaceDown()) {
                playerCards.get(1).setScore(0);
                playerCards.get(4).setScore(0);
            }
            else if (playerCards.get(2).getValue() == playerCards.get(5).getValue()
                    && !playerCards.get(2).isFaceDown() && !playerCards.get(5).isFaceDown()) {
                playerCards.get(2).setScore(0);
                playerCards.get(5).setScore(0);
            }

            for (Card card : playerCards) {
                if (!card.isFaceDown()){
                    player.setScore(player.getScore() + card.getScore());
                }
            }
        }
    }

    /**
     * Below are a group of getters for the controller class
     **/
    public ArrayList<Player> getCurrentPlayers() {
        return currentPlayers;
    }
    public int getPlayersTurn() {
        return playersTurn;
    }
    public Stack<Card> getDiscardpile() {
        return discardpile;
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public int getNumberOfDecks(){return playingshoe.numDecks();}
    public Shoe getPlayingShoe(){return playingshoe;}

    /**
     * Below is a setter for the controller class
     **/
    public void setPlayers(ArrayList<Player> players) {
        this.currentPlayers = players;
    }
}
