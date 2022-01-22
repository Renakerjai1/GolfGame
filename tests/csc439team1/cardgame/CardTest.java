package csc439team1.cardgame;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Test file for Card class
 * @author Tyler Monroe
 * version 1.0
 */
public class CardTest {
    private Card card;
    private Card copycard;

    /**
     * Set up method for test class, creates card that is a 10 of hearts
     */
    @Before
    public void setUp() throws Exception {
        card = new Card(1,10, true);
        copycard= new Card(card);
    }

    @Test
    public void defaultConstructorShouldSucceed() {
        Card test = new Card(2, 7);
        assertEquals(7, test.value);
        assertEquals(2, test.suit);
    }

    @Test
    public void toStringShouldSucceed() {
        assertEquals("Value: 10, Suit: Hearts", card.toString());
    }

    @Test
    public void getSuit() {
        assertEquals(1, card.getSuit());
    }

    @Test
    public void getValue() {
        assertEquals(10, card.getValue());
    }

    @Test
    public void isFaceDown() {
        assertTrue(card.isFaceDown());
    }
    @Test
    public void FaceTestFalse(){
        assertEquals("Value: -, Suit: -",card.getFace());
    }
    @Test
    public void FaceTestTrue(){
        copycard.flipUp();
        assertEquals("Value: 10, Suit: Hearts",copycard.getFace());
    }
    @Test
    public void FlipTest(){
        copycard.flipUp();
        assertTrue(!copycard.isFaceDown());
    }

    @Test
    public void SuitTestShouldSucceed() {
        card.flipUp();
        assertEquals("Hearts", card.printSuit(card.suit));
        card.suit = 2;
        assertEquals("Diamonds", card.printSuit(card.suit));
        card.suit = 3;
        assertEquals("Spades", card.printSuit(card.suit));
        card.suit = 4;
        assertEquals("Clubs", card.printSuit(card.suit));
    }

    @Test
    public void SuitTestShouldFail() {
        card.flipUp();
        card.suit = 9;
        assertEquals("Value must be between 1 and 4", card.printSuit(card.suit));
    }

    @Test
    public void ValueTestShouldSucceed() {
        card.flipUp();
        assertEquals("10", card.printValue(card.value));
        card.value = 11;
        assertEquals("Jack", card.printValue(card.value));
        card.value = 12;
        assertEquals("Queen", card.printValue(card.value));
        card.value = 13;
        assertEquals("King", card.printValue(card.value));
        card.value = 14;
        assertEquals("Ace", card.printValue(card.value));
    }

    @Test
    public void ValueTestShouldFail() {
        card.flipUp();
        card.value = 21;
        assertEquals("Value must be between 2 and 14", card.printValue(card.value));
    }

    @Test
    public void ScoreTestNeg2() {
        Card test = new Card(1, 2);
        assertEquals(-2, test.getScore());
    }

    @Test
    public void ScoreTestFaceValue() {
        Card test = new Card(1, 4);
        assertEquals(4, test.getScore());
    }

    @Test
    public void ScoreTest10() {
        Card test = new Card(1, 11);
        assertEquals(10, test.getScore());
    }

    @Test
    public void ScoreTest0() {
        Card test = new Card(1, 13);
        assertEquals(0, test.getScore());
    }

    @Test
    public void ScoreTest1() {
        Card test = new Card(1, 14);
        assertEquals(1, test.getScore());
    }
    @Test
    public void compareTest(){
        Card test = new Card(1, 14);
        Card test2 = new Card(2, 14);
        assertEquals(0,test.compareTo(test2));
    }

}