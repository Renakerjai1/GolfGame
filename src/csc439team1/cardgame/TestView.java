package csc439team1.cardgame;


import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * this testview class tries to input fixed values into the controller class so that the class can be tested, essentially flips all held
 * cards that are face down then dumps the cards from the draw pile into the discard pile. quitting is set to see if quitting will break the loop
 */
public class TestView extends View{
    int numberOfPlayers,numberOfHoles;
    Random rand;
    boolean quitting;
    public TestView(int preference){
        if(preference==1){
            numberOfPlayers=2;
            numberOfHoles=2;
        }
        else if(preference==2){
            numberOfPlayers=5;
            numberOfHoles=8;
        }
        rand=new Random();
        quitting=false;
    }

    @Override
    public int getNumPlayers(){
        return numberOfPlayers;
    }
    @Override
    public int getNumHoles(){return numberOfHoles;}

    @Override
    public void titleScreen() {

    }
    @Override
    public int chooseCard(Player currentPlayer,Card drawnCard, boolean isDiscard){
        if (isDiscard){//return a random card to replace if all are flipped
            return rand.nextInt(6);
        }
        else {//else replace the first face up card
            return checkingfaces(currentPlayer);
        }
    }
    public int checkingfaces(Player currentPlayer){
        for(int i=1;i<6;i++){
            if(currentPlayer.getHeldHand().getCards().get(i).isFaceDown()){
                return i;//first facedown card
            }
        }
        return 1;
    }

    @Override
    public int drawTurn(Player currentPlayer, int turn, Stack<Card> discardPile, int drawpilesize){
        //Card discardTop=discardPile.pop();
        //discardPile.push(discardTop);
        return drawChoices(currentPlayer,turn,discardPile);
    }
    @Override
    public void drawHand(Player iplayer){//don't need anything here, purely visual
    }
    public int drawChoices(Player currentPlayer, int turn, Stack<Card> discardPile){
        int replace=checkingfaces(currentPlayer);
        if(quitting) {
            return 3;
        }
        else if(replace==1) {//all faces up
            return 1;
        }
        else return 2;// all faces down, draw from
    }
    @Override
    public boolean quitGame(){
        System.out.println("You are quitting the game");
        return true;
    }
    public void setQuitting(boolean setquit){quitting=setquit;}
    public void scoreBoard(int holeNum, int totalHoles, ArrayList<Player> currentPlayers, boolean gameOver){System.out.println(currentPlayers.get(0).getName());}
}
