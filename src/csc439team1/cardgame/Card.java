package csc439team1.cardgame;

import java.util.Comparator;

/**
 * Card class for card games
 * @author Tyler Monroe, Carlos Garcia, Ian Renaker-Jansen, Cameron Bradshaw
 * @version 1.0
 **/
public class Card {
    protected int suit;
    protected int value;
    protected  boolean faceDown;
    protected int score;

    /**
     * Constructor for Card class
     * @param suit suit value for the card
     * 1 = hearts, 2 = diamonds, 3 = spades, 4 = clubs
     * @param value number value of the card (e.g 2-14)
     */
    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
        this.faceDown = true;
        this.setScore();

    }

    public Card(int suit, int value, boolean faceDown) {
        this.suit = suit;
        this.value = value;
        this.faceDown = faceDown;
        this.setScore();
    }

    /**
     * @param givenCard will copy the values of the given card.
     */
    public Card(Card givenCard){
        this.suit= givenCard.getSuit();
        this.value=givenCard.getValue();
        this.faceDown=givenCard.isFaceDown();
        this.score=givenCard.getScore();
    }

    /**
     * Gets card's suit value
     * 1 = hearts, 2 = diamonds, 3 = spades, 4 = clubs
     * @return card's suit value
     */
    public int getSuit() {
        return this.suit;
    }

    /**
     * Gets card's value
     * @return card's number value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns if card is face up or face down
     * @return true = face up, false = face up
     */
    public boolean isFaceDown() {
        return this.faceDown;
    }

    /**
     * will flip a card face up
     */
    public void flipUp(){faceDown=false;}

    /**
     * @return Card object as a string
     */
    public String toString() {
        return String.format("Value: %s, Suit: %s", printValue(getValue()), printSuit(getSuit()));
    }

    /**
     * @return the toString of the card based on the face of the card ( face up/ face down)
     *but will eventually return the named value and named suit
     */
    public String getFace(){
        if(isFaceDown()){
            return "Value: -, Suit: -";
        } else {
            return String.format("Value: %s, Suit: %s", printValue(getValue()), printSuit(getSuit()));
        }
    }

    /**
     * @return a string of the card on its suit. Instead of printing back an int to the user
     * this gives them the name of the suit making it more familiar for players.
     */
    public String printSuit(int suitValue) {
        if (suitValue == 1) {
            return "Hearts";
        }
        else if (suitValue == 2) {
            return "Diamonds";
        }
        else if (suitValue == 3) {
            return "Spades";
        }
        else if (suitValue == 4) {
            return "Clubs";
        }
        else {
            return "Value must be between 1 and 4";
        }
    }

    /**
     * @return a string on the value of the card. Mainly ensures that if a cards value
     * is over 10, making it a royal face, it will give the user the corresponding value for the card.
     */
    public String printValue(int value) {
        if (value < 11) {
            return String.format("%d", value);
        } else {
            if (value == 11) {
                return "Jack";
            }
            else if (value == 12) {
                return "Queen";
            }
            else if (value == 13) {
                return "King";
            }
            else if (value == 14){
                return "Ace";
            }
            else {
                return "Value must be between 2 and 14";
            }
        }
    }

    /**
     * sets the score for a card based on the rules of Golf
     */
    private void setScore() {
        if (value == 2) {
            this.score = -2;
        }
        else if (3 <= value && value <= 10) {
            this.score = this.value;
        }
        else if (value == 11 || value == 12) {
            this.score = 10;
        }
        else if (value == 13) {
            this.score = 0;
        }
        else { //value = 14
            this.score = 1;
        }

    }

    /**
     * setter for score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the score for the card
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @return the value that compares two cards together
     */
    public int compareTo (Card secondCard) {
        return this.value - secondCard.value;
    }
}
