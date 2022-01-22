package csc439team1.cardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class generates a shoe, an object that contains a collection of card decks within it.
 *
 * @version 4.0
 */
public class Shoe {
    private ArrayList<Deck> shoeDecks;

    public Shoe(int numDecks) {
        this.shoeDecks = new ArrayList<>(numDecks);

        for (int i = 0; i < numDecks; i++) {
            shoeDecks.add(new Deck());
        }
    }

    /**
     * This method is used to pick a card from the shoe.
     *
     * @return a card from a randomly chosen deck in the shoe
     * @throws Exception if no cards are left in the chosen deck
     */
    public Card pickCard() throws noCardsLeftException {
        Random random = new Random();
        int randomNum = random.nextInt(shoeDecks.size());

        while (shoeDecks.get(randomNum).getSize() == 0) {
            shoeDecks.remove(randomNum);
            randomNum = random.nextInt(shoeDecks.size());
        }
            return shoeDecks.get(randomNum).pick();
    }

    /**
     * @return This is what actually deals the cards, returns a hand of cards from this shoe with the 1st and 4th card flipped
     */
    public Hand dealCards() {
        ArrayList<Card> arrCards = new ArrayList();
        Random rand = new Random();
        int random;
        try {
            for (int i = 0; i < 6; i++) {
                arrCards.add(pickCard());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage() + " thrown in dealCards");
        }
        if(arrCards.size()==6) {//large enough to play
            random = rand.nextInt(6);
            arrCards.get(random).flipUp();
            random = rand.nextInt(6);
            while (!arrCards.get(random).isFaceDown()) {//if already face up
                random = rand.nextInt(6);
            }
            arrCards.get(random).flipUp();
        }
        return new Hand(arrCards);

    }

    /**
     *
     * This method gets the number of decks in the shoe.
     *
     * @return shoe size
     */
    public int numDecks() {
        return shoeDecks.size();
    }

    /**
     * This method gets the number of cards in the shoe.
     *
     * @return total number of cards
     */
    public int size() {
        int size = 0;
        for (Deck deck : shoeDecks) {
            size += deck.getSize();
        }
        return size;
    }
}
