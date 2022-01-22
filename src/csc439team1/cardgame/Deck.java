package csc439team1.cardgame;

import java.util.Random;
import java.util.ArrayList;

/**
 * A deck object that holds 52 cards, has a pick method which returns a random card, and a getter for size;
 * @author Ian
 * @version 4.0
 */
public class Deck {
    private ArrayList<Card> deckOfCards= new ArrayList<Card>(52);
    private Random rand;

    /**
     * A constructor for deck that takes no parameters, although it can take a deck type parameter in the future
     * such as an euchre deck.
     *
     * Currently this constructor fills the ArrayList deckOfCards with 52 cards to make a standard deck
     */
    public Deck(){
        //int suit int value, make aloop that
            for (int i = 1; i < 5; i++) {
                for (int j = 2; j < 15; j++) {
                    deckOfCards.add(new Card(i, j));
                }
            }
       rand= new Random();
    }
    public Deck(Deck givenDeck){
        for(int i=0;i< givenDeck.getSize();i++){
            deckOfCards.add(new Card(givenDeck.deckOfCards.get(i)));
        }
        rand= new Random();
    }

    /**
     * @return a randomly selected card in the array list
     * then deletes the selected card
     * @throws noCardsLeftException if there are no cards left in the deck
     */
    public Card pick() throws noCardsLeftException {
        int cardLoc;

        if (deckOfCards.size() < 1) {
            throw new noCardsLeftException("No cards remaining in the deck");
        } else {
            cardLoc = rand.nextInt(deckOfCards.size());
            Card pickedCard = deckOfCards.get(cardLoc);
            deckOfCards.remove(cardLoc);
            return pickedCard;
        }
    }

    /**
     * @return the size of the deck
     */
    public int getSize() {
        return deckOfCards.size();
    }

    /**
     * @return the list of cards for the deck
     */
    public ArrayList<Card> getDeck(){return deckOfCards;}
}

/**
 * this class creates an exception for when no cards are left
 */
class noCardsLeftException extends Exception{
    public noCardsLeftException(String message){
        super(message);
    }
}
