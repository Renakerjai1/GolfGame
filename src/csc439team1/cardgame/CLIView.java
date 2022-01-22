package csc439team1.cardgame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * This is the class that extends view and has all communication between the commandline
 * and the controller. Essentially, prints all necessary values for players to see
 * when playing the game
 * @version 4.0
 */
public class CLIView extends View {
    Scanner scan;

    /**
     * Creates a new <code>View</code> object.
     */
    public CLIView() {
        scan= new Scanner(System.in);
    }
    //TODO add to playGolf() method in controller
    @Override
    public void titleScreen() {
        System.out.println("Hello and welcome to...");
        System.out.println("*-----   *----*   |        *-----");
        System.out.println("|   _    |    |   |        |");
        System.out.println("|    |   |    |   |        |---");
        System.out.println("*----*   *----*   *-----   |");
        System.out.println("                   The card game!\n\n");
    }

    /**
     * @param currentPlayer is the player whose turn it is
     * @param turn tells the user what turn it is
     * @param discardPile is the discard pile
     * @return what the player would like to do, 1 to draw from the discard pile, 2 to draw from the draw pile, or 3 to quit
     * this will draw what is in the player's hand, what is in the discard pile, what turn it is, and
     */
    @Override
    public int drawTurn(Player currentPlayer, int turn, Stack<Card> discardPile, int drawpilesize){
        Card discardTop=discardPile.pop();
        discardPile.push(discardTop);
        System.out.println(String.format("\tit is currently %s's turn, it is turn %d.",currentPlayer.getName(),turn));
        drawHand(currentPlayer);
        System.out.println(String.format("\nThe draw pile has %d cards",drawpilesize));
        System.out.println("\nThe discard pile has: "+discardTop.toString());
        return drawChoices(currentPlayer,turn,discardPile);
    }

    /**
     * @param currentPlayer is the player whose turn it is
     * @param turn tells the user what turn it is
     * @param discardPile is the discard pile
     * @return what the player would like to do, 1 to draw from the draw pile, 2 to draw from the discard pile, or 3 to quit
     * this actually asks the user what they would like to do
     */
    public int drawChoices(Player currentPlayer, int turn, Stack<Card> discardPile){
        int response;
        System.out.println("\tYou can choose to draw from the draw pile(1), draw from the discard pile(2), or quit the game(3) ");
        response=scan.nextInt();
        while(response!=1&&response!=2&&response!=3){
            System.out.println("\tYou can choose to draw from the draw pile(1), draw from the discard pile(2), or quit the game(3) ");
            response=scan.nextInt();
        }
        return response;
    }

    /**
     * @param currentPlayer is the player whose turn it is
     * @param drawnCard is the card which was drawn from either the discard pile or the draw pile
     * @param isDiscard tells whether or not it was drawn from the discard pile or draw pile
     * @return the index of the card it wishes to throw away
     */
    public int chooseCard(Player currentPlayer,Card drawnCard, boolean isDiscard){
        int response;
        System.out.println("The card you drew is: "+drawnCard.toString());
        if(isDiscard){
            drawHand(currentPlayer);
            System.out.println("\nChoose one of your cards to replace,0-5");
            response=scan.nextInt();
            while (!(response>=0&&response<6)){
                System.out.println("\nYou must Choose one of your cards to replace,0-5");
                response=scan.nextInt();
            }
        }
        else{//otherwise it is from the draw shoe
            drawHand(currentPlayer);
            System.out.println("\nChoose one of your cards to replace,0-5 or 6 to place the drawn pile into the discard pile");
            response=scan.nextInt();
            while (!(response>=0&&response<7)){
                System.out.println("\nYou must Choose one of your cards to replace,0-5, or throw the chosen card into the discaard pile with 6");
                response=scan.nextInt();
            }
        }
        return response;
    }

    /**
     * this method prints out the value of a card
     */
    public void drawCard(Card givencard){
        System.out.println(givencard.getValue());
    }

    /**
     * @param iplayer draws the entire hand based on the visibility value
     */

    public void drawHand(Player iplayer){
        ArrayList<Card> givencards= iplayer.getHeldHand().getCards();
        System.out.println(String.format("\n\t%s 's hand:",iplayer.getName()));
        for(int i=0;i<6;i++){
            if(i==2){
                System.out.println("\t\t ("+i+") "+givencards.get(i).getFace());
            }else{
                System.out.print("\t\t ("+i+") "+givencards.get(i).getFace());
            }
        }
    }

    /**
     * @return the number of players the user wishes to have
     */
    @Override
    public int getNumPlayers(){
        System.out.print("Enter the number of players: ");
        return scan.nextInt();
    }

    /**
     * @return the number of holes
     */
    @Override
    public int getNumHoles(){
        System.out.print("\nEnter the number of Holes: ");
        return scan.nextInt();
    }

    /**
     * @return a boolean if the user decides they wish to really quit the game
     */
    @Override
    public boolean quitGame() {
        System.out.println("Are you sure you want to quit the game?");
        System.out.println("Re-enter q to quit: ");
        String quit = scan.next();

        if (quit.toLowerCase().equals("q")) {
            System.out.println("Thanks for playing, goodbye!");
            return true;
        }
        else {
            System.out.println("Glad you're sticking around! Now where were we?");
            return false;
        }
    }

    /**
     * The scoreBoard method shows the current hole number, the total number of holes,
     * and the scores of each player(sorted from low to high)
     */
    public void scoreBoard(int holeNum, int totalHoles, ArrayList<Player> currentPlayers, boolean gameOver){
        System.out.println("*-------------------------------------------*");
        if (gameOver) {
            System.out.println("|             Final Scoreboard              |");
            System.out.println("*---------------------*---------------------*");
            System.out.printf("| Total Holes Played  | %-15d     |%n", totalHoles);
        }
        else {
            System.out.println("|                 Scoreboard                |");
            System.out.println("*---------------------*---------------------*");
            System.out.printf("| Current Hole Number | %-15d     |%n", holeNum);
            System.out.printf("| Total Holes         | %-15d     |%n", totalHoles);
        }
        System.out.println("*---------------------*-------------*-------*");
        System.out.println("| Player Rank         | Name        | Score |");
        System.out.println("*---------------------*-------------*-------*");
        for (int i = 0; i < currentPlayers.size(); i++) {
            System.out.printf("| %-8d            | %-12s| %-5d |%n", i + 1, currentPlayers.get(i).name, currentPlayers.get(i).score);
        }
        System.out.println("*---------------------*-------------*-------*");

        if (gameOver) {
            System.out.printf("\nCongratulations, %s! You have won this game of Golf!%n", currentPlayers.get(0).name);
        }
    }
}

