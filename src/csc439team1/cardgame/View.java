package csc439team1.cardgame;

import java.util.ArrayList;
import java.util.Stack;

abstract class View {
    abstract void titleScreen();
    public abstract int drawTurn(Player currentPlayer, int turn, Stack<Card> discardPile, int drawpilesize);
    abstract int getNumPlayers();
    abstract int chooseCard(Player currentPlayer,Card drawnCard, boolean isDiscard);
    abstract int getNumHoles();
    abstract boolean quitGame();
    abstract void scoreBoard(int holeNum, int totalHoles, ArrayList<Player> currentPlayers, boolean gameOver);
    abstract void drawHand(Player iplayer);




}
