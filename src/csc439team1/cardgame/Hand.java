package csc439team1.cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * A hand object that contains a collection of cards
 * @author Tyler Monroe
 * @version 4.0
 */
public class Hand {
    private ArrayList<Card> cards;

    /**
     * Default constructor, creates a hand object with no cards
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * @param cards list object containing cards to be added to hand
     */
    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards.size());
        for (Card card : cards) {
            this.addCard(card);
        }
    }

    /**
     * @param card  card object that is to be added to the hand
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * @return  ArrayList containing all card objects contained in the hand
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * @return size of the hand
     */
    public int getSize() {
        return this.cards.size();
    }

    /**
     * @return  contents of hand object as a string.
     */
    public String toString() {
        ArrayList<String> output = new ArrayList<>();
        for (Card card : this.cards) {
            output.add(card.toString());
        }
        return output.toString();
    }

    /**
     * @param drawnCard will be replaced with the card located at the
     * @param cardindex where the card should be replaced for
     * @return the card which was replaced by the given card
     */
    public Card replace(Card drawnCard, int cardindex){
        Card temp= new Card(cards.get(cardindex));
        temp.flipUp();
        drawnCard.flipUp();
        cards.set(cardindex,drawnCard);
        cards.get(cardindex).flipUp();
        return temp;
    }//
    public boolean isAllFaceUp(){
        for (Card card : cards) {
            if(card.isFaceDown()){
                return false;
            }
        }
        return true;
    }
    public void flipAllCardsUp(){
        for (Card card : cards) {
            if(card.isFaceDown()){
                card.flipUp();
            }
        }
    }

    /**
     * @param givenHand copies the
     */
    public void setHand(Hand givenHand){
        cards.clear();
        for(int i=0;i<givenHand.getSize();i++){
            cards.add(givenHand.getCards().get(i));
        }

    }

    //TODO create compare method
    //TODO create a replace method, then return card
    //TODO create a get(index) method, then return that card
}
